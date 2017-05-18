package capic.com.karttracker.ui.tracksessiondates;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.joda.time.LocalDate;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import capic.com.karttracker.KartTracker;
import capic.com.karttracker.R;
import capic.com.karttracker.ui.tracksessions.TrackSessionsActivity;

public class TrackSessionDatesActivity extends AppCompatActivity implements TrackSessionDatesContract.View {
    private Long mTrackId;

    @Inject
    TrackSessionDatesContract.Presenter mPresenter;

    @BindView(R.id.trackSessionDatesListView)
    ListView mTrackSessionDatesListView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, TrackSessionDatesActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_session_dates);

        mTrackId = null;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mTrackId = extras.getLong("trackId");
        }

        ((KartTracker)getApplication()).getAppComponent().inject(this);

        ButterKnife.bind(this);

        mPresenter.setView(this);

        setUp();

        mPresenter.loadTrackSessionDates(mTrackId);
    }

    protected void setUp() {
//        setSupportActionBar(toolbar);

//        getActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("DEDANS");
        setSupportActionBar(toolbar);
    }

    @OnItemClick(R.id.trackSessionDatesListView)
    void onItemClick(int position) {
//        String pattern = ((SimpleDateFormat) mDateFormat).toLocalizedPattern();
//        DateTimeFormatter format = DateTimeFormat.forPattern(pattern);
        mPresenter.onTrackSessionDateItemClicked(mTrackId, (LocalDate)mTrackSessionDatesListView.getAdapter().getItem(position));
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
    public void showTrackSessionDate(List<LocalDate> trackSessionDatesList) {
        ArrayAdapter<LocalDate> adapter = new TrackSessionDateItemAdapter(this, R.layout.track_session_date_list_item, trackSessionDatesList);
        mTrackSessionDatesListView.setAdapter(adapter);
    }

    @Override
    public void openTrackSessionsActivity(Long trackId, LocalDate date) {
        Intent intent = TrackSessionsActivity.getStartIntent(TrackSessionDatesActivity.this);
        intent.putExtra("trackId", trackId);
        intent.putExtra("sessionDate", date.toDate().getTime());
        startActivity(intent);
        //finish();
    }
}
