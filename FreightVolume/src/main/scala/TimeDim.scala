import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types.{StringType, StructField, StructType}
import org.apache.log4j.{Level,Logger}


object TimeDim {

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

  //A站每天的总货运量统计
  def dailyTotalVolume():Unit = {
    val dailyVolume = spark.sql("select ZPRQ,ZZL from FreightVolume")
    val pairDailyVolume = dailyVolume.rdd.map{x=>(x(0).toString,x(1).toString.toInt)}
    val dailyTotalVolume = pairDailyVolume.reduceByKey(_+_).sortByKey()
    dailyTotalVolume.foreach(println)

    //保存为csv文件
    dailyTotalVolume.map(line=>{val word = line._1; val cnt = line._2; word + " " +cnt}).saveAsTextFile("file:///home/zhangbin/data/freight/dailyTotalVolume.csv")
    //写入mysql数据库
    dailyTotalVolume.foreachPartition(RDDtoMysql.insertToDailyTotalVolume)
  }

  //A站每月的货运量统计
  def monthlyTotalVolume():Unit = {
    val dailyVolume = spark.sql("select ZPRQ,ZZL from FreightVolume")
    val pairDailyVolume = dailyVolume.rdd.map{x=>(x(0).toString,x(1).toString.toInt)}
    val pairMonthlyVolume = pairDailyVolume.map{x=>(x._1.substring(0,6),x._2)}
    val monthlyTotalVolume = pairMonthlyVolume.reduceByKey(_+_).sortByKey()
    monthlyTotalVolume.foreach(println)

    //保存为csv文件
    monthlyTotalVolume.map(line=>{val word = line._1; val cnt = line._2; word + " " +cnt}).saveAsTextFile("file:///home/zhangbin/data/freight/monthlyTotalVolume.csv")
    //写入mysql数据库
    monthlyTotalVolume.foreachPartition(RDDtoMysql.insertToMonthlyTotalVolume)
  }

  //A站每天的收货量统计
  def dailyTotalTakeVolume():Unit = {
    val dailyTakeVolume = spark.sql("select ZPRQ,ZZL from FreightVolume where FZMC!='A站'")
    val pairDailyTakeVolume = dailyTakeVolume.rdd.map{x=>(x(0).toString,x(1).toString.toInt)}
    val dailyTotalTakeVolume = pairDailyTakeVolume.reduceByKey(_+_).sortByKey()
    dailyTotalTakeVolume.take(10).foreach(println)

    //保存为csv文件
    dailyTotalTakeVolume.map(line=>{val word = line._1; val cnt = line._2; word + " " +cnt}).saveAsTextFile("file:///home/zhangbin/data/freight/dailyTotalTakeVolume.csv")
    //写入mysql数据库
    dailyTotalTakeVolume.foreachPartition(RDDtoMysql.insertToDailyTotalTakeVolume)
  }

  //A站每天的发货量统计
  def dailyTotalDeliverVolume():Unit = {
    val dailyDeliverVolume = spark.sql("select ZPRQ,ZZL from FreightVolume where FZMC='A站'")
    val pairDailyDeliverVolume = dailyDeliverVolume.rdd.map{x=>(x(0).toString,x(1).toString.toInt)}
    val dailyTotalDeliverVolume = pairDailyDeliverVolume.reduceByKey(_+_).sortByKey()
    dailyTotalDeliverVolume.take(10).foreach(println)

    //保存为csv文件
    dailyTotalDeliverVolume.map(line=>{val word = line._1; val cnt = line._2; word + " " +cnt}).saveAsTextFile("file:///home/zhangbin/data/freight/dailyTotalDeliverVolume.csv")
    //写入mysql数据库
    dailyTotalDeliverVolume.foreachPartition(RDDtoMysql.insertToDailyTotalDeliverVolume)
  }

  //A站每月的收货量统计
  def monthlyTotalTakeVolume():Unit = {
    val dailyTakeVolume = spark.sql("select ZPRQ,ZZL from FreightVolume where FZMC!='A站'")
    val pairDailyTakeVolume = dailyTakeVolume.rdd.map{x=>(x(0).toString,x(1).toString.toInt)}
    val pairMonthlyTakeVolume = pairDailyTakeVolume.map{x=>(x._1.substring(0,6),x._2)}
    val monthlyTotalTakeVolume = pairMonthlyTakeVolume.reduceByKey(_+_).sortByKey()
    monthlyTotalTakeVolume.foreach(println)

    //保存为csv文件
    monthlyTotalTakeVolume.map(line=>{val word = line._1; val cnt = line._2; word + " " +cnt}).saveAsTextFile("file:///home/zhangbin/data/freight/monthlyTotalTakeVolume.csv")
    //写入mysql数据库
    monthlyTotalTakeVolume.foreachPartition(RDDtoMysql.insertToMonthlyTotalTakeVolume)
  }

  //A站每月的发货量统计
  def monthlyTotalDeliverVolume():Unit = {
    val dailyDeliverVolume = spark.sql("select ZPRQ,ZZL from FreightVolume where FZMC='A站'")
    val pairDailyDeliverVolume = dailyDeliverVolume.rdd.map{x=>(x(0).toString,x(1).toString.toInt)}
    val pairMonthlyDeliverVolume = pairDailyDeliverVolume.map{x=>(x._1.substring(0,6),x._2)}
    val monthlyTotalDeliverVolume = pairMonthlyDeliverVolume.reduceByKey(_+_).sortByKey()
    monthlyTotalDeliverVolume.foreach(println)

    //保存为csv文件
    monthlyTotalDeliverVolume.map(line=>{val word = line._1; val cnt = line._2; word + " " +cnt}).saveAsTextFile("file:///home/zhangbin/data/freight/monthlyTotalDeliverVolume.csv")
    //写入mysql数据库
    monthlyTotalDeliverVolume.foreachPartition(RDDtoMysql.insertToMonthlyTotalDeliverVolume)
  }

  //A站总货运量统计
  def totalVolume():Unit = {
    //总货运量
    val dailyVolume = spark.sql("select ZZL from FreightVolume")
    val rddDailyVolume = dailyVolume.rdd.map{x=>x(0).toString.toLong}
    val totalVolume = rddDailyVolume.reduce(_+_)
    println(totalVolume)

    //总收货量
    val dailyTakeVolume = spark.sql("select ZZL from FreightVolume where FZMC!='A站'")
    val rddDailyTakeVolume = dailyTakeVolume.rdd.map{x=>x(0).toString.toLong}
    val totalTakeVolume = rddDailyTakeVolume.reduce(_+_)
    println(totalTakeVolume)

    //总发货量
    val dailyDeliverVolume = spark.sql("select ZZL from FreightVolume where FZMC='A站'")
    val rddDailyDeliverVolume = dailyDeliverVolume.rdd.map{x=>x(0).toString.toLong}
    val totalDeliverVolume = rddDailyDeliverVolume.reduce(_+_)
    println(totalDeliverVolume)

    val rdd1 = sc.parallelize(Seq(totalTakeVolume))
    val rdd2 = sc.parallelize(Seq(totalDeliverVolume))
    val rdd = rdd1 zip rdd2


    //保存为csv文件
    rdd.map(line=>{val word = line._1; val cnt = line._2; word + " " +cnt}).saveAsTextFile("file:///home/zhangbin/data/freight/totalVolume.csv")
    //写入mysql数据库
    rdd.foreachPartition(RDDtoMysql.insertToTotalVolume)
  }





  def main(args: Array[String]): Unit = {

    dailyTotalVolume()
    monthlyTotalVolume()
    dailyTotalTakeVolume()
    dailyTotalDeliverVolume()
    monthlyTotalTakeVolume()
    monthlyTotalDeliverVolume()
    totalVolume()

  }
}
