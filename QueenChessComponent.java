package model;

import controller.ClickController;
import view.ChessboardPoint;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QueenChessComponent extends ChessComponent {
    private static Image IMG_WHITE;
    private static Image IMG_BLACK;

    public void loadResource() throws IOException {
        if (IMG_WHITE == null) {
            IMG_WHITE = ImageIO.read(new File("./images/queen-white.png"));
        }

        if (IMG_BLACK == null) {
            IMG_BLACK = ImageIO.read(new File("./images/queen-black.png"));
        }
    }

    private void initiateImage(ChessColor color) {
        try {
            loadResource();
            if (color == ChessColor.WHITE) {
                chessImage = IMG_WHITE;
            } else if (color == ChessColor.BLACK) {
                chessImage = IMG_BLACK;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public QueenChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size) {
        super(chessboardPoint, location, color, listener, size);
        initiateImage(color);
    }

    public List<ChessboardPoint> getCanMovePoints(){
        ArrayList steps = new ArrayList<>();
        int X = getChessboardPoint().getX();
        int Y = getChessboardPoint().getY();
        ChessComponent chess = chessboard[X][Y];
        for (int i = 1; i < 8 - X; i++) {  //right
            int x = X + i;
            int y = Y;
            if (add(steps, chess, x, y)) {
                break;
            }
        }
        for (int i = 1; i < X + 1; i++) {  //left
            int x = X - i;
            int y = Y;
            if (add(steps, chess, x, y)) {
                break;
            }
        }
        for (int i = 1; i < Y + 1; i++) {  //up
            int x = X;
            int y = Y - i;
            if (add(steps, chess, x, y)) {
                break;
            }
        }
        for (int i = 1; i < 8 - Y; i++) {  //down
            int x = X;
            int y = Y + i;
            if (add(steps, chess, x, y)) {
                break;
            }
        }
        for (int i = 1; i < 8; i++) {  //right&down
            int x = X + i;
            int y = Y + i;
            if (add(steps, chess, x, y)) {
                break;
            }
        }
        for (int i = 1; i < 8; i++) {  //left&down
            int x = X - i;
            int y = Y + i;
            if (add(steps, chess, x, y)) {
                break;
            }
        }
        for (int i = 1; i < 8; i++) {  //left&up
            int x = X - i;
            int y = Y - i;
            if (add(steps, chess, x, y)) {
                break;
            }
        }
        for (int i = 1; i < 8; i++) {  //right&up
            int x = X + i;
            int y = Y - i;
            if (add(steps, chess, x, y)) {
                break;
            }

        }
        return steps;
    };

}
