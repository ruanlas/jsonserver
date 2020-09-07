package com.data.jsonserver.service.router;

import com.data.jsonserver.service.file.FileServiceInterface;
import com.data.jsonserver.service.file.SubFolderInterface;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

@Service
public class RouterService implements RouterServiceInterface<JSONObject> {

    private FileServiceInterface<JSONObject> routerManagerFile;
    private SubFolderInterface dataStoreURIFolder;

    public RouterService(
            FileServiceInterface<JSONObject> routerManagerFile,
            SubFolderInterface dataStoreURIFolder
    ) {
        this.routerManagerFile = routerManagerFile;
        this.dataStoreURIFolder = dataStoreURIFolder;
    }

    @Override
    public JSONObject getRoutes() {
        return routerManagerFile.readFile();
    }

    @Override
    public JSONObject createRoutes(JSONObject jsonObject) {
        routerManagerFile.writeFile(jsonObject);

        JSONObject parse = this.handleParse(jsonObject);
        JSONArray routes = (JSONArray) parse.get("routes");
        System.out.println(routes);
        routes.forEach(route -> {
            dataStoreURIFolder.addSubFolder(route.toString());
        });
        return jsonObject;
    }

    private JSONObject handleParse(JSONObject jsonObject){
        try {
            JSONParser jsonParser = new JSONParser();
            return (JSONObject)jsonParser
                    .parse(jsonObject.toJSONString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
