package com.data.jsonserver.service.file.datastore;

import com.data.jsonserver.service.file.FileServiceInterface;
import com.data.jsonserver.service.file.SubFolderInterface;
import org.json.simple.JSONArray;

public interface DataStoreManagerFileInterface extends FileServiceInterface<JSONArray> {
    SubFolderInterface getFolderURI();
}
