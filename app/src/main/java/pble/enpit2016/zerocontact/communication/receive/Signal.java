package pble.enpit2016.zerocontact.communication.receive;

/**
 * Created by kyokn on 2016/11/07.
 */

public class Signal {

    private int rssi;
    private Long receivedTime;

    public Signal(int rssi, Long receivedTime) {
        this.rssi = rssi;
        this.receivedTime = receivedTime;
    }

    public int getRssi() {
        return rssi;
    }

    public Long getReceivedTime() {
        return receivedTime;
    }
}
