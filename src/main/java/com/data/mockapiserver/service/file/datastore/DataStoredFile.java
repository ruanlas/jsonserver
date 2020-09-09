package com.data.mockapiserver.service.file.datastore;

import com.data.mockapiserver.service.file.AbstractFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DataStoredFile extends AbstractFile {

    public DataStoredFile(
            @Value("${datastore.filename.default}")String dataStoreFileName,
            @Value("${datastore.fileextension.default}") String dataStoreFileExtension) {
        super(dataStoreFileName, dataStoreFileExtension);
    }
}
