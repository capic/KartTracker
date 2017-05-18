package capic.com.karttracker.ui.tracksessiondates;

import org.joda.time.LocalDate;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import capic.com.karttracker.services.datas.models.Track;
import capic.com.karttracker.services.datas.repositories.tracksessions.TrackSessionsRepository;
import capic.com.karttracker.ui.tracksessions.TrackSessionsContract;

/**
 * Created by capic on 16/05/2017.
 */

public class TrackSessionDatesPresenter implements TrackSessionDatesContract.Presenter {
    TrackSessionDatesContract.View mView;
    TrackSessionsRepository mTrackSessionsRepository;

    @Inject
    public TrackSessionDatesPresenter(TrackSessionsRepository trackSessionsRepository) {
        this.mTrackSessionsRepository = trackSessionsRepository;
    }


    @Override
    public void setView(TrackSessionDatesContract.View view) {
        this.mView = view;
    }

    @Override
    public void loadTrackSessionDates(Long trackId) {
        mView.showLoading();
        List<LocalDate> datesList = mTrackSessionsRepository.getSessionDatesByTrack(trackId);
        mView.showTrackSessionDate(datesList);
        mView.hideLoading();
    }

    @Override
    public void onTrackSessionDateItemClicked(Long trackId, LocalDate date) {
        mView.openTrackSessionsActivity(trackId, date);
    }
}
