package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.entities.File;
import org.example.repository.FileRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
@RequiredArgsConstructor
public class FileWriteService {

    private final FileRepository fileRepository;

    public File write(String name, String status, Date lastUpdate) {
        File file = new File();
        file.setName(name);
        file.setStatus(status);
        file.setLastUpdate(lastUpdate);
        fileRepository.save(file);
        return file;
    }
}
