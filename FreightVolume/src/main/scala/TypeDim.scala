import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types.{StringType, StructField, StructType}
import org.apache.log4j.{Level,Logger}


object TypeDim {

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

  //A站每类货物货运量
  def everyTypeVolume():Unit = {
    val volume = spark.sql("select PLZPLJC,ZZL from FreightVolume")
    val pairVolume = volume.rdd.map{x=>(x(0).toString,x(1).toString.toInt)}
    val everyTypeVolume = pairVolume.reduceByKey(_+_)
    everyTypeVolume.foreach(println)

    //保存为csv文件
    everyTypeVolume.map(line=>{val word = line._1; val cnt = line._2; word + " " +cnt}).saveAsTextFile("file:///home/zhangbin/data/freight/everyTypeVolume.csv")
    //写入mysql数据库
    everyTypeVolume.foreachPartition(RDDtoMysql.insertToEveryTypeVolume)
  }

  //A站每类货物收货量
  def everyTypeTakeVolume():Unit = {
    val takeVolume = spark.sql("select PLZPLJC,ZZL from FreightVolume where FZMC!='A站'")
    val pairTakeVolume = takeVolume.rdd.map{x=>(x(0).toString,x(1).toString.toInt)}
    val everyTypeTakeVolume = pairTakeVolume.reduceByKey(_+_)
    everyTypeTakeVolume.foreach(println)

    //保存为csv文件
    everyTypeTakeVolume.map(line=>{val word = line._1; val cnt = line._2; word + " " +cnt}).saveAsTextFile("file:///home/zhangbin/data/freight/everyTypeTakeVolume.csv")
    //写入mysql数据库
    everyTypeTakeVolume.foreachPartition(RDDtoMysql.insertToEveryTypeTakeVolume)
  }


  //A站每类货物发货量
  def everyTypeDeliverVolume():Unit = {
    val deliverVolume = spark.sql("select PLZPLJC,ZZL from FreightVolume where FZMC='A站'")
    val pairDeliverVolume = deliverVolume.rdd.map{x=>(x(0).toString,x(1).toString.toInt)}
    val everyTypeDeliverVolume = pairDeliverVolume.reduceByKey(_+_)
    everyTypeDeliverVolume.foreach(println)

    //保存为csv文件
    everyTypeDeliverVolume.map(line=>{val word = line._1; val cnt = line._2; word + " " +cnt}).saveAsTextFile("file:///home/zhangbin/data/freight/everyTypeDeliverVolume.csv")
    //写入mysql数据库
    everyTypeDeliverVolume.foreachPartition(RDDtoMysql.insertToEveryTypeDeliverVolume)
  }





  def main(args: Array[String]): Unit = {

    everyTypeVolume()
    everyTypeTakeVolume()
    everyTypeDeliverVolume()

  }







}
