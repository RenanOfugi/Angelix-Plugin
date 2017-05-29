package com.ufg.i4soft.angelix_plugin.view.windows;

import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;

public class MainWindows {

    public static void viewMessages(Project project, String message, String title) {

        Messages.showMessageDialog(project, message, title, Messages.getInformationIcon());
    }

    public static void windowsCheckboxes() {

        //TODO: Esta classe deverá implementar a interface de usuário que permitirá a escolha do tipo de ferramenta de reparo automatizado de código
    }

    public static String viewChooseFile(Project project) {

        final FileChooserDescriptor descriptor = FileChooserDescriptorFactory.createSingleFileDescriptor();
        descriptor.setTitle("Selecione o diretório do angelix");
        descriptor.setDescription("Selecione o diretório onde se localiza o Angelix");

        VirtualFile file = FileChooser.chooseFile(descriptor, project, null);

        String path = null;
        if (file != null){
            path = file.getPath();
        }

        return path;
    }
}
