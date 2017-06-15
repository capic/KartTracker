package capic.com.karttracker.services.datas.repositories.tracksessions;

import org.joda.time.LocalDate;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import capic.com.karttracker.services.datas.models.Session;

/**
 * Created by capic on 15/05/2017.
 */

public interface TrackSessionsRepository {
    List<Session> getSessionsByTrackAndDate(Long trackId, LocalDate sessionDate);
    Session insertSession(Session session);
    Session updateSession(Session session);
    Session getSession(Long id);
    Session getLastSessionByTrackAndDate(Long trackId, LocalDate sessionDate);
    List<LocalDate> getSessionDatesByTrack(Long trackId);
    Long getNumberOfSessionForTrack(Long trackId);
    void deleteSession(Session session);
    List<Session> getSessionsByTrack(Long trackId);
}
