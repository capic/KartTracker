package capic.com.karttracker.app.components;

import android.widget.ArrayAdapter;

import javax.inject.Named;

import capic.com.karttracker.app.ActivityScope;
import capic.com.karttracker.app.modules.TracksActivityModule;
import capic.com.karttracker.services.datas.models.Track;
import capic.com.karttracker.ui.tracks.TracksActivity;
import dagger.Subcomponent;

/**
 * Created by Vincent on 09/06/2017.
 */

@ActivityScope
@Subcomponent(modules = {TracksActivityModule.class})
public interface TracksActivitySubComponent {

    void inject(TracksActivity activity);

}
