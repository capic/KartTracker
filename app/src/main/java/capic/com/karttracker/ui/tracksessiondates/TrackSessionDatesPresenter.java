package capic.com.karttracker.ui.tracksessiondates;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.joda.time.LocalDate;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import capic.com.karttracker.services.datas.models.Session;
import capic.com.karttracker.services.datas.models.Track;
import capic.com.karttracker.services.datas.repositories.tracksessions.TrackSessionsRepository;
import capic.com.karttracker.ui.tracksessions.TrackSessionsContract;
import capic.com.karttracker.utils.SessionUtils;

/**
 * Created by capic on 16/05/2017.
 */
@InjectViewState
public class TrackSessionDatesPresenter extends MvpPresenter<TrackSessionDatesContract.View> {
    TrackSessionsRepository mTrackSessionsRepository;

    @Inject
    public TrackSessionDatesPresenter(TrackSessionsRepository trackSessionsRepository) {
        this.mTrackSessionsRepository = trackSessionsRepository;
    }

    public void loadTrackSessionDates(Long trackId) {
        getViewState().showLoading();
        List<LocalDate> datesList = mTrackSessionsRepository.getSessionDatesByTrack(trackId);
        getViewState().showTrackSessionDate(datesList);
        getViewState().hideLoading();
    }

    public void onTrackSessionDateItemClicked(LocalDate date) {
        getViewState().openTrackSessionsActivity(date);
    }

    public void onStartNewSessionClicked() {
        getViewState().showLoading();

        getViewState().openSessionDatasActivity();
    }
}
