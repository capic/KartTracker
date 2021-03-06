package capic.com.karttracker.ui.sessiondatas;

import android.content.Context;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import capic.com.karttracker.R;
import capic.com.karttracker.services.datas.models.SessionAccelerometerData;
import capic.com.karttracker.services.datas.models.SessionData;
import capic.com.karttracker.services.datas.models.SessionGpsData;
import capic.com.karttracker.utils.Constants;
import capic.com.karttracker.utils.SessionUtils;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SessionDatasFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SessionDatasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SessionDatasFragment extends Fragment {
    @BindView(R.id.text_longitude_value)
    TextView textLongitudeValue;

    @BindView(R.id.text_latitude_value)
    TextView textLatitudeValue;

    @BindView(R.id.text_altitude_value)
    TextView textAltitudeValue;

    @BindView(R.id.text_speed_value)
    TextView textSpeedValue;

    @BindView(R.id.text_gforce_x_value)
    TextView textGForceXValue;

    @BindView(R.id.text_gforce_y_value)
    TextView textGForceYValue;

    @BindView(R.id.text_gforce_z_value)
    TextView textGForceZValue;

    static  SessionDatasFragment init(SessionData val) {
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_session_datas, container, false);
        ButterKnife.bind(this, view);

        if (getArguments() != null) {
            setValues((SessionData) getArguments().getSerializable("val"));
            mInternalLocationReceiver = new DatasLocationReceiver(this);
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
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(mInternalLocationReceiver, new IntentFilter(Constants.BROADCASTER_SESSION_DATA_INSTANT_NAME));
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

    protected void setValues(SessionData sessionData) {
        if (sessionData != null) {
            if (sessionData.getMSessionGpsData() != null) {
                textLatitudeValue.setText(String.format(Locale.getDefault(), Constants.COORDINATE_FORMAT, sessionData.getMSessionGpsData().getMLatitude()));
                textLongitudeValue.setText(String.format(Locale.getDefault(), Constants.COORDINATE_FORMAT, sessionData.getMSessionGpsData().getMLongitude()));
                textAltitudeValue.setText(String.format(Locale.getDefault(), Constants.COORDINATE_FORMAT, sessionData.getMSessionGpsData().getMAltitude()));
                textSpeedValue.setText(String.format(Locale.getDefault(), Constants.SPEED_FORMAT, sessionData.getMSessionGpsData().getMSpeed()));
            }

            if (sessionData.getMSessionAccelerometerData() != null) {
                textGForceXValue.setText(String.format(Locale.getDefault(), Constants.GFORCE_FORMAT, /*SessionUtils.computeGForce(*/sessionData.getMSessionAccelerometerData().getMXAcceleration()))/*)*/;
                textGForceYValue.setText(String.format(Locale.getDefault(), Constants.GFORCE_FORMAT, /*SessionUtils.computeGForce(*/sessionData.getMSessionAccelerometerData().getMYAcceleration()))/*)*/;
                textGForceZValue.setText(String.format(Locale.getDefault(), Constants.GFORCE_FORMAT, /*SessionUtils.computeGForce(*/sessionData.getMSessionAccelerometerData().getMZAcceleration()))/*)*/;
            }
        }
    }
}
