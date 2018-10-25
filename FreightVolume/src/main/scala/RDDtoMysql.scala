import java.sql.Connection
import java.sql.DriverManager
object RDDtoMysql {

  val driver = "com.mysql.jdbc.Driver"
  val url = "jdbc:mysql://127.0.0.1:3306/FreightVolume"
  val username = "root"
  val password = "199504"
  var connectionMqcrm: Connection = null
  Class.forName(driver)
  connectionMqcrm = DriverManager.getConnection(url, username, password)

  //TimeDim
  def insertToDailyTotalVolume(iterator: Iterator[(String, Int)]):Unit = {
    val sql = "INSERT INTO dailyTotalVolume (date,volume) VALUES (?,?)"
    iterator.foreach(data => {
      val statement = connectionMqcrm.prepareStatement(sql)
      statement.setString(1, data._1)
      statement.setInt(2, data._2)
      var result = statement.executeUpdate()
      if (result == 1) {
        println("写入mysql成功.............")
      }
    })
  }

  def insertToMonthlyTotalVolume(iterator: Iterator[(String, Int)]):Unit = {
    val sql = "INSERT INTO monthlyTotalVolume (month,volume) VALUES (?,?)"
    iterator.foreach(data => {
      val statement = connectionMqcrm.prepareStatement(sql)
      statement.setString(1, data._1)
      statement.setInt(2, data._2)
      var result = statement.executeUpdate()
      if (result == 1) {
        println("写入mysql成功.............")
      }
    })
  }

  def insertToDailyTotalTakeVolume(iterator: Iterator[(String, Int)]):Unit = {
    val sql = "INSERT INTO dailyTotalTakeVolume (date,takeVolume) VALUES (?,?)"
    iterator.foreach(data => {
      val statement = connectionMqcrm.prepareStatement(sql)
      statement.setString(1, data._1)
      statement.setInt(2, data._2)
      var result = statement.executeUpdate()
      if (result == 1) {
        println("写入mysql成功.............")
      }
    })
  }

  def insertToMonthlyTotalTakeVolume(iterator: Iterator[(String, Int)]):Unit = {
    val sql = "INSERT INTO monthlyTotalTakeVolume (month,takeVolume) VALUES (?,?)"
    iterator.foreach(data => {
      val statement = connectionMqcrm.prepareStatement(sql)
      statement.setString(1, data._1)
      statement.setInt(2, data._2)
      var result = statement.executeUpdate()
      if (result == 1) {
        println("写入mysql成功.............")
      }
    })
  }

  def insertToDailyTotalDeliverVolume(iterator: Iterator[(String, Int)]):Unit = {
    val sql = "INSERT INTO dailyTotalDeliverVolume (date,deliverVolume) VALUES (?,?)"
    iterator.foreach(data => {
      val statement = connectionMqcrm.prepareStatement(sql)
      statement.setString(1, data._1)
      statement.setInt(2, data._2)
      var result = statement.executeUpdate()
      if (result == 1) {
        println("写入mysql成功.............")
      }
    })
  }

  def insertToMonthlyTotalDeliverVolume(iterator: Iterator[(String, Int)]):Unit = {
    val sql = "INSERT INTO monthlyTotalDeliverVolume (month,deliverVolume) VALUES (?,?)"
    iterator.foreach(data => {
      val statement = connectionMqcrm.prepareStatement(sql)
      statement.setString(1, data._1)
      statement.setInt(2, data._2)
      var result = statement.executeUpdate()
      if (result == 1) {
        println("写入mysql成功.............")
      }
    })
  }

  def insertToTotalVolume(iterator: Iterator[(Long, Long)]):Unit = {
    val sql = "INSERT INTO totalVolume (takeVolume,deliverVolume) VALUES (?,?)"
    iterator.foreach(data => {
      val statement = connectionMqcrm.prepareStatement(sql)
      statement.setLong(1, data._1)
      statement.setLong(2, data._2)
      var result = statement.executeUpdate()
      if (result == 1) {
        println("写入mysql成功.............")
      }
    })
  }



  //TransportDim
  def insertToEveryTransportVolume(iterator: Iterator[(String, Long)]):Unit = {
    val sql = "INSERT INTO everyTransportVolume (transport,volume) VALUES (?,?)"
    iterator.foreach(data => {
      val statement = connectionMqcrm.prepareStatement(sql)
      statement.setString(1, data._1)
      statement.setLong(2, data._2)
      var result = statement.executeUpdate()
      if (result == 1) {
        println("写入mysql成功.............")
      }
    })
  }

  def insertToEveryTransportTakeVolume(iterator: Iterator[(String, Long)]):Unit = {
    val sql = "INSERT INTO everyTransportTakeVolume (transport,takeVolume) VALUES (?,?)"
    iterator.foreach(data => {
      val statement = connectionMqcrm.prepareStatement(sql)
      statement.setString(1, data._1)
      statement.setLong(2, data._2)
      var result = statement.executeUpdate()
      if (result == 1) {
        println("写入mysql成功.............")
      }
    })
  }

  def insertToEveryTransportDeliverVolume(iterator: Iterator[(String, Long)]):Unit = {
    val sql = "INSERT INTO everyTransportDeliverVolume (transport,deliverVolume) VALUES (?,?)"
    iterator.foreach(data => {
      val statement = connectionMqcrm.prepareStatement(sql)
      statement.setString(1, data._1)
      statement.setLong(2, data._2)
      var result = statement.executeUpdate()
      if (result == 1) {
        println("写入mysql成功.............")
      }
    })
  }



  //TypeDim
  def insertToEveryTypeVolume(iterator: Iterator[(String, Int)]):Unit = {
    val sql = "INSERT INTO everyTypeVolume (type,volume) VALUES (?,?)"
    iterator.foreach(data => {
      val statement = connectionMqcrm.prepareStatement(sql)
      statement.setString(1, data._1)
      statement.setInt(2, data._2)
      var result = statement.executeUpdate()
      if (result == 1) {
        println("写入mysql成功.............")
      }
    })
  }

  def insertToEveryTypeTakeVolume(iterator: Iterator[(String, Int)]):Unit = {
    val sql = "INSERT INTO everyTypeTakeVolume (type,takeVolume) VALUES (?,?)"
    iterator.foreach(data => {
      val statement = connectionMqcrm.prepareStatement(sql)
      statement.setString(1, data._1)
      statement.setInt(2, data._2)
      var result = statement.executeUpdate()
      if (result == 1) {
        println("写入mysql成功.............")
      }
    })
  }

  def insertToEveryTypeDeliverVolume(iterator: Iterator[(String, Int)]):Unit = {
    val sql = "INSERT INTO everyTypeDeliverVolume (type,deliverVolume) VALUES (?,?)"
    iterator.foreach(data => {
      val statement = connectionMqcrm.prepareStatement(sql)
      statement.setString(1, data._1)
      statement.setInt(2, data._2)
      var result = statement.executeUpdate()
      if (result == 1) {
        println("写入mysql成功.............")
      }
    })
  }



  //AreaDim
  def insertToEveryAreaVolume(iterator: Iterator[(String, Long)]):Unit = {
    val sql = "INSERT INTO everyAreaVolume (area,volume) VALUES (?,?)"
    iterator.foreach(data => {
      val statement = connectionMqcrm.prepareStatement(sql)
      statement.setString(1, data._1)
      statement.setLong(2, data._2)
      var result = statement.executeUpdate()
      if (result == 1) {
        println("写入mysql成功.............")
      }
    })
  }

  def insertToEveryAreaTakeVolume(iterator: Iterator[(String, Long)]):Unit = {
    val sql = "INSERT INTO everyAreaTakeVolume (area,takeVolume) VALUES (?,?)"
    iterator.foreach(data => {
      val statement = connectionMqcrm.prepareStatement(sql)
      statement.setString(1, data._1)
      statement.setLong(2, data._2)
      var result = statement.executeUpdate()
      if (result == 1) {
        println("写入mysql成功.............")
      }
    })
  }

  def insertToEveryAreaDeliverVolume(iterator: Iterator[(String, Long)]):Unit = {
    val sql = "INSERT INTO everyAreaDeliverVolume (area,deliverVolume) VALUES (?,?)"
    iterator.foreach(data => {
      val statement = connectionMqcrm.prepareStatement(sql)
      statement.setString(1, data._1)
      statement.setLong(2, data._2)
      var result = statement.executeUpdate()
      if (result == 1) {
        println("写入mysql成功.............")
      }
    })
  }


  def insertToTop10AreaVolume(iterator: Iterator[(String, Long)]):Unit = {
    val sql = "INSERT INTO Top10AreaVolume (area,volume) VALUES (?,?)"
    iterator.foreach(data => {
      val statement = connectionMqcrm.prepareStatement(sql)
      statement.setString(1, data._1)
      statement.setLong(2, data._2)
      var result = statement.executeUpdate()
      if (result == 1) {
        println("写入mysql成功.............")
      }
    })
  }

  def insertToTop10AreaTakeVolume(iterator: Iterator[(String, Long)]):Unit = {
    val sql = "INSERT INTO Top10AreaTakeVolume (area,takeVolume) VALUES (?,?)"
    iterator.foreach(data => {
      val statement = connectionMqcrm.prepareStatement(sql)
      statement.setString(1, data._1)
      statement.setLong(2, data._2)
      var result = statement.executeUpdate()
      if (result == 1) {
        println("写入mysql成功.............")
      }
    })
  }

  def insertToTop10AreaDeliverVolume(iterator: Iterator[(String, Long)]):Unit = {
    val sql = "INSERT INTO Top10AreaDeliverVolume (area,deliverVolume) VALUES (?,?)"
    iterator.foreach(data => {
      val statement = connectionMqcrm.prepareStatement(sql)
      statement.setString(1, data._1)
      statement.setLong(2, data._2)
      var result = statement.executeUpdate()
      if (result == 1) {
        println("写入mysql成功.............")
      }
    })
  }

  def insertToRailwayStation(iterator: Iterator[(String, String)]):Unit = {
    val sql = "INSERT INTO RailwayStation (province,area) VALUES (?,?)"
    iterator.foreach(data => {
      val statement = connectionMqcrm.prepareStatement(sql)
      statement.setString(1, data._1)
      statement.setString(2, data._2)
      var result = statement.executeUpdate()
      if (result == 1) {
        println("写入mysql成功.............")
      }
    })
  }

































}