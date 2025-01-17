import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;
import java.awt.*;
import java.awt.event.MouseEvent;

public class Blizzard extends WindowProgram {
    private static final int SNOW_COUNT = 250;

    Snowflake[] snow = new Snowflake[SNOW_COUNT];
    RandomGenerator rnd = RandomGenerator.getInstance();
    private double wind = 0;

    public double getWind() {
        return wind;
    }

    public void setWind(double wind) {
        this.wind = wind;
    }

    public void run() {
        setBackground(Color.BLACK);
        addMouseListeners();
        makeItSnow();
    }

    private void makeItSnow() {
        createSnow();
        while (true) {
            for (Snowflake snowflake : snow) {
                double dX = (snowflake.getAmplitude() * Math.sin(snowflake.getPhase())) / snowflake.getdY();
                snowflake.move(dX + getWind(), snowflake.getdY());
                snowflake.rotate(snowflake.getRotation());
                snowflake.incrementPhase(0.1);
                if (snowflake.getPhase() >= 360) {
                    snowflake.setPhase(0);
                }
                if (snowflake.getY() >= getHeight()) {
                    snowflake.setLocation(rnd.nextDouble(0, getWidth()), 0);
                    snowflake.reset();
                }
                if (snowflake.getX() < 0) {
                    snowflake.setLocation(getWidth(), snowflake.getY());
                    snowflake.reset();
                }
                if (snowflake.getX() > getWidth()) {
                    snowflake.setLocation(0, snowflake.getY());
                    snowflake.reset();
                }
            }
            pause(1000.0 / 48);
        }
    }

    private void createSnow() {
        for (int i = 0; i < SNOW_COUNT; i++) {
            Snowflake snowflake = new Snowflake();
            snow[i] = snowflake;
            add(snowflake, rnd.nextDouble(0, getWidth()), rnd.nextDouble(0, getHeight()));
        }
    }

    public void mouseClicked(MouseEvent e) {
        for (Snowflake snowflake : snow) {
            snowflake.setColor(rnd.nextColor());
        }
    }

    public void mouseMoved(MouseEvent h) {
        double width = getWidth() / 2.0;
        if (h.getX() <= width) {
            setWind(-((width - h.getX()) / 100));
        } else {
            setWind((h.getX() - width) / 100);
        }
    }

}
