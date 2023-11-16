package Controller;


import Model.TestingLevel;
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
            view.showNewScreen(new TestingLevel());
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



//    class Lvl1ButtonListener implements ActionListener{
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            System.out.println("Show level 1");
//        }
//    }

    /*private void showLevelSelectorScreen() {
        NewScreenPanel newScreenPanel = new NewScreenPanel(new BackButtonListener());
        view.showNewScreen(newScreenPanel);
    }*/

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
