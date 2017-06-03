package capic.com.karttracker.services.datas.repositories.sessiongpsdatas;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import javax.inject.Inject;

import capic.com.karttracker.services.datas.models.DaoSession;
import capic.com.karttracker.services.datas.models.Session;
import capic.com.karttracker.services.datas.models.SessionGpsData;
import capic.com.karttracker.services.datas.models.SessionGpsDataDao;

/**
 * Created by Vincent on 01/06/2017.
 */

public class SessionGpsDatasRepositoryDb implements SessionGpsDatasRepository {
    DaoSession mDaoSession;

    @Inject
    public SessionGpsDatasRepositoryDb(DaoSession daoSession) {
        this.mDaoSession = daoSession;
    }

    @Override
    public List<SessionGpsData> getSessionGpsDatasBySession(Long sessionId) {
        QueryBuilder<SessionGpsData> qb = this.mDaoSession.getSessionGpsDataDao().queryBuilder();
        qb.where(SessionGpsDataDao.Properties.MSessionId.eq(sessionId));

        return qb.list();
    }

    @Override
    public SessionGpsData insertSessionGpsData(SessionGpsData sessionGpsData) {
        Long id = this.mDaoSession.insert(sessionGpsData);
        return getSessionGpsData(id);
    }

    @Override
    public SessionGpsData getSessionGpsData(Long id) {
        return this.mDaoSession.getSessionGpsDataDao().load(id);
    }
}