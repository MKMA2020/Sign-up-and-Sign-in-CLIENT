/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkma.signupsignin.signable;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import user_message.Message;

/**
 *
 * @author 2dam
 */
public class Worker extends Thread{
    
    private Message message;

    public Worker(Message message) {
        this.message = message;
    }
    
    @Override
    public void run() {
        //Creates the socket and the output stream
        Socket socket = null;
        OutputStream outputStream = null;
        ObjectOutputStream objectOutputStream = null;
        
        //Defines the object and the stream, and sends a message
        try {
            socket = new Socket("localhost", 6302);
            outputStream = socket.getOutputStream();
            objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(message);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            //Closes the socket and the stream
        } finally {
            try {
                objectOutputStream.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            try {
                outputStream.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            try {
                socket.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
