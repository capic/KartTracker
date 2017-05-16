package capic.com.karttracker.services.datas.repositories.tracks;


import java.util.List;

import capic.com.karttracker.services.datas.models.Track;

/**
 * Created by capic on 02/05/2017.
 */

public interface TracksRepository {
    List<Track> getAllTracks();
    Track getTrack(Long id);
    Track insertTrack(final Track trackData);
}
