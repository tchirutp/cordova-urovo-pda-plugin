package ru.kservice.ksm.cordova;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;

public class UrovoBroadcastReceiver extends BroadcastReceiver {
    private SoundPool soundpool = null;
    private int soundid;
    private String barcodeStr;
    private boolean isScaning = false;
    private BarcodeListener listener;
    private static final String ACTION_DECODE = ScanManager.ACTION_DECODE;   // default action
    private static final String ACTION_DECODE_IMAGE_REQUEST = "action.scanner_capture_image";
    private static final String ACTION_CAPTURE_IMAGE = "scanner_capture_image_result";
    private static final String BARCODE_STRING_TAG = ScanManager.BARCODE_STRING_TAG;
    private static final String BARCODE_TYPE_TAG = ScanManager.BARCODE_TYPE_TAG;
    private static final String BARCODE_LENGTH_TAG = ScanManager.BARCODE_LENGTH_TAG;
    private static final String DECODE_DATA_TAG = ScanManager.DECODE_DATA_TAG;
    public UrovoBroadcastReceiver(Context context) {
        soundpool = new SoundPool(1, AudioManager.STREAM_NOTIFICATION, 100); // MODE_RINGTONE
        soundid = soundpool.load("/etc/Scan_new.ogg", 1);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        isScaning = false;
        soundpool.play(soundid, 1, 1, 0, 0, 1);
        vibrator.vibrate(100);
        String action = intent.getAction();
        byte[] barcode = intent.getByteArrayExtra(DECODE_DATA_TAG);
        int barcodeLen = intent.getIntExtra(BARCODE_LENGTH_TAG, 0);
        byte temp = intent.getByteExtra(BARCODE_TYPE_TAG, (byte) 0);
        String barcodeStr = intent.getStringExtra(BARCODE_STRING_TAG);
        String scanResult = new String(barcode, 0, barcodeLen);
        
        if (listener != null) {
            listener.onBarcodeScanned(scanResult);
        }
    }

    public void addEventListener(BarcodeListener listener) {
        this.listener = listener;
    }
}
