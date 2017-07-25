/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.files;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.TextField;

/**
 *
 * @author Halliburton
 */
public class FileHelper {

    private String inputFileAbsPath;
    private String outputFileAbsPath;

    public String getInputFileAbsPath() {
        return inputFileAbsPath;
    }

    public void setInputFileAbsPath(String inputFileAbsPath) {
        this.inputFileAbsPath = inputFileAbsPath;
    }

    public String getOutputFileAbsPath() {
        return outputFileAbsPath;
    }

    public void setOutputFileAbsPath(String outputFileAbsPath) {
        this.outputFileAbsPath = outputFileAbsPath;
    }

//----------------------------------------------------------------------------------    
    public FileHelper() {

    }

    public FileHelper(String inputFileName) {
        this.inputFileAbsPath = inputFileName;
    }

    public FileHelper(String inputFileName, String outputFileName) {
        this.inputFileAbsPath = inputFileName;
        this.outputFileAbsPath = outputFileName;
    }

    /**
     * 
     * @param dir
     * @param sufx
     * @return 
     */
    public static List<File> getAllFilesInDirectory(String dir, String sufx) {
        File dirFile = (new File(dir));
        List outputFiles = new ArrayList<>();
        if (dirFile.isDirectory()) {
            for (File listFile : dirFile.listFiles()) {
                if (listFile.getName().contains(sufx)) {
                    outputFiles.add(listFile);
                }
            }
        }

        return outputFiles;
    }

    public static boolean deleteFolder(String sPath) {
        boolean flag = false;
        File file = new File(sPath);

        if (!file.exists()) {
            return flag;
        } else {

            if (file.isFile()) {
                return deleteFile(sPath);
            } else {
                return deleteDirectory(sPath);
            }
        }
    }

    private static boolean deleteFile(String sPath) {
        boolean flag = false;
        File file = new File(sPath);

        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }

    private static boolean deleteDirectory(String sPath) {

        boolean flag = false;
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);

        if (!dirFile.exists() || !dirFile.isDirectory()) {
            flag = false;
        } else {
            File[] files = dirFile.listFiles();
            for (File file : files) {
                if (file.isFile()) {
                    flag = deleteFile(file.getAbsolutePath());
                    if (!flag) {
                        break;
                    }
                } else {
                    flag = deleteDirectory(file.getAbsolutePath());
                    if (!flag) {
                        break;
                    }
                }
            }
        }

        if (!flag) {
            return false;
        }

        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }
}
