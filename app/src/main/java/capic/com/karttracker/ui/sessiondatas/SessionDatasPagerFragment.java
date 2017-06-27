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

import com.google.android.gms.location.LocationResult;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnPageChange;
import capic.com.karttracker.KartTracker;
import capic.com.karttracker.R;
import capic.com.karttracker.services.datas.models.Session;
import capic.com.karttracker.services.datas.models.SessionData;
import capic.com.karttracker.services.datas.models.SessionGpsData;
import capic.com.karttracker.utils.Constants;

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
            mPresenter.loadSessionDatas(getArguments().getLong(ARG_SESSION_ID));
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
    public void showSessionDatas(List<SessionData> list) {
        mSessionDatasPagerAdapter = new SessionDatasPagerAdapter(getChildFragmentManager(), list);
        mPager.setAdapter(mSessionDatasPagerAdapter);

        LocalBroadcastManager.getInstance(getContext()).sendBroadcast(new Intent(Constants.BROADCASTER_SESSION_DATA_INSTANT_NAME).putExtra(Constants.BROADCASTER_SESSION_DATA_EXTRA_DATAS_NAME, mSessionDatasPagerAdapter.getSessionData(0)));
    }

    @OnPageChange
    public void onPageChange(int position) {
        Log.d("SessionDataPageFragment", "change => " + position);

        SessionData[] sessionDataArray = new SessionData[3];
        if (position > 0) {
            sessionDataArray[0] = mSessionDatasPagerAdapter.getSessionData(position - 1);
        }
        sessionDataArray[1] = mSessionDatasPagerAdapter.getSessionData(position);
        if (position < mSessionDatasPagerAdapter.getCount() - 1) {
            sessionDataArray[2] = mSessionDatasPagerAdapter.getSessionData(position + 1);
        }

        LocalBroadcastManager.getInstance(getContext()).sendBroadcast(new Intent(Constants.BROADCASTER_SESSION_DATA_INSTANT_NAME).putExtra(Constants.BROADCASTER_SESSION_DATA_EXTRA_DATAS_ARRAY_NAME, sessionDataArray));
    }
}
