package capic.com.karttracker.ui.sessiondatas;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnPageChange;
import capic.com.karttracker.KartTracker;
import capic.com.karttracker.R;
import capic.com.karttracker.services.datas.models.Session;
import capic.com.karttracker.services.datas.models.SessionGpsData;

/**
 * Created by Vincent on 08/06/2017.
 */

public class SessionDatasPagerFragment extends Fragment implements SessionDatasContract.DatasView {
    private static final String ARG_SESSION_ID = "sessionId";

    SessionDatasPagerAdapter mSessionDatasPagerAdapter;

    @Inject
    SessionDatasContract.DatasPresenter mPresenter;

    @BindView(R.id.session_datas_pager)
    ViewPager mPager;

    public  SessionDatasPagerFragment() {
    }

    public static SessionDatasPagerFragment newInstance(Long sessionId) {
        SessionDatasPagerFragment fragment = new SessionDatasPagerFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_SESSION_ID, sessionId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((KartTracker)getActivity().getApplication()).getAppComponent().inject(this);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_session_datas_pager, container, false);

        ButterKnife.bind(this, v);
        mPresenter.setView(this);

        if (getArguments() != null) {
            mPresenter.loadSessionGpsDatas(getArguments().getLong(ARG_SESSION_ID));
        }

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

    @OnPageChange
    public void onPageChange(int position) {
        Log.d("SessionDataPageFragment", "change => " + position);
        SessionGpsData sessionGpsData = ((SessionDatasFragment)mSessionDatasPagerAdapter.getItem(position)).getmSessionGpsData();
        Location location = new Location("");
        location.setLongitude(sessionGpsData.getMLongitude());
        location.setLatitude(sessionGpsData.getMLatitude());
        location.setAltitude(sessionGpsData.getMAltitude());

        LocalBroadcastManager.getInstance(getContext()).sendBroadcast(new Intent("googleLocation").putExtra("result", location));
    }
}
