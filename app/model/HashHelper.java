package model;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Created by Ludwig on 09.04.2017.
 */
public class HashHelper {

    /**
     * Create an encrypted password from a clear string.
     *
     * @param clearString
     *            the clear string
     * @return an encrypted password of the clear string
     * @throws IllegalArgumentException
     *             Exception
     */
    public static String createPassword(String clearString) throws IllegalArgumentException{
        if (clearString == null) {
            throw new IllegalArgumentException("empty.password");
        }
        return BCrypt.hashpw(clearString, BCrypt.gensalt());
    }

    /**
     * Method to check if entered user password is the same as the one that is
     * stored (encrypted) in the database.
     *
     * @param candidate
     *            the clear text
     * @param encryptedPassword
     *            the encrypted password string to check.
     * @return true if the candidate matches, false otherwise.
     */
    public static boolean checkPassword(String candidate, String encryptedPassword) {
        if (candidate == null) {
            return false;
        }
        if (encryptedPassword == null) {
            return false;
        }
        return BCrypt.checkpw(candidate, encryptedPassword);
    }
}
