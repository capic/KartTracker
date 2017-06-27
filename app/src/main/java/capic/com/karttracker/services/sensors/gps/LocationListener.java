package capic.com.karttracker.services.sensors.gps;


import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import javax.inject.Inject;

import capic.com.karttracker.KartTracker;
import capic.com.karttracker.services.datas.models.SessionGpsData;
import capic.com.karttracker.services.datas.repositories.sessiondatas.SessionDatasRepository;
import capic.com.karttracker.services.sensors.SensorsSynchronizer;
import capic.com.karttracker.utils.Constants;

/**
 * Created by Vincent on 02/06/2017.
 */

public class LocationListener implements com.google.android.gms.location.LocationListener{
    private Long mSessionId;
    private Location previousLocation;
    private Long previousTimestamp;
    private Context mContext;


    @Inject
    SessionDatasRepository repository;

    @Inject
    SensorsSynchronizer sync;

    private String result;

    public LocationListener(Context context) {
        mContext = context;
        ((KartTracker)context.getApplicationContext()).getAppComponent().inject(this);
    }

    public void setSessionId(Long sessionId) {
        this.mSessionId = sessionId;
    }


    @Override
    public void onLocationChanged(Location location) {
        Long timestamp = System.currentTimeMillis();

        SessionGpsData sessionGpsData = new SessionGpsData();
        sessionGpsData.setMLatitude(location.getLatitude());
        sessionGpsData.setMLongitude(location.getLongitude());
        sessionGpsData.setMAltitude(location.getAltitude());

        if (previousLocation != null && previousTimestamp != null &&
                (previousLocation.getLatitude() != location.getLatitude() ||
                previousLocation.getLongitude() != location.getLongitude())) {

            Log.d("onLocationChanged", "Datas to calculate speed: ");
            Log.d("onLocationChanged", "previous latitude: " + previousLocation.getLatitude() + ", previous longitude: " + previousLocation.getLongitude() + ", previous timestamp: " + previousTimestamp);
            Log.d("onLocationChanged", "current latitude: " + location.getLatitude() + ", current longitude: " + location.getLongitude() + ", current timestamp: " + timestamp);

            float[] results = new float[1];
            Location.distanceBetween(previousLocation.getLatitude(), previousLocation.getLongitude(), location.getLatitude(), location.getLongitude(), results);
            Float distanceInMeter = results[0];
            Float timeInSecond = (timestamp - previousTimestamp) / 1000f;
            Float speedInMeterPerSecond = distanceInMeter / timeInSecond;
            Float speedInKilometerPerHour = (speedInMeterPerSecond * 3600) / 1000f;

            Log.d("onLocationChanged", "Calculate speed with distance in meter: " + distanceInMeter + ", time in second: " + timeInSecond + " => speed: " + speedInMeterPerSecond + " m/s");
            Log.d("onLocationChanged", "speed in kilometer per hour: " + speedInKilometerPerHour + " km/h");

            sessionGpsData.setMSpeed(speedInKilometerPerHour);
        } else {
            Log.d("onLocationChanged", "No speed calculation");
            sessionGpsData.setMSpeed(0f);
        }


        sync.setSessionGpsData(sessionGpsData);
        sync.unlock();
//        repository.insertSessionGpsData(sessionGpsData);

        previousLocation = location;
        previousTimestamp = timestamp;

//        LocalBroadcastManager.getInstance(mContext).sendBroadcast(new Intent(Constants.BROADCASTER_SESSION_DATA_INSTANT_NAME).putExtra(Constants.BROADCASTER_SESSION_DATA_EXTRA_GPS_NAME, sessionGpsData));
    }
}
