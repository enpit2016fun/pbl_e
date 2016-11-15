package pble.enpit2016.zerocontact.communication;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import pble.enpit2016.zerocontact.communication.receive.BLEReceiver;
import pble.enpit2016.zerocontact.communication.receive.Signal;
import pble.enpit2016.zerocontact.communication.transmit.BLETransmitter;

/**
 * BLE周りのサービス
 * Created by kyokn on 2016/11/01.
 */

public class CommunicationService implements BLEReceiver.IBeaconCallback {

    private Map<String, Signal> userMap;
    private BLEReceiver receiver;
    private BLETransmitter transmitter;

    private ScheduledExecutorService schedule;

    public CommunicationService(Context context) {
        receiver = new BLEReceiver(this, context);
        transmitter = new BLETransmitter(context);
        schedule = Executors.newSingleThreadScheduledExecutor();
        userMap = new HashMap<>();
    }

    public void startReceive() {
        receiver.startLeScan();
        schedule.scheduleAtFixedRate(new Event(), 1000, 1000, TimeUnit.MILLISECONDS);
    }

    public void stopReceive() {
        receiver.stopLeScan();
    }

    public void startTransmit() {
        transmitter.startAdvertise();
    }

    public void stopTransmit() {
        transmitter.stopAdvertise();
    }

    @Override
    public void execute(String id, int rssi) {
        userMap.put(id, new Signal(rssi, System.currentTimeMillis()));
    }

    public Map<String, Signal> getUserMap() {
        return userMap;
    }

    private class Event implements Runnable {

        @Override
        public void run() {
            long timestamp = System.currentTimeMillis();
            ArrayList<String> delList = new ArrayList<>();
            for (Map.Entry<String, Signal> entry : userMap.entrySet()) {
                if (timestamp - entry.getValue().getReceivedTime() > 5000) {
                    delList.add(entry.getKey());
                }
            }
            for (String s : delList) {
                userMap.remove(s);
            }
        }
    }

}
