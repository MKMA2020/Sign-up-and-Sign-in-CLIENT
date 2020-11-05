package mkma.signupsignin.signable;

import signable.Signable;

/**
 * Class that returns a implementation for the signable
 *
 * @author Aitor García
 */
public class SignableFactory {

    /**
     * This method gives the asking class a Signable object
     *
     * @return a signable object
     */
    public Signable getSignable() {
        return (new SignableImplementation());
    }
}
