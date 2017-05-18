package capic.com.karttracker.ui.tracksessions;

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
import capic.com.karttracker.services.datas.models.Session;
import capic.com.karttracker.services.datas.models.Track;

/**
 * Created by capic on 15/05/2017.
 */

public class TrackSessionItemAdapter extends ArrayAdapter<Session>  {
    private List<Session> mTrackSessionsList;

    public TrackSessionItemAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Session> objects) {
        super(context, resource);
        this.mTrackSessionsList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("TrackSessionItemAdapter.getView", "position: " + position + " | taille: " + mTrackSessionsList.size());

        Session session = getItem(position);

        if (session != null) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.track_session_list_item, parent, false);
            }

            TextView trackNameText = (TextView) convertView.findViewById(R.id.track_session_name_text);

            trackNameText.setText(parent.getResources().getString(R.string.title_activity_track_sessions, session.getMIdOfDay().toString()));

            Log.d("TrackSessionItemAdapter.getView", "sesionData: " + session.toString());
        }

        return convertView;
    }
}
