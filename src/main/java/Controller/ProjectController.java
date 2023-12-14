package Controller;


import Model.LevelOne;

import View.LevelView;
import View.ProjectView;
import View.HowToPlayView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProjectController {
    private final ProjectView view;

    public ProjectController(ProjectView view) {
        this.view = view;
        view.addLevelButtonListener(new LevelButtonListener());
        view.addHowToPlayButtonListener(new HTPButtonListener());
        view.addQuitButtonListener(new QuitButtonListener());
    }

    class LevelButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            LevelOne newLevel = new LevelOne();
            LevelView levelView = new LevelView(newLevel, view);
            view.showNewScreen(levelView);
            levelView.requestFocusInWindow();
        }
    }
    class HTPButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            showHowToPlayScreen();
        }
    }

    static class QuitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    private void showHowToPlayScreen() {
        HowToPlayView howToPlayView = new HowToPlayView(new BackButtonListener());
        view.showNewScreen(howToPlayView);
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
