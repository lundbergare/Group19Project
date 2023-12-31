import Controller.ProjectController;
import View.ProjectView;
import javax.swing.*;

public class Main {

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
