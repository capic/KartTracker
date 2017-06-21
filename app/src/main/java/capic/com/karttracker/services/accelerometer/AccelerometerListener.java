package capic.com.karttracker.services.accelerometer;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.support.v4.content.LocalBroadcastManager;

import javax.inject.Inject;

import capic.com.karttracker.KartTracker;
import capic.com.karttracker.services.datas.models.SessionAccelerometerData;
import capic.com.karttracker.services.datas.repositories.sessionaccelerometerdatas.SessionAccelerometerDatasRepository;

/**
 * Created by Vincent on 20/06/2017.
 */

public class AccelerometerListener implements SensorEventListener {
    private Context mContext;
    private Long mSessionId;

    @Inject
    SessionAccelerometerDatasRepository mRepository;

    public AccelerometerListener(Context context) {
        mContext = context;
        ((KartTracker)context.getApplicationContext()).getAppComponent().inject(this);
    }

    public void setSessionId(Long sessionId) {
        this.mSessionId = sessionId;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Long timestamp = System.currentTimeMillis();

        SessionAccelerometerData sessionAccelerometerData = new SessionAccelerometerData();
        sessionAccelerometerData.setMTimestamp(timestamp);
        sessionAccelerometerData.setMSessionId(mSessionId);
        sessionAccelerometerData.setMXAcceleration(event.values[0]);
        sessionAccelerometerData.setMYAcceleration(event.values[1]);
        sessionAccelerometerData.setMZAcceleration(event.values[2]);

        mRepository.insertSessionAccelerometerData(sessionAccelerometerData);

        LocalBroadcastManager.getInstance(mContext).sendBroadcast(new Intent("googleLocation").putExtra("sessionAccelerometerData", sessionAccelerometerData));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
