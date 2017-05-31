package capic.com.karttracker.ui.sessiondatas;

import capic.com.karttracker.services.datas.models.Session;
import capic.com.karttracker.services.datas.models.Track;

/**
 * Created by Vincent on 29/05/2017.
 */

public interface SessionDatasContract {
    public interface MapsView {
        void showLoading();
        void hideLoading();
        void stopGpsService();
    }

    public interface DatasView {
        void showLoading();
        void hideLoading();
    }

    public interface MapsPresenter {
        void setView(SessionDatasContract.MapsView view);
        void onStopSessionDatasClicked();
        Track loadTrack(Long trackId);
        Session loadSession(Long sessionId);
    }

    public interface DatasPresenter {
        void setView(SessionDatasContract.DatasView view);

    }
}
