package capic.com.karttracker.ui.sessiondatas;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import org.joda.time.LocalDate;

import java.util.Date;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import capic.com.karttracker.KartTracker;
import capic.com.karttracker.R;
import capic.com.karttracker.services.datas.models.Session;
import capic.com.karttracker.services.datas.models.Track;
import capic.com.karttracker.services.gps.GpsService;
import capic.com.karttracker.utils.ButterKnifeUtils;
import capic.com.karttracker.utils.ServiceUtils;

public class SessionDataMapsActivity extends FragmentActivity implements SessionDatasContract.MapsView, OnMapReadyCallback, SessionDatasFragment.OnFragmentInteractionListener {
    private Track mTrack;
    private Session mSession;

    @Inject
    SessionDatasContract.MapsPresenter mPresenter;

    @BindView(R.id.button_stop)
    Button buttonStopSessionData;

//    @BindView(R.id.toolbar_session_data_maps)
//    Toolbar mToolbar;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, SessionDataMapsActivity.class);
        return intent;
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

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if (extras.containsKey("trackId")) {
                mTrack = mPresenter.loadTrack(extras.getLong("trackId"));
            }

            if (extras.containsKey("sessionId")) {
                mSession = mPresenter.loadSession(extras.getLong("sessionId"));
            }
        }

        ButterKnife.bind(this);

        setUp();

        mPresenter.setView(this);
    }

    protected void setUp() {
//        setSupportActionBar(mToolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String trackName =  (mTrack != null) ? mTrack.getMName() : "";
        String sessionName = (mSession != null) ? getResources().getString(R.string.track_session_name_text, mSession.getMIdOfDay()) : "";

        setTitle(getResources().getString(R.string.title_activity_session_data_maps, trackName, sessionName));

        ButterKnife.apply(buttonStopSessionData, ButterKnifeUtils.VISIBILITY, ServiceUtils.isMyServiceRunning(this, GpsService.class) ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }

    @OnClick(R.id.button_stop)
    public void onStopSessionDatasClicked() {
        mPresenter.onStopSessionDatasClicked();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void stopGpsService() {
        stopService(new Intent(this, GpsService.class).putExtra("remove", true));
        finish();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
