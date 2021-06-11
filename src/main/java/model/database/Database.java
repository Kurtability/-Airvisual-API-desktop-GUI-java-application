//package model.database;
//
//import java.sql.*;
//
//public class Database {
//
//    public static void createNewDatabase(){ // difference between execute and executeupdate
//        String url = "jdbc:sqlite:IQAIR.db";
//
//        try (Connection conn = DriverManager.getConnection(url)) {
//            if (conn != null) {
//                DatabaseMetaData meta = conn.getMetaData();
//                System.out.println("The driver name is " + meta.getDriverName());
//                System.out.println("A new database has been created.");
//            }
//
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    public static void connectThenCreatTable() throws SQLException {
//        String url = "jdbc:sqlite:IQAIR.db";
//        Connection conn = null;
//        try {
//            conn = DriverManager.getConnection(url);
//            Statement statement = conn.createStatement();
//
//            //statement.executeUpdate("drop table if exists citiesInfo");
//            //statement.executeUpdate("CREATE TABLE IF NOT EXISTS citiesInfo (city text, state text, country text, info text);");
//            String sql0 = "DROP TABLE IF EXISTS warehouses";
//            String sql = "CREATE TABLE IF NOT EXISTS warehouses (\n"
//                    + "	id integer PRIMARY KEY,\n"
//                    + "	name text NOT NULL,\n"
//                    + "	capacity real\n"
//                    + ");";
//            String sql2 = "INSERT INTO warehouses(name,capacity) VALUES('abc',3000)";
////            statement.executeUpdate("insert into citiesInfo values('Beijing','Beijing','China','testing info'");
//            String sql3 = "SELECT id, name, capacity FROM warehouses";
//
//            statement.execute(sql0);
//            statement.execute(sql);
//            statement.execute(sql2);
//
//            ResultSet rs = statement.executeQuery(sql3);
//
//                // loop through the result set
//                while (rs.next()) {
//                    System.out.println(rs.getInt("id") +  "\t" +
//                            rs.getString("name") + "\t" +
//                            rs.getDouble("capacity"));
//                }
//
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }finally {
//            try {
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch (SQLException ex) {
//                System.out.println(ex.getMessage());
//            }
//        }
//
//
//    }
//
//}
//
