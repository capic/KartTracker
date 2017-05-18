package capic.com.karttracker.app.modules;

import javax.inject.Singleton;

import capic.com.karttracker.services.datas.repositories.tracks.TracksRepository;
import capic.com.karttracker.services.datas.repositories.tracksessions.TrackSessionsRepository;
import capic.com.karttracker.ui.tracks.TracksContract;
import capic.com.karttracker.ui.tracks.TracksPresenter;
import capic.com.karttracker.ui.tracksessiondates.TrackSessionDatesContract;
import capic.com.karttracker.ui.tracksessiondates.TrackSessionDatesPresenter;
import capic.com.karttracker.ui.tracksessions.TrackSessionsContract;
import capic.com.karttracker.ui.tracksessions.TrackSessionsPresenter;
import dagger.Module;
import dagger.Provides;

/**
 * Created by capic on 01/05/2017.
 */

@Module
public class PresenterModule {
    @Singleton
    @Provides
    TracksContract.Presenter provideTracksPresenter(TracksRepository repository) {
        return new TracksPresenter(repository);
    }

    @Singleton
    @Provides
    TrackSessionsContract.Presenter provideTrackSessionsPresenter(TrackSessionsRepository repository) {
        return new TrackSessionsPresenter(repository);
    }

    @Singleton
    @Provides
    TrackSessionDatesContract.Presenter provideTrackSessionDatesPresenter(TrackSessionsRepository repository) {
        return new TrackSessionDatesPresenter(repository);
    }
}
