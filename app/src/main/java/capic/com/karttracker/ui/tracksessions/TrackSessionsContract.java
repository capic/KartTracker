package capic.com.karttracker.ui.tracksessions;

import com.arellomobile.mvp.MvpView;

import org.joda.time.LocalDate;

import java.util.Date;
import java.util.List;

import capic.com.karttracker.services.datas.models.Session;

/**
 * Created by capic on 15/05/2017.
 */

public interface TrackSessionsContract {
    public interface View extends MvpView {
        void showLoading();
        void hideLoading();
        void showTrackSession(List<Session> trackSessions);
        void openSessionDatasActivity();
        void openSessionDatasActivity(Long sessionId);
    }
}
