package capic.com.karttracker.ui.tracks;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;

import android.support.v7.widget.SearchView;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.joda.time.LocalDate;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import capic.com.karttracker.KartTracker;
import capic.com.karttracker.R;
import capic.com.karttracker.services.datas.models.Track;
import capic.com.karttracker.services.gps.GpsService;
import capic.com.karttracker.ui.tracksessiondates.TrackSessionDatesActivity;
import capic.com.karttracker.ui.tracksessions.TrackSessionsActivity;
import capic.com.karttracker.utils.ServiceUtils;


public class TracksActivity extends AppCompatActivity
        implements TracksContract.View, NavigationView.OnNavigationItemSelectedListener, SearchView.OnQueryTextListener  {

    private static final int REQUEST_CODE = 100;

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

//    @BindView(R.id.search_view)
//    SearchView searchView;

    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracks);

        runtimePermissions();

        ((KartTracker)getApplication()).getAppComponent().inject(this);

        ButterKnife.bind(this);

        mPresenter.setView(this);

        setUp();

        mPresenter.loadTracks();
    }

    protected void setUp() {
        setTitle(R.string.title_activity_tracks);
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

    protected boolean runtimePermissions() {
        if (Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_CODE);

            return true;
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
/*
        if (requestCode == REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                startGpsService();
            }
        }
 */
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

//        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = (SearchView) menu.findItem(R.id.listsearch).getActionView();

//        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);

        searchView.setOnQueryTextListener(this);
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
        ((TrackItemAdapter)adapter).setPresenter(mPresenter);
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

                        openTrackSessionsActivity(trackData.getMId(), LocalDate.now());

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
    public void openTrackSessionsActivity(Long trackId, LocalDate sessionDate) {
        Intent intent = TrackSessionsActivity.getStartIntent(TracksActivity.this);
        intent.putExtra("trackId", trackId);
        intent.putExtra("sessionDate", sessionDate.toDate().getTime());
        startActivity(intent);
//        finish();
    }

    @Override
    public void openTrackSessionDatesActivity(Long trackId) {
        Intent intent = TrackSessionDatesActivity.getStartIntent(TracksActivity.this);
        intent.putExtra("trackId", trackId);
        startActivity(intent);
//        finish();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        ((TrackItemAdapter)mTracksListView.getAdapter()).getFilter().filter(newText);
        return false;
    }

    @Override
    public void startGpsService() {
        ServiceUtils.startGpsService(this);
//        startService(new Intent(this, GpsService.class).putExtra("request", true));
    }

    @Override
    public void stopGpsService() {
        ServiceUtils.stopGpsService(this);
//        stopService(new Intent(this, GpsService.class).putExtra("remove", true));
    }
}
