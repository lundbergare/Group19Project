
import Controller.ProjectController;

import Model.ProjectModel;

import View.ProjectView;

import javax.swing.*;

public class Main {

    //TODO: This is not correct according to MVC, but oh well

    private static void initWindow() {
        ProjectView view = new ProjectView();
        ProjectController controller = new ProjectController(view);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                initWindow();
            }
        });
    }
}
