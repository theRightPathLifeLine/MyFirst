package un.service;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.springframework.stereotype.Service;
import un.config.GlobalConfig;
import un.vo.Offset;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/12/18.
 */
@Service
public class ImageRecognitionServer {
    static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }

    private List<Point> mark;

    public Offset getoffset(String url){
        if(mark == null){
            GlobalConfig config = GlobalConfig.getInstance();
            mark = new ArrayList<>();
            Point p1 = new Point(Integer.parseInt(config.getProperty("p1.x")),Integer.parseInt(config.getProperty("p1.y")));
            Point p2 = new Point(Integer.parseInt(config.getProperty("p2.x")),Integer.parseInt(config.getProperty("p2.y")));
            mark.add(p1);
            mark.add(p2);
        }
        List<Point> points = getMarkPoint(url);
        Point left = points.get(0);
        Point right = points.get(1);
        Point markL = mark.get(0);
        Point markR = mark.get(1);
        Point p = new Point(left.x - markL.x,left.y + 1000 - markL.y);
        double t1 = Math.atan(Math.abs(right.y - left.y)/Math.abs(right.x - left.x + 500));
        double t2 = Math.atan(Math.abs(markR.y - markL.y)/Math.abs(markR.x - markL.x));
        double t = t1 - t2;
        t = t/Math.PI * 360;
        return new Offset(p,t);

    }

    private List<Point> getMarkPoint(String url){
        Mat src = Imgcodecs.imread(url);
        Mat left = new Mat(src,new Rect(0,1000,500,800));
        Mat right = new Mat(src,new Rect(500,1000,500,800));
        List<Point> points = new ArrayList<>();
        points.add(getPoint(left));
        points.add(getPoint(right));
        return points;
    }

    private Point getPoint(Mat mat){
        //转成灰度图
        Imgproc.cvtColor(mat, mat, Imgproc.COLOR_RGB2GRAY);
        Mat circles = new Mat();
        Imgproc.HoughCircles(mat, circles, Imgproc.HOUGH_GRADIENT, 1, 40, 300, 12, 16, 20);
        Point p = null;
        for (int i = 0; i < circles.cols(); i++){
            double[] vCircle = circles.get(0, i);
            Point point = new Point(vCircle[0], vCircle[1]);
            if(p == null){
                p = point;
            }else{
                if(point.y > p.y){
                    p = point;
                }
            }
        }
        return p;
    }

    public static void main(String[] args) {
        ImageRecognitionServer server = new ImageRecognitionServer();
        Offset offset = server.getoffset("E:\\20181217102958.jpg");
        System.out.println(offset);
    }


}
