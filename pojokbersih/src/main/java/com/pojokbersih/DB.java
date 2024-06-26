package com.pojokbersih;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("exports")
public class DB {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/pbo_pojokbersih";
    static final String USER = "root";
    static final String PASS = "";

    public Connection con;
    public Statement stmt;
    public ResultSet rs;

    public static void main(String[] args) {
        System.out.println("DB is started.");
    }

    public ArrayList<Object> runQuery(String sql) {
        ArrayList<Object> result = new ArrayList<Object>();

        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DB_URL, USER, PASS);

            while(!con.isClosed()){
                stmt = con.createStatement();
                rs = stmt.executeQuery(sql);

                while(rs.next()){
                    ResultSetMetaData metadata = rs.getMetaData();
                    int num = metadata.getColumnCount();

                    List<Object> row = new ArrayList<Object>();

                    for(int i = 1; i <= num; i++){
                        row.add(rs.getString(i));
                    }

                    result.add(row);
                }

                stmt.close();
                con.close();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return result;
    }

    public Boolean runSql(String sql){
        try{
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = con.createStatement();
            Boolean res = !stmt.execute(sql);

            stmt.close();
            con.close();

            return res;
        }
        catch(Exception e){
            e.printStackTrace();

            return false;
        }
    }

    public String getLatestID(String sql){
        try{
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            rs.next();
            String res = rs.getString(1);

            stmt.close();
            con.close();

            return res;
        }
        catch(Exception e){
            e.printStackTrace();

            return "false";
        }
    }
    
}
