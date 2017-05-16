package capic.com.karttracker.ui.tracks;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import capic.com.karttracker.R;
import capic.com.karttracker.services.datas.models.Track;


/**
 * Created by Vincent on 25/04/2017.
 */

public class TrackItemAdapter extends ArrayAdapter<Track> {

    private List<Track> mTracksList;

    public TrackItemAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Track> objects) {
        super(context, resource, objects);
        this.mTracksList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("TrackItemAdapter.getView", "position: " + position + " | taille: " + mTracksList.size());

        Track track = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.track_list_item, parent, false);
        }

        TextView trackNameText = (TextView) convertView.findViewById(R.id.track_name_text);

        trackNameText.setText(track.getMName());

        Log.d("TrackItemAdapter.getView", "trackData: " + track.toString());

        return convertView;
    }
}
