/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.ui.trees;

import tools.utilities.Logs;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
//import signalprocess.frames.MainPlotFrameJFree;
//import signalprocess.frames.MainPlotFrameXY;
//import signalprocess.frames.RunFuncJDialog;

/**
 *
 * @author JianGe
 */
public class FileTreeLeafPopMenu extends JPopupMenu {

    FileTree tree;

    JMenuItem loadMenuItem = new JMenuItem("Load a File");
    JSeparator separator = new JSeparator();

    JMenuItem medianFilterMenuItem = new JMenuItem("Median Filter");
    JMenuItem averageFilterMenuItem = new JMenuItem("Average Filter");
    JSeparator separator1 = new JSeparator();

    JMenuItem runMenuItem = new JMenuItem("Run Func");
    JMenuItem fftMenuItem = new JMenuItem("FFT");
    JMenuItem sftMenuItem = new JMenuItem("SFT");
    JSeparator separator2 = new JSeparator();

    JMenuItem drawMenuItem = new JMenuItem("Draw Curve");
    JMenuItem saveMenuItem = new JMenuItem("Save Curve");
    JMenuItem delMenuItem = new JMenuItem("Delete Curve");
    JSeparator separator3 = new JSeparator();

    public FileTreeLeafPopMenu(FileTree tree) {
        this();
        this.tree = tree;
    }

    public FileTreeLeafPopMenu() {
        this.add(loadMenuItem);
        this.add(separator);

        this.add(medianFilterMenuItem);
        this.add(averageFilterMenuItem);
        this.add(separator1);

        this.add(runMenuItem);
        this.add(fftMenuItem);
        this.add(sftMenuItem);
        this.add(separator2);

        this.add(saveMenuItem);
        this.add(drawMenuItem);
        this.add(delMenuItem);
        this.add(separator3);

        addActions();
    }

    private void addActions() { // process

        runMenuItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent event) {
//                Logs.e(event.getComponent().toString());
                runFunc();
            }
        });
        drawMenuItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent event) {
                Logs.e(event.getComponent().toString());
            }
        });

        saveMenuItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent event) {
                Logs.e(event.getComponent().toString());
            }
        });

        delMenuItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent event) {
                try {
//                    MainPlotFrameJFree.deleteNodeCurve();
                } catch (Exception e) {
                }

                tree.deleteNodes();

            }
        });

    }

    public void runFunc() {
        
//        new RunFuncJDialog(MainPlotFrame.mDataTree, MainPlotFrame.mFileCurveHolder).show();
//        new RunFuncJDialog(MainPlotFrameXY.mDataTree, MainPlotFrameXY.mFileCurveHolder).show();
    }

}
