package com.data.jsonserver.resources;

import com.data.jsonserver.service.router.RouterServiceInterface;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/_routes")
public class RoutesResources {

    private RouterServiceInterface<JSONObject> routerService;

    public RoutesResources(RouterServiceInterface<JSONObject> routerService) {
        this.routerService = routerService;
    }

    @GetMapping
    public ResponseEntity<JSONObject> getRoutes(){
        return ResponseEntity.ok(routerService.getRoutes());
    }

    @PostMapping
    public ResponseEntity<JSONObject> createRoutes(@RequestBody JSONObject object){
        JSONObject routes = routerService.createRoutes(object);
        return ResponseEntity.ok(routes);
    }
}
