package capic.com.karttracker.services.datas.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

/**
 * Created by capic on 15/05/2017.
 */

@Entity
public class Session {
    @Id
    @Property(nameInDb = "id")
    private Long mId;

    @Property(nameInDb = "id_of_day")
    private Long mIdOfDay;

    @Property(nameInDb = "date")
    private Date mDate;

    @Property(nameInDb = "track_id")
    private Long mTrackId;

    @ToOne(joinProperty = "mTrackId")
    private Track track;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1616835709)
    private transient SessionDao myDao;

    @Generated(hash = 96189326)
    public Session(Long mId, Long mIdOfDay, Date mDate, Long mTrackId) {
        this.mId = mId;
        this.mIdOfDay = mIdOfDay;
        this.mDate = mDate;
        this.mTrackId = mTrackId;
    }

    @Generated(hash = 1317889643)
    public Session() {
    }

    public Long getMId() {
        return this.mId;
    }

    public void setMId(Long mId) {
        this.mId = mId;
    }

    public Long getMIdOfDay() {
        return this.mIdOfDay;
    }

    public void setMIdOfDay(Long mIdOfDay) {
        this.mIdOfDay = mIdOfDay;
    }

    public Date getMDate() {
        return this.mDate;
    }

    public void setMDate(Date mDate) {
        this.mDate = mDate;
    }

    public Long getMTrackId() {
        return this.mTrackId;
    }

    public void setMTrackId(Long mTrackId) {
        this.mTrackId = mTrackId;
    }

    @Generated(hash = 668638957)
    private transient Long track__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1882085200)
    public Track getTrack() {
        Long __key = this.mTrackId;
        if (track__resolvedKey == null || !track__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            TrackDao targetDao = daoSession.getTrackDao();
            Track trackNew = targetDao.load(__key);
            synchronized (this) {
                track = trackNew;
                track__resolvedKey = __key;
            }
        }
        return track;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1269485123)
    public void setTrack(Track track) {
        synchronized (this) {
            this.track = track;
            mTrackId = track == null ? null : track.getMId();
            track__resolvedKey = mTrackId;
        }
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1458438772)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getSessionDao() : null;
    }
}
