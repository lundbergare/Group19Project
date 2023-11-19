// Main.java
import Controller.ProjectController;
import Model.ProjectModel;
import View.ProjectView;

import javax.swing.*;

public class Main {

    private static void initWindow() {
       ProjectModel model = new ProjectModel();
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