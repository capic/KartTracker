package capic.com.karttracker.ui.tracksessions;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
import capic.com.karttracker.KartTracker;
import capic.com.karttracker.R;
import capic.com.karttracker.services.datas.models.Session;
import capic.com.karttracker.services.datas.models.Track;
import capic.com.karttracker.ui.tracks.TrackItemAdapter;

public class TrackSessionsActivity extends AppCompatActivity implements TrackSessionsContract.View {
    private Long mTrackId;
    private LocalDate mSessionDate;

    @Inject
    TrackSessionsContract.Presenter mPresenter;

    @BindView(R.id.trackSessionsListView)
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

        setTitle(getResources().getString(R.string.title_activity_track_sessions, mSessionDate.toString(((KartTracker)getApplication()).getDateFormat().toLocalizedPattern())));
    }

    @OnClick(R.id.fab)
    public void onStartNewSessionClicked() {
        mPresenter.onStartNewSessionClicked(mTrackId);
//        mTrackSessionsListView.getAdapter().notifyAll();
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
}
