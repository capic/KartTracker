package capic.com.greendao_gen.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Vincent on 26/04/2017.
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
