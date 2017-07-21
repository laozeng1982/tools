/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.files;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tools.utilities.Logs;

/**
 *
 * @author deep
 */
public class JFxFileChooser {

    private String defaultPath;
    private Stage showStage;
    FileChooser fileChooser = new FileChooser();

    public JFxFileChooser(Stage stage, String path, String tips, String... fileTypes) {
        this.showStage = stage;

        if (path != null && !path.isEmpty()) {
            if ((new File(path)).getParent() != null) {
                defaultPath = (new File(path)).getAbsolutePath();
            }
        } else {
            defaultPath = System.getProperty("user.home");
        }

        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter(tips, fileTypes);

        fileChooser.getExtensionFilters().addAll(filter);
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Files", "*.*"));
    }

    /**
     *
     * @param textField
     * @param isOpen
     * @return
     */
    public File selectSingleFile(TextField textField, boolean isOpen) {

        File resultFile = null;

        //make sure the input path is real directory. If it is null, set it to current directory; else set it to it's parent directory.
        try {
            fileChooser.setInitialDirectory(new File(defaultPath));
            if (isOpen) {
                resultFile = fileChooser.showOpenDialog(showStage);
            }else{
                resultFile = fileChooser.showSaveDialog(showStage);
            }
            
            if (resultFile != null) {
                if (textField != null) {
                    textField.setText(resultFile.getAbsolutePath());
                }

//                for (String fileType : fileTypes) {
//                    if (fileType.contains("db")) {
//                        Logs.e("Selected Database file: " + resultFile.getAbsolutePath());
//
//                    } else if (fileType.contains("txt") || fileType.contains("dat")) {
//                        Logs.e("Selected Text file: " + resultFile.getAbsolutePath());
//
//                    } else {
//                        Logs.e("Selected other file: " + resultFile.getAbsolutePath());
//                    }
//                }
            }

        } catch (Exception e) {
        }

        return resultFile;

    }

    /**
     *
     * @param textField
     * @return
     */
    public List<File> selectMultiFiles(TextField textField) {

        List<File> resultFile = new ArrayList<>();

        try {
            File files = new File(defaultPath);
            fileChooser.setInitialDirectory(files);

            resultFile = fileChooser.showOpenMultipleDialog(showStage);
            if (resultFile != null && textField != null) {
                File dir = new File(resultFile.get(0).getParent());

                if (resultFile.size() < dir.listFiles().length) {
                    StringBuilder filesList = new StringBuilder();
                    ArrayList<String> filesListLog = new ArrayList<>();
                    for (int i = 0; i < resultFile.size(); i++) {
                        filesList.append(resultFile.get(i).getName()).append(" ");
                        filesListLog.add("Selected file: " + resultFile.get(i).getAbsolutePath());
                    }

                    textField.setText(filesList.toString());
                } else {
                    textField.setText(resultFile.get(0).getParent());
                    Logs.e("Selected directory: " + resultFile.get(0).getParent());

                }
            }

        } catch (Exception e) {
        }

        return resultFile;
    }

    public File selecDirectory(TextField textField) {

        File resultFile;

        try {
            File files = new File(defaultPath);
            fileChooser.setInitialDirectory(files);

            resultFile = fileChooser.showOpenDialog(showStage);
            if (resultFile != null && textField != null) {
                textField.setText(resultFile.getAbsolutePath());
            }

        } catch (Exception e) {
            resultFile = new File(defaultPath);
        }

        return resultFile;
    }

    /**
     *
     * @param textField
     * @param defaultPath
     * @param tips
     * @param fileTypes
     * @return
     */
    public static List<File> getAllFilesInDirectory(TextField textField, String defaultPath, String tips, String... fileTypes) {

        List<File> resultFile = new ArrayList<>();

        if (defaultPath == null || defaultPath.replaceAll(" +", "").isEmpty()) {
            defaultPath = System.getProperty("user.home");
        }
        try {
            File dir = new File(defaultPath);

            if (resultFile != null && textField != null) {
                resultFile.addAll(Arrays.asList(dir.listFiles()));
                Logs.e("Selected directory: " + resultFile.get(0).getParent());
            }
        } catch (Exception e) {
        }
        return resultFile;
    }
}
