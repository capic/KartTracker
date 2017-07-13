package capic.com.karttracker.ui.sessiondatas;

import android.content.Context;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import capic.com.karttracker.services.datas.models.Session;
import capic.com.karttracker.services.datas.models.SessionData;
import capic.com.karttracker.services.datas.models.SessionGpsData;
import capic.com.karttracker.services.datas.models.Track;

/**
 * Created by Vincent on 29/05/2017.
 */

public interface SessionDatasContract  {
    public interface MapsView extends MvpView {
        void showLoading();
        void hideLoading();
    }

    public interface DatasView extends MvpView {
        void showLoading();
        void hideLoading();
        void showSessionDatas(List<SessionData> list);
    }
}
