package com.data.jsonserver.service.store;

import com.data.jsonserver.service.file.datastore.DataStoreManagerFileInterface;
import com.data.jsonserver.service.file.exception.FileNotFoundServerException;
import com.data.jsonserver.service.store.exception.DataNotFoundRuntimeException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class StoreService implements StoreServiceInterface{

    private DataStoreManagerFileInterface dataStoreManagerFile;

    public StoreService(DataStoreManagerFileInterface dataStoreManagerFile) {
        this.dataStoreManagerFile = dataStoreManagerFile;
    }

    @Override
    public JSONArray getDataList(String path) {
        dataStoreManagerFile
                .getFolderURI()
                .setPath(path);
        try {
            return dataStoreManagerFile.readFile();
        } catch (FileNotFoundServerException e) {
            return new JSONArray();
        }
    }

    @Override
    public JSONObject getData(String path, String id) {
        JSONArray dataList = getDataList(path);
        Object[] objects = dataList.toArray();
        for (Object o: objects) {
            JSONObject json = (JSONObject) o;
            if (json.get("id").equals(id)){
                return json;
            }
        }
        throw new DataNotFoundRuntimeException(
                "Não foi encontrado a informação com o id=[ {id} ]".replace("{id}", id));
    }

    @Override
    public JSONObject save(String path, JSONObject jsonObject) {
        String id = UUID.randomUUID().toString();
        jsonObject.put("id", id);
        JSONArray dataList = getDataList(path);

        dataStoreManagerFile
                .getFolderURI()
                .setPath(path);
        dataList.add(jsonObject);
        dataStoreManagerFile.writeFile(dataList);
        return jsonObject;
    }
}
