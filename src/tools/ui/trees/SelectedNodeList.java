/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.ui.trees;

import java.util.ArrayList;

/**
 *
 * @author JianGe
 */
public class SelectedNodeList extends ArrayList<CheckNode> {

    @Override
    public boolean add(CheckNode node) {
        if (!hasNode(node)) {
            super.add(node);
        }
        return true;
    }

    public boolean hasNode(CheckNode node) {
        return getSelectedNodeID(node) >= 0;
    }

    public int getSelectedNodeID(CheckNode node) {
        int selectedID = -1;
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).equals(node)) {
                selectedID = i;
                break;
            }
        }
        return selectedID;
    }

    public ArrayList<String> getSelectedFiles(){
        String fileName = "";
        ArrayList<String>  fileNameArrayList = new ArrayList<>();
        for (int i = 0; i < this.size(); i++) {
            CheckNode get = this.get(i);
            if (get.getNodeParentName()!=null &&!get.getNodeParentName().equals(fileName)) {
                fileName=get.getNodeParentName();
                fileNameArrayList.add(fileName);
            }
        }
        return fileNameArrayList;
    }
    
    public int getSelectedFileNum() {     
        return getSelectedFiles().size();
        
    }

}
