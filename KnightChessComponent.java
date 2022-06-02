package model;

import controller.ClickController;
import view.ChessboardPoint;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class KnightChessComponent extends ChessComponent {
    private static Image IMG_WHITE;
    private static Image IMG_BLACK;

    public void loadResource() throws IOException {
        if (IMG_WHITE == null) {
            IMG_WHITE = ImageIO.read(new File("./images/knight-white.png"));
        }

        if (IMG_BLACK == null) {
            IMG_BLACK = ImageIO.read(new File("./images/knight-black.png"));
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

    public KnightChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size) {
        super(chessboardPoint, location, color, listener, size);
        initiateImage(color);
    }

    public List<ChessboardPoint> getCanMovePoints(){
        ArrayList steps = new ArrayList<>();
        int X = getChessboardPoint().getX();
        int Y = getChessboardPoint().getY();
        ChessComponent chess = chessboard[X][Y];
        int move[][] = {{1, 2}, {1, -2}, {2, 1}, {2, -1}, {-1, 2}, {-1, -2}, {-2, 1}, {-2, -1}};
        for (int i = 0; i < move.length; i++) {
            int x = X + move[i][0];
            int y = Y + move[i][1];
            add(steps, chess, x, y);
        }
        return steps;
    };
}
