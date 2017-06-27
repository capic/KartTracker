package capic.com.karttracker.services.datas.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

import java.io.Serializable;

/**
 * Created by Vincent on 26/06/2017.
 */

@Entity(nameInDb = "session_data")
public class SessionData implements Serializable{
    static final long serialVersionUID = 1L;

    @Id
    @Property(nameInDb = "id")
    private Long mId;

    @Property(nameInDb = "timestamp")
    private Long mTimestamp;

    @Property(nameInDb = "session_gps_data_id")
    private Long mSessionGpsDataId;

    @Property(nameInDb = "session_accelerometer_data_id")
    private Long mSessionAccelerometerDataId;

    @Property(nameInDb = "session_id")
    private Long mSessionId;

    @ToOne(joinProperty = "mSessionAccelerometerDataId")
    private SessionGpsData mSessionGpsData;

    @ToOne(joinProperty = "mSessionAccelerometerDataId")
    private SessionAccelerometerData mSessionAccelerometerData;

    @ToOne(joinProperty = "mSessionId")
    private Session mSession;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1698094633)
    private transient SessionDataDao myDao;



    @Generated(hash = 1334838274)
    public SessionData(Long mId, Long mTimestamp, Long mSessionGpsDataId,
            Long mSessionAccelerometerDataId, Long mSessionId) {
        this.mId = mId;
        this.mTimestamp = mTimestamp;
        this.mSessionGpsDataId = mSessionGpsDataId;
        this.mSessionAccelerometerDataId = mSessionAccelerometerDataId;
        this.mSessionId = mSessionId;
    }



    @Generated(hash = 1955036032)
    public SessionData() {
    }



    @Generated(hash = 1351059388)
    private transient Long mSessionGpsData__resolvedKey;

    @Generated(hash = 1218026513)
    private transient Long mSessionAccelerometerData__resolvedKey;

    @Generated(hash = 167543778)
    private transient Long mSession__resolvedKey;



    public String toString() {
        String s = "SessionData: \r\n";

        s += "  id: " + mId + "\r\n";
        s += "  timestamp: " + mTimestamp + "\r\n";
        s += "  sessionGpsDataId: " + mSessionGpsDataId + "\r\n";
        s += "  sessionAccelerometerDataId: " + mSessionAccelerometerDataId + "\r\n";

        return s;
    }



    public Long getMId() {
        return this.mId;
    }



    public void setMId(Long mId) {
        this.mId = mId;
    }



    public Long getMTimestamp() {
        return this.mTimestamp;
    }



    public void setMTimestamp(Long mTimestamp) {
        this.mTimestamp = mTimestamp;
    }



    public Long getMSessionGpsDataId() {
        return this.mSessionGpsDataId;
    }



    public void setMSessionGpsDataId(Long mSessionGpsDataId) {
        this.mSessionGpsDataId = mSessionGpsDataId;
    }



    public Long getMSessionAccelerometerDataId() {
        return this.mSessionAccelerometerDataId;
    }



    public void setMSessionAccelerometerDataId(Long mSessionAccelerometerDataId) {
        this.mSessionAccelerometerDataId = mSessionAccelerometerDataId;
    }



    public Long getMSessionId() {
        return this.mSessionId;
    }



    public void setMSessionId(Long mSessionId) {
        this.mSessionId = mSessionId;
    }



    /** To-one relationship, resolved on first access. */
    @Generated(hash = 328541656)
    public SessionGpsData getMSessionGpsData() {
        Long __key = this.mSessionAccelerometerDataId;
        if (mSessionGpsData__resolvedKey == null
                || !mSessionGpsData__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            SessionGpsDataDao targetDao = daoSession.getSessionGpsDataDao();
            SessionGpsData mSessionGpsDataNew = targetDao.load(__key);
            synchronized (this) {
                mSessionGpsData = mSessionGpsDataNew;
                mSessionGpsData__resolvedKey = __key;
            }
        }
        return mSessionGpsData;
    }



    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 487110371)
    public void setMSessionGpsData(SessionGpsData mSessionGpsData) {
        synchronized (this) {
            this.mSessionGpsData = mSessionGpsData;
            mSessionAccelerometerDataId = mSessionGpsData == null ? null
                    : mSessionGpsData.getMId();
            mSessionGpsData__resolvedKey = mSessionAccelerometerDataId;
        }
    }



    /** To-one relationship, resolved on first access. */
    @Generated(hash = 339977289)
    public SessionAccelerometerData getMSessionAccelerometerData() {
        Long __key = this.mSessionAccelerometerDataId;
        if (mSessionAccelerometerData__resolvedKey == null
                || !mSessionAccelerometerData__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            SessionAccelerometerDataDao targetDao = daoSession
                    .getSessionAccelerometerDataDao();
            SessionAccelerometerData mSessionAccelerometerDataNew = targetDao.load(__key);
            synchronized (this) {
                mSessionAccelerometerData = mSessionAccelerometerDataNew;
                mSessionAccelerometerData__resolvedKey = __key;
            }
        }
        return mSessionAccelerometerData;
    }



    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 781570241)
    public void setMSessionAccelerometerData(
            SessionAccelerometerData mSessionAccelerometerData) {
        synchronized (this) {
            this.mSessionAccelerometerData = mSessionAccelerometerData;
            mSessionAccelerometerDataId = mSessionAccelerometerData == null ? null
                    : mSessionAccelerometerData.getMId();
            mSessionAccelerometerData__resolvedKey = mSessionAccelerometerDataId;
        }
    }



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
    @Generated(hash = 645204408)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getSessionDataDao() : null;
    }


}
