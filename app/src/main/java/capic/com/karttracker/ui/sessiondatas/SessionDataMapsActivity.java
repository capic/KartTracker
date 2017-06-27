package capic.com.karttracker.ui.sessiondatas;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import capic.com.karttracker.KartTracker;
import capic.com.karttracker.R;
import capic.com.karttracker.services.datas.models.Session;
import capic.com.karttracker.services.datas.models.SessionData;
import capic.com.karttracker.services.datas.models.SessionGpsData;
import capic.com.karttracker.services.datas.models.Track;
import capic.com.karttracker.services.sensors.gps.GpsService;
import capic.com.karttracker.utils.ButterKnifeUtils;
import capic.com.karttracker.utils.Constants;
import capic.com.karttracker.utils.ServiceUtils;

public class SessionDataMapsActivity extends FragmentActivity implements SessionDatasContract.MapsView, OnMapReadyCallback, SessionDatasFragment.OnFragmentInteractionListener {
    @Inject
    SessionDatasContract.MapsPresenter mPresenter;

    @BindView(R.id.button_stop)
    Button buttonStopSessionData;

    private MapsLocationReceiver mInternalLocationReceiver;

//    @BindView(R.id.toolbar_session_data_maps)
//    Toolbar mToolbar;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, SessionDataMapsActivity.class);
    }

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_data_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        ((KartTracker)getApplication()).getAppComponent().inject(this);

        mPresenter.setView(this);

        Track track = null;
        Session session = null;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if (extras.containsKey("trackId")) {
                track = mPresenter.loadTrack(extras.getLong("trackId"));
            }

            if (extras.containsKey("sessionId")) {
                session = mPresenter.loadSession(extras.getLong("sessionId"));
//                mPresenter.loadSessionGpsDatas(session.getMId());

                SessionDatasPagerFragment sessionDatasPagerFragment = SessionDatasPagerFragment.newInstance(session.getMId());
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.session_datas_frame, sessionDatasPagerFragment);
                transaction.commit();
            } else {
                if (track != null) {
                    mPresenter.startNewSession(this, track.getMId());
                    SessionDatasFragment sessionDatasFragment = SessionDatasFragment.newInstance();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.add(R.id.session_datas_frame, sessionDatasFragment);
                    transaction.commit();
//                    sessionDatasPagerFragment.initForTracking();
                }
            }
        }

        mInternalLocationReceiver = new MapsLocationReceiver(this);

        ButterKnife.bind(this);


        setUp(track, session);

        mPresenter.setView(this);
    }

    protected void setUp(Track track, Session session) {
//        setSupportActionBar(mToolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String trackName =  (track != null) ? track.getMName() : "";
        String sessionName = (session != null) ? getResources().getString(R.string.track_session_name_text, session.getMIdOfDay()) : "";

        setTitle(getResources().getString(R.string.title_activity_session_data_maps, trackName, sessionName));

        ButterKnife.apply(buttonStopSessionData, ButterKnifeUtils.VISIBILITY, ServiceUtils.isMyServiceRunning(this, GpsService.class) ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }

    @OnClick(R.id.button_stop)
    public void onStopSessionDatasClicked() {
        mPresenter.onStopSessionDatasClicked(this);
        finish();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mPresenter.onStopSessionDatasClicked(this);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(mInternalLocationReceiver, new IntentFilter(Constants.BROADCASTER_SESSION_DATA_INSTANT_NAME));
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mInternalLocationReceiver);
    }

    public void markStartingLocationOnMap(SessionGpsData sessionGpsData){
        if (mMap != null && sessionGpsData != null) {
            mMap.clear();

            LatLng locationMarker = new LatLng(sessionGpsData.getMLatitude(), sessionGpsData.getMLongitude());
            mMap.addMarker(new MarkerOptions().position(locationMarker).title("Current location"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(locationMarker));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(locationMarker)
                    .zoom(16)
                    .build();
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }

    }

    public void drawRouteOnMap(SessionData[] sessionDataArray){
        if (mMap != null && sessionDataArray != null) {
            PolylineOptions options = new PolylineOptions().width(5);

            if (sessionDataArray[0] != null) {
                options.color(Color.RED);
                options.add(
                        new LatLng(sessionDataArray[0].getMSessionGpsData().getMLatitude(), sessionDataArray[0].getMSessionGpsData().getMLongitude()),
                        new LatLng(sessionDataArray[1].getMSessionGpsData().getMLatitude(), sessionDataArray[1].getMSessionGpsData().getMLongitude()));
            }

            if (sessionDataArray[2] != null) {
                options.color(Color.GREEN);
                options.add(
                        new LatLng(sessionDataArray[1].getMSessionGpsData().getMLatitude(), sessionDataArray[1].getMSessionGpsData().getMLongitude()),
                        new LatLng(sessionDataArray[2].getMSessionGpsData().getMLatitude(), sessionDataArray[2].getMSessionGpsData().getMLongitude()));
            }


            mMap.addPolyline(options);
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(sessionDataArray[1].getMSessionGpsData().getMLatitude(), sessionDataArray[1].getMSessionGpsData().getMLongitude()))
                    .zoom(16)
                    .bearing(90)
                    .tilt(40)
                    .build();
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }
    }
}
