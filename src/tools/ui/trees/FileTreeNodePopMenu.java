/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.ui.trees;

import tools.utilities.Logs;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;

/**
 *
 * @author JianGe
 */
public class FileTreeNodePopMenu extends JPopupMenu {

    FileTree tree;

    JMenuItem collapseMenuItem = new JMenuItem("Collapse All");
    JMenuItem expandMenuItem = new JMenuItem("Expand All");
    JSeparator separator = new JSeparator();
    JCheckBoxMenuItem ddBoxMenuItem = new JCheckBoxMenuItem("Always Expand");

    JRadioButtonMenuItem rr1ButtonMenuItem = new JRadioButtonMenuItem("HeHe");
    JRadioButtonMenuItem rr2ButtonMenuItem = new JRadioButtonMenuItem("HeiHei");
    ButtonGroup bg = new ButtonGroup();

    JSeparator separator1 = new JSeparator();

    JMenuItem drawMenuItem = new JMenuItem("Draw All Curve");
    JMenuItem delMenuItem = new JMenuItem("Delete All Curve");
    JSeparator separator2 = new JSeparator();

    public FileTreeNodePopMenu() {
        this.add(collapseMenuItem);
        this.add(expandMenuItem);
        this.add(separator);

        this.add(ddBoxMenuItem);
        this.add(rr1ButtonMenuItem);
        this.add(rr2ButtonMenuItem);
        bg.add(rr1ButtonMenuItem);
        bg.add(rr2ButtonMenuItem);
        this.add(separator1);

        this.add(drawMenuItem);
        this.add(delMenuItem);
        this.add(separator2);

//        int x = event.getX();
//        int y = event.getY();
//        int row = tree.getRowForLocation(x, y);
//        TreePath path = tree.getPathForRow(row);
        addActions();
    }

    private void addActions() { // process
        collapseMenuItem.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                 Logs.e(e.toString());
            }
        });

        expandMenuItem.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                 Logs.e(e.toString());
            }
        });

        drawMenuItem.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                 Logs.e(e.toString());
            }
        });

        delMenuItem.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                 Logs.e(e.toString());
            }
        });

//        delMenuItem.addMouseListener(new CheckNodeMouseEvent());
    }

}
