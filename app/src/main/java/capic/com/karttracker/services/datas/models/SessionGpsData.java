package capic.com.karttracker.services.datas.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

import java.io.Serializable;

/**
 * Created by Vincent on 01/06/2017.
 */

@Entity
public class SessionGpsData implements Serializable {
    static final long serialVersionUID = 1L;

    @Id
    @Property(nameInDb = "id")
    private Long mId;

    @Property(nameInDb = "latitude")
    private Double mLatitude;

    @Property(nameInDb = "longitude")
    private Double mLongitude;

    @Property(nameInDb = "altitude")
    private Double mAltitude;

    @Property(nameInDb = "speed")
    private Float mSpeed;

    @Property(nameInDb = "timestamp")
    private Long mTimestamp;

    @Property(nameInDb = "session_id")
    private Long mSessionId;

    @ToOne(joinProperty = "mSessionId")
    private Session mSession;

    public String toString() {
        String s = "SessionGpsData: \r\n";

        s += "  id: " + mId + "\r\n";
        s += "  latitude: " + mLatitude + "\r\n";
        s += "  longitude: " + mLongitude + "\r\n";
        s += "  altitude: " + mAltitude + "\r\n";
        s += "  speed: " + mSpeed + "\r\n";
        s += "  timestamp: " + mTimestamp + "\r\n";
        s += "  sessionId: " + mSessionId + "\r\n";

        return s;
    }

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1754399995)
    private transient SessionGpsDataDao myDao;

    @Generated(hash = 1866244192)
    public SessionGpsData(Long mId, Double mLatitude, Double mLongitude,
            Double mAltitude, Float mSpeed, Long mTimestamp, Long mSessionId) {
        this.mId = mId;
        this.mLatitude = mLatitude;
        this.mLongitude = mLongitude;
        this.mAltitude = mAltitude;
        this.mSpeed = mSpeed;
        this.mTimestamp = mTimestamp;
        this.mSessionId = mSessionId;
    }

    @Generated(hash = 83835103)
    public SessionGpsData() {
    }

    public Long getMId() {
        return this.mId;
    }

    public void setMId(Long mId) {
        this.mId = mId;
    }

    public Double getMLatitude() {
        return this.mLatitude;
    }

    public void setMLatitude(Double mLatitude) {
        this.mLatitude = mLatitude;
    }

    public Double getMLongitude() {
        return this.mLongitude;
    }

    public void setMLongitude(Double mLongitude) {
        this.mLongitude = mLongitude;
    }

    public Double getMAltitude() {
        return this.mAltitude;
    }

    public void setMAltitude(Double mAltitude) {
        this.mAltitude = mAltitude;
    }

    public Float getMSpeed() {
        return this.mSpeed;
    }

    public void setMSpeed(Float mSpeed) {
        this.mSpeed = mSpeed;
    }

    public Long getMTimestamp() {
        return this.mTimestamp;
    }

    public void setMTimestamp(Long mTimestamp) {
        this.mTimestamp = mTimestamp;
    }

    public Long getMSessionId() {
        return this.mSessionId;
    }

    public void setMSessionId(Long mSessionId) {
        this.mSessionId = mSessionId;
    }

    @Generated(hash = 167543778)
    private transient Long mSession__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 857447053)
    public Session getMSession() {
        Long __key = this.mSessionId;
        if (mSession__resolvedKey == null || !mSession__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            SessionDao targetDao = daoSession.getSessionDao();
            Session mSessionNew = targetDao.load(__key);
            synchronized (this) {
                mSession = mSessionNew;
                mSession__resolvedKey = __key;
            }
        }
        return mSession;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1442458053)
    public void setMSession(Session mSession) {
        synchronized (this) {
            this.mSession = mSession;
            mSessionId = mSession == null ? null : mSession.getMId();
            mSession__resolvedKey = mSessionId;
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
    @Generated(hash = 917067398)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getSessionGpsDataDao() : null;
    }

    
}