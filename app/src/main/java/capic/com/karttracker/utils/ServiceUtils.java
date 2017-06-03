package capic.com.karttracker.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import capic.com.karttracker.services.gps.GpsService;

/**
 * Created by Vincent on 31/05/2017.
 */

public class ServiceUtils {
    public static void startGpsService(Context context, Long sessionId) {
        context.startService(new Intent(context, GpsService.class).putExtra("request", true).putExtra("sessionId", sessionId));
    }

    public static void stopGpsService(Context context) {
        context.stopService(new Intent(context, GpsService.class).putExtra("remove", true));
    }

    public static boolean isMyServiceRunning(Context context, Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
