package capic.com.karttracker.ui.tracksessiondates;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.joda.time.LocalDate;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import capic.com.karttracker.KartTracker;
import capic.com.karttracker.R;
import capic.com.karttracker.services.datas.models.Session;

/**
 * Created by capic on 15/05/2017.
 */

public class TrackSessionDateItemAdapter extends ArrayAdapter<LocalDate>  {
    private DateFormat mDateFormat;

    private List<LocalDate> mTrackSessionDatesList;

    public TrackSessionDateItemAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<LocalDate> objects) {
        super(context, resource,objects);
        this.mTrackSessionDatesList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        Log.d("TrackSessionDateItemAdapter.getView", "position: " + position + " | taille: " + mTrackSessionDatesList.size());

        LocalDate date = getItem(position);

        if (date != null) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.track_session_date_list_item, parent, false);
            }

            TextView dateText = (TextView) convertView.findViewById(R.id.track_session_date_text);
            dateText.setText(date.toString(((KartTracker)((Activity)getContext()).getApplication()).getDateFormat().toLocalizedPattern()));

//            Log.d("TrackSessionItemAdapter.getView", "sesionData: " + session.toString());
        }

        return convertView;
    }
}
