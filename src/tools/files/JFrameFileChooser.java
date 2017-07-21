/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.files;

import java.io.File;
import javax.swing.filechooser.FileFilter;
import javax.swing.JFileChooser;
import tools.utilities.Logs;
import tools.utilities.Utils;

/**
 *
 * @author Halliburton
 */
public class JFrameFileChooser extends JFileChooser {

    public JFrameFileChooser(String filePath) {

        setAcceptAllFileFilterUsed(false);
        setCurrentDirectory(new File(filePath));

//        setMultiSelectionEnabled(true);
        this.addChoosableFileFilter(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return true;
            }

            @Override
            public String getDescription() {
                return "All Files(*.*)";
            }
        });
    }

    public FILE_TOOLS showDialog(int openType) {
        File file;

        if (openType == Utils.OPEN_DIAG.OPEN) {
            this.showOpenDialog(null);
            if (this.getSelectedFile() == null) {
                return null;
            }
            file = this.getSelectedFile().getAbsoluteFile();

//            Files[] files = this.getSelectedFiles();
        } else {
            this.showSaveDialog(null);
            if (this.getSelectedFile() == null) {
                return null;
            }
            file = this.getSelectedFile().getAbsoluteFile();
        }

        if (file != null) {
            Logs.e(file.getName());
            return new FILE_TOOLS(file.getName(), file.getParent(), file.getAbsolutePath());
        } else {
            return null;
        }
    }

    public void setFilters(String[][] fileENames) {

        for (final String[] fileEName : fileENames) {
            this.setFileFilter(new javax.swing.filechooser.FileFilter() {
                public boolean accept(File file) {
                    if (file.getName().endsWith(fileEName[0]) || file.isDirectory()) {
                        return true;
                    } else {
                        return false;
                    }
                }

                @Override
                public String getDescription() {
                    return fileEName[1];
                }

            });
        }
    }

}
