package capic.com.karttracker.utils;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;

import java.util.Calendar;

import capic.com.karttracker.services.datas.models.Session;
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
        session.setMStartTime(DateTime.now(DateTimeZone.forTimeZone(Calendar.getInstance().getTimeZone())).toLocalTime());
        session.setMTrackId(trackId);

        return session;
    }
}
