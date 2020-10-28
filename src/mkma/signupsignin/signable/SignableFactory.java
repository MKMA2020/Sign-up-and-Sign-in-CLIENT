/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkma.signupsignin.signable;

import signable.Signable;

/**
 * Class that returns a implementation for the signable
 * @author Aitor García
 */
public class SignableFactory {
    /**
     * This method gives the asking class a Signable object
     * @return a signable object
     */
    public Signable getSignable() {
        return (new SignableImplementation());
    }
}
