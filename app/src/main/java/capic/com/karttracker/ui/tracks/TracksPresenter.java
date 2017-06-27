package capic.com.karttracker.ui.tracks;

import org.joda.time.LocalDate;

import java.util.List;

import javax.inject.Inject;

import capic.com.karttracker.services.datas.models.Session;
import capic.com.karttracker.services.datas.models.Track;
import capic.com.karttracker.services.datas.repositories.sessiondatas.SessionDatasRepository;
import capic.com.karttracker.services.datas.repositories.tracks.TracksRepository;
import capic.com.karttracker.services.datas.repositories.tracksessions.TrackSessionsRepository;


/**
 * Created by capic on 29/04/2017.
 */

public class TracksPresenter implements TracksContract.Presenter {

    TracksContract.View mView;
    TracksRepository mTracksRepository;
    TrackSessionsRepository mTrackSessionRepository;
    SessionDatasRepository mSessionDatasRepository;

    @Inject
    public TracksPresenter(TracksRepository tracksRepository, TrackSessionsRepository trackSessionsRepository,SessionDatasRepository sessionDatasRepository) {
        this.mTracksRepository = tracksRepository;
        this.mTrackSessionRepository = trackSessionsRepository;
        this.mSessionDatasRepository = sessionDatasRepository;
    }

    @Override
    public void setView(TracksContract.View view) {
        this.mView = view;
    }

    @Override
    public void loadTracks() {
        mView.showLoading();
        List<Track> trackDataList = mTracksRepository.getAllTracks();
        mView.showTracks(trackDataList);
        mView.hideLoading();
    }

    @Override
    public void onCreateTrackClicked() {
        mView.showCreateTrack();
    }

    @Override
    public void onCreateTrack(Track track) {
        track = mTracksRepository.insertTrack(track);
        mView.addTrack(track);
        mView.openTrackSessionsActivity(track.getMId(), LocalDate.now());
    }

    @Override
    public void onTrackItemClicked(Track track) {
       mView.openTrackSessionDatesActivity(track.getMId());
    }

    @Override
    public void onTrackPlayItemClicked(Track track) {
        mView.openTrackSessionsActivity(track.getMId(), LocalDate.now());
    }

    @Override
    public void onDeleteTrackClicked(Track track) {
        // check if the track has sessions
        Long numberOfSessions = mTrackSessionRepository.getNumberOfSessionForTrack(track.getMId());

        if (numberOfSessions > 0) {
            mView.showWarningDialogBoxTrackHasSessions(track);
        } else {
            deleteTrack(track);
        }
    }

    @Override
    public void onDeleteTrackWarningOkClicked(Track track) {
        deleteTrack(track);
    }


    private void deleteTrack(Track track) {
        mView.showLoading();

        for (Session session : mTrackSessionRepository.getSessionsByTrack(track.getMId())) {
            mSessionDatasRepository.deleteSessionData(session.getMId());
            mTrackSessionRepository.deleteSession(session);
        }

        mTracksRepository.deleteTrack(track);

        mView.removeTrack(track);
        mView.hideLoading();
    }
}
