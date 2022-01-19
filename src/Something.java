import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Something extends WindowProgram {
    private static final int SNOW_COUNT = 250;

    ArrayList<Snowflake> snow = new ArrayList<>();
    RandomGenerator rnd = new RandomGenerator();
    private double wind = 0;

    public double getWind() {
        return wind;
    }

    public void setWind(double wind) {
        this.wind = wind;
    }

    public void run() {
        getMenuBar().setVisible(false);
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
                snowflake.getPolygon().rotate(snowflake.getRotation());
                snowflake.setPhase(snowflake.getPhase() + 0.1);
                if (snowflake.getPhase() >= 360) {
                    snowflake.setPhase(0);
                }
                if (snowflake.getY() >= getHeight()) {
                    snowflake.setLocation(rnd.nextDouble(0, getWidth()), 0);
                    snowflake.reset();
                }
            }
            pause(1000.0 / 48);
        }
    }

    private void createSnow() {

        for (int i = 0; i < SNOW_COUNT; i++) {
            Snowflake snowflake = new Snowflake();
            snow.add(snowflake);
            add(snowflake, rnd.nextDouble(0, getWidth()), rnd.nextDouble(0, getHeight()));
        }
    }

    public void mouseClicked(MouseEvent e) {
        for (Snowflake snowflake : snow) {
            snowflake.getPolygon().setColor(rnd.nextColor());
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

    public void mouseExited(MouseEvent e) {
        setWind(0);
    }
}
