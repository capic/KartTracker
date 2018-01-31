package capic.com.karttracker.ui.sessiondatas;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import capic.com.karttracker.KartTracker;
import capic.com.karttracker.services.datas.models.Session;
import capic.com.karttracker.services.datas.models.SessionGpsData;
import capic.com.karttracker.services.datas.models.Track;
import capic.com.karttracker.services.datas.repositories.sessiondatas.SessionDatasRepository;
import capic.com.karttracker.services.datas.repositories.tracks.TracksRepository;
import capic.com.karttracker.services.datas.repositories.tracksessions.TrackSessionsRepository;
import capic.com.karttracker.services.sensors.gps.GpsService;
import capic.com.karttracker.utils.ServiceUtils;
import capic.com.karttracker.utils.SessionUtils;

/**
 * Created by Vincent on 30/05/2017.
 */

@InjectViewState
public class SessionDatasMapsPresenter extends MvpPresenter<SessionDatasContract.MapsView> {
    private Session mSession;

    private List<SessionGpsData> mSessionGpsDataList;

    @Inject
    TracksRepository mTracksRepository;
    @Inject
    TrackSessionsRepository mTrackSessionsRepository;
    @Inject
    SessionDatasRepository mSessionDatasRepository;


    public SessionDatasMapsPresenter() {
        KartTracker.getAppComponent().inject(this);
    }

    public void onStopSessionDatasClicked(Context context) {
        if (ServiceUtils.isMyServiceRunning(context, GpsService.class)) {
            mSession.setMEndTime(DateTime.now(DateTimeZone.forTimeZone(Calendar.getInstance().getTimeZone())).toLocalTime());
            mTrackSessionsRepository.updateSession(mSession);
            ServiceUtils.stopGpsService(context);
            ServiceUtils.stopAccelerometerService(context);
        }
    }

    public Track loadTrack(Long trackId) {
        return mTracksRepository.getTrack(trackId);
    }

    public Session loadSession(Long sessionId) {
        return mTrackSessionsRepository.getSession(sessionId);
    }

    public void startNewSession(Context context, Long trackId) {
        mSession = SessionUtils.generateNewSessionForTheDay(mTrackSessionsRepository, trackId);

        Session sessionInserted = mTrackSessionsRepository.insertSession(mSession);
        ServiceUtils.startGpsService(context, sessionInserted.getMId());
        ServiceUtils.startAccelerometerService(context, sessionInserted.getMId());
    }
}
