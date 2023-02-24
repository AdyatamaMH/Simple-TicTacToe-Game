package assignment;

public class Assign extends Shape {
    public int width, height;

    public Assign(int width, int height, String color) {
        super(color);
        this.width = width;
        this.height = height;
    }

    @Override
    public int getArea() {
        return width * height;
    }

    public static void main(String[] args) {
     Assign A1 = new Assign(10, 6, "Gay V.2");
     System.out.printf("The area of this %s rectangle is: %d\n", A1.color, A1.getArea());
    }
}
