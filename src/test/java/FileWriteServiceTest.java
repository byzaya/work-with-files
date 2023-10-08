import org.example.entities.File;
import org.example.repository.FileRepository;
import org.example.service.FileWriteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class FileWriteServiceTest {

    @Mock
    private FileRepository fileRepository;

    @InjectMocks
    private FileWriteService fileWriteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testWrite() {
        String name = "TestFile";
        String status = "InProgress";
        Date lastUpdate = new Date(System.currentTimeMillis());
        File expectedFile = new File(1L, name, status, lastUpdate);

        when(fileRepository.save(any(File.class))).thenReturn(expectedFile);
        File resultFile = fileWriteService.write(name, status, lastUpdate);

        assertEquals(expectedFile.getName(), resultFile.getName());
        assertEquals(expectedFile.getStatus(), resultFile.getStatus());
        assertEquals(expectedFile.getLastUpdate(), resultFile.getLastUpdate());

        verify(fileRepository).save(
                argThat(file -> file.getName().equals(name) &&
                        file.getStatus().equals(status) &&
                        file.getLastUpdate().equals(lastUpdate)));
    }
}
