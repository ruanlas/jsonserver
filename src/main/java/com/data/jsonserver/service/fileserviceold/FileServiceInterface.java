package com.data.jsonserver.service.fileserviceold;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface FileServiceInterface {

    public void createServerFolder();

    public JSONObject getDataById(String id);

    public JSONArray getDataList();

    public String getFolderServerName();

    public String getFilename();

    public String getPathToFile();
}
