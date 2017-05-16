package capic.com.karttracker.services.datas.repositories.tracksessions;

import java.util.Date;
import java.util.List;

import capic.com.karttracker.services.datas.models.Session;

/**
 * Created by capic on 15/05/2017.
 */

public interface TrackSessionsRepository {
    List<Session> getSessionsByTrackAndDate(Long trackId, Date sessionDate);
}
