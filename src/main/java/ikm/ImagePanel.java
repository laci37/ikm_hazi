package ikm;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;

class ImagePanel extends JPanel{

  Image img;
  Mat mimg;
  
  public void setImage(Mat mat){
    mimg = mat;
    img = toBufferedImage(mat);
    repaint();
  }

  public Image getImage(){
    return img;
  }

  public Mat getMat(){
    return mimg;
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
}
