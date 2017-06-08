package capic.com.karttracker.ui.sessiondatas;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import capic.com.karttracker.services.datas.models.SessionGpsData;
import capic.com.karttracker.services.datas.repositories.sessiongpsdatas.SessionGpsDatasRepository;

/**
 * Created by Vincent on 01/06/2017.
 */

public class SessionDatasDatasPresenter implements SessionDatasContract.DatasPresenter {
    private SessionDatasContract.DatasView mView;

    SessionGpsDatasRepository mRepository;

    @Inject
    public SessionDatasDatasPresenter(SessionGpsDatasRepository repository) {
        this.mRepository = repository;
    }

    @Override
    public void setView(SessionDatasContract.DatasView view) {
        mView = view;
    }

    @Override
    public void loadSessionGpsDatas(Long sessionId) {
        mView.showLoading();

        List<SessionGpsData> list = mRepository.getSessionGpsDatasBySession(sessionId);
        mView.showSessionDatasGps(list);

        mView.hideLoading();
    }

    @Override
    public void loadForTracking() {
        mView.showLoading();

        List<SessionGpsData> list = new ArrayList<>(1);
        list.add(new SessionGpsData());
        mView.showSessionDatasGps(list);

        mView.hideLoading();
    }
}
