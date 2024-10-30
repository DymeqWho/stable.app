package misiejuk.dymitr.stable.app.service;

import misiejuk.dymitr.stable.app.entities.Rider;
import misiejuk.dymitr.stable.app.entities.RiderBuilder;
import misiejuk.dymitr.stable.app.database.RiderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RiderServiceTest {

    @Mock
    private RiderRepository riderRepository;

    @InjectMocks
    private RiderService riderService;

    private Rider rider;

    @BeforeEach
    void setUp() {
        rider = new RiderBuilder()
                .setName("John")
                .setDescription("Experienced rider")
                .build();
    }

    @Test
    void testFindAll() {
        List<Rider> riders = List.of(rider);
        when(riderRepository.findAll()).thenReturn(riders);

        List<Rider> result = riderService.findAll();

        assertEquals(1, result.size());
        assertEquals("John", result.getFirst().getName());
        verify(riderRepository, times(1)).findAll();
    }

    @Test
    void testSave() {
        when(riderRepository.save(rider)).thenReturn(rider);

        Rider savedRider = riderService.save(rider);

        assertNotNull(savedRider);
        assertEquals("John", savedRider.getName());
        verify(riderRepository, times(1)).save(rider);
    }

    @Test
    void testUpdateRiderExists() {
        Rider updatedRider = new RiderBuilder()
                .setName("John")
                .setDescription("Updated description")
                .build();

        when(riderRepository.findById("John")).thenReturn(Optional.of(rider));
        when(riderRepository.save(any(Rider.class))).thenReturn(updatedRider);

        Rider result = riderService.update("John", updatedRider);

        assertNotNull(result);
        assertEquals("John", result.getName());
        assertEquals("Updated description", result.getDescription());
        verify(riderRepository, times(1)).findById("John");
        verify(riderRepository, times(1)).save(any(Rider.class));
    }

    @Test
    void testUpdateRiderNotExists() {
        Rider newRider = new RiderBuilder()
                .setName("Alex")
                .setDescription("New Rider")
                .build();

        when(riderRepository.findById("Alex")).thenReturn(Optional.empty());
        when(riderRepository.save(newRider)).thenReturn(newRider);

        Rider result = riderService.update("Alex", newRider);

        assertNotNull(result);
        assertEquals("Alex", result.getName());
        assertEquals("New Rider", result.getDescription());
        verify(riderRepository, times(1)).findById("Alex");
        verify(riderRepository, times(1)).save(newRider);
    }

    @Test
    void testFindByIdRiderExists() {
        when(riderRepository.findById("John")).thenReturn(Optional.of(rider));

        Rider result = riderService.findById("John");

        assertNotNull(result);
        assertEquals("John", result.getName());
        assertEquals("Experienced rider", result.getDescription());
        verify(riderRepository, times(1)).findById("John");
    }

    @Test
    void testFindByIdRiderNotExists() {
        when(riderRepository.findById("Unknown")).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> riderService.findById("Unknown"));
        verify(riderRepository, times(1)).findById("Unknown");
    }

    @Test
    void testDeleteRider() {
        doNothing().when(riderRepository).deleteById("John");

        riderService.deleteRider("John");

        verify(riderRepository, times(1)).deleteById("John");
    }
}
