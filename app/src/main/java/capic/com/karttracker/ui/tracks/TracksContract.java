package capic.com.karttracker.ui.tracks;

import com.arellomobile.mvp.MvpView;

import org.joda.time.LocalDate;

import java.util.List;

import capic.com.karttracker.services.datas.models.Track;

/**
 * Created by capic on 29/04/2017.
 */

public interface TracksContract {
    public interface View extends MvpView {
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
}
