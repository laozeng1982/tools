/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.files;

/**
 *
 * @author JianGe
 */
public class FILE_TOOLS {

    private String fileName;
    private String fileParentPath;
    private String fileAbsPath;

    public FILE_TOOLS(String fileName, String parentPath, String absPath) {
        this.fileName = fileName;
        this.fileParentPath = parentPath;
        this.fileAbsPath = absPath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String FileName) {
        this.fileName = FileName;
    }

    public String getFileParentPath() {
        return fileParentPath;
    }

    public void setFileParentPath(String FileParentPath) {
        this.fileParentPath = FileParentPath;
    }

    public String getFileAbsPath() {
        return fileAbsPath;
    }

    public void setFileAbsPath(String FilePath) {
        this.fileAbsPath = FilePath;
    }

    public static String autoGenOutputFile(FILE_TOOLS input, String autoAdd) {
        String outputFileName;

        String fileName = input.getFileName().split("\\.")[0];
        String fileExtends = "." + input.getFileName().split("\\.")[1];

        outputFileName = input.getFileParentPath() + "\\" + fileName + autoAdd + fileExtends;
        return outputFileName;
    }

}
