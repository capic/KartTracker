package capic.com.karttracker.ui.tracks;

import org.joda.time.LocalDate;

import java.util.List;

import capic.com.karttracker.services.datas.models.Track;

/**
 * Created by capic on 29/04/2017.
 */

public interface TracksContract {
    public interface View {
        void showLoading();
        void hideLoading();
        void showTracks(List<Track> tracksList);
        void showCreateTrack();
        void addTrack(Track track);
        void removeTrack(Track track);
        void openTrackSessionsActivity(Long trackId, LocalDate sessionDate);
        void openTrackSessionDatesActivity(Long trackId);
        void showWarningDialogBoxTrackHasSessions(Track track);
    }

    public interface Presenter {
        void setView(TracksContract.View view);
        void loadTracks();
        void onCreateTrackClicked();
        void onCreateTrack(Track track);
        void onTrackItemClicked(Track track);
        void onTrackPlayItemClicked(Track track);
        void onDeleteTrackClicked(Track track);
        void onDeleteTrackWarningOkClicked(Track track);
    }
}
