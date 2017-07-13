package capic.com.karttracker.ui.tracks;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import capic.com.karttracker.KartTracker;
import capic.com.karttracker.R;
import capic.com.karttracker.services.datas.models.Track;


/**
 * Created by Vincent on 25/04/2017.
 */

public class TrackItemAdapter extends ArrayAdapter<Track> implements Filterable {
    TracksPresenter  mPresenter;

    private List<Track> mTracksList;
    private List<Track> mTracksFilteredList;
    private ValueFilter filter;

    public TrackItemAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Track> objects) {
        super(context, resource, objects);

        ((KartTracker)context.getApplicationContext()).getAppComponent().inject(this);

        this.mTracksList = objects;
        this.mTracksFilteredList = objects;
    }

//    public void setPresenter(TracksContract.Presenter presenter) {
//        mPresenter = presenter;
//    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("getView", "position: " + position + " | taille: " + mTracksList.size());

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

        Log.d("getView", "trackData: " + track.toString());

        return convertView;
    }

    /**
     * Get size of user list
     * @return userList size
     */
    @Override
    public int getCount() {
        return mTracksFilteredList.size();
    }

    /**
     * Get specific item from user list
     * @param i item index
     * @return list item
     */
    @Override
    public Track getItem(int i) {
        return mTracksFilteredList.get(i);
    }

    @Override
    public Filter getFilter() {
        // TODO Auto-generated method stub
        if(filter == null)
        {
            filter=new ValueFilter();
        }
        return filter;
    }


    class ViewHolder {
        Track track;

        @BindView(R.id.track_name_text)
        TextView trackNameText;


        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    private class ValueFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();
            if (constraint!=null && constraint.length()>0) {
                ArrayList<Track> tempList = new ArrayList<>();

                for (Track track : mTracksList) {
                    if (track.getMName().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        tempList.add(track);
                    }
                }

                filterResults.count = tempList.size();
                filterResults.values = tempList;
            } else {
                filterResults.count = mTracksList.size();
                filterResults.values = mTracksList;
            }

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mTracksFilteredList=(List<Track>)results.values;
            notifyDataSetChanged();
        }
    }
}
