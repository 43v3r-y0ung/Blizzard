import acm.graphics.GCompound;
import acm.graphics.GPolygon;
import acm.util.RandomGenerator;

class Snowflake extends GCompound {
    RandomGenerator rnd = new RandomGenerator();
    public int dY;
    public double phase;
    public double amplitude;
    public double rotation;
    public GPolygon polygon;


    public Snowflake() {
        GPolygon snowflake = makePolygon();
        add(snowflake);
        this.polygon = snowflake;
        reset();
    }

    private GPolygon makePolygon() {
        GPolygon poly = new GPolygon();
        double polyRadius = rnd.nextDouble(4, 15);
        double beams = rnd.nextInt(5, 12);
        double beamsAngle = 0;
        double beamsAngleIncrement = 360.0 / beams;

        poly.addVertex(0, 0);
        for (int i = 0; i < beams; i++) {
            poly.addPolarEdge(polyRadius, beamsAngle);
            poly.addPolarEdge(polyRadius, beamsAngle + 180);
            beamsAngle += beamsAngleIncrement;
        }
        poly.setColor(rnd.nextColor());
        return poly;
    }


    public int dY() {
        return dY;
    }

    public double phase() {
        return phase;
    }

    public double amplitude() {
        return amplitude;
    }

    private double probablyNegative(double x) {
        if (rnd.nextBoolean()) {
            return x;
        } else {
            return -x;
        }
    }

    public void reset() {
        dY = rnd.nextInt(1, 3);
        amplitude = probablyNegative(rnd.nextDouble(0.5, 5));
        phase = rnd.nextDouble(0,360.0);
        rotation = probablyNegative(rnd.nextDouble(0.5, 5));
        polygon.setColor(rnd.nextColor());
    }
}
