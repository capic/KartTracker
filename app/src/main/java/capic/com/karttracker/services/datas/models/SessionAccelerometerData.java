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

@Entity(nameInDb = "session_accelerometer")
public class SessionAccelerometerData implements Serializable{
    static final long serialVersionUID = 3L;

    @Id
    @Property(nameInDb = "id")
    private Long mId;

    @Property(nameInDb = "x_acceleration")
    private Float mXAcceleration;

    @Property(nameInDb = "y_acceleration")
    private Float mYAcceleration;

    @Property(nameInDb = "z_acceleration")
    private Float mZAcceleration;

    @Generated(hash = 1721170214)
    public SessionAccelerometerData(Long mId, Float mXAcceleration,
            Float mYAcceleration, Float mZAcceleration) {
        this.mId = mId;
        this.mXAcceleration = mXAcceleration;
        this.mYAcceleration = mYAcceleration;
        this.mZAcceleration = mZAcceleration;
    }

    @Generated(hash = 181679625)
    public SessionAccelerometerData() {
    }

    public String toString() {
        String s = "SessionAccelerometerData: \r\n";

        s += "  id: " + mId + "\r\n";
        s += "  xAcceleraion: " + mXAcceleration + "\r\n";
        s += "  yAcceleraion: " + mYAcceleration + "\r\n";
        s += "  zAcceleraion: " + mZAcceleration + "\r\n";

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
}
