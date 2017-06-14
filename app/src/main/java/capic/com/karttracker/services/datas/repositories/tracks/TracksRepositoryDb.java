package capic.com.karttracker.services.datas.repositories.tracks;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import capic.com.karttracker.services.datas.models.DaoSession;
import capic.com.karttracker.services.datas.models.Track;

/**
 * Created by capic on 02/05/2017.
 */

@Singleton
public class TracksRepositoryDb implements TracksRepository {

    DaoSession mDaoSession;

    @Inject
    public TracksRepositoryDb(DaoSession daoSession) {
        this.mDaoSession = daoSession;
    }

    @Override
    public List<Track> getAllTracks() {
        Log.d("getAllTracks", "Load all the tracks");
//        return Arrays.asList(new Track(1L, "track 1"), new Track(2L, "track 2"));
        return this.mDaoSession.getTrackDao().loadAll();
    }

    @Override
    public Track getTrack(Long id) {
        return this.mDaoSession.getTrackDao().load(id);
    }

    @Override
    public Track insertTrack(Track trackData) {
        Log.d("insertTrack", "Insertion of " + trackData);
        Long id = this.mDaoSession.getTrackDao().insert(trackData);
        return getTrack(id);
    }

    @Override
    public void deleteTrack(Track track) {
        this.mDaoSession.delete(track);
    }
}
