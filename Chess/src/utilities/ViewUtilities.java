package utilities;

import javax.swing.*;

public final class ViewUtilities {

    /**
     * Prompts the player for team name
     * @param team
     * team number 1 or 2
     * @return
     * Custom team name
     */
    public static String promptForName(int team) {
        Boolean nameReceived = false;
        String input = "";

        while(!nameReceived) {
            input = JOptionPane.showInputDialog("Please enter player " + team + " name: ");
            if (input.length() > 0) {
                nameReceived = true;
            } else {
                System.out.println("Please enter name!");
            }
        }

        return input;
    }

}
