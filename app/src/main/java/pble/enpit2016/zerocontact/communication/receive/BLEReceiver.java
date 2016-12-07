package pble.enpit2016.zerocontact.communication.receive;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.util.Log;

import static android.bluetooth.BluetoothDevice.ACTION_FOUND;

/**
 * BLEの受信を操作するクラス
 * Created by kyokn on 2016/11/01.
 */

public class BLEReceiver implements BluetoothAdapter.LeScanCallback {

    private boolean isScanning;

    private BluetoothAdapter adapter;

    private final IBeaconCallback callback;

    public BLEReceiver(IBeaconCallback callback, Context context) {
        this.callback = callback;
        BluetoothManager manager = (BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);
        this.adapter = manager.getAdapter();
    }

    public interface IBeaconCallback {
        void execute(String id, int rssi);
    }

    public void startLeScan() {
        if (adapter != null && !isScanning) {
            adapter.startLeScan(this);
        }
        isScanning = true;
    }

    public void stopLeScan() {
        if (adapter != null) {
            adapter.stopLeScan(this);
        }
        isScanning = false;
    }

    private String scanRecordToMajor(byte[] newScanRecord) {
        return String.valueOf(newScanRecord[25] & 0xff) + String.valueOf(newScanRecord[26] & 0xff);
    }

    private String scanRecordToMinor(byte[] newScanRecord) {
        return String.valueOf(newScanRecord[27] & 0xff) + String.valueOf(newScanRecord[28] & 0xff);

    }

    private boolean isIbeacon(byte[] newScanRecord) {
        String str = "0221";
        return str.equals(String.valueOf(newScanRecord[6] & 0xff) + String.valueOf(newScanRecord[7] & 0xff) + String.valueOf(newScanRecord[8] & 0xff));
    }

    private String scanRecordToUuid(byte[] newScanRecord) {
        StringBuilder str = new StringBuilder();
        for (int i = 9; i <= 24; i++) {
            str.append(IntToHex(newScanRecord[i] & 0xff));
        }
        return str.toString();
    }

    private String IntToHex(int i) {
        char hex[] = {Character.forDigit((i >> 4) & 0x0f, 10), Character.forDigit(i & 0x0f, 10)};
        String hex_str = new String(hex);
        return hex_str.toUpperCase();
    }

    private boolean isTargetUuid(byte[] newScanRecord) {
        return true;
    }

    @Override
    public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
        if (!isIbeacon(scanRecord)) return;
        if (scanRecordToUuid(scanRecord).equals("44444444444444444444444444444444")) {
            this.callback.execute(toIntegerStr("4"), rssi);
            return;
        } else if (scanRecordToUuid(scanRecord).equals("55555555555555555555555555555555")) {
            this.callback.execute(toIntegerStr("5"), rssi);
            return;
        } else if (scanRecordToUuid(scanRecord).equals("66666666666666666666666666666666")) {
            this.callback.execute(toIntegerStr("6"), rssi);
            return;
        }


        this.callback.execute(toIntegerStr(scanRecordToMinor(scanRecord)), rssi);
    }

    private String toIntegerStr(String value) {
        return String.valueOf(Integer.parseInt(value));
    }
}
