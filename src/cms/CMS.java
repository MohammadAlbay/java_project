/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms;

import cms.client.models.*;
import cms.client.ui.MainFrame;
import cms.database.Database;
/**
 *
 * @author Moham
 */
public class CMS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if(args == null || args.length == 0 || args[0].equals("client")) {
            System.out.println("Server mode:\nDatabase details : Mysql (host:localhost, port:3306, user:root, pass: root)");
            boolean result = Database.Builder.prepareFirstLaunch();
            if(!result) {
                System.out.println("Error initializing database");
                System.exit(0);
            }
            
//            Student stu = createStu("md@gmail.com");
//            Instructor ins = createInst("drmhmd@gmail.com");
//            Course course = createCourse("ITSE424");
//            
//            // first time
//            stu.save();
//            ins.save();
//            course.save();
//            
//            
//            createInst("fucckSS@gmail.com").save();
//            createInst("Asshol@gg.com").save();
            Instructor drMohammad = Instructor.getInstructor("email = 'drmhmd@gmail.com'");
            Course designPatt = Course.getCourse("course_id = 'ITSE424'");
            
//            InstructorsCourses ic = new InstructorsCourses(drMohammad, designPatt, "summer/2022");
//            ic.save();
            InstructorsCourses ic = InstructorsCourses.getInstructorsCourses("course_id = 'ITSE424'", "&&", "email = 'drmhmd@gmail.com'");
            System.out.println("IC/"+ic.getSemester()+", "+ic.getInstructor().getFirstName()+". c="+ic.getCourse().getTitle());
            
            
            java.awt.EventQueue.invokeLater(() -> new MainFrame().setVisible(true));
        }
//        else if(args[0].equals("server")) {
//            System.out.println("-----------------------------------------------------------");
            System.out.println("Server mode:\nDatabase details : Mysql (host:localhost, port:3306, user:root, pass: root)");
            boolean result = Database.Builder.prepareFirstLaunch();
            if(!result) {
                System.out.println("Error initializing database");
                System.exit(0);
            }
//            // STARTING THE SERVER
//            
//            System.out.println("Note : Shutdown server by pressing control+c");
//            Server server = new cms.server.Server(5060);
//            server.setUseMainThread(false);
//            server.startup();
//        }
        
         
        
    }
    
    
    static Student createStu(String email) {
        Student st = new Student();
        st.setEmail(email);
        st.setFirstName("MD");
        st.setLastName("Dev");
        st.setBirthDate("22-4-1999");
        st.setPassword("1234");
        return st;
    }
    static Instructor createInst(String email) {
        Instructor st = new Instructor();
        st.setEmail(email);
        st.setFirstName("Dr. Mohammad");
        st.setLastName("Albay");
        st.setBirthDate("24-1-1399");
        st.setPassword("4234");
        return st;
    }
    static Course createCourse(String c) {
        Course st = new Course();
        st.setCourseId(c);
        st.setTitle("Design pattern");
        st.setDescription("None for now");
        st.setCredits(3);
        return st;
    }
}
