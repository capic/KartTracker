package capic.com.karttracker.ui.sessiondatas;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import javax.inject.Inject;

import capic.com.karttracker.services.datas.models.SessionData;
import capic.com.karttracker.services.datas.models.SessionGpsData;
import capic.com.karttracker.services.datas.repositories.sessiondatas.SessionDatasRepository;

/**
 * Created by Vincent on 01/06/2017.
 */

@InjectViewState
public class SessionDatasDatasPresenter extends MvpPresenter<SessionDatasContract.DatasView> {

    SessionDatasRepository mRepository;

    @Inject
    public SessionDatasDatasPresenter(SessionDatasRepository repository) {
        this.mRepository = repository;
    }

    public void loadSessionDatas(Long sessionId) {
        getViewState().showLoading();

        List<SessionData> list = mRepository.getSessionDatasBySession(sessionId);
        getViewState().showSessionDatas(list);

        getViewState().hideLoading();
    }
}
