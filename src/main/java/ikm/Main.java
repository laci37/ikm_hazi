package ikm;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;

class Main extends JFrame{

  public Main(){
    still = new ImagePanel(); 
    rt = new RTImagePanel();
    JButton btn = new JButton("Minta");

    btn.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        still.setImage(rt.getMat());
      }
    });

    still.setPreferredSize(new Dimension(640,480));
    System.out.println(still.getSize());
    setSize(new Dimension(1280,500));
    Container pane = getContentPane();
    pane.add(rt, BorderLayout.CENTER);
    pane.add(still, BorderLayout.LINE_END);
    pane.add(btn, BorderLayout.PAGE_END);
  }

  ImagePanel still;
  RTImagePanel rt;

  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      @Override
      public void run() {
        Main ex = new Main();
        ex.setVisible(true);
      }
    });
  }
}
