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
public class InstructorsCourses extends Model {
    Instructor instructor;
    Course course;
    String semester;
    public InstructorsCourses(Instructor ins, Course cr, String sem) {
        instructor = ins;
        course = cr;
        semester = sem;
    }
    @Override
    public boolean save() {
        Database db = Database.getInstance();
        Statement st = db.getStatement();
        if(st == null) return false;
        
        try {
            st.executeUpdate("INSERT INTO cms.teacher_courses (email, course_id, semester) \n" +
                "VALUES ('"+instructor.getEmail()+"', '"+course.getCourseId()+"', '"+semester+"');");
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
//            st.executeUpdate("UPDATE cms.courses SET course_id = '"+courseId+"', title = '"+title+"', `description` = '"+description+"', credits = "+credits+" where course_id = '"+courseId+"';");
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
//            ResultSet result =  st.executeQuery("SELECT course_id, title, `description`, credits FROM cms.courses where "+whereStatement.substring(0, whereStatement.length()-1)+";");
//            if(!result.next())
//                return null;
//            else {
//                setCourseId(result.getString("course_id"));
//                setTitle(result.getString("title"));
//                setDescription(result.getString("description"));
//                setCredits(result.getInt("credits"));
//            }
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
    public Course getCourse() {return course;}
    public Instructor getInstructor() {return instructor;}
    // SET
    public void setCourse(Course s) {course = s;}
    public void setInstructor(Instructor s) {instructor = s;}

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
    
    
    
    
    
    
    
    public static InstructorsCourses getStudent(String... v) {
        return (InstructorsCourses) new InstructorsCourses().get(v);
    } 

}
