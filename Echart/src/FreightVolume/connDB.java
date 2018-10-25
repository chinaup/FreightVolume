package FreightVolume;
import java.sql.*;
import java.util.ArrayList;
 
public class connDB {
    private static Connection con = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
 
    //连接数据库方法
    public static void startConn(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            //连接数据库中间件
            try{
                con = DriverManager.getConnection("jdbc:MySQL://localhost:3306/FreightVolume","root","199504");
            }catch(SQLException e){
                e.printStackTrace();
            }
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }
 
    //关闭连接数据库方法
    public static void endConn() throws SQLException{
        if(con != null){
            con.close();
            con = null;
        }
        if(rs != null){
            rs.close();
            rs = null;
        }
        if(stmt != null){
            stmt.close();
            stmt = null;
        }
    }
    
    //A站总收发货量对比
    public static ArrayList totalVolume() throws SQLException{
        ArrayList<String[]> list = new ArrayList();
        startConn();
        System.out.println("connect database");
        stmt = con.createStatement();
        rs = stmt.executeQuery("select takeVolume,deliverVolume from totalVolume");
        while(rs.next()){
            String[] temp={rs.getString("takeVolume"),rs.getString("deliverVolume")};
            list.add(temp);
        }
        endConn();
        return list;
    }
    
    //A站每月总货运量
    public static ArrayList monthlyTotalVolume() throws SQLException{
        ArrayList<String[]> list = new ArrayList();
        startConn();
        System.out.println("connect database");
        stmt = con.createStatement();
        rs = stmt.executeQuery("select month,volume from monthlyTotalVolume");
        while(rs.next()){
            String[] temp={rs.getString("month"),rs.getString("volume")};
            list.add(temp);
        }
        endConn();
        return list;
    }
    
    //A站每月总收货量
    public static ArrayList monthlyTotalTakeVolume() throws SQLException{
        ArrayList<String[]> list = new ArrayList();
        startConn();
        System.out.println("connect database");
        stmt = con.createStatement();
        rs = stmt.executeQuery("select month,takeVolume from monthlyTotalTakeVolume");
        while(rs.next()){
            String[] temp={rs.getString("month"),rs.getString("takeVolume")};
            list.add(temp);
        }
        endConn();
        return list;
    }
    
    //A站每月总发货量
    public static ArrayList monthlyTotalDeliverVolume() throws SQLException{
        ArrayList<String[]> list = new ArrayList();
        startConn();
        System.out.println("connect database");
        stmt = con.createStatement();
        rs = stmt.executeQuery("select month,deliverVolume from monthlyTotalDeliverVolume");
        while(rs.next()){
            String[] temp={rs.getString("month"),rs.getString("deliverVolume")};
            list.add(temp);
        }
        endConn();
        return list;
    }
    
    //A站每天总货运量
    public static ArrayList dailyTotalVolume() throws SQLException{
        ArrayList<String[]> list = new ArrayList();
        startConn();
        System.out.println("connect database");
        stmt = con.createStatement();
        rs = stmt.executeQuery("select date,volume from dailyTotalVolume");
        while(rs.next()){
            String[] temp={rs.getString("date"),rs.getString("volume")};
            list.add(temp);
        }
        endConn();
        return list;
    }
    
    //A站每天总收货量
    public static ArrayList dailyTotalTakeVolume() throws SQLException{
        ArrayList<String[]> list = new ArrayList();
        startConn();
        System.out.println("connect database");
        stmt = con.createStatement();
        rs = stmt.executeQuery("select date,takeVolume from dailyTotalTakeVolume");
        while(rs.next()){
            String[] temp={rs.getString("date"),rs.getString("takeVolume")};
            list.add(temp);
        }
        endConn();
        return list;
    }
    
    //A站每天总发货量
    public static ArrayList dailyTotalDeliverVolume() throws SQLException{
        ArrayList<String[]> list = new ArrayList();
        startConn();
        System.out.println("connect database");
        stmt = con.createStatement();
        rs = stmt.executeQuery("select date,deliverVolume from dailyTotalDeliverVolume");
        while(rs.next()){
            String[] temp={rs.getString("date"),rs.getString("deliverVolume")};
            list.add(temp);
        }
        endConn();
        return list;
    }
    
    //A站每类货物货运量
    public static ArrayList everyTypeVolume() throws SQLException{
        ArrayList<String[]> list = new ArrayList();
        startConn();
        System.out.println("connect database");
        stmt = con.createStatement();
        rs = stmt.executeQuery("select type,volume from everyTypeVolume");
        while(rs.next()){
            String[] temp={rs.getString("type"),rs.getString("volume")};
            list.add(temp);
        }
        endConn();
        return list;
    }
    
    //A站每类货物收货量
    public static ArrayList everyTypeTakeVolume() throws SQLException{
        ArrayList<String[]> list = new ArrayList();
        startConn();
        System.out.println("connect database");
        stmt = con.createStatement();
        rs = stmt.executeQuery("select type,takeVolume from everyTypeTakeVolume");
        while(rs.next()){
            String[] temp={rs.getString("type"),rs.getString("takeVolume")};
            list.add(temp);
        }
        endConn();
        return list;
    }
    
    //A站每类货物发货量
    public static ArrayList everyTypeDeliverVolume() throws SQLException{
        ArrayList<String[]> list = new ArrayList();
        startConn();
        System.out.println("connect database");
        stmt = con.createStatement();
        rs = stmt.executeQuery("select type,deliverVolume from everyTypeDeliverVolume");
        while(rs.next()){
            String[] temp={rs.getString("type"),rs.getString("deliverVolume")};
            list.add(temp);
        }
        endConn();
        return list;
    }
    
  //A站每种运输方式货运量
    public static ArrayList everyTransportVolume() throws SQLException{
        ArrayList<String[]> list = new ArrayList();
        startConn();
        System.out.println("connect database");
        stmt = con.createStatement();
        rs = stmt.executeQuery("select transport,volume from everyTransportVolume");
        while(rs.next()){
            String[] temp={rs.getString("transport"),rs.getString("volume")};
            list.add(temp);
        }
        endConn();
        return list;
    }
    
  //A站每种运输方式收货量
    public static ArrayList everyTransportTakeVolume() throws SQLException{
        ArrayList<String[]> list = new ArrayList();
        startConn();
        System.out.println("connect database");
        stmt = con.createStatement();
        rs = stmt.executeQuery("select transport,takeVolume from everyTransportTakeVolume");
        while(rs.next()){
            String[] temp={rs.getString("transport"),rs.getString("takeVolume")};
            list.add(temp);
        }
        endConn();
        return list;
    }
    
  //A站每种运输方式发货量
    public static ArrayList everyTransportDeliverVolume() throws SQLException{
        ArrayList<String[]> list = new ArrayList();
        startConn();
        System.out.println("connect database");
        stmt = con.createStatement();
        rs = stmt.executeQuery("select transport,deliverVolume from everyTransportDeliverVolume");
        while(rs.next()){
            String[] temp={rs.getString("transport"),rs.getString("deliverVolume")};
            list.add(temp);
        }
        endConn();
        return list;
    }
    
    //各区域货运量
    public static ArrayList everyAreaVolume() throws SQLException{
        ArrayList<String[]> list = new ArrayList();
        startConn();
        System.out.println("connect database");
        stmt = con.createStatement();
        rs = stmt.executeQuery("select area,volume from everyAreaVolume");
        while(rs.next()){
            String[] temp={rs.getString("area"),rs.getString("volume")};
            list.add(temp);
        }
        endConn();
        return list;
    }
    
  //各区域收货量
    public static ArrayList everyAreaTakeVolume() throws SQLException{
        ArrayList<String[]> list = new ArrayList();
        startConn();
        System.out.println("connect database");
        stmt = con.createStatement();
        rs = stmt.executeQuery("select area,takeVolume from everyAreaTakeVolume");
        while(rs.next()){
            String[] temp={rs.getString("area"),rs.getString("takeVolume")};
            list.add(temp);
        }
        endConn();
        return list;
    }
    
  //各区域发货量
    public static ArrayList everyAreaDeliverVolume() throws SQLException{
        ArrayList<String[]> list = new ArrayList();
        startConn();
        System.out.println("connect database");
        stmt = con.createStatement();
        rs = stmt.executeQuery("select area,deliverVolume from everyAreaDeliverVolume");
        while(rs.next()){
            String[] temp={rs.getString("area"),rs.getString("deliverVolume")};
            list.add(temp);
        }
        endConn();
        return list;
    }
    
    
    //Top10区域货运量
    public static ArrayList Top10AreaVolume() throws SQLException{
        ArrayList<String[]> list = new ArrayList();
        startConn();
        System.out.println("connect database");
        stmt = con.createStatement();
        rs = stmt.executeQuery("select area,volume from Top10AreaVolume");
        while(rs.next()){
            String[] temp={rs.getString("area"),rs.getString("volume")};
            list.add(temp);
        }
        endConn();
        return list;
    }
    
  //Top10区域收货量
    public static ArrayList Top10AreaTakeVolume() throws SQLException{
        ArrayList<String[]> list = new ArrayList();
        startConn();
        System.out.println("connect database");
        stmt = con.createStatement();
        rs = stmt.executeQuery("select area,takeVolume from Top10AreaTakeVolume");
        while(rs.next()){
            String[] temp={rs.getString("area"),rs.getString("takeVolume")};
            list.add(temp);
        }
        endConn();
        return list;
    }
    
  //Top10区域发货量
    public static ArrayList Top10AreaDeliverVolume() throws SQLException{
        ArrayList<String[]> list = new ArrayList();
        startConn();
        System.out.println("connect database");
        stmt = con.createStatement();
        rs = stmt.executeQuery("select area,deliverVolume from Top10AreaDeliverVolume");
        while(rs.next()){
            String[] temp={rs.getString("area"),rs.getString("deliverVolume")};
            list.add(temp);
        }
        endConn();
        return list;
    }
    
    //各省货运量
    public static ArrayList everyProvinceVolume() throws SQLException{
        ArrayList<String[]> list = new ArrayList();
        startConn();
        System.out.println("connect database");
        stmt = con.createStatement();
        rs = stmt.executeQuery("select province,volume from everyProvinceVolume");
        while(rs.next()){
            String[] temp={rs.getString("province"),rs.getString("volume")};
            list.add(temp);
        }
        endConn();
        return list;
    }
    
  //各省收货量
    public static ArrayList everyProvinceTakeVolume() throws SQLException{
        ArrayList<String[]> list = new ArrayList();
        startConn();
        System.out.println("connect database");
        stmt = con.createStatement();
        rs = stmt.executeQuery("select province,takeVolume from everyProvinceTakeVolume");
        while(rs.next()){
            String[] temp={rs.getString("province"),rs.getString("takeVolume")};
            list.add(temp);
        }
        endConn();
        return list;
    }
    
    //各省发货量
    public static ArrayList everyProvinceDeliverVolume() throws SQLException{
        ArrayList<String[]> list = new ArrayList();
        startConn();
        System.out.println("connect database");
        stmt = con.createStatement();
        rs = stmt.executeQuery("select province,deliverVolume from everyProvinceDeliverVolume");
        while(rs.next()){
            String[] temp={rs.getString("province"),rs.getString("deliverVolume")};
            list.add(temp);
        }
        endConn();
        return list;
    }
    
    

}
