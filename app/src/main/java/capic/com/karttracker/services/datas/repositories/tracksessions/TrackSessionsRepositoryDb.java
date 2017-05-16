package capic.com.karttracker.services.datas.repositories.tracksessions;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import capic.com.karttracker.services.datas.models.DaoSession;
import capic.com.karttracker.services.datas.models.Session;
import capic.com.karttracker.services.datas.models.SessionDao;

/**
 * Created by capic on 15/05/2017.
 */

public class TrackSessionsRepositoryDb implements TrackSessionsRepository {

    DaoSession mDaoSession;

    @Inject
    public TrackSessionsRepositoryDb(DaoSession daoSession) {
        this.mDaoSession = daoSession;
    }

    @Override
    public List<Session> getSessionsByTrackAndDate(Long trackId, Date sessionDate) {
        QueryBuilder<Session> qb = this.mDaoSession.getSessionDao().queryBuilder();
        qb.where(SessionDao.Properties.MTrackId.eq(trackId), SessionDao.Properties.MDate.eq(sessionDate));
        return qb.list();
    }
}
