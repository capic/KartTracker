package capic.com.karttracker.ui.tracksessions;

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
    }

    public interface Presenter {
        void setView(TrackSessionsContract.View view);
        void loadTrackSessions(Long trackId, Date sessionDate);
        void onStartNewSessionClicked();
    }
}
