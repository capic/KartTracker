package capic.com.karttracker.adapters;

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

import capic.com.greendao_gen.models.Track;
import capic.com.karttracker.R;


/**
 * Created by Vincent on 25/04/2017.
 */

public class TrackAdapter extends ArrayAdapter<Track> {

    private List<Track> tracksList;

    public TrackAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Track> objects) {
        super(context, resource, objects);
        this.tracksList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("TrackAdapter.getView", "position: " + position + " | taille: " + tracksList.size());

        Track track = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.track_list_item, parent, false);
        }

        TextView trackNameText = (TextView) convertView.findViewById(R.id.track_name_text);

        trackNameText.setText(track.getMName());

        Log.d("TrackAdapter.getView", "track: " + track.toString());

        return convertView;
    }
}
