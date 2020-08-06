package com.data.jsonserver.resources;

import com.data.jsonserver.service.file.FileServiceInterface;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/_routes")
public class RoutesResources {

    private FileServiceInterface<JSONObject> routerFileService;

    public RoutesResources(FileServiceInterface<JSONObject> routerFileService) {
        this.routerFileService = routerFileService;
    }

    @GetMapping
    public ResponseEntity<JSONObject> getRoutes(){
        return ResponseEntity.ok(routerFileService.readFile());
    }

    @PostMapping
    public ResponseEntity<JSONObject> createRoutes(@RequestBody JSONObject object){
        routerFileService.writeFile(object);
        return ResponseEntity.ok(object);
    }
}
