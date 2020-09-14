package com.data.mockapiserver.service.router;

import com.data.mockapiserver.service.file.ManagerFileInterface;
import com.data.mockapiserver.service.file.exception.FileNotFoundServerException;
import com.data.mockapiserver.service.router.exception.PathNotFoundRuntimeException;
import com.data.mockapiserver.service.router.exception.RouterNotFoundRuntimeException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class RouterValidate implements RouterValidateInterface{

    private ManagerFileInterface<JSONObject> routerManagerFile;

    public RouterValidate(ManagerFileInterface<JSONObject> routerManagerFile) {
        this.routerManagerFile = routerManagerFile;
    }

    @Override
    public void validPath(String... paths) {
//        for (String path : paths) {
//            System.out.println(path);
//        }
        JSONArray routes = this.getRoutes();
        if (!routes.contains( paths[0] )){
            throw new PathNotFoundRuntimeException("Este endpoint não existe.");
        }
    }

    private JSONArray getRoutes(){
        try {
            JSONObject object = routerManagerFile.readFile();
            JSONArray routes = (JSONArray) object.get("routes");
            return routes;
        } catch (FileNotFoundServerException e) {
            throw new RouterNotFoundRuntimeException(
                    "Não foi possível encontrar o arquivo de rotas para validar o endpoint.",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
