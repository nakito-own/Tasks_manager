package com.databasefx;

import java.sql.*;
import java.util.ArrayList;

public class DataBaseConnector {

    private final String HOST = "localhost";
    private final String PORT = "3306";
    private final String DATABASENAME = "tasks";
    private final String LOGIN = "root";
    private final String PASSWORD = "Huytebe8911";

    private Connection dbConn = null;

    private Connection getConn() throws ClassNotFoundException, SQLException {

        String conn = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASENAME;
        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConn = DriverManager.getConnection(conn, LOGIN, PASSWORD);
        return dbConn;
    }

    public void insertTask(String task) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO tasks (task) VALUES (?)";

        PreparedStatement prSt = getConn().prepareStatement(sql);
        prSt.setString(1, task);

        prSt.executeUpdate();

    }

    public ArrayList<String> getTasks() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM tasks ORDER BY `id` DESC";

        Statement statement = getConn().createStatement();
        ResultSet res = statement.executeQuery(sql);

        ArrayList<String> tasks = new ArrayList<>();
        while(res.next())
            tasks.add(res.getString("task"));

        return tasks;
    }
}
