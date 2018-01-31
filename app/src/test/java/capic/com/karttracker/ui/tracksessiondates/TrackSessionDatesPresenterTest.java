package capic.com.karttracker.ui.tracksessiondates;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.common.collect.Lists;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import capic.com.karttracker.services.datas.models.Session;
import capic.com.karttracker.services.datas.models.Track;
import capic.com.karttracker.services.datas.repositories.tracksessions.TrackSessionsRepository;
import capic.com.karttracker.utils.SessionUtils;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Vincent on 23/05/2017.
 */

public class TrackSessionDatesPresenterTest {
    private static List<LocalDate> datesList;

    @Mock
    private TrackSessionDatesContract.View mView;
    @Mock
    private TrackSessionsRepository mRepository;

    @InjectPresenter
    private TrackSessionDatesPresenter mPresenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        mPresenter = new TrackSessionDatesPresenter();
        datesList = new ArrayList<>();
        datesList.add(LocalDate.parse("2017-01-01"));
        datesList.add(LocalDate.parse("2017-01-02"));
        datesList.add(LocalDate.parse("2017-01-03"));
    }

    @Test
    public void loadTrackSessionDates_Test() {
       when(mRepository.getSessionDatesByTrack(1L)).thenReturn(datesList);

        mPresenter.loadTrackSessionDates(1L);

        verify(mView).showLoading();
        verify(mRepository).getSessionDatesByTrack(1L);
        verify(mView).showTrackSessionDate(datesList);
        verify(mView).hideLoading();
    }

    @Test
    public void onTrackSessionDateItemClicked_Test() {
         mPresenter.onTrackSessionDateItemClicked(LocalDate.parse("2017-01-01"));

        verify(mView).openTrackSessionsActivity(LocalDate.parse("2017-01-01"));
    }

    @Test
    public void onStartNewSessionClicked_Test() {
        Session sessionGenerated = new Session(1L, 1L, LocalDate.parse("2017-01-01"), LocalTime.parse("00:00:00"),   LocalTime.parse("12:00:00"), 1L);

        when(mRepository.getLastSessionByTrackAndDate(1L, LocalDate.parse("2017-01-01"))).thenReturn(sessionGenerated);
        when(mRepository.insertSession(sessionGenerated)).thenReturn(sessionGenerated);

        mPresenter.onStartNewSessionClicked();
    }
}
