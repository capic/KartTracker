package capic.com.karttracker.ui.tracks;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

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
@InjectViewState
public class TracksPresenter extends MvpPresenter<TracksContract.View> {

    TracksRepository mTracksRepository;
    TrackSessionsRepository mTrackSessionRepository;
    SessionDatasRepository mSessionDatasRepository;

    @Inject
    public TracksPresenter(TracksRepository tracksRepository, TrackSessionsRepository trackSessionsRepository,SessionDatasRepository sessionDatasRepository) {
        this.mTracksRepository = tracksRepository;
        this.mTrackSessionRepository = trackSessionsRepository;
        this.mSessionDatasRepository = sessionDatasRepository;
    }

    public void loadTracks() {
        getViewState().showLoading();
        List<Track> trackDataList = mTracksRepository.getAllTracks();
        getViewState().showTracks(trackDataList);
        getViewState().hideLoading();
    }

    public void onCreateTrackClicked() {
        getViewState().showCreateTrack();
    }

    public void onCreateTrack(Track track) {
        track = mTracksRepository.insertTrack(track);
        getViewState().addTrack(track);
        getViewState().openTrackSessionsActivity(track.getMId(), LocalDate.now());
    }

    public void onTrackItemClicked(Track track) {
        getViewState().openTrackSessionDatesActivity(track.getMId());
    }

    public void onTrackPlayItemClicked(Track track) {
        getViewState().openTrackSessionsActivity(track.getMId(), LocalDate.now());
    }

    public void onDeleteTrackClicked(Track track) {
        // check if the track has sessions
        Long numberOfSessions = mTrackSessionRepository.getNumberOfSessionForTrack(track.getMId());

        if (numberOfSessions > 0) {
            getViewState().showWarningDialogBoxTrackHasSessions(track);
        } else {
            deleteTrack(track);
        }
    }

    public void onDeleteTrackWarningOkClicked(Track track) {
        deleteTrack(track);
    }


    private void deleteTrack(Track track) {
        getViewState().showLoading();

        for (Session session : mTrackSessionRepository.getSessionsByTrack(track.getMId())) {
            mSessionDatasRepository.deleteSessionData(session.getMId());
            mTrackSessionRepository.deleteSession(session);
        }

        mTracksRepository.deleteTrack(track);

        getViewState().removeTrack(track);
        getViewState().hideLoading();
    }
}
