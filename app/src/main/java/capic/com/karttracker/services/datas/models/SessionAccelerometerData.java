package capic.com.karttracker.services.datas.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToOne;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

/**
 * Created by Vincent on 20/06/2017.
 */

@Entity
public class SessionAccelerometerData  implements Serializable {
    static final long serialVersionUID = 2L;

    @Id
    @Property(nameInDb = "id")
    private Long mId;

    @Property(nameInDb = "x_acceleration")
    private Float mXAcceleration;

    @Property(nameInDb = "y_acceleration")
    private Float mYAcceleration;

    @Property(nameInDb = "z_acceleration")
    private Float mZAcceleration;

    @Property(nameInDb = "timestamp")
    private Long mTimestamp;

    @Property(nameInDb = "session_id")
    private Long mSessionId;

    @ToOne(joinProperty = "mSessionId")
    private Session mSession;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 960128158)
    private transient SessionAccelerometerDataDao myDao;

    @Generated(hash = 2133947927)
    public SessionAccelerometerData(Long mId, Float mXAcceleration,
            Float mYAcceleration, Float mZAcceleration, Long mTimestamp,
            Long mSessionId) {
        this.mId = mId;
        this.mXAcceleration = mXAcceleration;
        this.mYAcceleration = mYAcceleration;
        this.mZAcceleration = mZAcceleration;
        this.mTimestamp = mTimestamp;
        this.mSessionId = mSessionId;
    }

    @Generated(hash = 181679625)
    public SessionAccelerometerData() {
    }

    @Generated(hash = 167543778)
    private transient Long mSession__resolvedKey;

    public String toString() {
        String s = "SessionAccelerometerData: \r\n";

        s += "  id: " + mId + "\r\n";
        s += "  xAcceleraion: " + mXAcceleration + "\r\n";
        s += "  yAcceleraion: " + mYAcceleration + "\r\n";
        s += "  zAcceleraion: " + mZAcceleration + "\r\n";
        s += "  timestamp: " + mTimestamp + "\r\n";
        s += "  sessionId: " + mSessionId + "\r\n";

        return s;
    }

    public Long getMId() {
        return this.mId;
    }

    public void setMId(Long mId) {
        this.mId = mId;
    }

    public Float getMXAcceleration() {
        return this.mXAcceleration;
    }

    public void setMXAcceleration(Float mXAcceleration) {
        this.mXAcceleration = mXAcceleration;
    }

    public Float getMYAcceleration() {
        return this.mYAcceleration;
    }

    public void setMYAcceleration(Float mYAcceleration) {
        this.mYAcceleration = mYAcceleration;
    }

    public Float getMZAcceleration() {
        return this.mZAcceleration;
    }

    public void setMZAcceleration(Float mZAcceleration) {
        this.mZAcceleration = mZAcceleration;
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
    @Generated(hash = 1735806622)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getSessionAccelerometerDataDao() : null;
    }
}
