/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.utilities;

/**
 *
 * @author JianGe
 */
import java.util.*;
import java.io.*;

public class StreamWatch extends Thread {

    InputStream is;
    String type;
    List<String> output = new ArrayList<String>();
    boolean debug = false;

    public StreamWatch(InputStream is, String type) {
        this(is, type, false);
    }

    public StreamWatch(InputStream is, String type, boolean debug) {
        this.is = is;
        this.type = type;
        this.debug = debug;
    }

    @Override
    public void run() {
        try {
            PrintWriter pw = null;
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            while ((line = br.readLine()) != null) {
                output.add(line);
                if (debug) {
                    System.out.println(type + ">" + line);
                }
            }
            if (pw != null) {
                pw.flush();
            }
        } catch (IOException ioe) {
        }
    }

    public List<String> getOutput() {
        return output;
    }
}
