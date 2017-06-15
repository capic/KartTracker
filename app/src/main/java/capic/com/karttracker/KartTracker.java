package capic.com.karttracker;

import android.app.Application;

import net.danlew.android.joda.JodaTimeAndroid;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import capic.com.karttracker.app.components.AppComponent;
import capic.com.karttracker.app.components.DaggerAppComponent;
import capic.com.karttracker.app.modules.AppModule;
import capic.com.karttracker.app.modules.DataModule;


/**
 * Created by Vincent on 26/04/2017.
 */

public class KartTracker extends Application {
    private AppComponent mAppComponent;
    private DateFormat mDateFormat;
    private DateFormat mTimeFormat;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = initDagger(this);
        JodaTimeAndroid.init(this);
        mDateFormat = android.text.format.DateFormat.getDateFormat(this);
        mTimeFormat = android.text.format.DateFormat.getTimeFormat(this);
    }

    protected AppComponent initDagger(KartTracker application) {
       return DaggerAppComponent.builder()
               .appModule(new AppModule(application))
               .dataModule(new DataModule())
               .build();
    }

    public void setAppComponent(AppComponent appComponent) {
        mAppComponent = appComponent;
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    public SimpleDateFormat getDateFormat() {
        return (SimpleDateFormat)mDateFormat;
    }

    public SimpleDateFormat getTimeFormat() {
        return (SimpleDateFormat)mTimeFormat;
    }
}
