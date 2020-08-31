package com.data.jsonserver.service.file;

import com.data.jsonserver.service.file.exception.CreateFolderRuntimeException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class AbstractFolder implements FolderInterface {

    @Override
    public void create() {
        try {
            Path folder = Paths.get(
                    getPath()
            ).toAbsolutePath().normalize();
            Files.createDirectories(folder);
        } catch (IOException e) {
            throw new CreateFolderRuntimeException(failCreateFolderMessage());
        }
    }

    public abstract String failCreateFolderMessage();
}
