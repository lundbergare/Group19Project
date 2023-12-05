package Controller;

import Model.Level;
import Model.LevelListener;
import View.LevelView;

public class LevelController implements LevelListener {
    private final Level level;
    private final LevelView view;

    public LevelController(Level level, LevelView view) {
        this.level = level;
        this.view = view;
        this.level.setLevelListener(this);
        PlayerController playerController = new PlayerController(level.player);
        view.addKeyListener(playerController);


        // Make sure the view is focusable and has focus for key events to work
        level.startTimer();
        view.setFocusable(true);
        view.requestFocusInWindow();
    }

    @Override
    public void onTimerTick() {
        view.camera.update(level.player.getPos(), 3000, 1000);
        uiUpdate();


        view.repaint();

    }
    public void uiUpdate(){
        this.view.setScore(level.player.getScore());
        this.view.setLives(level.player.getLives());
    }

    public void powerUpUpdate(){

    }

}