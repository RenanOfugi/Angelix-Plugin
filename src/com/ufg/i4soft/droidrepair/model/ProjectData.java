package com.ufg.i4soft.droidrepair.model;

import com.intellij.openapi.project.Project;

public class ProjectData {

    private static Project project;
    private String typeRepair;
    private static String path_of_patch;

    public static Project getProject() {
        return project;
    }

    public static void setProject(Project project) {
        ProjectData.project = project;
    }

    public String getTypeRepair() {
        return typeRepair;
    }

    public void setTypeRepair(String typeRepair) {
        this.typeRepair = typeRepair;
    }

    public static String getPath_of_patch() {
        return path_of_patch;
    }

    public static void setPath_of_patch(String path_of_patch) {
        ProjectData.path_of_patch = path_of_patch;
    }
}
