package capic.com.karttracker.services.datas.repositories.tracksessions;

import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;
import org.joda.time.LocalDate;

import java.text.DateFormat;
import java.util.Date;
import java.util.LinkedList;
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

    protected QueryBuilder<Session> getSessionsByTrackAndDateQuery(Long trackId, LocalDate sessionDate) {
        Session.DateConverter converter = new Session.DateConverter();

        QueryBuilder<Session> qb = this.mDaoSession.getSessionDao().queryBuilder();
        qb.where(SessionDao.Properties.MTrackId.eq(trackId), SessionDao.Properties.MDate.eq(converter.convertToDatabaseValue(sessionDate)));

        return qb;
    }

    @Override
    public List<Session> getSessionsByTrackAndDate(Long trackId, LocalDate sessionDate) {
        QueryBuilder<Session> qb = getSessionsByTrackAndDateQuery(trackId, sessionDate);
        qb.orderAsc(SessionDao.Properties.MIdOfDay);

        return qb.list();
    }

    @Override
    public Session insertSession(Session session) {
        Long id = this.mDaoSession.insert(session);
        return getSession(id);
    }

    @Override
    public Session getSession(Long id) {
        return this.mDaoSession.getSessionDao().load(id);
    }

    @Override
    public Session getLastSessionByTrackAndDate(Long trackId, LocalDate sessionDate) {
        QueryBuilder<Session> qb = getSessionsByTrackAndDateQuery(trackId, sessionDate);
        qb.orderDesc(SessionDao.Properties.MIdOfDay).limit(1);

        return qb.unique();
    }

    @Override
    public List<LocalDate> getSessionDatesByTrack(Long trackId) {
        QueryBuilder<Session> qb = this.mDaoSession.getSessionDao().queryBuilder();
        qb.where(SessionDao.Properties.MTrackId.eq(trackId));
        
        List<Session> list = qb.list();

        List<LocalDate> dates = new LinkedList<>();
        for (Session session : list) {
            if (!dates.contains(session.getMDate())) {
                dates.add(session.getMDate());
            }
        }
        
        return dates;
    }
}
