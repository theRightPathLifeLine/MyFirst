package un.vo;

import org.opencv.core.Point;

/**
 * Created by admin on 2018/12/18.
 */
public class Offset {

    private Point point;

    private double angle;

    public Offset(Point point, double angle) {
        this.point = point;
        this.angle = angle;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    @Override
    public String toString() {
        return "Offset{" +
                "point=" + point +
                ", angle=" + angle +
                '}';
    }
}
