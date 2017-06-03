package capic.com.karttracker.ui.sessiondatas;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.location.LocationResult;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import capic.com.karttracker.KartTracker;
import capic.com.karttracker.R;
import capic.com.karttracker.services.datas.models.SessionGpsData;
import capic.com.karttracker.services.datas.repositories.sessiongpsdatas.SessionGpsDatasRepository;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SessionDatasFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SessionDatasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SessionDatasFragment extends Fragment implements SessionDatasContract.DatasView {
    @BindView(R.id.text_longitude_value)
    TextView textLongitudeValue;

    @BindView(R.id.text_latitude_value)
    TextView textLatitudeValue;

    @BindView(R.id.text_altitude_value)
    TextView textAltitudeValue;

    @Inject
    SessionDatasContract.DatasPresenter mPresenter;

    private OnFragmentInteractionListener mListener;
    private InternalLocationReceiver mInternalLocationReceiver;

    public SessionDatasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SessionDatasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SessionDatasFragment newInstance(String param1, String param2) {
        SessionDatasFragment fragment = new SessionDatasFragment();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((KartTracker)getActivity().getApplication()).getAppComponent().inject(this);
        mInternalLocationReceiver = new InternalLocationReceiver(this);
        /*
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        */
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_session_datas, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(mInternalLocationReceiver, new IntentFilter("googleLocation"));
    }

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(mInternalLocationReceiver);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    protected void setValues(Location location) {
        if (location != null) {
            textLatitudeValue.setText(String.valueOf(location.getLatitude()));
            textLongitudeValue.setText(String.valueOf(location.getLongitude()));
            textAltitudeValue.setText(String.valueOf(location.getAltitude()));
        }
    }

    private static class InternalLocationReceiver extends BroadcastReceiver {
        private SessionDatasFragment mActivity;

        public InternalLocationReceiver(SessionDatasFragment mActivity) {
            this.mActivity = mActivity;
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            final SessionDatasFragment activity = mActivity;
            if (activity != null) {
                LocationResult result = intent.getParcelableExtra("result");
                Log.i("SessionDatasFragment", "Receive " + result.getLastLocation().getLatitude() + " " + result.getLastLocation().getLongitude());

                mActivity.setValues(result.getLastLocation());
            }
        }
    }
}
