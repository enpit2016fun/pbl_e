package pble.enpit2016.zerocontact.communication;

import android.content.Context;
import android.util.Log;

import pble.enpit2016.zerocontact.communication.receive.BLEReceiver;
import pble.enpit2016.zerocontact.communication.transmit.BLETransmitter;

/**
 * BLE周りのサービス
 * Created by kyokn on 2016/11/01.
 */

public class CommunicationService implements BLEReceiver.IBeaconCallback {

    private BLEReceiver receiver;
    private BLETransmitter transamitter;

    public CommunicationService(Context context) {
        receiver = new BLEReceiver(this, context);
        transamitter = new BLETransmitter(context);
    }

    public void startReceive() {
        receiver.startLescan();
    }

    public void stopReceive() {
        receiver.stopLeScan();
    }

    public void startTransmit() {
        transamitter.startAdvertise();
    }

    public void stopTransmit() {
        transamitter.stopAdvertise();
    }

    @Override
    public void execute(String id, int rssi) {
        Log.d("mytag", id + rssi);
    }
}
