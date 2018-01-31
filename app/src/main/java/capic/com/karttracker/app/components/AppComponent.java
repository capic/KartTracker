package capic.com.karttracker.app.components;

import javax.inject.Singleton;

import capic.com.karttracker.app.modules.AppModule;
import capic.com.karttracker.app.modules.DataModule;
import capic.com.karttracker.app.modules.SensorModule;
import capic.com.karttracker.services.sensors.accelerometer.AccelerometerListener;
import capic.com.karttracker.services.sensors.gps.LocationListener;
import capic.com.karttracker.ui.sessiondatas.SessionDataMapsActivity;
import capic.com.karttracker.ui.sessiondatas.SessionDatasDatasPresenter;
import capic.com.karttracker.ui.sessiondatas.SessionDatasMapsPresenter;
import capic.com.karttracker.ui.sessiondatas.SessionDatasPagerFragment;
import capic.com.karttracker.ui.tracks.TrackItemAdapter;
import capic.com.karttracker.ui.tracks.TracksActivity;
import capic.com.karttracker.ui.tracks.TracksPresenter;
import capic.com.karttracker.ui.tracksessiondates.TrackSessionDatesActivity;
import capic.com.karttracker.ui.tracksessiondates.TrackSessionDatesPresenter;
import capic.com.karttracker.ui.tracksessions.TrackSessionsActivity;
import capic.com.karttracker.ui.tracksessions.TrackSessionsPresenter;
import dagger.Component;

/**
 * Created by capic on 01/05/2017.
 */

@Singleton
@Component(modules = {AppModule.class, DataModule.class, SensorModule.class})
public interface AppComponent {
    void inject(TracksActivity activity);
    void inject(TrackItemAdapter adapter);
    void inject(TrackSessionsActivity activity);
    void inject(TrackSessionDatesActivity activity);
    void inject(SessionDataMapsActivity activity);
    void inject(SessionDatasPagerFragment fragment);

    void inject(LocationListener listener);
    void inject(AccelerometerListener listener);

    void inject(TracksPresenter presenter);
    void inject(TrackSessionDatesPresenter presenter);
    void inject(TrackSessionsPresenter presenter);
    void inject(SessionDatasDatasPresenter presenter);
    void inject(SessionDatasMapsPresenter presenter);
}
