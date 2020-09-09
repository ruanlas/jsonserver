package com.data.mockapiserver.service.router;

import com.data.mockapiserver.service.file.FileServiceInterface;
import com.data.mockapiserver.service.file.SubFolderInterface;
import com.data.mockapiserver.service.file.exception.FileNotFoundServerException;
import com.data.mockapiserver.service.router.exception.RouterNotFoundRuntimeException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
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
        try {
            return routerManagerFile.readFile();
        } catch (FileNotFoundServerException e) {
            throw new RouterNotFoundRuntimeException(
                    "Não foi possível encontrar o arquivo de rotas.", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public JSONObject createRoutes(JSONObject jsonObject) {
        routerManagerFile.writeFile(jsonObject);

        JSONObject parse = this.handleParse(jsonObject);
        JSONArray routes = (JSONArray) parse.get("routes");
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
