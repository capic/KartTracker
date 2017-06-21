package capic.com.karttracker.services.datas.repositories.sessionaccelerometerdatas;

import capic.com.karttracker.services.datas.models.SessionAccelerometerData;

/**
 * Created by Vincent on 20/06/2017.
 */

public interface SessionAccelerometerDatasRepository {
    SessionAccelerometerData getSessionAccelerometerData(Long id);
    SessionAccelerometerData insertSessionAccelerometerData(SessionAccelerometerData sessionAccelerometerData);
}
