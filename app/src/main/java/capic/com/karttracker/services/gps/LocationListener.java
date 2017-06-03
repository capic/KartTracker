package capic.com.karttracker.services.gps;


import android.content.Context;
import android.location.Location;
import android.util.Log;

import javax.inject.Inject;

import capic.com.karttracker.KartTracker;
import capic.com.karttracker.services.datas.models.SessionGpsData;
import capic.com.karttracker.services.datas.repositories.sessiongpsdatas.SessionGpsDatasRepository;

/**
 * Created by Vincent on 02/06/2017.
 */

public class LocationListener implements com.google.android.gms.location.LocationListener{
    private Long mSessionId;

    @Inject
    SessionGpsDatasRepository repository;

    public LocationListener(Context context) {
        ((KartTracker)context.getApplicationContext()).getAppComponent().inject(this);
    }

    public void setSessionId(Long sessionId) {
        this.mSessionId = sessionId;
    }


    @Override
    public void onLocationChanged(Location location) {


        SessionGpsData sessionGpsData = new SessionGpsData();
        sessionGpsData.setMLatitude(location.getLatitude());
        sessionGpsData.setMLongitude(location.getLongitude());
        sessionGpsData.setMAltitude(location.getAltitude());
        sessionGpsData.setMSessionId(mSessionId);

        repository.insertSessionGpsData(sessionGpsData);
    }
}
