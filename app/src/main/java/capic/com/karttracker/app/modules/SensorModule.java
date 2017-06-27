package capic.com.karttracker.app.modules;

import javax.inject.Singleton;

import capic.com.karttracker.services.sensors.SensorsSynchronizer;
import capic.com.karttracker.services.sensors.SensorsSynchronizerImpl;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Vincent on 23/06/2017.
 */

@Module
public class SensorModule {
    @Singleton
    @Provides
    SensorsSynchronizer provideSensorSynchronizer() {
        return new SensorsSynchronizerImpl();
    }
}
