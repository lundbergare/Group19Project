package Controller;

import View.LevelSelectorView;
import View.ProjectView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProjectController {
    private ProjectView view;

    public ProjectController(ProjectView view) {
        this.view = view;

        // Set up action listeners in the controller
        view.addLevelButtonListener(new LevelButtonListener());
        view.addQuitButtonListener(new QuitButtonListener());
    }

    // ActionListener for "Start Game" button
    class LevelButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            showNewScreen();
        }
    }

    // ActionListener for "Quit" button
    class QuitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    private void showNewScreen() {
        view.showNewScreen(new LevelSelectorView(new BackButtonListener()));
    }

    class BackButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            showPreviousScreen();
        }
    }

    private void showPreviousScreen() {
        view.showPreviousScreen();
    }
}
