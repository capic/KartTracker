package capic.com.karttracker.ui.sessiondatas;

/**
 * Created by Vincent on 01/06/2017.
 */

public class SessionDatasDatasPresenter implements SessionDatasContract.DatasPresenter {
    private SessionDatasContract.DatasView mView;

    @Override
    public void setView(SessionDatasContract.DatasView view) {
        mView = view;
    }
}
