package com.data.mockapiserver.service.file.datastore;

import com.data.mockapiserver.service.file.AbstractFolder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DataStoreFolder extends AbstractFolder {

    private String dataStoreFolderPath;

    public DataStoreFolder(@Value("${server.folder.datastore}") String dataStoreFolderPath) {
        this.dataStoreFolderPath = dataStoreFolderPath;
    }

    @Override
    public String failCreateFolderMessage() {
        return "Não foi possível criar a pasta de armazenamento";
    }

    @Override
    public String getPath() {
        return this.dataStoreFolderPath;
    }
}
