package misiejuk.dymitr.stable.app.service;

import misiejuk.dymitr.stable.app.entities.Horse;
import misiejuk.dymitr.stable.app.database.HorseRepository;
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
class HorseServiceTest {

    @Mock
    private HorseRepository horseRepository;

    @InjectMocks
    private HorseService horseService;

    private Horse horse;

    @BeforeEach
    void setUp() {
        horse = new Horse();
        horse.setName("Thunder");
        horse.setDescription("Fast and strong");
    }

    @Test
    void testFindAll() {
        List<Horse> horses = List.of(horse);
        when(horseRepository.findAll()).thenReturn(horses);

        List<Horse> result = horseService.findAll();

        assertEquals(1, result.size());
        assertEquals("Thunder", result.getFirst().getName());
        verify(horseRepository, times(1)).findAll();
    }

    @Test
    void testSave() {
        when(horseRepository.save(horse)).thenReturn(horse);

        Horse savedHorse = horseService.save(horse);

        assertNotNull(savedHorse);
        assertEquals("Thunder", savedHorse.getName());
        verify(horseRepository, times(1)).save(horse);
    }

    @Test
    void testUpdateHorseExists() {
        Horse updatedHorse = new Horse();
        updatedHorse.setName("Thunder");
        updatedHorse.setDescription("Updated description");

        when(horseRepository.findById("Thunder")).thenReturn(Optional.of(horse));
        when(horseRepository.save(any(Horse.class))).thenReturn(updatedHorse);

        Horse result = horseService.update("Thunder", updatedHorse);

        assertNotNull(result);
        assertEquals("Thunder", result.getName());
        assertEquals("Updated description", result.getDescription());
        verify(horseRepository, times(1)).findById("Thunder");
        verify(horseRepository, times(1)).save(any(Horse.class));
    }

    @Test
    void testUpdateHorseNotExists() {
        Horse newHorse = new Horse();
        newHorse.setName("Storm");
        newHorse.setDescription("New Horse");

        when(horseRepository.findById("Storm")).thenReturn(Optional.empty());
        when(horseRepository.save(newHorse)).thenReturn(newHorse);

        Horse result = horseService.update("Storm", newHorse);

        assertNotNull(result);
        assertEquals("Storm", result.getName());
        verify(horseRepository, times(1)).findById("Storm");
        verify(horseRepository, times(1)).save(newHorse);
    }

    @Test
    void testFindByIdHorseExists() {
        when(horseRepository.findById("Thunder")).thenReturn(Optional.of(horse));

        Horse result = horseService.findById("Thunder");

        assertNotNull(result);
        assertEquals("Thunder", result.getName());
        assertEquals("Fast and strong", result.getDescription());
        verify(horseRepository, times(1)).findById("Thunder");
    }

    @Test
    void testFindByIdHorseNotExists() {
        when(horseRepository.findById("Unknown")).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> horseService.findById("Unknown"));
        verify(horseRepository, times(1)).findById("Unknown");
    }

    @Test
    void testDeleteHorse() {
        doNothing().when(horseRepository).deleteById("Thunder");

        horseService.deleteHorse("Thunder");

        verify(horseRepository, times(1)).deleteById("Thunder");
    }
}
