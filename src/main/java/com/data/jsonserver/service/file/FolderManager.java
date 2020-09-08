package com.data.jsonserver.service.file;

import com.data.jsonserver.service.file.exception.CreateFolderRuntimeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class FolderManager implements FolderManagerInterface {

    private String rootFolder;
    private List<FolderInterface> childFolders;

    public FolderManager(
            @Value("${server.folder.root}") String rootFolder,
            List<FolderInterface> childFolders) {
        this.rootFolder = rootFolder;
        this.childFolders = childFolders;
    }

    @Override
    public void createRoot() {
        try {
            Path folder = Paths.get(
                    getRootPath()
            ).toAbsolutePath().normalize();
            Files.createDirectories(folder);
            childFolders.forEach(f -> f.create());
        } catch (IOException e) {
            throw new CreateFolderRuntimeException("Não foi possível criar a pasta raiz");
        }
    }

    @Override
    public String getRootPath() {
        return rootFolder;
    }
}
