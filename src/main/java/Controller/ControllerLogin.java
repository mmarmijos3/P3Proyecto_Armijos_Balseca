
package Controller;

import java.util.Arrays;

/**
 *
 * @author Manfred Armijos
 */
public class ControllerLogin {
    //<editor-fold defaultstate="collapsed" desc="Login">
    /*
    public void userLogin() {
        String user = getViewLogin().getTxfUser().getText().trim();  // Get username from login field
        char[] key = getViewLogin().getPsfKey().getPassword();       // Get password as char array for security

        // User credentials
        String userManfred = "mmarmijos3";
        char[] keyManfred = {'1','5','9','9'};

        String userValeska = "vebalseca";
        char[] keyValeska = {'v','a','l','e'};

        boolean success = false;  // Track authentication result

        // Validate first user credentials (Manfred)
        if (user.equals(userManfred) && Arrays.equals(key, keyManfred)) {
            success = true;
        }

        // Validate second user credentials (Valeska)
        if (user.equals(userValeska) && Arrays.equals(key, keyValeska)) {
            success = true;
        }

        if (success) {
            // Authentication successful - open main application window
            new ViewPort().setVisible(true);       // Display the main port management interface
            getViewLogin().setVisible(false);      // Hide the login window
        } else {
            // Authentication failed - show error messages
            getViewLogin().getLblErrorKey().setText("Incorrect username or password");   // Password field error
        }
    }
*/
    // </editor-fold>
}
