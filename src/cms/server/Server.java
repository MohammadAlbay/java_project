/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms.server;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Moham
 */
public class Server {
    public Server(int port) {
        try {
            server = new ServerSocket(port);
        } catch (IOException ex) {
            errorAtInitilization = true;
            ex.printStackTrace();
        }
    }
    
    
    public void run() {
        if(errorAtInitilization) {
            System.err.println("[CMS][Server] : server couldn't inilialzied properly");
            return;
        }
        new Thread( () -> {
            while(shutdownRequested) {
                try {
                    Socket clientConnection = server.accept();
                    new Thread(() -> {
                        try {
                            InputStream is;
                            InputStreamReader isr;
                            String state = "command";
                            
                            while(shutdownRequested) {
                                is = clientConnection.getInputStream();
                                if(state.equals("command")) {
                                    isr = new InputStreamReader(is);
                                    //isr.rea
                                }
                                state = state.equals("command") ? "data" : "command";
                            }
                            clientConnection.close();
                        } catch (IOException ex) {
                            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }).start();
                }
                catch(IOException e) {
                    e.printStackTrace();
                }
            }
            
        }).start();
    }
    
    public void shutdown() {
        shutdownRequested = true;
        System.out.println("[CMS][Server] : shutdown requested");
    }
    
    
    boolean errorAtInitilization = false;
    boolean shutdownRequested = false;
    ServerSocket server;
}
