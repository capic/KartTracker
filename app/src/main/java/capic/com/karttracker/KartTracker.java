package capic.com.karttracker;

import android.app.Application;

import capic.com.karttracker.app.components.AppComponent;
import capic.com.karttracker.app.components.DaggerAppComponent;
import capic.com.karttracker.app.modules.AppModule;
import capic.com.karttracker.app.modules.DataModule;


/**
 * Created by Vincent on 26/04/2017.
 */

public class KartTracker extends Application {
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = initDagger(this);
    }

    protected AppComponent initDagger(KartTracker application) {
       return DaggerAppComponent.builder()
               .appModule(new AppModule(application))
               .dataModule(new DataModule())
               .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
