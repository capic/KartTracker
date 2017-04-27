package capic.com.karttracker;

import android.app.Application;

import org.greenrobot.greendao.database.Database;

import capic.com.greendao_gen.models.DaoMaster;
import capic.com.greendao_gen.models.DaoSession;

/**
 * Created by Vincent on 26/04/2017.
 */

public class KartTracker extends Application {
    public static final boolean ENCRYPTED = false;

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, ENCRYPTED ? "karttracker-encrypted" : "karttracker-db");
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
