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
    setSize(new Dimension(640,480));
    initCamera();
    ActionListener timed = new ActionListener(){
      public void actionPerformed(ActionEvent e){
        displayImage(getImage());
      }
    };
    t = new Timer((int)(1.0/fps*1000),timed);
    t.start();
  }

  int fps = 30;
  Image img;
  Timer t;

  VideoCapture camera;
  public void initCamera(){
    System.out.println(System.getProperty("java.library.path"));
    System.loadLibrary("opencv_java2412");
    camera = new VideoCapture(0);
    camera.open(0);
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

  public Mat getImage(){
    Mat frame = new Mat();

    camera.read(frame);

    return frame;
  }

  public void displayImage(Mat mat){
    img = toBufferedImage(mat);
    repaint();
  }

  public void paint(Graphics g){
    super.paint(g);
    if(img!=null){
      g.drawImage(img, 0, 0, this);
    } else {
      System.out.println("no img");
    }
  }

  public Image toBufferedImage(Mat m){
    int type = BufferedImage.TYPE_BYTE_GRAY;
    if ( m.channels() > 1 ) {
      type = BufferedImage.TYPE_3BYTE_BGR;
    }
    int bufferSize = m.channels()*m.cols()*m.rows();
    byte [] b = new byte[bufferSize];
    m.get(0,0,b); // get all the pixels
    BufferedImage image = new BufferedImage(m.cols(),m.rows(), type);
    final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
    System.arraycopy(b, 0, targetPixels, 0, b.length);  
    return image;

  }
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
