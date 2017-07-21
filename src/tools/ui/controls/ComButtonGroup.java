/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.ui.controls;

import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;

/**
 *
 * @author JianGe
 */
public class ComButtonGroup extends ButtonGroup {

    public void setAllEnable(boolean isEnable) {
        Enumeration<AbstractButton> enmuation =this.getElements();
        
        while (enmuation.hasMoreElements()) {
            enmuation.nextElement().setEnabled(isEnable);
        }

    }
}
