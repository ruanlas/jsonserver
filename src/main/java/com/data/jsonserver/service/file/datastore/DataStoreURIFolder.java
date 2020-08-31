package com.data.jsonserver.service.file.datastore;

import com.data.jsonserver.service.file.FolderInterface;
import com.data.jsonserver.service.file.SubFolderInterface;
import com.data.jsonserver.service.file.exception.CreateFolderRuntimeException;
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

//    @Override
//    public FolderInterface getRoot() {
//        return dataStoreFolder;
//    }

//    @Override
//    private String getSubFolder(String path) {
//        return dataStoreFolder.getPath() + "/" + path;
//    }

//    @Override
//    public boolean existsSubFolder(String subFolderPath) {
//        return false;
//    }

    @Override
    public void setPath(String subFolderPath) {
        this.subFolderPath = subFolderPath;
    }

    @Override
    public String getPath() {
        if (subFolderPath == null){
//      retornar uma exception que não foi setado o path da subfolder
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
