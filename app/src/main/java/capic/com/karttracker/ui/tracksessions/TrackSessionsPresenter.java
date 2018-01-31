package capic.com.karttracker.ui.tracksessions;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.joda.time.LocalDate;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import capic.com.karttracker.KartTracker;
import capic.com.karttracker.services.datas.models.Session;
import capic.com.karttracker.services.datas.repositories.tracksessions.TrackSessionsRepository;
import capic.com.karttracker.utils.SessionUtils;

/**
 * Created by capic on 15/05/2017.
 */
@InjectViewState
public class TrackSessionsPresenter extends MvpPresenter<TrackSessionsContract.View> {
    @Inject
    TrackSessionsRepository mTrackSessionsRepository;


    public TrackSessionsPresenter() {
        KartTracker.getAppComponent().inject(this);
    }

    public void loadTrackSessions(Long trackId, LocalDate sessionDate) {
        getViewState().showLoading();
        List<Session> sessionDataList = mTrackSessionsRepository.getSessionsByTrackAndDate(trackId, sessionDate);
        getViewState().showTrackSession(sessionDataList);
        getViewState().hideLoading();
    }

    public void onStartNewSessionClicked() {
        getViewState().showLoading();

        getViewState().openSessionDatasActivity();

        getViewState().hideLoading();
    }

    public void onTrackSessionItemClicked(Session session) {
        getViewState().openSessionDatasActivity(session.getMId());
    }
}
