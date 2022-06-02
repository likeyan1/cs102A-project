package view;

import controller.BGMusic;
import controller.GameController;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 这个类表示游戏过程中的整个游戏界面，是一切的载体
 */
public class ChessGameFrame extends JFrame {
    //    public final Dimension FRAME_SIZE ;
    private final int WIDTH;
    private final int HEIGTH;
    public final int CHESSBOARD_SIZE;
    private GameController gameController;

    private JLabel roundLabel;
    private JLabel loginLbl;
    private String loginName = "default";
    private String lastfile ;
    private File bgmFile;
    private BufferedImage bgImg;
    private boolean paintBG = true;
    private BGMusic bgmPlayer;
    private JFrame home;

    public ChessGameFrame(int width, int height, String username, JFrame home) {
        setTitle("2022 CS102A Project Demo"); //设置标题
        this.WIDTH = width;
        this.HEIGTH = height;
        this.CHESSBOARD_SIZE = HEIGTH * 4 / 5;
        this.home = home;

        loginName = username;

        setSize(WIDTH, HEIGTH);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);
        setResizable(false);

        if(bgImg == null){
            try {
                bgImg = ImageIO.read(new File("images/bk.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        initGUI();
        lastfile = new String("./data/newGame.txt");
        gameController.loadGameFromFile("./data/newGame.txt");

        addWindowListener(new myWinListener());
        BGMusic_play();
    }

    public void BGMusic_play()
    {
        //启动线程, 播放背景音乐
        File bgmFile = new File("waves/bgm0.wav");
        BGMusic bgmPlayer = new BGMusic();
        bgmPlayer.play(bgmFile,true);
    }

    public void BGMusic_stop()
    {
        bgmPlayer.close();
    }

         /**
         * 在游戏面板中添加棋盘
         */
    private void initGUI() {
        //init round label
        int dd = 12;
        int bW = 180;
        int bH = 60;
        int bSP = 85;


        loginLbl = new JLabel();
        loginLbl.setBackground(Color.orange);
        loginLbl.setLocation(HEIGTH, 30);
        loginLbl.setSize(bW, bH);
        loginLbl.setFont(new Font("Rockwell", Font.BOLD, 20));
        loginLbl.setText("login:" + loginName);
        add(loginLbl);

        roundLabel = new JLabel("1.while round");
        roundLabel.setBackground(Color.orange);
        roundLabel.setLocation(HEIGTH, HEIGTH / dd);
        roundLabel.setSize(bW, bH);
        roundLabel.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(roundLabel);


        //init chessboard
        Chessboard chessboard = new Chessboard(CHESSBOARD_SIZE, CHESSBOARD_SIZE);
        gameController = new GameController(chessboard);
        chessboard.setLocation(HEIGTH / dd, HEIGTH / dd );
        add(chessboard);

        chessboard.setRoundLabel(roundLabel);
        chessboard.setMainFrame(this);
        chessboard.setLastFile(lastfile);

        JButton button = new JButton("New game");
        button.addActionListener((e) -> {
//            JOptionPane.showMessageDialog(this, "Hello, world!")
            gameController.loadGameFromFile("./data/newGame.txt");
        });
        button.setLocation(HEIGTH, HEIGTH / dd + bSP);
        button.setSize(bW, bH);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);

        button = new JButton("Replay");
        button.addActionListener((e) -> {
            if (lastfile != null)
                gameController.loadGameFromFile(lastfile);
        });
        button.setLocation(HEIGTH, HEIGTH / dd + + bSP * 2);
        button.setSize(bW, bH);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);


        button = new JButton("Undo");
        button.addActionListener((e) -> {
            //OptionPane.showMessageDialog(this, "return last");
            gameController.undo();
        });
        button.setLocation(HEIGTH, HEIGTH / dd +  bSP * 3);
        button.setSize(bW, bH);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);


        button = new JButton("Load");
        button.setLocation(HEIGTH, HEIGTH / dd + bSP * 4);
        button.setSize(bW, bH);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);

        button.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser("./data");
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "game data", "txt");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(this);
            if(returnVal == JFileChooser.APPROVE_OPTION) {

                String sfile = chooser.getSelectedFile().getAbsolutePath() ;
                gameController.loadGameFromFile(sfile);
                lastfile = sfile;
                chessboard.setLastFile(lastfile);
                System.out.println("You chose to open this file: " +
                        sfile);
            }
        });
        button = new JButton("Save");
        button.setLocation(HEIGTH, HEIGTH / dd + bSP * 5);
        button.setSize(bW, bH);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);

        button.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser("./data");
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "game data", "txt");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showSaveDialog(this);
            if(returnVal == JFileChooser.APPROVE_OPTION) {

                String sfile = chooser.getSelectedFile().getAbsolutePath() ;
                gameController.saveGame(sfile);
                lastfile = sfile;
                System.out.println("You chose to open this file: " +   sfile);
            }
        });

        button = new JButton("Return");
        button.setLocation(HEIGTH, HEIGTH / dd + bSP * 6);
        button.setSize(bW, bH);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent a) {
                //bgmPlayer.close();
                SwingUtilities.invokeLater(() -> {
                    if (home == null)
                        home = new HomePage(WIDTH,HEIGTH);
                    home.setVisible(true);
                });
                setVisible(false);
            }
        });

    }
}
