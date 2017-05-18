package capic.com.karttracker.ui.tracks;

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

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import capic.com.karttracker.R;
import capic.com.karttracker.services.datas.models.Track;


/**
 * Created by Vincent on 25/04/2017.
 */

public class TrackItemAdapter extends ArrayAdapter<Track> {
    TracksContract.Presenter mPresenter;

    private List<Track> mTracksList;

    public TrackItemAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Track> objects) {
        super(context, resource, objects);
        this.mTracksList = objects;
    }

    public void setPresenter(TracksContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("TrackItemAdapter.getView", "position: " + position + " | taille: " + mTracksList.size());

        ViewHolder holder;

        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.track_list_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        Track track = getItem(position);
        holder.trackNameText.setText(track.getMName());
        holder.track = track;

        Log.d("TrackItemAdapter.getView", "trackData: " + track.toString());

        return convertView;
    }

    class ViewHolder {
        Track track;

        @BindView(R.id.track_name_text)
        TextView trackNameText;

        @BindView(R.id.start_session_image)
        ImageView startSessionImage;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        @OnClick(R.id.start_session_image)
        void onClick() {
            mPresenter.onTrackPlayItemClicked(track);
        }
    }
}
