package camera;

public class Camera implements WriteListener{

    private Sensor sensor;
    private MemoryCard memoryCard;
    private byte[] data;
    private boolean cameraOn;
    private boolean isWriting;

    public Camera(Sensor sensor, MemoryCard memoryCard, byte[] data) {
        this.sensor = sensor;
        this.memoryCard = memoryCard;
        this.data = data;
        this.cameraOn = false;
    }

    public void pressShutter() {
        if (cameraOn) {
            isWriting = true;
            memoryCard.write(data);
        }
    }

    public void powerOn() {
        cameraOn = true;
        sensor.powerUp();
    }

    public void powerOff() {
        if (!isWriting) {
            sensor.powerDown();
        }
        cameraOn = false;
    }

    @Override
    public void writeComplete() {
        isWriting = false;
        sensor.powerDown();
    }
}

