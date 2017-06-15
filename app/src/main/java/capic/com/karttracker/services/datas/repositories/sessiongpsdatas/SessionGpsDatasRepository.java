package capic.com.karttracker.services.datas.repositories.sessiongpsdatas;

import java.util.List;

import capic.com.karttracker.services.datas.models.SessionGpsData;

/**
 * Created by Vincent on 01/06/2017.
 */

public interface SessionGpsDatasRepository {
    List<SessionGpsData> getSessionGpsDatasBySession(Long sessionId);
    SessionGpsData insertSessionGpsData(SessionGpsData sessionGpsData);
    SessionGpsData getSessionGpsData(Long id);
    void deleteSessionGpsData(SessionGpsData sessionGpsData);
    void deleteAllSessionGpsDataForSession(Long sessionId);
}
