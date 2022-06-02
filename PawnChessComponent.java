package model;

import controller.ClickController;
import view.ChessboardPoint;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PawnChessComponent extends ChessComponent {
    private static Image IMG_WHITE;
    private static Image IMG_BLACK;

    public void loadResource() throws IOException {
        if (IMG_WHITE == null) {
            IMG_WHITE = ImageIO.read(new File("./images/pawn-white.png"));
        }

        if (IMG_BLACK == null) {
            IMG_BLACK = ImageIO.read(new File("./images/pawn-black.png"));
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

    public PawnChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size) {
        super(chessboardPoint, location, color, listener, size);
        initiateImage(color);
    }

    public boolean peat(List<ChessboardPoint> steps, ChessComponent chess, int x, int y) {
        if (x < 0 || y < 0 || x >= 8 || y >= 8)
            return true;
        boolean stop = false;
        ChessComponent c = chessboard[x][y];
        if (c.getChessColor() != chess.getChessColor() && !(c instanceof EmptySlotComponent)) {
            steps.add(c.getChessboardPoint());
            stop = true;
        }
        return stop;
    }

    public boolean pmove(List<ChessboardPoint> steps, ChessComponent chess, int x, int y) {
        if (x < 0 || y < 0 || x >= 8 || y >= 8)
            return true;
        boolean stop = false;
        ChessComponent c = chessboard[x][y];
        if (c instanceof EmptySlotComponent) {
            steps.add(c.getChessboardPoint());
        }
        return stop;
    }
    public List<ChessboardPoint> getCanMovePoints(){
        ArrayList steps = new ArrayList<>();
        int X = getChessboardPoint().getX();
        int Y = getChessboardPoint().getY();
        ChessComponent chess = chessboard[X][Y];
        if(chess.getChessColor() == ChessColor.WHITE){
            if (chess.getChessboardPoint().getX() == 6) {
                int x = 4;
                int y = Y;
                ChessComponent  c  =   chessboard[5][y];
                if (c instanceof  EmptySlotComponent)
                    pmove(steps, chess, x, y);
            }
            int x = X - 1;
            int y = Y;
            pmove(steps, chess, x, y);

            x = X - 1;
            y = Y - 1;
            peat(steps,chess,x,y);
            x = X - 1;
            y = Y + 1;
            peat(steps,chess,x,y);
        }
        if(chess.getChessColor() == ChessColor.BLACK){
            if (chess.getChessboardPoint().getX() == 1) {
                int x = 3;
                int y = Y;
                ChessComponent  c  =   chessboard[2][y];
                if (c instanceof  EmptySlotComponent)
                    pmove(steps, chess, x, y);
            }
            int x = X + 1;
            int y = Y;
            pmove(steps, chess, x, y);

            x = X + 1;
            y = Y + 1;
            peat(steps,chess,x,y);
            x = X + 1;
            y = Y - 1;
            peat(steps,chess,x,y);
        }
        return steps;
    };
}
