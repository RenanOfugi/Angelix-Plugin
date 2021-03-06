package com.ufg.i4soft.droidrepair.model;

import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import com.ufg.i4soft.droidrepair.enums.FileExtensions;

public class FilterData {

    public String filterPath(VirtualFile file) {

        String path;

        if (file != null && !file.getPath().endsWith("/")) {

            path = file.getPath();
            return path;

        }

        return null;
    }

    public String[] splitPath(String path) {

        int local_split = path.lastIndexOf("/");

        String[] subPath = new String[2];
        subPath[0] = path.substring(0, local_split); // Parte representando o path sem o arquivo
        subPath[1] = path.substring(local_split + 1, path.length()); //nome do arquivo

        return subPath;
    }

    public boolean verifyExtensionFile(String file){

        boolean valid;

        for (FileExtensions extension: FileExtensions.values()) {

            valid = file.toLowerCase().endsWith(extension.getValue().toLowerCase());

            if(valid){
                return true;
            }
        }

        return false;
    }
}
