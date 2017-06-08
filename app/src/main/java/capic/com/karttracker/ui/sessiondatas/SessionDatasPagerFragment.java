package capic.com.karttracker.ui.sessiondatas;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import capic.com.karttracker.KartTracker;
import capic.com.karttracker.R;
import capic.com.karttracker.services.datas.models.Session;
import capic.com.karttracker.services.datas.models.SessionGpsData;

/**
 * Created by Vincent on 08/06/2017.
 */

public class SessionDatasPagerFragment extends Fragment implements SessionDatasContract.DatasView {
    SessionDatasPagerAdapter mSessionDatasPagerAdapter;

    private Session mSession;

    @Inject
    SessionDatasContract.DatasPresenter mPresenter;

    @BindView(R.id.session_datas_pager)
    ViewPager mPager;

    public  SessionDatasPagerFragment() {
    }

    public static SessionDatasPagerFragment newInstance() {
        SessionDatasPagerFragment fragment = new SessionDatasPagerFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public void init(Session session) {
        mSession = session;
        mPresenter.loadSessionGpsDatas(mSession.getMId());
    }

    public void initForTracking() {
        mSession = null;
        mPresenter.loadForTracking();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((KartTracker)getActivity().getApplication()).getAppComponent().inject(this);

        mPresenter.setView(this);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_session_datas_pager, container, false);

        ButterKnife.bind(this, v);

        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showSessionDatasGps(List<SessionGpsData> list) {
        mSessionDatasPagerAdapter = new SessionDatasPagerAdapter(getChildFragmentManager(), list);
        mPager.setAdapter(mSessionDatasPagerAdapter);
    }
}
