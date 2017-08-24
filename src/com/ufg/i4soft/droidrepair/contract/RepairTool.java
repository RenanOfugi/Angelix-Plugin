package com.ufg.i4soft.droidrepair.contract;

import java.util.ArrayList;

public interface RepairTool {

    void startRepairTool(String path);

    ArrayList<String> collectParameters(String path);

    void verifyInputs(ArrayList<String> args);

    void executeRepairTool(ArrayList<String> args);
}
