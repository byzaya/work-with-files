import org.example.entities.File;
import org.example.repository.FileRepository;
import org.example.service.FileStatusService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class FileStatusServiceTest {
    @Mock
    private FileRepository fileRepository;

    @InjectMocks
    private FileStatusService fileStatusService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSetStatus() {
        File file = new File();
        file.setId(1L);
        file.setStatus("старый статус");
        when(fileRepository.findById(1L)).thenReturn(Optional.of(file));
        fileStatusService.setStatus(1L, "новый статус");
        assertEquals("новый статус", file.getStatus());
        verify(fileRepository).save(file);
    }

    @Test
    public void testSetStatusFileNotExist() {
        Long fileId = 1L;
        String newStatus = "новый статус";
        when(fileRepository.findById(fileId)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> {
            fileStatusService.setStatus(fileId, newStatus);
        });
        verify(fileRepository, never()).save(any(File.class));
    }
}
