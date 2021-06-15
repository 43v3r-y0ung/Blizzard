import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Something extends WindowProgram {
    private static final int SNOW_COUNT = 250;

    ArrayList<Snowflake> snow = new ArrayList<>();
    RandomGenerator rnd = new RandomGenerator();

    public void run() {
        getMenuBar().setVisible(false);
        setBackground(Color.BLACK);
        addMouseListeners();
        makeItSnow();
    }

    private void makeItSnow() {
        createSnow();
        while(true){
            for (Snowflake snowflake : snow) {
                double dX = (snowflake.amplitude * Math.sin(snowflake.phase)) / snowflake.dY;
                snowflake.move(dX, snowflake.dY);
                snowflake.polygon.rotate(snowflake.rotation);
                snowflake.phase += 0.1;
                if (snowflake.phase >= 360) {
                    snowflake.phase = 0;
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

        for (int i = 0; i < SNOW_COUNT; i++){
            Snowflake snowflake = new Snowflake();
            snow.add(snowflake);
            add(snowflake, rnd.nextDouble(0 , getWidth()), rnd.nextDouble(0 , getHeight()));
        }
    }

    public void mouseClicked(MouseEvent e){
        for (Snowflake snowflake : snow) {
            snowflake.setColor(rnd.nextColor());
            System.out.println("Color changed");
        }
    }

}
