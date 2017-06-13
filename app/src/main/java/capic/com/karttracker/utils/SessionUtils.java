package capic.com.karttracker.utils;

import android.location.Location;

import org.joda.time.LocalDate;

import capic.com.karttracker.services.datas.models.Session;
import capic.com.karttracker.services.datas.models.SessionGpsData;
import capic.com.karttracker.services.datas.repositories.tracksessions.TrackSessionsRepository;

/**
 * Created by capic on 18/05/2017.
 */

public class SessionUtils {
    public static Session generateNewSessionForTheDay(TrackSessionsRepository repository, Long trackId) {
        LocalDate today = LocalDate.now();

        Session lastSession = repository.getLastSessionByTrackAndDate(trackId, today);
        Long lastSessionId = lastSession == null ? 0: lastSession.getMIdOfDay();

        Session session = new Session();
        session.setMDate(today);
        session.setMIdOfDay(lastSessionId + 1);
        session.setMTrackId(trackId);

        return session;
    }

}
