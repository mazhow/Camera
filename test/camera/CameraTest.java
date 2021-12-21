package camera;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class CameraTest {

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    private final Sensor sensor = context.mock(Sensor.class);
    private final MemoryCard memoryCard = context.mock(MemoryCard.class);
    Camera camera = new Camera(sensor, memoryCard);

    @Test
    public void switchingTheCameraOnPowersUpTheSensor() {

        context.checking(new Expectations() {{
            exactly(1).of(sensor).powerUp();
        }});

        camera.powerOn();

    }

    @Test
    public void switchingTheCameraOffPowersDownTheSensor() {

        context.checking(new Expectations() {{
            exactly(1).of(sensor).powerDown();
        }});

        camera.powerOff();
    }

    @Test
    public void pressingShutterWhenPowerIsOffDoesNothing() {

        context.checking(new Expectations() {{
            ignoring(sensor).powerDown();
        }});

        camera.powerOff();
        camera.pressShutter();
    }

    @Test
    public void pressingShutterWhenPowerIsOnCopiesDataFromSensorToMemoryCard() {

        byte[] data = new byte[10];

        context.checking(new Expectations() {{
            ignoring(sensor).powerUp();
            exactly(1).of(sensor).readData(); will(returnValue(data));
            exactly(1).of(memoryCard).write(data);
        }});

        camera.powerOn();
        camera.pressShutter();
    }
}
