package model.database;

import java.sql.*;

public class Database {

    public static Connection createNewDatabase(){
        String url = "jdbc:sqlite:IQAIR.db";
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url);
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void connectThenCreateTable(Connection conn){
        try {
            Statement statement = conn.createStatement();

            statement.execute("DROP TABLE IF EXISTS citiesInfo");
            statement.execute("CREATE TABLE IF NOT EXISTS citiesInfo (city text NOT NULL," +
                    "state text NOT NULL," +
                    "country text NOT NULL," +
                    "info text NOT NULL);");


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insertData(Connection conn, String city, String state, String country, String info){

        String sql = "INSERT INTO citiesInfo(city,state, country, info) VALUES(?,?,?,?)";
        try{

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, city);
            preparedStatement.setString(2, state);
            preparedStatement.setString(3, country);
            preparedStatement.setString(4, info);
            preparedStatement.executeUpdate();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static String queryData(Connection conn){
        try{

            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM citiesInfo");
            String result = rs.getString("city") + " " + rs.getString("state") + " "
                    + rs.getString("country") + " " + rs.getString("info");
            //return result;

            //loop through the result set
            while (rs.next()) {
                System.out.println(rs.getString("city") + " " + rs.getString("state") + " "
                        + rs.getString("country") + " " + rs.getString("info"));
            }
            return result;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return null;
    }

}

