package capic.com.karttracker.ui.sessiondatas;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import capic.com.karttracker.services.datas.models.SessionData;
import capic.com.karttracker.services.datas.models.SessionGpsData;

/**
 * Created by Vincent on 08/06/2017.
 */

public class SessionDatasPagerAdapter extends FragmentStatePagerAdapter {
    private List<SessionData> mList;

    public SessionDatasPagerAdapter(FragmentManager fragmentManager, List<SessionData> list) {
        super(fragmentManager);
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Fragment getItem(int position) {
        return SessionDatasFragment.init(getSessionData(position));
    }

    public SessionData getSessionData(int position) {
        return  mList.get(position);
    }
}
