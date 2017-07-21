/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.ui.trees;

import tools.utilities.Logs;
import java.awt.Color;
import java.awt.datatransfer.Transferable;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

/**
 *
 * @author JianGe
 */
public class FileTree extends JTree implements MouseListener, MouseMotionListener, TreeSelectionListener {

    private static CheckNode rootNode = new CheckNode("All Datas");
    private DefaultTreeModel model;
    private SelectedNodeList selectedNodeList = new SelectedNodeList();

    CheckRenderer checkRenderer = new CheckRenderer(Color.BLACK);

    public FileTree() {
        super(rootNode);

        this.setCellRenderer(checkRenderer);
        this.setDragEnabled(true);
        this.setExpandsSelectedPaths(true);
        this.getSelectionModel().setSelectionMode(
                TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);
        this.addMouseListener(new NodeSelectionListener(this));
        this.addMouseListener(this);

    }

    //Add a node too rootNode
    public void addRootNode(CheckNode insertNode) {
        addNode(insertNode, rootNode);
    }

    //Add a node
    public void addNode(CheckNode insertNode, CheckNode parentNode) {
        model = (DefaultTreeModel) this.getModel();
        //same name node, just add once
        for (int i = 0; i < parentNode.getChildCount(); i++) {
            if (parentNode.getChildAt(i).toString().equals(insertNode.toString())) {
                return;
            }
        }
        model.insertNodeInto(insertNode, parentNode, parentNode.getChildCount());

        this.updateUI();
    }

    //delete a selected node
    public void deleteNodes() {
        model = (DefaultTreeModel) this.getModel();
        CheckNode selectedNode = (CheckNode) this.getLastSelectedPathComponent();

        if (selectedNode != null) {
            if (!selectedNode.isLeaf()) {
                selectedNode.removeAllChildren();

                model.removeNodeFromParent(selectedNode);
            } else {
                model.removeNodeFromParent(selectedNode);
            }

            this.updateUI();
        }
    }

    public SelectedNodeList getSelectedNodeList() {
        return selectedNodeList;
    }

    @Override
    public void mouseClicked(MouseEvent evt) {
        CheckNode selectedNode = (CheckNode) this.getLastSelectedPathComponent();

        if (evt.getButton() != evt.BUTTON3 && selectedNode != null && !selectedNode.isRoot()) {
            //If the node is a leaf, add or remove this node; otherwise, add or remove all curve 

            if (selectedNode.isLeaf()) {
                if (selectedNode.isSelected()) {
                    selectedNodeList.add(selectedNode);
                } else {
                    selectedNodeList.remove(selectedNode);
                }
            } else {
                for (int i = 0; i < selectedNode.getChildCount(); i++) {
                    CheckNode tmpNode = (CheckNode) selectedNode.getChildAt(i);
                    if (selectedNode.isSelected()) {
                        selectedNodeList.add(tmpNode);
                    } else {
                        selectedNodeList.remove(tmpNode);
                    }
                }
            }
            Logs.e(selectedNode.toString() + " selected is " + selectedNode.isSelected());

            Logs.e("Total Curve is " + selectedNodeList.size());
            for (int i = 0; i < selectedNodeList.size(); i++) {
                Logs.e("Total Curve is " + selectedNodeList.size() + ", and Curve is: " + selectedNodeList.get(i).getNodeParentName() + "->" + selectedNodeList.get(i).getNodeName());

            }
//            xYPlotPanel.repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent evt) {
//        Logs.e(evt);
//        return;
    }

    @Override
    public void mouseReleased(MouseEvent evt) {
//        Logs.e("FileTree Released!");
//        return;
    }

    @Override
    public void mouseEntered(MouseEvent evt) {
//        Logs.e(evt);
//        return;
    }

    @Override
    public void mouseExited(MouseEvent evt) {
//        Logs.e(evt);
//        return;
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
//        Logs.e("FileTree Draging!");
//        Transferable 
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void valueChanged(TreeSelectionEvent evt) {
//        Logs.e(evt);
//        return;
    }



}
