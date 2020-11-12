package mkma.signupsignin.signable;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import user_message.Message;

/**
 * Worker class of the client. It creates a socket and sends the message to the
 * server.
 *
 * @author Kerman Rodríguez, Martín Gros
 */
public class Worker extends Thread {

    private Message message;
    private Message received;

    /**
     * Constructor that sets the Worker message.
     *
     * @param message message that is set.
     */
    public Worker(Message message) {
        this.message = message;
    }

    /**
     * Method Thread method run.
     */
    @Override
    public synchronized void run() {
        //Creates the socket and the output stream
        Socket socket = null;
        OutputStream outputStream = null;
        ObjectOutputStream objectOutputStream = null;
        ObjectInputStream entry = null;
        InputStream input = null;
        ResourceBundle configFile;
        configFile = ResourceBundle.getBundle("mkma.signupsignin.signable.config");

        //Defines the object and the stream, and sends a message
        try {
            socket = new Socket(configFile.getString("URL"), Integer.parseInt(configFile.getString("PORT")));
            outputStream = socket.getOutputStream();
            objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(message);
            input = socket.getInputStream();
            entry = new ObjectInputStream(input);
            received = (Message) entry.readObject();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            //Closes the socket and the stream
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                try {
                    entry.close();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
                try {
                    input.close();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
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
            } catch (NullPointerException e) {
                Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, "Connection refused");
            }
        }
    }

    /**
     * Method that sends the message back to the client who sent it.
     *
     * @return the treated message
     */
    public Message getReceived() {
        return received;
    }
}
