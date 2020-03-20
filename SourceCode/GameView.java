package com.example.myapplication;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;

    Arrow arrowUp;
    Arrow arrowDown;
    Arrow arrowLeft;
    Arrow arrowRight;

    int sizeOne = 0, sizeTwo = 0;

    DisplayMetrics displayMetrics;

    public GameView(Context context) {
        super(context);
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);
        setFocusable(true);
        displayMetrics = getResources().getDisplayMetrics();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.setRunning(true);
        thread.start();

        //Assigning pictures for movement arrows to draw
        arrowUp = new Arrow("Up", BitmapFactory.decodeResource(getResources(),R.drawable.up_arrow));
        arrowDown = new Arrow("Down", BitmapFactory.decodeResource(getResources(),R.drawable.down_arrow));
        arrowRight = new Arrow("Left", BitmapFactory.decodeResource(getResources(),R.drawable.right_arrow));
        arrowLeft = new Arrow("Right", BitmapFactory.decodeResource(getResources(),R.drawable.left_arrow));
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();
            }

            catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    public void update() {
    }

    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        float x = event.getX();
        float y = event.getY();

        switch (action) {
            case MotionEvent.ACTION_DOWN:

                //If the game is on Victory phase, tapping the screen will put it on to size selection phase
                if(thread.gameStart == 0) {
                    thread.gameStart = 1;
                    thread.board.totalMoves = 0;
                }

                //The part where the user selects the size of the board, thread.gameStart == 1 means the user is selecting the row size of the board and thread.gameStart == 2 means user is selecting the column size of the board.
                else if(thread.gameStart == 1 || thread.gameStart == 2) {
                    if (x > 0 && x < (float)(displayMetrics.widthPixels * 0.145) && y > (float)(displayMetrics.heightPixels * 0.44) && y < (float)(displayMetrics.heightPixels * 0.51)) {
                        if(thread.gameStart == 1) {
                            sizeOne = 3;
                            thread.gameStart += 1;
                        }

                        else {
                            sizeTwo = 3;
                            thread.gameStart += 1;
                        }
                    }

                    else if (x > (float)(displayMetrics.widthPixels * 0.175) && x < (float)(displayMetrics.widthPixels * 0.35) && y > (float)(displayMetrics.heightPixels * 0.44) && y < (float)(displayMetrics.heightPixels * 0.51)) {
                        if(thread.gameStart == 1) {
                            sizeOne = 4;
                            thread.gameStart += 1;
                        }

                        else {
                            sizeTwo = 4;
                            thread.gameStart += 1;
                        }
                    }

                    else if (x > (float)(displayMetrics.widthPixels * 0.35) && x < (float)(displayMetrics.widthPixels * 0.45) && y > (float)(displayMetrics.heightPixels * 0.44) && y < (float)(displayMetrics.heightPixels * 0.51)) {
                        if(thread.gameStart == 1) {
                            sizeOne = 5;
                            thread.gameStart += 1;
                        }

                        else {
                            sizeTwo = 5;
                            thread.gameStart += 1;
                        }
                    }

                    else if (x > (float)(displayMetrics.widthPixels * 0.45) && x < (float)(displayMetrics.widthPixels * 0.60) && y > (float)(displayMetrics.heightPixels * 0.44) && y < (float)(displayMetrics.heightPixels * 0.51)) {
                        if(thread.gameStart == 1) {
                            sizeOne = 6;
                            thread.gameStart += 1;
                        }

                        else {
                            sizeTwo = 6;
                            thread.gameStart += 1;
                        }
                    }

                    else if (x > (float)(displayMetrics.widthPixels * 0.60) && x < (float)(displayMetrics.widthPixels * 0.75) && y > (float)(displayMetrics.heightPixels * 0.44) && y < (float)(displayMetrics.heightPixels * 0.51)) {
                        if(thread.gameStart == 1) {
                            sizeOne = 7;
                            thread.gameStart += 1;
                        }

                        else {
                            sizeTwo = 7;
                            thread.gameStart += 1;
                        }
                    }

                    else if (x > (float)(displayMetrics.widthPixels * 0.75) && x < (float)(displayMetrics.widthPixels * 0.88) && y > (float)(displayMetrics.heightPixels * 0.44) && y < (float)(displayMetrics.heightPixels * 0.51)) {
                        if(thread.gameStart == 1) {
                            sizeOne = 8;
                            thread.gameStart += 1;
                        }

                        else {
                            sizeTwo = 8;
                            thread.gameStart += 1;
                        }
                    }

                    else if (x > (float)(displayMetrics.widthPixels * 0.88) && x < (float)(displayMetrics.widthPixels * 1.1) && y > (float)(displayMetrics.heightPixels * 0.44) && y < (float)(displayMetrics.heightPixels * 0.51)) {
                        if(thread.gameStart == 1) {
                            sizeOne = 9;
                            thread.gameStart += 1;
                        }

                        else {
                            sizeTwo = 9;
                            thread.gameStart += 1;
                        }
                    }

                    //If the secondSize(column) is selected, game will start immediately
                    if(thread.gameStart == 3) {
                        thread.board = new GameBoard(sizeOne, sizeTwo);
                        thread.board.shuffle();
                    }
                }

                //If the game is neither on victory or size selection phase, movement reading will start
                else if (x >= arrowUp.getA() && x < (arrowUp.getA() + arrowUp.getSprite().getWidth()) && y >= arrowUp.getB() && y < (arrowUp.getB() + arrowUp.getSprite().getHeight())) {
                    Log.d("Clicked", "UP ARROW");
                    thread.board.move(2);
                }

                else if (x >= arrowDown.getA() && x < (arrowDown.getA() + arrowDown.getSprite().getWidth()) && y >= arrowDown.getB() && y < (arrowDown.getB() + arrowDown.getSprite().getHeight())) {
                    Log.d("Clicked", "DOWN ARROW");
                    thread.board.move(3);
                }

                else if (x >= arrowLeft.getA() && x < (arrowLeft.getA() + arrowLeft.getSprite().getWidth()) && y >= arrowLeft.getB() && y < (arrowLeft.getB() + arrowLeft.getSprite().getHeight())) {
                    Log.d("Clicked", "LEFT ARROW");
                    thread.board.move(1);
                }

                else if (x >= arrowRight.getA() && x < (arrowRight.getA() + arrowRight.getSprite().getWidth()) && y >= arrowRight.getB() && y < (arrowRight.getB() + arrowRight.getSprite().getHeight())) {
                    Log.d("Clicked", "RIGHT ARROW");
                    thread.board.move(0);
                }

                break;
        }

        return false;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) {
            //Drawing canvas and setting size for to text
            canvas.drawColor(Color.WHITE);

            Paint paint = new Paint();
            paint.setColor(Color.WHITE);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawPaint(paint);

            paint.setColor(Color.rgb(250, 0, 0));
            paint.setTextSize(50);

            //thread.gameStart == 1 and 2 means game is on board size selection phase, thread.gameStart == 0 means game is on victory screen phase and thread.gameStart == 3(else) means game is on play state
            if(thread.gameStart == 1 || thread.gameStart == 2) {
                paint.setTextSize(75);
                for(int i = 3; i < 10; i++)
                    canvas.drawText(String.valueOf(i), (float)(displayMetrics.widthPixels * 0.06) + (i - 3) * (float)(displayMetrics.widthPixels * 0.139), (float)(displayMetrics.heightPixels * 0.47), paint);
            }


            else if(thread.gameStart == 0) {
                paint.setTextSize(75);
                canvas.drawText("VICTORY!", (float)(displayMetrics.widthPixels * 0.25), (float)(displayMetrics.heightPixels * 0.47), paint);
                canvas.drawText("Total Moves: " + thread.board.totalMoves, (float)(displayMetrics.widthPixels * 0.16), (float)(displayMetrics.heightPixels * 0.53), paint);
            }

            else {
                //Draws the board to screen
                for(int i = 0; i < thread.board.getRow(); i++) {
                    for (int j = 0; j < thread.board.getColumn(); j++) {
                        if (thread.board.cell(i, j) == -1) {
                            canvas.drawText("B", (float)(displayMetrics.widthPixels * 0.08) + (j * (float)(displayMetrics.widthPixels * 0.1)), (float)(displayMetrics.heightPixels * 0.06) + (i * (float)(displayMetrics.heightPixels * 0.06)), paint);
                        }

                        else {
                            canvas.drawText(String.valueOf(thread.board.cell(i, j)), (float)(displayMetrics.widthPixels * 0.05) + (j * (float)(displayMetrics.widthPixels * 0.1)), (float)(displayMetrics.heightPixels * 0.06) + (i * (float)(displayMetrics.heightPixels * 0.06)), paint);
                        }
                    }
                }

                //Draws the movement arrows
                arrowUp.draw(canvas , displayMetrics.widthPixels * 0.416, displayMetrics.heightPixels * 0.557);
                arrowDown.draw(canvas , displayMetrics.widthPixels * 0.416, displayMetrics.heightPixels * 0.836);
                arrowRight.draw(canvas , displayMetrics.widthPixels * 0.787, displayMetrics.heightPixels * 0.7);
                arrowLeft.draw(canvas , displayMetrics.widthPixels * 0.093, displayMetrics.heightPixels * 0.7);
                canvas.drawText("" + thread.board.totalMoves, (float)(displayMetrics.widthPixels * 0.05), (float)(displayMetrics.heightPixels * 0.98), paint);
            }
        }
    }
}
