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
public class Course extends Model {
    String courseId, title, description;
    int credits;
    @Override
    public boolean save() {
        Database db = Database.getInstance();
        Statement st = db.getStatement();
        if(st == null) return false;
        
        try {
            st.executeUpdate("INSERT INTO cms.courses (course_id, title, `description`, credits) \n" +
                "VALUES ('"+courseId+"', '"+title+"', '"+description+"', "+credits+");");
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
            st.executeUpdate("UPDATE cms.courses SET course_id = '"+courseId+"', title = '"+title+"', `description` = '"+description+"', credits = "+credits+" where course_id = '"+courseId+"';");
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
        String whereStatement = "";
        for(String s : v)
            whereStatement += s+",";
        try {
            ResultSet result =  st.executeQuery("SELECT course_id, title, `description`, credits FROM cms.courses where "+whereStatement.substring(0, whereStatement.length()-1)+";");
            if(!result.next())
                return null;
            else {
                setCourseId(result.getString("course_id"));
                setTitle(result.getString("title"));
                setDescription(result.getString("description"));
                setCredits(result.getInt("credits"));
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
    public String getCourseId() {return courseId;}
    public String getTitle() {return title;}
    public String getDescription() {return description;}
    public int getCredits() {return credits;}
    // SET
    public void setCourseId(String s) {courseId = s;}
    public void setTitle(String s) {title = s;}
    public void setDescription(String s) {description = s;}
    public void setCredits(int s) {credits = s;}

    @Override
    public boolean delete() {
        Database db = Database.getInstance();
        Statement st = db.getStatement();
        if(st == null) return false;
        
        try {
            st.executeUpdate("DELETE FROM cms.courses WHERE course_id = '"+courseId+"';");
            st.close();
            return true;
        }
        catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    
    
    
    
    
    public static Course getStudent(String... v) {
        return (Course) new Course().get(v);
    } 

}
