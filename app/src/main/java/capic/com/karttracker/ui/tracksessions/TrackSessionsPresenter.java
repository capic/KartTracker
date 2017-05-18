package capic.com.karttracker.ui.tracksessions;

import org.joda.time.LocalDate;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import capic.com.karttracker.services.datas.models.Session;
import capic.com.karttracker.services.datas.repositories.tracksessions.TrackSessionsRepository;

/**
 * Created by capic on 15/05/2017.
 */

public class TrackSessionsPresenter implements TrackSessionsContract.Presenter {
    TrackSessionsContract.View mView;
    TrackSessionsRepository mTrackSessionsRepository;

    @Inject
    public TrackSessionsPresenter(TrackSessionsRepository trackSessionsRepository) {
        mTrackSessionsRepository = trackSessionsRepository;
    }

    @Override
    public void setView(TrackSessionsContract.View view) {
        mView = view;
    }

    @Override
    public void loadTrackSessions(Long trackId, LocalDate sessionDate) {
        mView.showLoading();
        List<Session> sessionDataList = mTrackSessionsRepository.getSessionsByTrackAndDate(trackId, sessionDate);
        mView.showTrackSession(sessionDataList);
        mView.hideLoading();
    }

    @Override
    public void onStartNewSessionClicked(Long trackId) {
        mView.showLoading();

        LocalDate today = LocalDate.now();

        Session lastSession = mTrackSessionsRepository.getLastSessionByTrackAndDate(trackId, today);
        Long lastSessionId = lastSession == null ? 0: lastSession.getMIdOfDay();


        Session session = new Session();
        session.setMDate(today);
        session.setMIdOfDay(lastSessionId + 1);
        session.setMTrackId(trackId);

        mTrackSessionsRepository.insertSession(session);


        mView.hideLoading();
    }
}
