import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    @Test
    public void testDistance() {
        Point p1 = new Point(7,4);
        Point p2 = new Point(5,2);
        Assert.assertEquals(p1.distance(p2), 2.8284271247461903);
    }
    @Test
    public void testDistance2() {
        Point p1 = new Point(2,3);
        Point p2 = new Point(3,5);
        Assert.assertEquals(p1.distance(p2), 2.23606797749979);
    }
    @Test
    public void testDistance3() {
        Point p1 = new Point(4,5);
        Point p2 = new Point(10,11);
        Assert.assertEquals(p1.distance(p2), 8.48528137423857);
    }
    @Test
    public void testDistance4() {
        Point p1 = new Point(125,75);
        Point p2 = new Point(222,99);
        Assert.assertEquals(p1.distance(p2), 99.92497185388645);
    }
}
