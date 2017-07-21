/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.ui.trees;

import java.awt.Color;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.swing.Icon;
import javax.swing.JPopupMenu;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author JianGe
 */
//Node keep data items
public class CheckNode extends DefaultMutableTreeNode {

    JPopupMenu popupMenu;
    private String nodeName;
    private String nodeParentName;
    private String nodeType;
    private Color nodeColor;

    final Lock lock = new ReentrantLock();
    final Condition isChange = lock.newCondition();

    private int selectionMode = 0;
    private boolean isSelected = false;
    private Icon icon = null;

    //addMouseListener(new CheckNodeMouseEvent());
    public CheckNode() {
        this(null);
    }

    public CheckNode(Object userObject) {
        this(userObject, true, false);

    }

    public CheckNode(Object userObject, boolean allowsChildren,
            boolean isSelected) {
        super(userObject, allowsChildren);
        this.isSelected = isSelected;
        setNodeType();
    }

    public int getSelectionMode() {
        return selectionMode;
    }

    public void setSelected(boolean isSelected) throws InterruptedException {
        lock.lock();

        try {
            this.isSelected = isSelected;
            isChange.signal();
        } finally {
            lock.unlock();
        }

    }

    public boolean isSelected() {
        lock.lock();

        try {
//            isChange.signal();
            return isSelected;
        } finally {
            lock.unlock();

        }

    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public Icon getIcon() {
        return icon;
    }

    public String getNodeName() {
        return this.toString();
    }

    public String getNodeParentName() {
        return this.getParent().toString();
    }

    public Color getNodeColor() {
        return nodeColor;
    }

    public void setNodeColor(Color nodeColor) {
        this.nodeColor = nodeColor;
    }

    public String getNodeType() {
        if (this.getParent().toString().contains("txt")) {
            return "txt";
        } else if (this.getParent().toString().contains("dat")) {
            return "dat";
        } else {
            return "db";
        }

    }

    @Override
    public boolean equals(Object aObject) {
        //was inserted
        if (aObject != null && this.getParent() != null) {
            CheckNode tmp = (CheckNode) aObject;
            return tmp.toString().equals(this.toString())
                    && tmp.getParent().toString().equals(this.getParent().toString())
                    && tmp.getNodeType().equals(this.getNodeType());
        } else {
            return false;
        }
    }

    public void setNodeType() {
        if (this.getParent() != null) {
            nodeName = this.toString();
            nodeParentName = this.getParent().toString();

            if (this.nodeParentName.contains("txt")) {
                this.nodeType = "txt";
            } else {
                this.nodeType = "db";
            }
        }

    }

    public boolean isDataBaseNode() {
        return this.nodeType.equals("db");
    }
}
