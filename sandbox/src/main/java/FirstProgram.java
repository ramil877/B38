public class FirstProgram {

  public static void main(String[] args) {
    System.out.println("Hello world!");

    Point p1 = new Point(7,4);
    Point p2 = new Point(5,2);
    System.out.println("Расстояние между двумя точками = " + p1.distance(p2));
  }
}