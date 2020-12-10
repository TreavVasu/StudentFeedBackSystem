package com.vasu.osfs.utilities;

import javax.swing.*;

public class commonFunction {
    public ImageIcon createImageIcon(String s, String description) {
        java.net.URL imgURL = getClass().getResource(s);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + s);
            return null;
        }

    }
}
