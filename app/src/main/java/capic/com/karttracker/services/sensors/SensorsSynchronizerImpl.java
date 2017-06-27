package capic.com.karttracker.services.sensors;

import capic.com.karttracker.services.datas.models.SessionData;
import capic.com.karttracker.services.datas.models.SessionGpsData;

/**
 * Created by Vincent on 23/06/2017.
 */

public class SensorsSynchronizerImpl implements SensorsSynchronizer {
    private boolean lock;
    private SessionGpsData sessionGpsData;

    public SensorsSynchronizerImpl() {
        lock = true;
    }

    @Override
    public SessionGpsData getSessionGpsData() {
        return sessionGpsData;
    }

    @Override
    public void setSessionGpsData(SessionGpsData sessionGpsData) {
        this.sessionGpsData = sessionGpsData;
    }

    @Override
    public void lock() {
        lock = true;
    }

    @Override
    public void unlock() {
        lock = false;
    }

    public boolean isLock() {
        return lock;
    }
}
