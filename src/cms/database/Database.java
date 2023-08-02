/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms.database;

import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Moham
 */
public class Database {
    private static final String JDBC = "com.mysql.jdbc.Driver";
    private static final String USER = "root", PASS = "root";
    private final String URL = "jdbc:mysql://localhost:3306/cms";
    private static Database instance;
    
    private Database() {
        // establish connection
        try {
            Class.forName(JDBC);
            connection = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Connection works");
            
            //connection.close();
        }
        catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static Database getInstance() {
        if(instance == null) 
            instance = new Database();
        return instance;
    }
    
    public Statement getStatement() {
        try {
            Statement st = connection.createStatement();
            return st;
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    
    
    public static class Builder {
        public static boolean prepareFirstLaunch() {
        Connection conn;
        try {
            Class.forName(JDBC);
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", USER, PASS);
            System.out.println("[CMS] : Database Connectioned");
            if(checkDatabaseExistence(conn))
                System.out.println("[CMS] : system is ready to use");
            else {
                if(createDatabase(conn)) {
                    createUserTable(conn);
                    createCoursesTable(conn);
                    createStudentCoursesTable(conn);
                    createTeacherCoursesTable(conn);
                } else {
                    conn.close();
                    throw new Exception();
                }
            }
                
            conn.close();
            return true;
        }
        catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
        }
        
        return false;
    }
        private static boolean checkDatabaseExistence(Connection conn) throws SQLException {
        boolean state = false;
        Statement st = conn.createStatement();
        ResultSet result = st.executeQuery("show databases where `database` = 'cms';");
        state = result.next();
        st.close();
        return state;
    }
        private static boolean createDatabase(Connection conn)  throws SQLException {
        Statement st = conn.createStatement();
        int effectedRows = st.executeUpdate("create database cms;");
        st.close();
        return effectedRows > 0;
    }
        private static void createUserTable(Connection conn)   throws SQLException {
        Statement st = conn.createStatement();
        int effectedRows = st.executeUpdate("CREATE TABLE cms.users (\n" +
"    email varchar(50) not null,\n" +
"    first_name varchar(30) not null,\n" +
"    last_name varchar(30) not null,\n" +
"    birth_date varchar(30) not null,\n" +
"    password varchar(30) not null,\n" +
"    is_instructor int default 0,\n"+
"    primary key(email)\n" +
"); ");
        st.close();
    }
        private static void createCoursesTable(Connection conn)   throws SQLException {
        Statement st = conn.createStatement();
        int effectedRows = st.executeUpdate("CREATE TABLE cms.courses (\n" +
"	\n" +
"    course_id char(7) not null,\n" +
"    title varchar(70) not null,\n" +
"    `description` text,\n" +
"    credits int default 2,\n" +
"    primary key(course_id)\n" +
"); ");
        st.close();
    }
        private static void createStudentCoursesTable(Connection conn)   throws SQLException {
        Statement st = conn.createStatement();
        int effectedRows = st.executeUpdate("CREATE TABLE cms.student_courses (\n" +
"	email varchar(50) not null,\n" +
"    course_id char(7) not null,\n" +
"    enroll_date date default now(),\n" +
"    semester varchar(20) not null\n" +
"); ");
        st.close();
    }
        private static void createTeacherCoursesTable(Connection conn)   throws SQLException {
        Statement st = conn.createStatement();
        int effectedRows = st.executeUpdate("CREATE TABLE cms.teacher_courses (\n" +
"	email varchar(50) not null,\n" +
"    course_id char(7) not null,\n" +
"    creation_date date default now(),\n" +
"    semester varchar(20) not null\n" +
"); ");
        st.close();
    }
    }
    
    
    
    
    private Connection connection;
    
}
