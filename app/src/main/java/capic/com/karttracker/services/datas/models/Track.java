package capic.com.karttracker.services.datas.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by capic on 29/04/2017.
 */

@Entity
public class Track {
    @Id
    @Property(nameInDb = "id")
    private Long mId;

    @Property(nameInDb = "name")
    private String mName;

    @Generated(hash = 1803596928)
    public Track(Long mId, String mName) {
        this.mId = mId;
        this.mName = mName;
    }

    @Generated(hash = 1672506944)
    public Track() {
    }

    public Long getMId() {
        return this.mId;
    }

    public void setMId(Long mId) {
        this.mId = mId;
    }

    public String getMName() {
        return this.mName;
    }

    public void setMName(String mName) {
        this.mName = mName;
    }
}