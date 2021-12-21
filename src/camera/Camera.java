package camera;

public class Camera {

    private Sensor sensor;
    private MemoryCard memoryCard;
    private byte[] data;

    public Camera(Sensor sensor, MemoryCard memoryCard) {
        this.sensor = sensor;
        this.memoryCard = memoryCard;
    }

    public void pressShutter() {
        //sensor.readData();
        memoryCard.write(data);
    }

    public void powerOn() {
        sensor.powerUp();
    }

    public void powerOff() {
       sensor.powerDown();
    }
}

