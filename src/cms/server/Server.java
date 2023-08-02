/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms.server;

import java.net.*;
import java.io.*;

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
    
    
    public void startup() {
        if(errorAtInitilization) {
            System.err.println("[CMS][Server] : server couldn't inilialzied properly");
            return;
        }
        if(useMainThread)
            process();
        else
            new Thread(() ->  process()).start();
    }
    
    public void shutdown() {
        shutdownRequested = true;
        System.out.println("[CMS][Server] : shutdown requested");
    }
    
    public void setUseMainThread(boolean b) {
        useMainThread = b;
    }
    
    private void process() {
        while(!shutdownRequested) {
                try {
                    Socket clientConnection = server.accept();
                    new Thread(() -> {
                        try {
                            BufferedReader reader = new BufferedReader(new InputStreamReader(clientConnection.getInputStream()));
                            PrintWriter writer = new PrintWriter(clientConnection.getOutputStream());
                            OutputStream baseWriter = clientConnection.getOutputStream();
                            InputStream baseReader = clientConnection.getInputStream();
                            String state = "command";
                            
                            
                            while(!shutdownRequested) {
                                String line = reader.readLine();
                                
                                if(line == null) continue;
                                if(line.equals("quite"))
                                    break;
                                byte[] data = new byte[baseReader.available()];
                                baseReader.read(data);
                                
                                
                                System.out.println("Recieved data  :   [Line] = "+line + ", [Data] = "+data.length);
                                
                                writer.write("MD;18;333");                                
                                writer.write("Ahmed;22;444");

                                writer.flush();
                            }
                            clientConnection.close();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }).start();
                }
                catch(IOException e) {
                    e.printStackTrace();
                }
            }
    }
    boolean errorAtInitilization = false;
    boolean shutdownRequested = false;
    boolean useMainThread = false;
    ServerSocket server;
}
