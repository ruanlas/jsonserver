package com.data.mockapiserver.service.file.datastore;

import com.data.mockapiserver.service.file.FileServiceInterface;
import com.data.mockapiserver.service.file.SubFolderInterface;
import org.json.simple.JSONArray;

public interface DataStoreManagerFileInterface extends FileServiceInterface<JSONArray> {
    SubFolderInterface getFolderURI();
}
