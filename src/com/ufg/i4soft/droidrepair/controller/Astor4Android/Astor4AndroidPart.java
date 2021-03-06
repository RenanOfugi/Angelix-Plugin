package com.ufg.i4soft.droidrepair.controller.Astor4Android;

import com.intellij.openapi.ui.Messages;
import com.ufg.i4soft.droidrepair.controller.ExecuteShell;
import com.ufg.i4soft.droidrepair.model.Astor4AndroidData;
import com.ufg.i4soft.droidrepair.view.windows.MainWindows;
import com.ufg.i4soft.droidrepair.view.windows.WaitExecuteShell;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.List;

public class Astor4AndroidPart {

    public void startAstor4Android() {

        mavenClean();

        collectParameters();

        boolean parameters_ok = verifyParameters();

        if (parameters_ok) {

            executeAstor4Android();
        }
    }

    private void mavenClean() {

        String mvn = "cd " + Astor4AndroidData.getAstor4android_directory() + ";mvn clean compile";

        ExecuteShell shell = new ExecuteShell();
        shell.executeShellLoadingMessage(mvn, false, null);
    }

    private void collectParameters() {

        MainWindows location_windows = new MainWindows();
        String location_directory = location_windows.viewChooseFile("LOCATION", "Selecione o diretório do projeto que sofrerá o reparo");
        Astor4AndroidData.setLocation(location_directory);

        if (Astor4AndroidData.getLocation() != null) {

            String instrumentationfailing = Messages.showInputDialog("Entre com testes de instrumentação separados por dois-pontos", "INSTRUMENTATION FAILING", Messages.getInformationIcon());
            Astor4AndroidData.setInstrumentationfailing(instrumentationfailing);
        }
    }

    private boolean verifyParameters() {

        return Astor4AndroidData.getLocation() != null && Astor4AndroidData.getInstrumentationfailing() != null;
    }

    public void executeShellFinalAstor4Android(String command, @NotNull boolean write_file, String path_file) {

        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {

            final WaitExecuteShell waitExecuteShell = WaitExecuteShell.showCommand();

            @Override
            protected Void doInBackground() throws Exception {

                publish();
                ExecuteShell shell = new ExecuteShell();

                shell.executeCommand(command, write_file, path_file);

                return null;
            }

            @Override
            protected void process(List<Void> chunks) {
                waitExecuteShell.setVisible(true);
            }

            @Override
            protected void done() {
                waitExecuteShell.setVisible(false);
                ManageAstor4Android.messageFinishExecution();
            }
        };

        worker.execute();

    }

    private void executeAstor4Android() {

        Messages.showMessageDialog("Será realizado a tentativa de reparo. Este procedimento poderá demorar algum tempo. Seja paciente", "INICIO DE REPARO", Messages.getWarningIcon());

        String build_maven = "cd " + Astor4AndroidData.getAstor4android_directory() + ";" +
                "mvn dependency:build-classpath;" +
                "mvn dependency:build-classpath | egrep -v \"(^\\[INFO\\]|^\\[WARNING\\])\" | tee astor-classpath.txt";

        ExecuteShell shell = new ExecuteShell();
        shell.executeShellLoadingMessage(build_maven, false, null);

        String execute_astor4android = "cd " + Astor4AndroidData.getAstor4android_directory() + ";" +
                Astor4AndroidData.getCommandbase_run_astor4android() + " " +
                "-mode " + Astor4AndroidData.getMode() + " " +
                "-location " + Astor4AndroidData.getLocation() + " " +
                "-androidsdk " + Astor4AndroidData.getAndroid_home() + " " +
                "-androidjar " + Astor4AndroidData.getAndroidjar() + " " +
                "-jvm4testexecution " + Astor4AndroidData.getJava_home() + "/bin " +
                "-javacompliancelevel " + Astor4AndroidData.getJava_compliance_level() + " " +
                "-stopfirst " + Astor4AndroidData.getStopfirst() + " " +
                "-flthreshold " + Astor4AndroidData.getFlthreshold() + " " +
                "-instrumentationfailing " + Astor4AndroidData.getInstrumentationfailing() + " " +
                "-port " + Astor4AndroidData.getHostport();

        executeShellFinalAstor4Android(execute_astor4android, true, System.getProperty("user.home") + "/droidrepair/logAstor4Android.txt");

    }
}
