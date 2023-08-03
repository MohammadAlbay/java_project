/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms.client.models;

import cms.database.Database;
import java.sql.*;

/**
 *
 * @author Moham
 */
public class Instructor extends Model {
    String email, firstName, lastName, birthDate, password;

    public Instructor() {}
    public Instructor(String email) {
        get("email = '"+email+"'");
    }
    @Override
    public boolean save() {
        Database db = Database.getInstance();
        Statement st = db.getStatement();
        if(st == null) return false;
        
        try {
            st.executeUpdate("INSERT INTO cms.users (email, first_name, last_name, birth_date, password, is_instructor) \n" +
                "VALUES ('"+email+"', '"+firstName+"', '"+lastName+"', '"+birthDate+"', '"+password+"', 1);");
            st.close();
            return true;
            
        }
        catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean update() {
        Database db = Database.getInstance();
        Statement st = db.getStatement();
        if(st == null) return false;
        
        try {
            st.executeUpdate("UPDATE cms.users SET first_name = '"+firstName+"', last_name = '"+lastName+"', birth_date = '"+birthDate+"', password = '"+password+"' where email = '"+email+"';");
            return true;
        }
        catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    Model get(String... v) {
        Database db = Database.getInstance();
        Statement st = db.getStatement();
        if(st == null) return null;
        String whereStatement = super.generateConditionString(v);
        //System.out.println(whereStatement);
        try {
            ResultSet result =  st.executeQuery("SELECT email, first_name, last_name, birth_date, `password` FROM cms.users where "+whereStatement+" AND is_instructor = 1;");
            if(!result.next())
                return null;
            else {
                setEmail(result.getString("email"));
                setFirstName(result.getString("first_name"));
                setLastName(result.getString("last_name"));
                setBirthDate(result.getString("birth_date"));
                setPassword(result.getString("password"));
            }
            return this;
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    Model[] getAll(String... v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    // GET
    public String getEmail() {return email;}
    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public String getBirthDate() {return birthDate;}
    public String getPassword() {return password;}
    // SET
    public void setEmail(String s) {email = s;}
    public void setPassword(String s) {password = s;}
    public void setFirstName(String s) {firstName = s;}
    public void setLastName(String s) {lastName = s;}
    public void setBirthDate(String d) {birthDate = d;}

    @Override
    public boolean delete() {
        Database db = Database.getInstance();
        Statement st = db.getStatement();
        if(st == null) return false;
        
        try {
            st.executeUpdate("DELETE FROM cms.users WHERE email = '"+email+"';");
            st.close();
            return true;
        }
        catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    
    
    
    
    
    public static Instructor getInstructor(String... v) {
        return (Instructor) new Instructor().get(v);
    } 

}
