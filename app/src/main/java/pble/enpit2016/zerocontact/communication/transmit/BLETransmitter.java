package pble.enpit2016.zerocontact.communication.transmit;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.AdvertiseCallback;
import android.bluetooth.le.AdvertiseData;
import android.bluetooth.le.AdvertiseSettings;
import android.bluetooth.le.BluetoothLeAdvertiser;
import android.content.Context;
import android.os.ParcelUuid;
import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.UUID;

/**
 * BLEのアドバタイズメントを操作するクラス
 * Created by kyokn on 2016/11/02.
 */

public class BLETransmitter extends AdvertiseCallback {

    private BluetoothManager manager;
    private BluetoothAdapter adapter;
    private BluetoothLeAdvertiser advertiser;

    public BLETransmitter(Context context) {
        manager = (BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);
        adapter = manager.getAdapter();
        advertiser = adapter.getBluetoothLeAdvertiser();
    }

    public void startAdvertise() {
        advertiser.startAdvertising(advertiseSettings(), advertiseData(), this);
    }

    public void stopAdvertise() {

    }

    @Override
    public void onStartSuccess(AdvertiseSettings settingsInEffect) {
        super.onStartSuccess(settingsInEffect);
        Log.d("mytag", "success");
    }

    @Override
    public void onStartFailure(int errorCode) {
        super.onStartFailure(errorCode);
        Log.d("mytag", "failer");
    }

    private AdvertiseSettings advertiseSettings() {
        AdvertiseSettings.Builder settingBuilder = new AdvertiseSettings.Builder();
        settingBuilder.setAdvertiseMode(AdvertiseSettings.ADVERTISE_MODE_LOW_POWER);
        settingBuilder.setConnectable(false);
        settingBuilder.setTimeout(0);
        settingBuilder.setTxPowerLevel(AdvertiseSettings.ADVERTISE_TX_POWER_HIGH);
        return settingBuilder.build();
    }

    private AdvertiseData advertiseData() {
        final byte[] manufacturerData = new byte[23];
        ByteBuffer byteBuffer = ByteBuffer.wrap(manufacturerData);
        byteBuffer.order(ByteOrder.BIG_ENDIAN);
        // iBeacon固定値(8バイト目)
        byteBuffer.put((byte) 0x02);
        // iBeaconのデータバイト数(9バイト目)
        byteBuffer.put((byte) 0x15);

        // UUID（10―25バイト目）
        final UUID uuid = UUID.fromString("12345678-3234-1234-1234-123456789012");
        // 上位64ビットを追加
        byteBuffer.putLong(uuid.getMostSignificantBits());
        // 下位64ビットを追加
        byteBuffer.putLong(uuid.getLeastSignificantBits());

        // major(26-27バイト目)
        byteBuffer.putShort((short) 0x0A);
        // minor(28-29バイト目)
        byteBuffer.putShort((short) 0x1F);
        // 電波強度を表す2の補数(30バイト目)
        byteBuffer.put((byte) 0x99);

        // 会社コード(6-7バイト目)
        final int appleManufactureId = 0x004C;

        AdvertiseData.Builder dataBuilder = new AdvertiseData.Builder();
        dataBuilder.addManufacturerData(appleManufactureId, manufacturerData);

        return dataBuilder.build();
    }


}
