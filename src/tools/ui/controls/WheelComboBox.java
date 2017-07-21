/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.ui.controls;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.JComboBox;

/**
 *
 * @author JianGe
 */
public class WheelComboBox<Object> extends JComboBox<Object> implements MouseWheelListener, MouseListener {
    
    private int currentPosition;
    
    public WheelComboBox() {
        super();
        addMouseListener(this);
        addMouseWheelListener(this);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
//        Logs.e("Clicked!");
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
//       Logs.e("Pressed!");
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
//       Logs.e("Released!");
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
//       Logs.e("Entered!");
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
//        Logs.e("Exited!");
    }
    
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {

//        Logs.e("Wheel moved!");
        int rotationNum = 0;
        
        if (e.getWheelRotation() > 0) {
            rotationNum++;
        } else {
            rotationNum--;
        }
        
        currentPosition = this.getSelectedIndex() + rotationNum < 0 ? 0 : this.getSelectedIndex() + rotationNum;
        
        if (currentPosition < this.getItemCount()) {
            this.setSelectedIndex(currentPosition);
        }
    }
    
    public int getCurrentPosition() {
        return currentPosition;
    }
    
    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }
    
}
