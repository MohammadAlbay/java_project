/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms.client;

import java.net.*;
import java.io.*;
import java.util.*;
/**
 *
 * @author Moham
 */
public class Connection {
    private Connection(int port) {
        try { 
            socket = new Socket(InetAddress.getLocalHost(), 5060); 
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream());
            baseWriter = socket.getOutputStream();
        }
        catch(IOException e) {
            initFailed = true;
            e.printStackTrace();
        }
    }
    
    public void shutdown() {
        try {
            socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void send(String header,byte[] payload, ConnectionCallBack event) {
        try {
            writer.write(header);
            writer.flush();
            baseWriter.write(payload);
            baseWriter.flush();
            event.handle(ConnectionState.SUCCESS);
        }
        catch(IOException e) { 
            e.printStackTrace(); 
            event.handle(ConnectionState.FAILURE);
        }
        
    }
    public String recieve(ConnectionCallBack event) {
        try {
            String result = reader.readLine();
            event.handle(ConnectionState.SUCCESS);
            return result;
        } catch (IOException ex) {
            ex.printStackTrace();
            event.handle(ConnectionState.FAILURE);
        }
        return null;
    }
    
    public String[][] recieveAll(ConnectionCallBack event) {
        try {
            List<String[]> list = new ArrayList<>();
            String line = "";
            while(true) {
                line = reader.readLine();
                if(line == null || line.isEmpty())
                    break;
                else
                    list.add(line.split(";"));
            }
            event.handle(ConnectionState.SUCCESS);
            return (String[][])list.toArray();
        } catch (IOException ex) {
            ex.printStackTrace();
            event.handle(ConnectionState.FAILURE);
        }
        return null;
    }
    
    public static synchronized Connection getInstance() {
        if(instance == null)
            instance = new  Connection(5060);
        
        return instance;
    }
    
    private static Connection instance;
    
    
    private boolean initFailed = false;
    Socket socket;
    BufferedReader reader;
    PrintWriter writer;
    OutputStream baseWriter;
}
