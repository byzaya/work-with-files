package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.entities.File;
import org.example.repository.FileRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FileStatusService {

    private final FileRepository fileRepository;

    public void setStatus(Long id, String newStatus) {
        Optional<File> file = fileRepository.findById(id);
        if (file.isEmpty()) {
            throw new NoSuchElementException();
        }
        file.get().setStatus(newStatus);
        fileRepository.save(file.get());
    }

}
