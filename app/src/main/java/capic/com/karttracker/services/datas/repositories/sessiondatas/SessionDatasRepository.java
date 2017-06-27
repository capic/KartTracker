package capic.com.karttracker.services.datas.repositories.sessiondatas;

import java.util.List;

import capic.com.karttracker.services.datas.models.SessionAccelerometerData;
import capic.com.karttracker.services.datas.models.SessionData;
import capic.com.karttracker.services.datas.models.SessionGpsData;

/**
 * Created by Vincent on 26/06/2017.
 */

public interface SessionDatasRepository {
    SessionData getSessionData(Long id);
    SessionData insertSessionData(SessionData sessionData);
    SessionData createSessionData(SessionGpsData sessionGpsData, SessionAccelerometerData sessionAccelerometerData, Long sessionId);
    List<SessionData> getSessionDatasBySession(Long sessionId);
    void deleteSessionData(Long sessionId);

    SessionAccelerometerData getSessionAccelerometerData(Long id);
    SessionAccelerometerData insertSessionAccelerometerData(SessionAccelerometerData sessionAccelerometerData);


    SessionGpsData insertSessionGpsData(SessionGpsData sessionGpsData);
    SessionGpsData getSessionGpsData(Long id);
}
