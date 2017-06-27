package capic.com.karttracker.ui.sessiondatas;

import java.util.List;

import javax.inject.Inject;

import capic.com.karttracker.services.datas.models.SessionData;
import capic.com.karttracker.services.datas.repositories.sessiondatas.SessionDatasRepository;

/**
 * Created by Vincent on 01/06/2017.
 */

public class SessionDatasDatasPresenter implements SessionDatasContract.DatasPresenter {
    private SessionDatasContract.DatasView mView;

    SessionDatasRepository mRepository;

    @Inject
    public SessionDatasDatasPresenter(SessionDatasRepository repository) {
        this.mRepository = repository;
    }

    @Override
    public void setView(SessionDatasContract.DatasView view) {
        mView = view;
    }

    @Override
    public void loadSessionDatas(Long sessionId) {
        mView.showLoading();

        List<SessionData> list = mRepository.getSessionDatasBySession(sessionId);
        mView.showSessionDatas(list);

        mView.hideLoading();
    }
}
