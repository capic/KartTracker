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

import butterknife.BindView;
import butterknife.ButterKnife;
import capic.com.karttracker.R;
import capic.com.karttracker.services.datas.models.SessionGpsData;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SessionDatasFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SessionDatasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SessionDatasFragment extends Fragment {
    private SessionGpsData mSessionGpsData;

    @BindView(R.id.text_longitude_value)
    TextView textLongitudeValue;

    @BindView(R.id.text_latitude_value)
    TextView textLatitudeValue;

    @BindView(R.id.text_altitude_value)
    TextView textAltitudeValue;

    static  SessionDatasFragment init(SessionGpsData val) {
        SessionDatasFragment inst = new SessionDatasFragment();
        // Supply val input as an argument.
        Bundle args = new Bundle();
        args.putSerializable("val", val);
        inst.setArguments(args);
        return inst;
    }

    private DatasLocationReceiver mInternalLocationReceiver;

    public SessionDatasFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static SessionDatasFragment newInstance() {
        SessionDatasFragment fragment = new SessionDatasFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mSessionGpsData = (SessionGpsData) getArguments().getSerializable("val");
            mInternalLocationReceiver = new DatasLocationReceiver(this);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_session_datas, container, false);
        ButterKnife.bind(this, view);

        if (mSessionGpsData != null) {
            textLongitudeValue.setText(String.valueOf(mSessionGpsData.getMLongitude()));
            textLatitudeValue.setText(String.valueOf(mSessionGpsData.getMLatitude()));
            textAltitudeValue.setText(String.valueOf(mSessionGpsData.getMAltitude()));
        }

        return view;
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
    public void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(mInternalLocationReceiver, new IntentFilter("googleLocation"));
    }

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(mInternalLocationReceiver);
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
}
