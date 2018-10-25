import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types.{StringType, StructField, StructType}
import org.apache.log4j.{Level,Logger}

object AreaDim {

  Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
  Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.OFF)

  val spark = SparkSession.builder()
    .appName("TimeDim")
    .master("local")
    .getOrCreate()

  val sc = spark.sparkContext //使用SparkSession对象创建SparkContext

  val info = sc.textFile("file:///home/zhangbin/data/freight/全球程序员节数据_铁路.csv")
  val schemaString = "ZPRQ FZMC DZMC ZPLDM PLZPLJC YSLB ZZL"
  val fields = schemaString.split(" ").map(fieldName=>StructField(fieldName,StringType,nullable=true))
  val schema = StructType(fields)
  val row = info.map(_.split(",")).map(attributes=>Row(attributes(0),attributes(1),attributes(2),attributes(3),attributes(4),attributes(5),attributes(6)))
  val FreightVolumeDF = spark.createDataFrame(row,schema)
  FreightVolumeDF.createOrReplaceTempView("FreightVolume")


  //各区域货运量
  def everyAreaVolume():Unit = {
    val deliverVolume = spark.sql("select FZMC,ZZL from FreightVolume where FZMC!='A站'")
    val takeVolume = spark.sql("select DZMC,ZZL from FreightVolume where DZMC!='A站'")

    val pairDeliverVolume = deliverVolume.rdd.map{x=>(x(0).toString,x(1).toString.toLong)}
    val pairtakeVolume = takeVolume.rdd.map{x=>(x(0).toString,x(1).toString.toLong)}
    val pairAreaVolume = pairDeliverVolume union pairtakeVolume

    val everyAreaVolume = pairAreaVolume.reduceByKey(_+_).sortBy(_._2,false)
    everyAreaVolume.foreach(println)

    //保存为csv文件
    everyAreaVolume.map(line=>{val word = line._1; val cnt = line._2; word + " " +cnt}).saveAsTextFile("file:///home/zhangbin/data/freight/everyAreaVolume.csv")
    //写入mysql数据库
    everyAreaVolume.foreachPartition(RDDtoMysql.insertToEveryAreaVolume)


    val top10 = everyAreaVolume.take(10)
    top10.foreach(println)
    val pairTop10 = sc.parallelize(top10)
    pairTop10.map(line=>{val word = line._1; val cnt = line._2; word + " " +cnt}).saveAsTextFile("file:///home/zhangbin/data/freight/Top10AreaVolume.csv")
    pairTop10.foreachPartition(RDDtoMysql.insertToTop10AreaVolume)
  }

  //各区域收货量
  def everyAreaTakeVolume():Unit = {
    val takeVolume = spark.sql("select DZMC,ZZL from FreightVolume where DZMC!='A站'")
    val pairTakeVolume = takeVolume.rdd.map{x=>(x(0).toString,x(1).toString.toLong)}
    val everyAreaTakeVolume = pairTakeVolume.reduceByKey(_+_).sortBy(_._2,false)
    everyAreaTakeVolume.foreach(println)

    //保存为csv文件
    everyAreaTakeVolume.map(line=>{val word = line._1; val cnt = line._2; word + " " +cnt}).saveAsTextFile("file:///home/zhangbin/data/freight/everyAreaTakeVolume.csv")
    //写入mysql数据库
    everyAreaTakeVolume.foreachPartition(RDDtoMysql.insertToEveryAreaTakeVolume)


    val top10 = everyAreaTakeVolume.take(10)
    top10.foreach(println)
    val pairTop10 = sc.parallelize(top10)
    pairTop10.map(line=>{val word = line._1; val cnt = line._2; word + " " +cnt}).saveAsTextFile("file:///home/zhangbin/data/freight/Top10AreaTakeVolume.csv")
    pairTop10.foreachPartition(RDDtoMysql.insertToTop10AreaTakeVolume)
  }

  //各区域发货量
  def everyAreaDeliverVolume():Unit = {
    val deliverVolume = spark.sql("select FZMC,ZZL from FreightVolume where FZMC!='A站'")
    val pairDeliverVolume = deliverVolume.rdd.map{x=>(x(0).toString,x(1).toString.toLong)}
    val everyAreaDeliverVolume = pairDeliverVolume.reduceByKey(_+_).sortBy(_._2,false)
    everyAreaDeliverVolume.foreach(println)

    //保存为csv文件
    everyAreaDeliverVolume.map(line=>{val word = line._1; val cnt = line._2; word + " " +cnt}).saveAsTextFile("file:///home/zhangbin/data/freight/everyAreaDeliverVolume.csv")
    //写入mysql数据库
    everyAreaDeliverVolume.foreachPartition(RDDtoMysql.insertToEveryAreaDeliverVolume)


    val top10 = everyAreaDeliverVolume.take(10)
    top10.foreach(println)
    val pairTop10 = sc.parallelize(top10)
    pairTop10.map(line=>{val word = line._1; val cnt = line._2; word + " " +cnt}).saveAsTextFile("file:///home/zhangbin/data/freight/Top10AreaDeliverVolume.csv")
    pairTop10.foreachPartition(RDDtoMysql.insertToTop10AreaDeliverVolume)
  }

  //全国火车站对应省份
  def railwayStation():Unit = {
    val rdd = sc.textFile("file:///home/zhangbin/data/freight/全国火车站名单.csv")
    val pairRDD = rdd.map{x=>x.split("\t")}.map{x=>(x(0),x(1))}
    pairRDD.take(10).foreach(println)
    pairRDD.foreachPartition(RDDtoMysql.insertToRailwayStation)
  }






  def main(args: Array[String]): Unit = {

    everyAreaVolume()
    everyAreaTakeVolume()
    everyAreaDeliverVolume()
    railwayStation()


  }
}
