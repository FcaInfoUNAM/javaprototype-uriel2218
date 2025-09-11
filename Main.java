public class Main {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        // Creating multiple similar objects is inefficient!
        GraphicElement button1 = new GraphicElement("Rectangle", "Blue", 10, 20);
        GraphicElement button2 = new GraphicElement("Rectangle", "Blue", 40, 60);
        // We want button2 to be just like button1 but at a different position.

        long endTime = System.currentTimeMillis();
        System.out.println("Time taken: " + (endTime - startTime) + " ms");
    }
}