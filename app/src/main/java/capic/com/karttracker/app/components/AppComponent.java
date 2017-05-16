package capic.com.karttracker.app.components;

import javax.inject.Singleton;

import capic.com.karttracker.app.modules.AppModule;
import capic.com.karttracker.app.modules.DataModule;
import capic.com.karttracker.app.modules.PresenterModule;
import capic.com.karttracker.ui.tracks.TracksActivity;
import capic.com.karttracker.ui.tracksessions.TrackSessionsActivity;
import dagger.Component;

/**
 * Created by capic on 01/05/2017.
 */

@Singleton
@Component(modules = {AppModule.class, DataModule.class, PresenterModule.class})
public interface AppComponent {
    void inject(TracksActivity activity);
    void inject(TrackSessionsActivity activity);
}
