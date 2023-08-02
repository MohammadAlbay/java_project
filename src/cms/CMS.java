/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms;

import cms.client.Connection;
import cms.client.models.Course;
import cms.client.models.Instructor;
import cms.client.models.Student;
import cms.client.ui.MainFrame;
import cms.database.Database;
import cms.server.Server;
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
            
            Instructor ins = Instructor.getInstructor("email = 'alwaer@uot.com'");
            Course course = new Course();
            course.setCourseId("ITSE424");
            course.setTitle("Design pattern");
            course.setDescription("What ever");
            course.setCredits(3);
            //course.save();
            course.delete();
            //ins.setPassword("12233");
            //ins.update();
            //ins.delete();
            
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
    
}
