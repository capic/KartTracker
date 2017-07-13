package capic.com.karttracker.ui.tracksessiondates;

import com.arellomobile.mvp.MvpView;

import org.joda.time.LocalDate;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import capic.com.karttracker.services.datas.models.Session;
import capic.com.karttracker.services.datas.models.Track;
import capic.com.karttracker.ui.tracks.TracksContract;

/**
 * Created by capic on 16/05/2017.
 */

public interface TrackSessionDatesContract {
    public interface View extends MvpView {
        void showLoading();
        void hideLoading();
        void showTrackSessionDate(List<LocalDate> trackSessionDatesList);
        void openTrackSessionsActivity(LocalDate date);
        void openSessionDatasActivity();
    }
}
