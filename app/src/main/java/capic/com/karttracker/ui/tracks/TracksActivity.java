package capic.com.karttracker.ui.tracks;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnItemSelected;
import capic.com.karttracker.KartTracker;
import capic.com.karttracker.R;
import capic.com.karttracker.services.datas.models.Track;
import capic.com.karttracker.ui.tracksessions.TrackSessionsActivity;


public class TracksActivity extends AppCompatActivity
        implements TracksContract.View, NavigationView.OnNavigationItemSelectedListener {

    @Inject
    TracksContract.Presenter mPresenter;

    @BindView(R.id.tracksListView)
    ListView mTracksListView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fab)
    FloatingActionButton mFab;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracks);

        ((KartTracker)getApplication()).getAppComponent().inject(this);

        ButterKnife.bind(this);

        mPresenter.setView(this);

        setUp();

        mPresenter.loadTracks();
    }

    protected void setUp() {
        setSupportActionBar(toolbar);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            /*
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                hideKeyboard();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
            */
        };

        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @OnItemClick(R.id.tracksListView)
    void onItemClick(int position) {
        Track track = (Track)mTracksListView.getAdapter().getItem(position);
        mPresenter.onTrackItemClicked(track);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @OnClick(R.id.fab)
    public void onCreateTrackClicked() {
        mPresenter.onCreateTrackClicked();
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
    public void showTracks(List<Track> tracksList) {
        ArrayAdapter<Track> adapter = new TrackItemAdapter(this, R.layout.track_list_item, tracksList);
        mTracksListView.setAdapter(adapter);
//        mTracksListView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void showCreateTrack() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.create_track_dialog_title)
                .setView(R.layout.create_track_dialog)
                .setPositiveButton(R.string.create_track_dialog_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText editText = (EditText) ((AlertDialog) dialog).findViewById(R.id.create_track_dialog_track_name_edittext);

                        Log.d("TracksActivity.onCreate", editText.getText().toString());

                        Track trackData = new Track();
                        trackData.setMName(editText.getText().toString());

                        trackData = mPresenter.createTrack(trackData);

                        openSessionsTrackActivity(trackData.getMId(), new Date());

                    }
                })
                .setNegativeButton(R.string.create_track_dialog_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("TracksActivity.onCreate", "dedans1");
                    }
                })
                .show();
    }
    @Override
    public void openSessionsTrackActivity(Long trackId, Date sessionDate) {
        Intent intent = TrackSessionsActivity.getStartIntent(TracksActivity.this);
        intent.putExtra("trackId", trackId);
        intent.putExtra("sessionDate", sessionDate.getTime());
        startActivity(intent);
        finish();
    }


}
