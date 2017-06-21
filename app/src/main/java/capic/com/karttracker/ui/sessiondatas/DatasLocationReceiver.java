package capic.com.karttracker.ui.sessiondatas;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.location.LocationResult;

import capic.com.karttracker.services.datas.models.SessionAccelerometerData;
import capic.com.karttracker.services.datas.models.SessionGpsData;

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
            SessionGpsData sessionGpsData = (SessionGpsData)intent.getSerializableExtra("sessionGpsData");
            SessionAccelerometerData sessionAccelerometerData = (SessionAccelerometerData)intent.getSerializableExtra("sessionAccelerometerData");

            mActivity.setValues(sessionGpsData, sessionAccelerometerData);

        }
    }
}