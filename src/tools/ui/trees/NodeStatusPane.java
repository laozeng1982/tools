/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.ui.trees;

import tools.utilities.Logs;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import javax.swing.JPanel;


/**
 *
 * @author JianGe
 */
public class NodeStatusPane extends JPanel {

    @Override
    public synchronized void addMouseListener(MouseListener listener) {
        super.addMouseListener(listener); //To change body of generated methods, choose Tools | Templates.

    }

    public NodeStatusPane() {
        this.addMouseListener(new mL());
    }

    public class mL extends MouseAdapter {

        @Override
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            Logs.e("Clicked");
        }

        @Override
        public void mousePressed(java.awt.event.MouseEvent evt) {
            Logs.e("Pressed");
        }

        @Override
        public void mouseReleased(java.awt.event.MouseEvent evt) {
            Logs.e("Released");
        }
    }

}
