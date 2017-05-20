package capic.com.karttracker.ui.tracksessiondates;

import org.joda.time.LocalDate;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import capic.com.karttracker.services.datas.models.Track;
import capic.com.karttracker.ui.tracks.TracksContract;

/**
 * Created by capic on 16/05/2017.
 */

public interface TrackSessionDatesContract {
    public interface View {
        void showLoading();
        void hideLoading();
        void showTrackSessionDate(List<LocalDate> trackSessionDatesList);
        void openTrackSessionsActivity(Long trackId, LocalDate date);
    }

    public interface Presenter {
        void setView(TrackSessionDatesContract.View view);
        void loadTrackSessionDates(Long trackId);
        void onTrackSessionDateItemClicked(Long trackId, LocalDate date);
        void onStartNewSessionClicked(Long trackId);
    }
}
