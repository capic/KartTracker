package capic.com.karttracker.ui.sessiondatas;

import javax.inject.Inject;

import capic.com.karttracker.services.datas.models.Session;
import capic.com.karttracker.services.datas.models.Track;
import capic.com.karttracker.services.datas.repositories.tracks.TracksRepository;
import capic.com.karttracker.services.datas.repositories.tracksessions.TrackSessionsRepository;
import capic.com.karttracker.ui.tracks.TracksContract;

/**
 * Created by Vincent on 30/05/2017.
 */

public class SessionDatasMapsPresenter implements SessionDatasContract.MapsPresenter {
    SessionDatasContract.MapsView mView;

    TracksRepository mTracksRepository;
    TrackSessionsRepository mTrackSessionsRepository;

    @Inject
    public SessionDatasMapsPresenter(TracksRepository tracksRepository, TrackSessionsRepository trackSessionsRepository) {
        mTracksRepository = tracksRepository;
        mTrackSessionsRepository = trackSessionsRepository;
    }

    @Override
    public void setView(SessionDatasContract.MapsView view) {
        mView = view;
    }

    @Override
    public void onStopSessionDatasClicked() {
        mView.stopGpsService();
    }

    @Override
    public Track loadTrack(Long trackId) {
        return mTracksRepository.getTrack(trackId);
    }

    @Override
    public Session loadSession(Long sessionId) {
        return mTrackSessionsRepository.getSession(sessionId);
    }
}
