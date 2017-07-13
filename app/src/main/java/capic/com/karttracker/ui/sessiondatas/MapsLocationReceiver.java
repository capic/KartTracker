package capic.com.karttracker.ui.sessiondatas;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.location.LocationResult;

import capic.com.karttracker.services.datas.models.SessionData;
import capic.com.karttracker.services.datas.models.SessionGpsData;
import capic.com.karttracker.utils.Constants;

/**
 * Created by capic on 08/06/2017.
 */

public class MapsLocationReceiver extends BroadcastReceiver {
    private SessionDataMapsActivity mActivity;

    public MapsLocationReceiver(SessionDataMapsActivity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        final SessionDataMapsActivity activity = mActivity;
        if (activity != null) {
            if (intent.hasExtra(Constants.BROADCASTER_SESSION_DATA_EXTRA_DATAS_ARRAY_NAME)) {
                SessionData[] sessionDatasArray = (SessionData[]) intent.getSerializableExtra(Constants.BROADCASTER_SESSION_DATA_EXTRA_DATAS_ARRAY_NAME);
                mActivity.drawRouteOnMap(sessionDatasArray);
//                mActivity.markStartingLocationOnMap(sessionDatasArray[1].getMSessionGpsData());
            } else {
                SessionGpsData sessionGpsData = new SessionGpsData();
                if (intent.hasExtra(Constants.BROADCASTER_SESSION_DATA_EXTRA_GPS_NAME)) {
                    sessionGpsData = (SessionGpsData) intent.getSerializableExtra(Constants.BROADCASTER_SESSION_DATA_EXTRA_GPS_NAME);
                } else if (intent.hasExtra(Constants.BROADCASTER_SESSION_DATA_EXTRA_DATAS_NAME)) {
                    sessionGpsData = ((SessionData) intent.getSerializableExtra(Constants.BROADCASTER_SESSION_DATA_EXTRA_DATAS_NAME)).getMSessionGpsData();
                }

                mActivity.markStartingLocationOnMap(sessionGpsData);
            }

        }
    }
}