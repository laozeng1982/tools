/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.ui.trees;

import tools.utilities.Logs;
import tools.ui.trees.CheckNode;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

/**
 *
 * @author JianGe
 */
public class NodeSelectionListener extends MouseAdapter {

    final JTree tree;
    static FileTreeLeafPopMenu leafPopupMenu = new FileTreeLeafPopMenu();
    static FileTreeNodePopMenu nodePopupMenu = new FileTreeNodePopMenu();

    public NodeSelectionListener(JTree tree) {
        this.tree = tree;
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        //Get the node which is choosen
        int x = event.getX();
        int y = event.getY();
        int row = tree.getRowForLocation(x, y);
        TreePath path = tree.getPathForRow(row);

        if (event.getButton() == event.BUTTON1) {
            //Determin is it single clicked!
            if (path != null) {
                //Get the node which is single clicked!!! This is how to get Node!!!
                CheckNode node = (CheckNode) path.getLastPathComponent();
                boolean isSelected = !(node.isSelected());
                //Set the CheckBox and reverse it's status
                try {
                    node.setSelected(isSelected);
                } catch (Exception e) {
                }

                //If node was selected, expand the list
                if (isSelected) {
                    tree.expandPath(path);
                } else {
                    tree.collapsePath(path);
                }
                //If selected node is not the leaf node, set all it's child nodes' CheckBox with the same status 
                if (!node.isLeaf()) {
                    node.getNextNode();
                    Enumeration enu = node.children();
                    while (enu.hasMoreElements()) {
                        CheckNode n = (CheckNode) enu.nextElement();
                        try {
                            n.setSelected(node.isSelected());
                        } catch (Exception e) {
                        }

                        if (!n.isLeaf()) {
                            Enumeration enuc = n.children();
                            while (enuc.hasMoreElements()) {
                                CheckNode c = (CheckNode) enuc.nextElement();
                                try {
                                    c.setSelected(node.isSelected());
                                } catch (Exception e) {
                                }

                            }
                        }
                    }
                }
                refreshTreeUI(node);

//            Logs.e("Has selected: " + tree.getSelectionCount() + " items");
//                for (int i = 0; i < MainPlotFrame.mFileTree.getSelectionPaths().length; i++) {
//                    Logs.e("Has selected: " + MainPlotFrame.mFileTree.getSelectionPaths()[i] + " items");
//                }
            }
        }
        if (event.getButton() == event.BUTTON3) {
            //If the right button clicked, popup a menu.
            if (path != null) {

                CheckNode node = (CheckNode) path.getLastPathComponent();
                if (node.isLeaf()) {
                    leafPopupMenu.show(event.getComponent(), event.getX(), event.getY());
                    Logs.e(node.toString() + " leaf right clicked!");
                } else {
                    nodePopupMenu.show(event.getComponent(), event.getX(), event.getY());
                    Logs.e(node.toString() + " node right clicked!");
                }
            }
        }
    }

    private void refreshTreeUI(CheckNode node) {
        //Refresh tree, it is necessary to get new status;
//        Logs.e(node.toString() + " selected is " + node.isSelected());
        ((DefaultTreeModel) tree.getModel()).nodeChanged(node);
        tree.revalidate();
        tree.repaint();
    }

}
