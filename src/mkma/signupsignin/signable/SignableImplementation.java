package mkma.signupsignin.signable;

import exceptions.DataBaseConnectionException;
import exceptions.PassNotCorrectException;
import exceptions.ServerErrorException;
import exceptions.TimeOutException;
import exceptions.UserExistsException;
import exceptions.UserNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import signable.Signable;
import user_message.Message;
import user_message.MessageType;
import user_message.User;

/**
 * This class contains the sign-up, sign-in and sign-out methods implemented
 * from the signable interface.
 *
 * @author Kerman Rodríguez, Martin Gros and Aitor García
 */
public class SignableImplementation implements Signable {

    /**
     * This method creates a message with the user and sends it to the server in
     * order to check if the user exists, and log them in.
     *
     * @param user the user that needs a sign-in
     * @return the user to check if the method was successful
     * @throws DataBaseConnectionException when there is an error connecting to
     * the database
     * @throws PassNotCorrectException when the password is not correct
     * @throws ServerErrorException when there is an error in the server
     * @throws TimeOutException when the server times out
     * @throws UserNotFoundException when the user does not exist
     */
    @Override
    public User signIn(User user) throws DataBaseConnectionException, PassNotCorrectException, ServerErrorException, TimeOutException, UserNotFoundException {
        Message received = new Message(null, null);
        try {
            //Creates the message
            Message message = new Message(user, MessageType.SIGNIN);

            //Creates the thread
            Worker worker = new Worker(message);
            worker.start();
            worker.join();

            received = worker.getReceived();

            switch (received.getMessageType()) {

                case DATABASEERROR:
                    throw new DataBaseConnectionException();
                case PASSNOTCORRECT:
                    throw new PassNotCorrectException();
                case SERVERERROR:
                    throw new ServerErrorException();
                case TIMEOUTEXCEPTION:
                    throw new TimeOutException();
                case USERNOTFOUND:
                    throw new UserNotFoundException();
                default:
                    break;
            }

        } catch (InterruptedException ex) {
            Logger.getLogger(SignableImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return received.getUser();
    }

    /**
     * This method creates a message with the user and sends it to the server,
     * in order to add him to the database.
     *
     * @param user the user that needs a sign-up
     * @return the user to check if the method was successful
     * @throws DataBaseConnectionException when there is an error connecting to
     * the database
     * @throws ServerErrorException when there is an error in the server
     * @throws TimeOutException when the server times out
     * @throws UserExistsException when the user already exists
     */
    @Override
    public User signUp(User user) throws DataBaseConnectionException, ServerErrorException, TimeOutException, UserExistsException {
        Message received = new Message(null, null);
        try {
            //Creates the message
            Message message = new Message(user, MessageType.SIGNUP);

            //Creates the thread
            Worker worker = new Worker(message);
            worker.start();
            worker.join();
            received = worker.getReceived();

            switch (received.getMessageType()) {

                case DATABASEERROR:
                    throw new DataBaseConnectionException();
                case SERVERERROR:
                    throw new ServerErrorException();
                case TIMEOUTEXCEPTION:
                    throw new TimeOutException();
                case USEREXISTS:
                    throw new UserExistsException();
                default:
                    break;
            }

        } catch (InterruptedException ex) {
            Logger.getLogger(SignableImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return received.getUser();
    }

    /**
     * This method opens a new windows and greets the user that has logged in.
     *
     * @param user the user that needs a sign-out
     * @return the user to check if the method was successful
     */
    @Override
    public User signOut(User user) {

        return user;

    }

}
