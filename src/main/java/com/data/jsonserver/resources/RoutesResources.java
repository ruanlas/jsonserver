package com.data.jsonserver.resources;

import com.data.jsonserver.service.file.FileServiceInterface;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/_routes")
public class RoutesResources {

    private FileServiceInterface<JSONObject> routerManagerFile;

    public RoutesResources(FileServiceInterface<JSONObject> routerManagerFile) {
        this.routerManagerFile = routerManagerFile;
    }

    @GetMapping
    public ResponseEntity<JSONObject> getRoutes(){
        return ResponseEntity.ok(routerManagerFile.readFile());
    }

    @PostMapping
    public ResponseEntity<JSONObject> createRoutes(@RequestBody JSONObject object){
        routerManagerFile.writeFile(object);
        return ResponseEntity.ok(object);
    }
}
