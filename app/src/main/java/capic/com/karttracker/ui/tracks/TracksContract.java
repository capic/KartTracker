package capic.com.karttracker.ui.tracks;

import org.joda.time.LocalDate;

import java.util.Date;
import java.util.List;

import capic.com.karttracker.services.datas.models.Track;

/**
 * Created by capic on 29/04/2017.
 */

public class TracksContract {
    public interface View {
        void showLoading();
        void hideLoading();
        void showTracks(List<Track> tracksList);
        void showCreateTrack();
        void openTrackSessionsActivity(Long trackId, LocalDate sessionDate);
        void openTrackSessionDatesActivity(Long trackId);
    }

    public interface Presenter {
        void setView(TracksContract.View view);
        void loadTracks();
        void onCreateTrackClicked();
        Track createTrack(Track track);
        void onTrackItemClicked(Track track);
        void onTrackPlayItemClicked(Track track);
    }
}
