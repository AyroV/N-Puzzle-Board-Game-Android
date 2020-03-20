package com.example.myapplication;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

public class MainThread extends Thread {
    private SurfaceHolder surfaceHolder;
    private GameView gameView;
    private boolean running;
    public static Canvas canvas;
    GameBoard board;
   int gameStart;

    MainThread (SurfaceHolder surfaceHolder, GameView gameView) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gameView = gameView;
        board = new GameBoard(9);
        gameStart = 1;
    }

    @Override
    public void run() {
        while (running) {
            canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized(surfaceHolder) {
                    this.gameView.update();

                    if(board.isSolved() && gameStart == 3) {
                        gameStart = 0;
                    }

                    else {
                        this.gameView.draw(canvas);
                    }
                }
            }

            catch (Exception e) {}

            finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    void setRunning(boolean isRunning) {
        running = isRunning;
    }
}
