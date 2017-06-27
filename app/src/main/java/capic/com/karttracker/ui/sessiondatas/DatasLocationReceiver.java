package capic.com.karttracker.ui.sessiondatas;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.location.LocationResult;

import capic.com.karttracker.services.datas.models.SessionAccelerometerData;
import capic.com.karttracker.services.datas.models.SessionData;
import capic.com.karttracker.services.datas.models.SessionGpsData;
import capic.com.karttracker.utils.Constants;

/**
 * Created by capic on 08/06/2017.
 */

public class DatasLocationReceiver extends BroadcastReceiver {
    private SessionDatasFragment mActivity;

    public DatasLocationReceiver(SessionDatasFragment mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        final SessionDatasFragment activity = mActivity;
        if (activity != null) {
            SessionData sessionData = new SessionData();
            if (intent.hasExtra(Constants.BROADCASTER_SESSION_DATA_EXTRA_GPS_NAME)) {
                sessionData.setMSessionGpsData((SessionGpsData) intent.getSerializableExtra(Constants.BROADCASTER_SESSION_DATA_EXTRA_GPS_NAME));
            } else if (intent.hasExtra(Constants.BROADCASTER_SESSION_DATA_EXTRA_ACCELEROMETER_NAME)) {
                sessionData.setMSessionAccelerometerData((SessionAccelerometerData) intent.getSerializableExtra(Constants.BROADCASTER_SESSION_DATA_EXTRA_ACCELEROMETER_NAME));
            } else if (intent.hasExtra(Constants.BROADCASTER_SESSION_DATA_EXTRA_DATAS_NAME)) {
                sessionData = ((SessionData)intent.getSerializableExtra(Constants.BROADCASTER_SESSION_DATA_EXTRA_DATAS_NAME));
            }

            mActivity.setValues(sessionData);

        }
    }
}