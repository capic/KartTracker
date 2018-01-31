package capic.com.karttracker.ui;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

/**
 * Created by Vincent on 15/09/2017.
 */

@InjectViewState
public class HelloWorldPresenter extends MvpPresenter<HelloWorldView> {
    void setMessage(int mess) {
        getViewState().showMessage(mess);
    }
}
