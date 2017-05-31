package capic.com.karttracker.ui.tracksessions;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import capic.com.karttracker.KartTracker;
import capic.com.karttracker.R;
import capic.com.karttracker.services.datas.models.Session;
import capic.com.karttracker.services.datas.models.Track;
import capic.com.karttracker.services.gps.GpsService;
import capic.com.karttracker.ui.sessiondatas.SessionDataMapsActivity;
import capic.com.karttracker.ui.tracks.TrackItemAdapter;
import capic.com.karttracker.ui.tracks.TracksActivity;
import capic.com.karttracker.ui.tracksessiondates.TrackSessionDatesActivity;
import capic.com.karttracker.utils.ButterKnifeUtils;

public class TrackSessionsActivity extends AppCompatActivity implements TrackSessionsContract.View {
    private Long mTrackId;
    private LocalDate mSessionDate;

    @Inject
    TrackSessionsContract.Presenter mPresenter;

    @BindView(R.id.track_sessions_list_view)
    ListView mTrackSessionsListView;

    @BindView(R.id.toolbar_track_session)
    Toolbar toolbar;

    @BindView(R.id.fab)
    FloatingActionButton mFab;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, TrackSessionsActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_sessions);

        mTrackId = null;
        mSessionDate = LocalDate.now();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mTrackId = extras.getLong("trackId");
            mSessionDate = new LocalDate(new Date(extras.getLong("sessionDate")));
        }

        ((KartTracker)getApplication()).getAppComponent().inject(this);

        ButterKnife.bind(this);

        mPresenter.setView(this);

        setUp();

        mPresenter.loadTrackSessions(mTrackId, mSessionDate);
    }

    protected void setUp() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.apply(mFab, ButterKnifeUtils.VISIBILITY, mSessionDate.equals(LocalDate.now()) ? View.VISIBLE : View.GONE);

        setTitle(getResources().getString(R.string.title_activity_track_sessions, mSessionDate.toString(((KartTracker)getApplication()).getDateFormat().toLocalizedPattern())));
    }

    @OnClick(R.id.fab)
    public void onStartNewSessionClicked() {
        mPresenter.onStartNewSessionClicked(mTrackId);
//        mTrackSessionsListView.getAdapter().notifyAll();
    }

    @OnItemClick(R.id.track_sessions_list_view)
    void onItemClick(int position) {
        Session session = (Session)mTrackSessionsListView.getAdapter().getItem(position);
        mPresenter.onTrackSessionItemClicked(session);
    }

    @Override
    public void showLoading() {
        Log.i("ShowLoading", "Loading !!!!!!");
    }

    @Override
    public void hideLoading() {
        Log.i("HideLoading", "End of Loading !!!!!!");
    }

    @Override
    public void showTrackSession(List<Session> trackSessionsList) {
        ArrayAdapter<Session> adapter = new TrackSessionItemAdapter(this, R.layout.track_session_list_item, trackSessionsList);
        mTrackSessionsListView.setAdapter(adapter);
    }

    @Override
    public void openSessionDatasActivity(Long sessionId) {
        Intent intent = SessionDataMapsActivity.getStartIntent(TrackSessionsActivity.this);
        intent.putExtra("trackId", mTrackId);
        intent.putExtra("sessionId", sessionId);
        startActivity(intent);
    }

    @Override
    public void startGpsService() {
        startService(new Intent(this, GpsService.class).putExtra("request", true));
    }

    @Override
    public void stopGpsService() {
        stopService(new Intent(this, GpsService.class).putExtra("remove", true));
    }
}
