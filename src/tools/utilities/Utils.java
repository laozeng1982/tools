/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.utilities;

import java.util.ArrayList;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

/**
 *
 * @author Halliburton
 */
public class Utils {

    public static class MarketType {

        public static final String SZ = "sz";
        public static final String SH = "sh";
    }

    public static class OPEN_DIAG {

        public static final int OPEN = 1;
        public static final int SAVE = 2;
    }

    public static String[] removeAllSpace(String input) {
        String[] output;
        output = input.replaceAll("\t", " ").replaceAll(" +", " ").split(" ");

        return output;

    }

    public static boolean isFileSelected(JTextField jTextField, String type) {
        if (jTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please Choose a " + type + " file!", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public static boolean isFileSelected(TextField textField, String type) {
        if (textField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please Choose a " + type + " file!", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public static void showContents(ArrayList<String> contents, JTextPane jTextPane) {
        StringBuffer buffer = new StringBuffer();

        for (int i = 0; i < contents.size(); i++) {
            buffer.append(i + "\t\t" + contents.get(i) + "\n");
        }

        jTextPane.setText(buffer.toString());
    }

    public static void showContent(String content, JTextPane jTextPane) {

        jTextPane.setText(content);
    }

    public static String bytesToHex(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = src.length - 1; i >= 0; i--) {
            String hex = Integer.toHexString(src[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            stringBuilder.append(hex);
        }
        return stringBuilder.toString();

    }

    public static String byteToHex(byte b) {
        String result = Integer.toHexString(b & 0xFF);
        if (result.length() == 1) {
            result = '0' + result;
        }
        return result;
    }

    public static double getCorrelation(double x[], double y[]) {
        if (x.length > y.length) {
            Logs.e("getCorrelation Error");
            return -1;
        }

        double meanX, meanY, tempX, tempY, tempZ;
        double sumX, sumY;
        double result;
        sumX = 0.0;
        sumY = 0.0;
        for (int i = 0; i < x.length; i++) {
            sumX = sumX + x[i];
            sumY = sumY + y[i];
        }
        meanX = sumX / x.length;
        meanY = sumY / x.length;

        //for(i=0;i<N;i++){
        //	x[i]=x[i]-meanX;
        //	y[i]=y[i]-meanY;
        //}
        tempX = 0.0;
        tempY = 0.0;
        tempZ = 0.0;

        for (int i = 0; i < x.length; i++) {
            tempX = tempX + (x[i] - meanX) * (y[i] - meanY);
            tempY = tempY + (x[i] - meanX) * (x[i] - meanX);
            tempZ = tempZ + (y[i] - meanY) * (y[i] - meanY);
        }

        result = tempX / (Math.sqrt(tempY) * Math.sqrt(tempZ));

        return result;
    }
}
