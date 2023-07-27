/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms;

import cms.ui.MainFrame;
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
        // create connection 
        boolean result = Database.Builder.prepareFirstLaunch();
        if(!result) {
            System.out.println("Error initializing database");
            System.exit(0);
        }
        
        new cms.server.Server().run();
         
        // TODO code application logic here
         java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
    
}
