package capic.com.karttracker.services.datas.repositories.sessionaccelerometerdatas;

import android.util.Log;

import javax.inject.Inject;

import capic.com.karttracker.services.datas.models.DaoSession;
import capic.com.karttracker.services.datas.models.SessionAccelerometerData;

/**
 * Created by Vincent on 20/06/2017.
 */

public class SessionAccelerometerDatasRepositoryDb implements SessionAccelerometerDatasRepository {
    DaoSession mDaoSession;

    @Inject
    public SessionAccelerometerDatasRepositoryDb(DaoSession daoSession) {
        this.mDaoSession = daoSession;
    }

    @Override
    public SessionAccelerometerData getSessionAccelerometerData(Long id) {
        return this.mDaoSession.getSessionAccelerometerDataDao().load(id);
    }

    @Override
    public SessionAccelerometerData insertSessionAccelerometerData(SessionAccelerometerData sessionAccelerometerData) {
        Log.d("insertSessionAccelerometerData", "Insertion of " + sessionAccelerometerData);

        Long id = this.mDaoSession.insert(sessionAccelerometerData);
        return getSessionAccelerometerData(id);
    }
}
