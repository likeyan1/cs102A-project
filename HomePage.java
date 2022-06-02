package view;

import controller.GameController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class HomePage extends JFrame {
    private int WIDTH;
    private int HEIGH;

    JButton rankBtn;
    JButton enterBtn;
    JButton exitBtn;
    JButton setNameBtn;

    JLabel chessLbl;

    JFrame home;
    JFrame gameGUI;

    private String userName;

    public HomePage(int a, int b) {
        WIDTH = a;
        HEIGH = b;
        userName = new String("default");
        setTitle("Chess game"); //设置标题
        setSize(WIDTH, HEIGH);

        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了

        addExitBtn();
        addEnterBtn();
        addRankBtn();
        addUserBtn();
        addchessLbl();

        home = this;

        Box hb = Box.createHorizontalBox();
        Box vb = Box.createVerticalBox();
        this.add(hb);
        hb.add(Box.createVerticalStrut(100));  //添加高度为100的垂直框架
        hb.add(chessLbl);
        hb.add(Box.createHorizontalStrut(100));   //添加长度为100的水平框架
        hb.add(enterBtn);
        hb.add(Box.createHorizontalGlue());    //添加水平胶水
        hb.add(vb);
        //添加宽度为100，高度为20的固定区域
        vb.add(Box.createRigidArea(new Dimension(100, 20)));
        vb.add(rankBtn);
        vb.add(Box.createVerticalGlue());
        vb.add(setNameBtn);
        vb.add(Box.createVerticalGlue());
        vb.add(exitBtn);
        vb.add(Box.createVerticalStrut(100));    //添加长度为100的垂直框架

        setVisible(true);

    }

    private void addRankBtn() {
        rankBtn = new JButton("Ranking");
        rankBtn.setContentAreaFilled(false);
        rankBtn.setBorder(null);
        rankBtn.setFont(new Font("Rockwell", Font.BOLD, 40));//退出
        rankBtn.addActionListener(new ActionListener() {//click
                                      @Override
                                      public void actionPerformed(ActionEvent e) {
                                          Object[] options = {"Character Rating:" + "-100%", "B-50%", "C-0", "D-0"};
                                          String sss = (String) JOptionPane.showInputDialog(null, "Choose an item to return", "This is title",
                                                  JOptionPane.PLAIN_MESSAGE, null, options, options[1]);
                                          System.out.printf("selected item = %s\n", sss);
                                      }
                                  }
        );
    }

    private void addExitBtn() {
        exitBtn = new JButton("Exit");
        exitBtn.setContentAreaFilled(false);
        exitBtn.setBorder(null);
        exitBtn.setFont(new Font("Rockwell", Font.BOLD, 40));//退出
        exitBtn.addMouseListener(new MouseAdapter() {

                                     @Override
                                     public void mouseClicked(MouseEvent a) {
                                         System.exit(0);
                                     }
                                 }
        );
    }

    private void addEnterBtn() {
        enterBtn = new JButton("Enter Game");
        enterBtn.setContentAreaFilled(false);
        enterBtn.setBorder(null);
        enterBtn.setFont(new Font("Rockwell", Font.BOLD, 40));//退出
        enterBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent a) {
                SwingUtilities.invokeLater(() -> {
                    System.out.printf("login user:%s\n", userName);
                    gameGUI = new ChessGameFrame(WIDTH, HEIGH, userName, home);
                    gameGUI.setVisible(true);
                });
                setVisible(false);
            }
        });
    }

    private void setUserName()
    {
        userName = JOptionPane.showInputDialog(this, "Set Your Name");
    }

    private void addUserBtn() {
        setNameBtn = new JButton("Set Character");
        setNameBtn.setContentAreaFilled(false);
        setNameBtn.setBorder(null);
        setNameBtn.setFont(new Font("Rockwell", Font.BOLD, 40));//退出
        setNameBtn.addActionListener(e -> {
            setUserName();
        });
    }

    void addchessLbl() {
        ImageIcon icon = null;
        icon = new ImageIcon("images/king-black.png");
        chessLbl = new JLabel(icon);
        chessLbl.setBounds(0, 0, WIDTH, HEIGH);
    }

}

