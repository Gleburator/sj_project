package mainprogram;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class MainFrame extends JFrame {
    Toolkit toolkit =Toolkit.getDefaultToolkit();
    MainFrame() {
        Image cursor=new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Cursor newcursor= toolkit.createCustomCursor(
                cursor, new Point(0,0 ), "cursor"
        );
        MainPanel panel=new MainPanel();
        this.setTitle("MainProgram");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.add(panel);
        this.pack();
        this.setCursor(newcursor);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
