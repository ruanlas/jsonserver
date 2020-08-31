package com.data.jsonserver.service.store;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface StoreServiceInterface {

    JSONArray getDataList(String path);
    JSONObject getData(String path, String id);
    JSONObject save(String path, JSONObject jsonObject);
}
