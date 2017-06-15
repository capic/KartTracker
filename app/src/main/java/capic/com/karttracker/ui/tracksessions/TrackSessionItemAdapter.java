package capic.com.karttracker.ui.tracksessions;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import capic.com.karttracker.KartTracker;
import capic.com.karttracker.R;
import capic.com.karttracker.services.datas.models.Session;
import capic.com.karttracker.services.datas.models.Track;
import capic.com.karttracker.ui.tracks.TrackItemAdapter;

/**
 * Created by capic on 15/05/2017.
 */

public class TrackSessionItemAdapter extends ArrayAdapter<Session>  {
    private List<Session> mTrackSessionsList;

    public TrackSessionItemAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Session> objects) {
        super(context, resource, objects);
        this.mTrackSessionsList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("TgetView", "position: " + position + " | taille: " + mTrackSessionsList.size());

        TrackSessionItemAdapter.ViewHolder holder;

        if (convertView != null) {
            holder = (TrackSessionItemAdapter.ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.track_session_list_item, parent, false);
            holder = new TrackSessionItemAdapter.ViewHolder(convertView);
            convertView.setTag(holder);
        }

        Session session = getItem(position);
        holder.trackSessionNameText.setText(parent.getResources().getString(R.string.track_session_name_text, session.getMIdOfDay()));
        holder.trackSessionTimeText.setText(session.getMStartTime().toString(((KartTracker)((Activity)getContext()).getApplication()).getTimeFormat().toLocalizedPattern()));


        Log.d("getView", "sessionData: " + session);

        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.track_session_name_text)
        TextView trackSessionNameText;

        @BindView(R.id.track_session_time_text)
        TextView trackSessionTimeText;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
