import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types.{StringType, StructField, StructType}
import org.apache.log4j.{Level,Logger}


object TransportDim {

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

  //A站每种运输方式货运量
  def everyTransportVolume():Unit = {
    val transportVolume = spark.sql("select YSLB,ZZL from FreightVolume")
    val pairTransportVolume = transportVolume.rdd.map{x=>(x(0).toString,x(1).toString.toLong)}
    val everyTransportVolume = pairTransportVolume.reduceByKey(_+_)
    everyTransportVolume.foreach(println)

    //保存为csv文件
    everyTransportVolume.map(line=>{val word = line._1; val cnt = line._2; word + " " +cnt}).saveAsTextFile("file:///home/zhangbin/data/freight/everyTransportVolume.csv")
    //写入mysql数据库
    everyTransportVolume.foreachPartition(RDDtoMysql.insertToEveryTransportVolume)
  }

  //A站每种运输方式收货量
  def everyTransportTakeVolume():Unit = {
    val transportTakeVolume = spark.sql("select YSLB,ZZL from FreightVolume where FZMC!='A站'")
    val pairTransportTakeVolume = transportTakeVolume.rdd.map{x=>(x(0).toString,x(1).toString.toLong)}
    val everyTransportTakeVolume = pairTransportTakeVolume.reduceByKey(_+_)
    everyTransportTakeVolume.foreach(println)

    //保存为csv文件
    everyTransportTakeVolume.map(line=>{val word = line._1; val cnt = line._2; word + " " +cnt}).saveAsTextFile("file:///home/zhangbin/data/freight/everyTransportTakeVolume.csv")
    //写入mysql数据库
    everyTransportTakeVolume.foreachPartition(RDDtoMysql.insertToEveryTransportTakeVolume)
  }

  //A站每种运输方式发货量
  def everyTransportDeliverVolume():Unit = {
    val transportDeliverVolume = spark.sql("select YSLB,ZZL from FreightVolume where FZMC='A站'")
    val pairTransportDeliverVolume = transportDeliverVolume.rdd.map{x=>(x(0).toString,x(1).toString.toLong)}
    val everyTransportDeliverVolume = pairTransportDeliverVolume.reduceByKey(_+_)
    everyTransportDeliverVolume.foreach(println)

    //保存为csv文件
    everyTransportDeliverVolume.map(line=>{val word = line._1; val cnt = line._2; word + " " +cnt}).saveAsTextFile("file:///home/zhangbin/data/freight/everyTransportDeliverVolume.csv")
    //写入mysql数据库
    everyTransportDeliverVolume.foreachPartition(RDDtoMysql.insertToEveryTransportDeliverVolume)
  }




  def main(args: Array[String]): Unit = {

    everyTransportVolume()
    everyTransportTakeVolume()
    everyTransportDeliverVolume()
  }

}
