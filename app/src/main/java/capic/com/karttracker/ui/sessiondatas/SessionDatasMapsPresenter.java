package capic.com.karttracker.ui.sessiondatas;

import android.content.Context;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import capic.com.karttracker.services.datas.models.Session;
import capic.com.karttracker.services.datas.models.SessionData;
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

public class SessionDatasMapsPresenter implements SessionDatasContract.MapsPresenter {
    SessionDatasContract.MapsView mView;

    private Session mSession;

    private List<SessionGpsData> mSessionGpsDataList;

    TracksRepository mTracksRepository;
    TrackSessionsRepository mTrackSessionsRepository;
    SessionDatasRepository mSessionDatasRepository;

    @Inject
    public SessionDatasMapsPresenter(TracksRepository tracksRepository, TrackSessionsRepository trackSessionsRepository, SessionDatasRepository sessionGpsDatasRepository) {
        mTracksRepository = tracksRepository;
        mTrackSessionsRepository = trackSessionsRepository;
        mSessionDatasRepository = sessionGpsDatasRepository;
    }

    @Override
    public void setView(SessionDatasContract.MapsView view) {
        mView = view;
    }

    @Override
    public void onStopSessionDatasClicked(Context context) {
        if (ServiceUtils.isMyServiceRunning(context, GpsService.class)) {
            mSession.setMEndTime(DateTime.now(DateTimeZone.forTimeZone(Calendar.getInstance().getTimeZone())).toLocalTime());
            mTrackSessionsRepository.updateSession(mSession);
            ServiceUtils.stopGpsService(context);
            ServiceUtils.stopAccelerometerService(context);
        }
    }

    @Override
    public Track loadTrack(Long trackId) {
        return mTracksRepository.getTrack(trackId);
    }

    @Override
    public Session loadSession(Long sessionId) {
        return mTrackSessionsRepository.getSession(sessionId);
    }

    @Override
    public void startNewSession(Context context, Long trackId) {
        mSession = SessionUtils.generateNewSessionForTheDay(mTrackSessionsRepository, trackId);

        Session sessionInserted = mTrackSessionsRepository.insertSession(mSession);
        ServiceUtils.startGpsService(context, sessionInserted.getMId());
        ServiceUtils.startAccelerometerService(context, sessionInserted.getMId());
    }
}
