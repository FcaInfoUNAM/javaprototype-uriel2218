package prototype;

public class GraphicElement {
    private String shape;
    private String color;
    private int x, y;

    // Expensive constructor (simulated with a Thread.sleep)
    public GraphicElement(String shape, String color, int x, int y) {
        this.shape = shape;
        this.color = color;
        this.x = x;
        this.y = y;
        try {
            // Simulate expensive setup (e.g., loading resources, complex calculations)
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Expensive GraphicElement constructor called");
    }
     @Override
    public String toString() {
        return String.format("GraphicElement [shape=%s, color=%s, x=%d, y=%d]", shape, color, x, y);
    }
}

    // ... getters and setters for the fields ...