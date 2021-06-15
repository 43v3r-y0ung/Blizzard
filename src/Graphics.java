import acm.graphics.GCompound;
import acm.graphics.GPolygon;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.util.ArrayList;

public class Graphics extends WindowProgram {
    ArrayList<Obj> objects = new ArrayList<Obj>();
    RandomGenerator rnd = new RandomGenerator();

    public void run() {
        getMenuBar().setVisible(false);
        setBackground(Color.BLACK);

        for (int i = 0; i < 100; i++){
            Obj object = new Obj();
            objects.add(object);
            add(object, rnd.nextDouble(0 , getWidth()), rnd.nextDouble(0 , getHeight()));
        }
        while (true){
            makeItFly();
            pause(1000.0 / 48);
        }

    }
    private void makeItFly(){

        for (Obj object : objects) {
            object.move(object.dX, object.dY);

            if (object.getX() <= 0 || object.getX() >= getWidth()) {
                object.dX = -object.dX;
            }
            if (object.getY() <= 0 || object.getY() >= getHeight()) {
                object.dY = -object.dY;
            }
        }
    }
    public class Obj extends GCompound {

        public int dX;
        public int dY;



        public Obj() {
            GPolygon poly = createHexagon(rnd.nextInt(5, 20));
            add(poly);
            int dX = probablyNegative(rnd.nextInt(1, 3));
            int dY = probablyNegative(rnd.nextInt(1,3));
            int rotation = probablyNegative(rnd.nextInt(1,5));
            this.dX = dX;
            this.dY = dY;

        }

        public int dX(){
            return dX;
        }

        public int dY(){
            return dY;
        }

        private int probablyNegative (int x){
            if (rnd.nextBoolean()){
                return x;
            }
            else{
                return -x;
            }
        }


        private GPolygon createHexagon(int side) {
            GPolygon hex = new GPolygon();
            hex.addVertex(-side, 0);
            int angle = 60;
            for (int i = 0; i < 6; i++) {
                hex.addPolarEdge(side, angle);
                angle -= 60;
            }
            hex.setFilled(true);
            hex.setColor(rnd.nextColor());
            return hex;
        }

    }

}
