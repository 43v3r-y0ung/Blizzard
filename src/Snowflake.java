import acm.graphics.GPolygon;
import acm.util.RandomGenerator;

class Snowflake extends GPolygon {
    RandomGenerator rnd = RandomGenerator.getInstance();
    private double dY;
    private double phase;
    private double amplitude;
    private double rotation;

    public Snowflake() {
        super();
        double polyRadius = rnd.nextDouble(4, 15);
        double beams = rnd.nextInt(5, 12);
        double beamsAngle = 0;
        double beamsAngleIncrement = 360.0 / beams;
        addVertex(0, 0);
        for (int i = 0; i < beams; i++) {
            addPolarEdge(polyRadius, beamsAngle);
            addPolarEdge(polyRadius, beamsAngle + 180);
            beamsAngle += beamsAngleIncrement;
        }
        reset();
    }

    private double probablyNegative(double x) {
        return rnd.nextBoolean() ? x : -x;
    }

    public void reset() {
        setdY(rnd.nextDouble(1, 3));
        setAmplitude(probablyNegative(rnd.nextDouble(0.5, 5)));
        setPhase(rnd.nextDouble(0, 360.0));
        setRotation(probablyNegative(rnd.nextDouble(0.5, 5)));
        setColor(rnd.nextColor());
    }

    public double getdY() {
        return dY;
    }

    public void setdY(double dY) {
        this.dY = dY;
    }

    public double getPhase() {
        return phase;
    }

    public void setPhase(double phase) {
        this.phase = phase;
    }

    public double getAmplitude() {
        return amplitude;
    }

    public void setAmplitude(double amplitude) {
        this.amplitude = amplitude;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public void incrementPhase(double increment) {
        setPhase(getPhase() + increment);
    }
}
