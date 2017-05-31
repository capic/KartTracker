package capic.com.karttracker.ui.tracksessions;

import org.joda.time.LocalDate;

import java.util.Date;
import java.util.List;

import capic.com.karttracker.services.datas.models.Session;

/**
 * Created by capic on 15/05/2017.
 */

public interface TrackSessionsContract {
    public interface View {
        void showLoading();
        void hideLoading();
        void showTrackSession(List<Session> trackSessions);
        void openSessionDatasActivity();
        void openSessionDatasActivity(Long sessionId);
        void startGpsService();
        void stopGpsService();
    }

    public interface Presenter {
        void setView(TrackSessionsContract.View view);
        void loadTrackSessions(Long trackId, LocalDate sessionDate);
        void onStartNewSessionClicked();
        void onTrackSessionItemClicked(Session session);
    }
}
