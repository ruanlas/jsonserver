package com.data.jsonserver.service.file.router;

import com.data.jsonserver.service.file.FolderInterface;
import com.data.jsonserver.service.file.exception.CreateFolderRuntimeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class RouterFolder implements FolderInterface {

    private String routerFolderPath;

    public RouterFolder(@Value("${server.folder.router}") String routerFolderPath) {
        this.routerFolderPath = routerFolderPath;
    }

    @Override
    public void create() {
        try {
            Path folder = Paths.get(
                    getPath()
            ).toAbsolutePath().normalize();
            Files.createDirectories(folder);
        } catch (IOException e) {
            throw new CreateFolderRuntimeException("Não foi possível criar a pasta de rotas");
        }
    }

    @Override
    public String getPath() {
        return routerFolderPath;
    }
}
