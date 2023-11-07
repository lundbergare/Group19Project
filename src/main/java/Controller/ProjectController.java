package Controller;

import Model.ProjectModel;
import View.ProjectView;

public class ProjectController {
    private ProjectModel model;
    private ProjectView view;

    public ProjectController(ProjectModel model, ProjectView view) {
        this.model = model;
        this.view = view;
    }
}
