package ma.youcode.smartbank.services.implementations;

import ma.youcode.smartbank.daos.interfaces.RequestDao;
import ma.youcode.smartbank.entities.History;
import ma.youcode.smartbank.entities.Request;
import ma.youcode.smartbank.entities.Status;
import ma.youcode.smartbank.services.interfaces.HistoryService;
import ma.youcode.smartbank.services.interfaces.StatusService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class RequestServiceImplTest {

    @InjectMocks
    private RequestServiceImpl requestService;

    @Mock
    private StatusService statusService;

    @Mock
    private HistoryService historyService;

    @Mock
    private RequestDao requestDaoImpl;

    @BeforeEach
    public void setup() {
            MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSave_validRequest() {
        Request r = new Request();
        r.setAmount(200000);
        r.setDuration(21);
        r.setMonthly(requestService.calculateMonthly(r));

        Status s = new Status();
        s.setStatusName("EN ATTENTE");
        when(statusService.getStatusByName("EN ATTENTE")).thenReturn(Optional.of(s));

        requestService.save(r);

        ArgumentCaptor<History> historyCaptor = ArgumentCaptor.forClass(History.class);
        verify(historyService).save(historyCaptor.capture());
        verify(requestDaoImpl).save(r);
        History h = historyCaptor.getValue();
        assertEquals(h , historyCaptor.getValue());
        assertEquals(s , h.getStatus());
        assertEquals(r , h.getRequest());

    }

    @Test
    public void testSave_mismatchedMonthlyThrowsException() {
        Request r = new Request();
        r.setAmount(200000);
        r.setDuration(21);
        r.setMonthly(4000);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class , () -> {
            requestService.save(r);
        });

        assertEquals("Monthly payment does not match the expected value." , exception.getMessage());

        verifyNoInteractions(historyService);
        verifyNoInteractions(requestDaoImpl);
        
    }




}