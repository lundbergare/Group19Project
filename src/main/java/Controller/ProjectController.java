package Controller;


import Model.LevelOne;


import View.LevelView;
import View.ProjectView;
import View.HowToPlayView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//This class is probably too big right now, should be separate controllers?

public class ProjectController {
    private ProjectView view;

    public ProjectController(ProjectView view) {
        this.view = view;

        // Set up action listeners in the controller
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

    // ActionListener for "Quit" button
    class QuitButtonListener implements ActionListener {
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
