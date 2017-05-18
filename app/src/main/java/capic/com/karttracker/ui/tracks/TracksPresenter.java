package capic.com.karttracker.ui.tracks;

import org.joda.time.LocalDate;

import java.util.List;

import javax.inject.Inject;

import capic.com.karttracker.services.datas.models.Track;
import capic.com.karttracker.services.datas.repositories.tracks.TracksRepository;


/**
 * Created by capic on 29/04/2017.
 */

public class TracksPresenter implements TracksContract.Presenter {

    TracksContract.View mView;
    TracksRepository mTracksRepository;

    @Inject
    public TracksPresenter(TracksRepository tracksRepository) {
        this.mTracksRepository = tracksRepository;
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
    public Track createTrack(Track track) {
        return mTracksRepository.insertTrack(track);
    }

    @Override
    public void onTrackItemClicked(Track track) {
       mView.openTrackSessionDatesActivity(track.getMId());
    }

    @Override
    public void onTrackPlayItemClicked(Track track) {
        mView.openTrackSessionsActivity(track.getMId(), LocalDate.now());
    }
}
