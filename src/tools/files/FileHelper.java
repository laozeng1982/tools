/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.files;

import java.io.File;

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

        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);

        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        boolean flag = true;

        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {

            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) {
                    break;
                }
            } else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) {
                    break;
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
