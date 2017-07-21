/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.ui.trees;

import tools.utilities.Logs;
import java.awt.Color;
import tools.ui.trees.CheckNode;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.plaf.ColorUIResource;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;

/**
 *
 * @author JianGe
 */
public class CheckRenderer extends JPanel implements TreeCellRenderer {
//树的节点JPanel编辑,每个节点都是一个JPanel,JPanel中有一个JCheckBox和一个JLabel
//TreeCellRenderer从DefaultMutableTreeNode(即CheckNode)中取得数据设置节点
//TreeCellRenderer主要设置Tree的外观,

    FileTree tree;
    protected JCheckBox mCheckBx;
    protected NodeStatusPane mColorPane;
    protected Color mColor = new Color(234);
    protected TreeLabel mNameLabel;

    public CheckRenderer(Color clColor) {
        mCheckBx = new JCheckBox();
        mColorPane = new NodeStatusPane();

        mNameLabel = new TreeLabel();
        mColorPane.setSize(mCheckBx.getPreferredSize().height - 2, mCheckBx.getPreferredSize().height - 2);
        mColorPane.setVisible(true);
        mColorPane.setBorder(BorderFactory.createLineBorder(Color.blue));

        setLayout(null);
        add(mCheckBx);
        add(mColorPane);
        add(mNameLabel);
        mCheckBx.setBackground(UIManager.getColor("Tree.textBackground"));
//        mColorPane.setForeground(Color.red);
        mColorPane.setBackground(clColor);
        mNameLabel.setForeground(UIManager.getColor("Tree.textForeground"));
    }

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value,
            boolean isSelected, boolean expanded, boolean leaf, int row,
            boolean hasFocus) {
        String stringValue = tree.convertValueToText(value, isSelected, //返回节点值的String表示形式
                expanded, leaf, row, hasFocus);

        setEnabled(tree.isEnabled());
        mCheckBx.setSelected(((CheckNode) value).isSelected());
        mNameLabel.setFont(tree.getFont());
        mNameLabel.setText(stringValue);
        mNameLabel.setSelected(isSelected);
        mNameLabel.setFocus(hasFocus);
        if (leaf) {
            if (((CheckNode) value).getIcon() != null) {
                mNameLabel.setIcon(((CheckNode) value).getIcon());
            } else {
                mNameLabel.setIcon(UIManager.getIcon("Tree.leafIcon"));
            }

        } else if (expanded) {
            mNameLabel.setIcon(UIManager.getIcon("Tree.openIcon"));
        } else {
            mNameLabel.setIcon(UIManager.getIcon("Tree.closedIcon"));
        }
        return this;
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension d_check = mCheckBx.getPreferredSize();
        Dimension d_colorLabel = mColorPane.getSize();
        Dimension d_nameLabel = mNameLabel.getPreferredSize();
        return new Dimension(d_check.width + d_colorLabel.width + d_nameLabel.width,
                (d_check.height < d_nameLabel.height ? d_nameLabel.height
                        : d_check.height));
    }
//布局check和label

    @Override
    public void doLayout() {
        Dimension d_check = mCheckBx.getPreferredSize();
        Dimension d_colorLabel = mColorPane.getSize();
        Dimension d_nameLabel = mNameLabel.getPreferredSize();
        int y_check = 0;
        int y_label = 0;
        if (d_check.height < d_nameLabel.height) {
            y_check = (d_nameLabel.height - d_check.height) / 2;
        } else {
            y_label = (d_check.height - d_nameLabel.height) / 2;
        }
        mCheckBx.setLocation(0, y_check);
        mCheckBx.setBounds(0, y_check, d_check.width, d_check.height);
        mColorPane.setLocation(d_check.width, y_label);
        mColorPane.setBounds(d_check.width, y_label, d_colorLabel.width, d_colorLabel.height);
        mNameLabel.setLocation(d_check.width + d_colorLabel.width, y_label);
        mNameLabel.setBounds(d_check.width + d_colorLabel.width, y_label, d_nameLabel.width, d_nameLabel.height);
    }
//设置背景颜色

    @Override
    public void setBackground(Color color) {
        if (color instanceof ColorUIResource) {
            color = null;
        }
        super.setBackground(color);
    }

}
