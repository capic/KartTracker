package capic.com.karttracker.utils;

import android.support.annotation.NonNull;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by Vincent on 31/05/2017.
 */

public class ButterKnifeUtils {
    public static final ButterKnife.Setter<View, Integer> VISIBILITY = new
            ButterKnife.Setter<View,Integer>() {
                @Override
                public void set(@NonNull View view, Integer value, int index) {
                    view.setVisibility(value);
                }
            };
}
