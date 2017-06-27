package capic.com.karttracker.app.modules;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

import javax.inject.Singleton;

import capic.com.karttracker.services.datas.models.DaoMaster;
import capic.com.karttracker.services.datas.models.DaoSession;
import capic.com.karttracker.services.datas.repositories.sessiondatas.SessionDatasRepository;
import capic.com.karttracker.services.datas.repositories.sessiondatas.SessionDatasRepositoryDb;
import capic.com.karttracker.services.datas.repositories.tracks.TracksRepository;
import capic.com.karttracker.services.datas.repositories.tracks.TracksRepositoryDb;
import capic.com.karttracker.services.datas.repositories.tracksessions.TrackSessionsRepository;
import capic.com.karttracker.services.datas.repositories.tracksessions.TrackSessionsRepositoryDb;
import dagger.Module;
import dagger.Provides;

/**
 * Created by capic on 14/05/2017.
 */

@Module
public class DataModule {
    private static final String DB_NAME = "karttracker-db";
//    private static final String DB_PASSWORD = "capic_20_04_1982";

    @Singleton
    @Provides
    public DaoSession provideDaoSession(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
        Database db = helper.getWritableDb();
        return new DaoMaster(db).newSession();
    }

    @Singleton
    @Provides
    public TracksRepository provideTrackRepository(DaoSession daoSession) {
        return new TracksRepositoryDb(daoSession);
    }

    @Singleton
    @Provides
    public TrackSessionsRepository provideTrackSessionsRepository(DaoSession daoSession) {
        return new TrackSessionsRepositoryDb(daoSession);
    }

    @Singleton
    @Provides
    public SessionDatasRepository provideSessionDatasRepository(DaoSession daoSession) {
        return new SessionDatasRepositoryDb(daoSession);
    }
}
