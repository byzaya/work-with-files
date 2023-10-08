package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.entities.File;
import org.example.repository.FileRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FileSearchService {

    private final FileRepository fileRepository;

    public File findById(Long id) {
        Optional<File> file = fileRepository.findById(id);
        if (file.isEmpty()) {
            throw new NoSuchElementException();
        }
        return file.get();
    }
}
