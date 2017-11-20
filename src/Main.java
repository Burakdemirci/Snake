
import game.GameControl;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author Burak DEMİRCİ
 *  141044091
 *  burakdemirci@gtu.edu.tr
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame jf = new JFrame();
        //jf.setContentPane(new GameControl());
        GameControl gc = new GameControl();
        jf.setContentPane(gc);
        // Head Setting *******************************
        jf.setTitle("İŞSİZ YILAN");
        ImageIcon im = new ImageIcon("src/icon.png");
        jf.setIconImage(im.getImage());
        //*********************************************
        // Size settings *********       
        jf.setBounds(0,0,525,580);
        jf.setLocation(200, 200);
        jf.setResizable(false);

        //***********************
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }

}
