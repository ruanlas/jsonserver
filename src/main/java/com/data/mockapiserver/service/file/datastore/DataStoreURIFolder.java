package com.data.mockapiserver.service.file.datastore;

import com.data.mockapiserver.service.file.FolderInterface;
import com.data.mockapiserver.service.file.SubFolderInterface;
import com.data.mockapiserver.service.file.exception.CreateFolderRuntimeException;
import com.data.mockapiserver.service.file.exception.PathNotDefinedRuntimeException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class DataStoreURIFolder implements SubFolderInterface {

    private FolderInterface dataStoreFolder;
    private String subFolderPath;

    public DataStoreURIFolder(FolderInterface dataStoreFolder) {
        this.dataStoreFolder = dataStoreFolder;
        this.subFolderPath = null;
    }

    @Override
    public void setPath(String subFolderPath) {
        this.subFolderPath = subFolderPath;
    }

    @Override
    public String getPath() {
        if (subFolderPath == null){
            throw new PathNotDefinedRuntimeException("A propriedade [subFolderPath] nao foi definida pelo metodo [setPath()]");
        }
        return dataStoreFolder.getPath() + "/" + subFolderPath;
    }

    @Override
    public void addSubFolder(String subFolderPath) {
        setPath(subFolderPath);
        try {
            Path folder = Paths.get(
                    getPath()
            ).toAbsolutePath().normalize();
            Files.createDirectories(folder);
        } catch (IOException e) {
            throw new CreateFolderRuntimeException("Não foi possível criar a subpasta de armazenamento");
        }
    }
}
