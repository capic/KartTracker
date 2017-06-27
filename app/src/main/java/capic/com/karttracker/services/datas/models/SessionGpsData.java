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

@Entity(nameInDb = "session_gps_data")
public class SessionGpsData implements Serializable {
    static final long serialVersionUID = 2L;

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

    @Generated(hash = 1874190592)
    public SessionGpsData(Long mId, Double mLatitude, Double mLongitude,
            Double mAltitude, Float mSpeed) {
        this.mId = mId;
        this.mLatitude = mLatitude;
        this.mLongitude = mLongitude;
        this.mAltitude = mAltitude;
        this.mSpeed = mSpeed;
    }

    @Generated(hash = 83835103)
    public SessionGpsData() {
    }

    public String toString() {
        String s = "SessionGpsData: \r\n";

        s += "  id: " + mId + "\r\n";
        s += "  latitude: " + mLatitude + "\r\n";
        s += "  longitude: " + mLongitude + "\r\n";
        s += "  altitude: " + mAltitude + "\r\n";
        s += "  speed: " + mSpeed + "\r\n";

        return s;
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
}