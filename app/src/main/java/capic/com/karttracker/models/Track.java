package capic.com.karttracker.models;

/**
 * Created by Vincent on 25/04/2017.
 */

public class Track {
    public Long mId;
    public String mName;

    public Track(Long mId, String mName) {
        this.mId = mId;
        this.mName = mName;
    }

    public Long getmId() {
        return mId;
    }

    public void setmId(Long mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    @Override
    public String toString() {
        return this.getmName();
    }
}
