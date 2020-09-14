package com.data.mockapiserver.service.file.datastore;

import com.data.mockapiserver.service.file.ManagerFileInterface;
import org.json.simple.JSONArray;

public interface DataStoreManagerFileInterface extends ManagerFileInterface<JSONArray> {
    void setPathURI(String path);
}
