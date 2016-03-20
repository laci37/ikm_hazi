package ikm;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;

class RTImagePanel extends ImagePanel{
  public RTImagePanel(){
    setSize(new Dimension(640,480));
    initCamera();
    ActionListener timed = new ActionListener(){
      public void actionPerformed(ActionEvent e){
        setImage(getCameraImage());
      }
    };
    t = new Timer((int)(1.0/fps*1000),timed);
    t.start();
  }

  int fps = 30;
  Timer t;
  VideoCapture camera;
  
  public void initCamera(){
    System.loadLibrary("opencv_java2412");
    camera = new VideoCapture(0);
    camera.open(0); //Useless
    try{
    Thread.sleep(1000);
    } catch(Exception e) {
    }
    if(!camera.isOpened()){
      System.out.println("Camera Error");
    }
    else{
      System.out.println("Camera OK?");
    }
  }

  public Mat getCameraImage(){
    Mat frame = new Mat();
    camera.read(frame);
    return frame;
  }
}
