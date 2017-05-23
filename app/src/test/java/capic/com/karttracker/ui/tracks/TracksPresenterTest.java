package capic.com.karttracker.ui.tracks;

import com.google.common.collect.Lists;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import capic.com.karttracker.services.datas.models.Track;
import capic.com.karttracker.services.datas.repositories.tracks.TracksRepository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Vincent on 22/05/2017.
 */
public class TracksPresenterTest {
    private static List<Track> trackList;

    @Mock
    private TracksContract.View mView;

    @Mock
    private TracksRepository mRepository;

    private TracksContract.Presenter mPresenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        mPresenter = new TracksPresenter(mRepository);
        mPresenter.setView(mView);

        trackList = Lists.newArrayList(new Track(1L, "Track 1"), new Track(2L, "Track 2"), new Track(3L, "Track 3"));
    }

    @Test
    public void loadTracks_Test() {
        when(mRepository.getAllTracks()).thenReturn(trackList);

        mPresenter.loadTracks();

        verify(mView).showLoading();
        verify(mRepository).getAllTracks();
        verify(mView).showTracks(trackList);
        verify(mView).hideLoading();
    }

    @Test
    public void onCreateTrackClicked_Test() {
        mPresenter.onCreateTrackClicked();

        verify(mView).showCreateTrack();
    }

    @Test
    public void createTrack_Test() {
        Track createdTrack = new Track(1L, "Created Track");
        when(mRepository.insertTrack(createdTrack)).thenReturn(createdTrack);

        Track track = mPresenter.createTrack(createdTrack);

        verify(mRepository).insertTrack(createdTrack);

        assertEquals(createdTrack.getMId(), track.getMId());
        assertEquals(createdTrack.getMName(), track.getMName());
    }

    @Test
    public void onTrackItemClicked_Test() {
        Track track = new Track(1L, "Track 1");
        mPresenter.onTrackItemClicked(track);

        verify(mView).openTrackSessionDatesActivity(track.getMId());
    }

    @Test
    public void onTrackPlayItemClicked_Test() {
        Track track =  new Track(1L, "Track 1");

        mPresenter.onTrackPlayItemClicked(track);

        verify(mView).openTrackSessionsActivity(track.getMId(), LocalDate.now());
    }
}
