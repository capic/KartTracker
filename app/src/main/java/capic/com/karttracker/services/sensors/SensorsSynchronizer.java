package capic.com.karttracker.services.sensors;

import capic.com.karttracker.services.datas.models.SessionGpsData;

/**
 * Created by Vincent on 23/06/2017.
 */

public interface SensorsSynchronizer {
    void lock();
    void unlock();
    boolean isLock();
    void setSessionGpsData(SessionGpsData sessionGpsData);
    SessionGpsData getSessionGpsData();
}
