package capic.com.karttracker.services.sensors.accelerometer;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.support.v4.content.LocalBroadcastManager;

import javax.inject.Inject;

import capic.com.karttracker.KartTracker;
import capic.com.karttracker.services.datas.models.SessionAccelerometerData;
import capic.com.karttracker.services.datas.models.SessionData;
import capic.com.karttracker.services.datas.repositories.sessiondatas.SessionDatasRepository;
import capic.com.karttracker.services.sensors.SensorsSynchronizer;
import capic.com.karttracker.utils.Constants;

/**
 * Created by Vincent on 20/06/2017.
 */

public class AccelerometerListener implements SensorEventListener {
    private Context mContext;
    private Long mSessionId;

    @Inject
    SessionDatasRepository mRepository;

    @Inject
    SensorsSynchronizer sync;

    public AccelerometerListener(Context context) {
        mContext = context;
        ((KartTracker)context.getApplicationContext()).getAppComponent().inject(this);
    }

    public void setSessionId(Long sessionId) {
        this.mSessionId = sessionId;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        SessionAccelerometerData sessionAccelerometerData = new SessionAccelerometerData();
        sessionAccelerometerData.setMXAcceleration(event.values[0]);
        sessionAccelerometerData.setMYAcceleration(event.values[1]);
        sessionAccelerometerData.setMZAcceleration(event.values[2]);

        if (sync.isLock() == false) {
            SessionData sessionData = mRepository.createSessionData(sync.getSessionGpsData(), sessionAccelerometerData, mSessionId);
//            LocalBroadcastManager.getInstance(mContext).sendBroadcast(new Intent(Constants.BROADCASTER_SESSION_DATA_INSTANT_NAME).putExtra(Constants.BROADCASTER_SESSION_DATA_EXTRA_ACCELEROMETER_NAME, sessionAccelerometerData));
            LocalBroadcastManager.getInstance(mContext).sendBroadcast(new Intent(Constants.BROADCASTER_SESSION_DATA_INSTANT_NAME).putExtra(Constants.BROADCASTER_SESSION_DATA_EXTRA_DATAS_NAME, sessionData));
            sync.lock();
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
