import Controller.ProjectController;
import Model.ProjectModel;
import View.ProjectView;

public class Main {
    public static void main(String[] args) {
        ProjectModel model = new ProjectModel();
        ProjectView view = new ProjectView();
        ProjectController controller = new ProjectController(model, view);
    }
    //helloo
}
