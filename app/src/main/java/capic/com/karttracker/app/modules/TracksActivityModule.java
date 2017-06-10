package capic.com.karttracker.app.modules;

import android.widget.ArrayAdapter;

import java.util.List;

import javax.inject.Named;

import capic.com.karttracker.R;
import capic.com.karttracker.app.ActivityScope;
import capic.com.karttracker.services.datas.models.Track;
import capic.com.karttracker.ui.tracks.TrackItemAdapter;
import capic.com.karttracker.ui.tracks.TracksActivity;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Vincent on 09/06/2017.
 */

@Module
public class TracksActivityModule {
    private final TracksActivity mActivity;

    public TracksActivityModule(TracksActivity activity) {
        mActivity = activity;
    }

    @Provides
    @ActivityScope
//    @Named("tracks_list_adapter")
    public ArrayAdapter<Track> provideTracksListAdapter(List<Track> list) {
        return new TrackItemAdapter(mActivity, R.layout.track_list_item, list);
    }
}
