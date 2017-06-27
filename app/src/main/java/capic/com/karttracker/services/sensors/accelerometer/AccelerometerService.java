package capic.com.karttracker.services.sensors.accelerometer;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Vincent on 20/06/2017.
 */

public class AccelerometerService extends Service {
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;

    private AccelerometerListener mAccelerometerListener;
    private HandlerThread mHandlerThread;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mHandlerThread = new HandlerThread("AccelerometerLogListener");
        mHandlerThread.start();


        mAccelerometerListener = new AccelerometerListener(this);
        mSensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
//        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent.getBooleanExtra("request", false)) {
            Handler handler = new Handler(mHandlerThread.getLooper());
            mSensorManager.registerListener(mAccelerometerListener, mAccelerometer, SensorManager.SENSOR_DELAY_FASTEST, handler);
            mAccelerometerListener.setSessionId(intent.getExtras().getLong("sessionId"));
        }else if (intent.getBooleanExtra("remove", false)) {
            stopSelf();
        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if(mHandlerThread.isAlive())
            mHandlerThread.quitSafely();

        mSensorManager.unregisterListener(mAccelerometerListener);
    }


}
