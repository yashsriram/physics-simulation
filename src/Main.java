import camera.LiamCam;
import linalg.Vec3;
import physical.SeriesSpringMassSystem;
import physical.SpringMass;
import processing.core.PApplet;

public class Main extends PApplet {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;

    private LiamCam liamCam;

    private SeriesSpringMassSystem seriesSpringMassSystem;

    public void settings() {
        size(WIDTH, HEIGHT, P3D);
    }

    public void setup() {
        noStroke();
        surface.setTitle("Processing");
        liamCam = new LiamCam(this);

        seriesSpringMassSystem = new SeriesSpringMassSystem(this);
        seriesSpringMassSystem.addMass(new SpringMass(this, 5, Vec3.of(5, -80, -200), Vec3.zero(), Vec3.zero(), true));
        seriesSpringMassSystem.addMass(new SpringMass(this, 5, Vec3.of(6, -50, -200), Vec3.zero(), Vec3.zero(), false));
        seriesSpringMassSystem.addMass(new SpringMass(this, 5, Vec3.of(7, -10, -200), Vec3.zero(), Vec3.zero(), false));
        seriesSpringMassSystem.addMass(new SpringMass(this, 5, Vec3.of(8, 20, -200), Vec3.zero(), Vec3.zero(), false));
    }

    public void draw() {
        liamCam.Update(1.0f / frameRate);

        try {
            seriesSpringMassSystem.update(0.05f);
        } catch (Exception e) {
            e.printStackTrace();
        }

        background(0);
        seriesSpringMassSystem.draw();
    }

    public void keyPressed() {
        liamCam.HandleKeyPressed();
    }

    public void keyReleased() {
        liamCam.HandleKeyReleased();
    }

    static public void main(String[] passedArgs) {
        String[] appletArgs = new String[]{"Main"};
        if (passedArgs != null) {
            PApplet.main(concat(appletArgs, passedArgs));
        } else {
            PApplet.main(appletArgs);
        }
    }
}
