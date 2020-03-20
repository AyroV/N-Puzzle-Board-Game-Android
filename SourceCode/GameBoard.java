package com.example.myapplication;

import android.util.Log;

class GameBoard {
    private int[][] board;
    private int x; //x position of blank cellSprite
    private int y; //y position of blank cellSprite
    private int row;
    private int column;
    int totalMoves;

    GameBoard (int sizeOne, int sizeTwo) {
        board = new int[sizeOne][sizeTwo];
        row = sizeOne;
        column = sizeTwo;
        x = y = totalMoves = 0;

        int number = 1;
        for(int i = 0; i < sizeOne; i++) {
            for(int j = 0; j < sizeTwo; j++) {
                board[i][j] = number;
                number++;

                if(number == sizeOne * sizeTwo + 1) {
                    board[i][j] = -1;
                    break;
                }
            }
        }

        setX(sizeTwo - 1);
        setY(sizeOne - 1);
    }

    GameBoard(int sizeOne) {
        board = new int[sizeOne][sizeOne];
        row = column = sizeOne;
        x = y = totalMoves = 0;

        int number = 1;
        for(int i = 0; i < sizeOne; i++) {
            for(int j = 0; j < sizeOne; j++) {
                board[i][j] = number;
                number++;

                if(number == sizeOne * sizeOne + 1) {
                    board[i][j - 1] = -1;
                    board[i][j] = number - 2;
                    break;
                }
            }
        }

        setX(sizeOne - 2);
        setY(sizeOne - 1);
    }

    void setSize(int x) {
        int boardSize = x;
        int number = 1;

        board = new int[boardSize][boardSize];

        for(int i = 0; i < boardSize; i++) {
            for(int j = 0; j < boardSize; j++) {
                board[i][j] = number;
                number++;

                if(number == boardSize * boardSize + 1) {
                    board[i][j] = -1;
                    break;
                }
            }
        }

        setX(boardSize - 1);
        setY(boardSize - 1);
        setRow(boardSize);
        setColumn(boardSize);
    }

    int cell(int y, int x) {
        return board[y][x];
    }

    private void setRow(int a) { row = a; }
    private void setColumn(int a) { column = a; }
    private void setX(int a) { x = a; }
    private void setY(int a) { y = a; }
    int getRow() { return row; }
    int getColumn() { return column; }

    //checks if the move is legal and if it is, moves the blank cell to that direction and updates the object
    boolean move(int direction) {
        if(direction == 0 && x != getColumn() - 1 && board[y][x + 1] != 0) {
            board[y][x] = board[y][x + 1];
            board[y][x + 1] = -1;

            x += 1;
            Log.d("MOVE","Moved Right");
            totalMoves++;
            return true;
        }

        else if(direction == 1 && x != 0 && board[y][x - 1] != 0) {
            board[y][x] = board[y][x - 1];
            board[y][x - 1] = -1;

            x -= 1;
            Log.d("MOVE","Moved Left");
            totalMoves++;
            return true;
        }

        else if(direction == 2 && y != 0 && board[y - 1][x] != 0) {
            board[y][x] = board[y - 1][x];
            board[y - 1][x] = -1;

            y -= 1;
            Log.d("MOVE","Moved Up");
            totalMoves++;
            return true;
        }

        else if(direction == 3 && y != getRow() - 1 && board[y + 1][x] != 0) {
            board[y][x] = board[y + 1][x];
            board[y + 1][x] = -1;

            y += 1;
            Log.d("MOVE","Moved Down");
            totalMoves++;
            return true;
        }

        else {
            Log.d("MOVE","Wrong Move");
            return false;
        }
    }

    boolean isSolved() {
        int check = 0;
        int checkCounter = 1;

        for(int i = 0; i < getRow(); i++) {
            for(int j = 0; j < getColumn(); j++) {
                if(board[i][j] == checkCounter) {
                    check++;
                    checkCounter++;
                }

                else if(board[i][j] == 0)
                {
                    //empty
                }

                else
                    break;

                if(check == (getRow() * getColumn()) - 1)
                    return true;
            }
        }

        return false;
    }

    void shuffle() {
        int moveCounter = (int)(Math.random() * ((getRow() * getColumn() - getRow()) + 1)) + getRow();
        int move;

        for(int i = 0; i < moveCounter; i++) {
            move = (int) (Math.random() * 4);
            if(move(move))
                totalMoves--;
        }

        if(isSolved()) {
            shuffle();
        }
    }
}
