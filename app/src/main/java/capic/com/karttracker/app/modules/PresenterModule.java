package capic.com.karttracker.app.modules;

import javax.inject.Singleton;

import capic.com.karttracker.services.datas.repositories.sessiondatas.SessionDatasRepository;
import capic.com.karttracker.services.datas.repositories.tracks.TracksRepository;
import capic.com.karttracker.services.datas.repositories.tracksessions.TrackSessionsRepository;
import capic.com.karttracker.ui.sessiondatas.SessionDatasContract;
import capic.com.karttracker.ui.sessiondatas.SessionDatasDatasPresenter;
import capic.com.karttracker.ui.sessiondatas.SessionDatasMapsPresenter;
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
    TracksContract.Presenter provideTracksPresenter(TracksRepository tracksRepository, TrackSessionsRepository trackSessionsRepository, SessionDatasRepository sessionDatasRepository) {
        return new TracksPresenter(tracksRepository, trackSessionsRepository, sessionDatasRepository);
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

    @Singleton
    @Provides
    SessionDatasContract.MapsPresenter provideSessionDataMapsPresenter(TracksRepository trackRepository, TrackSessionsRepository trackSessionsRepository, SessionDatasRepository sessionDatasRepository) {
        return  new SessionDatasMapsPresenter(trackRepository, trackSessionsRepository, sessionDatasRepository);
    }

    @Singleton
    @Provides
    SessionDatasContract.DatasPresenter provideSessionDatasDatasPresenter(SessionDatasRepository repository) {
        return  new SessionDatasDatasPresenter(repository);
    }
}
