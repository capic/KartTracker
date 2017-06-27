package capic.com.karttracker.services.datas.repositories.sessiondatas;

import android.util.Log;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import javax.inject.Inject;

import capic.com.karttracker.services.datas.models.DaoSession;
import capic.com.karttracker.services.datas.models.SessionAccelerometerData;
import capic.com.karttracker.services.datas.models.SessionData;
import capic.com.karttracker.services.datas.models.SessionDataDao;
import capic.com.karttracker.services.datas.models.SessionGpsData;
import capic.com.karttracker.services.datas.models.SessionGpsDataDao;

/**
 * Created by Vincent on 26/06/2017.
 */

public class SessionDatasRepositoryDb implements SessionDatasRepository {
    DaoSession mDaoSession;

    @Inject
    public SessionDatasRepositoryDb(DaoSession daoSession) {
        this.mDaoSession = daoSession;
    }

    @Override
    public SessionData getSessionData(Long id) {
        return this.mDaoSession.getSessionDataDao().loadDeep(id);
    }

    @Override
    public SessionData insertSessionData(SessionData sessionData) {
        Log.d("insertSessionData", "Insertion of " + sessionData );

        Long id = this.mDaoSession.insert(sessionData);
        return getSessionData(id);
    }

    @Override
    public SessionData createSessionData(SessionGpsData sessionGpsData, SessionAccelerometerData sessionAccelerometerData, Long sessionId) {
        SessionData sessionData = new SessionData();
        sessionGpsData = insertSessionGpsData(sessionGpsData);
        sessionAccelerometerData = insertSessionAccelerometerData(sessionAccelerometerData);

        sessionData.setMSessionGpsDataId(sessionGpsData.getMId());
        sessionData.setMSessionId(sessionId);
        sessionData.setMSessionAccelerometerDataId(sessionAccelerometerData.getMId());
        sessionData.setMTimestamp(System.currentTimeMillis());

        Long id = this.mDaoSession.insert(sessionData);

        return getSessionData(id);
    }

    @Override
    public List<SessionData> getSessionDatasBySession(Long sessionId) {
        QueryBuilder<SessionData> qb = this.mDaoSession.getSessionDataDao().queryBuilder();
        qb.where(SessionDataDao.Properties.MSessionId.eq(sessionId));

        return qb.list();
    }

    @Override
    public void deleteSessionData(Long sessionId) {
        for (SessionData sessionData : getSessionDatasBySession(sessionId)) {
            this.mDaoSession.getSessionGpsDataDao().deleteByKey(sessionData.getMSessionGpsDataId());
            this.mDaoSession.getSessionAccelerometerDataDao().deleteByKey(sessionData.getMSessionAccelerometerDataId());
            this.mDaoSession.delete(sessionData);
        }
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

    @Override
    public SessionGpsData insertSessionGpsData(SessionGpsData sessionGpsData) {
        Log.d("insertSessionGpsData", "Insertion of " + sessionGpsData);

        Long id = this.mDaoSession.insert(sessionGpsData);

        return getSessionGpsData(id);
    }

    @Override
    public SessionGpsData getSessionGpsData(Long id) {
        return this.mDaoSession.getSessionGpsDataDao().load(id);
    }
}
