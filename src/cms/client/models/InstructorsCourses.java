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
    private InstructorsCourses() {}
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
            st.executeUpdate("UPDATE cms.teacher_courses SET course_id = '"+course.getCourseId()+"', email = '"+instructor.getEmail()+"', semester = '"+semester+"' where course_id = '"+course.getCourseId()+"' AND email = '"+instructor.getEmail()+"';");
            return true;
        }
        catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean delete() {
        Database db = Database.getInstance();
        Statement st = db.getStatement();
        if(st == null) return false;
        
        try {
            st.executeUpdate("DELETE FROM cms.teacher_courses WHERE course_id = '"+course.getCourseId()+"' AND email = '"+instructor.getEmail()+"';");
            st.close();
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
            whereStatement += s+"";
        try {
            ResultSet result =  st.executeQuery("SELECT course_id, email, semester FROM cms.teacher_courses where "+whereStatement.substring(0, whereStatement.length()-1)+";");
            if(!result.next())
                return null;
            else {
                setCourse(new Course(result.getString("course_id")));
                setInstructor(new Instructor(result.getString("email")));
                setSemester(result.getString("semester"));
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
    public Course getCourse() {return course;}
    public Instructor getInstructor() {return instructor;}
    public String getSemester() {return semester;}
    // SET
    public void setCourse(Course s) {course = s;}
    public void setInstructor(Instructor s) {instructor = s;}
    public void setSemester(String s) {semester = s;}
    
    
    
    
    
    
    
    
    public static InstructorsCourses getInstructorsCourses(String... v) {
        return (InstructorsCourses) new InstructorsCourses().get(v);
    } 

}
