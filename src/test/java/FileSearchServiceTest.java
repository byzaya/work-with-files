import org.example.entities.File;
import org.example.repository.FileRepository;
import org.example.service.FileSearchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class FileSearchServiceTest {

    @Mock
    private FileRepository fileRepository;

    @InjectMocks
    private FileSearchService fileSearchService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindById() {
        File file = new File();
        file.setId(1L);
        file.setName("nameOfFile.txt");
        when(fileRepository.findById(1L)).thenReturn(Optional.of(file));
        File resultFile = fileSearchService.findById(1L);
        assertEquals(file, resultFile);
    }

    @Test
    void testFindByIdFileNotExist() {
        when(fileRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> {
            fileSearchService.findById(1L);
        });
    }
}
