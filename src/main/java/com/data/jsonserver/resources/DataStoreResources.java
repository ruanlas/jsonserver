package com.data.jsonserver.resources;

import com.data.jsonserver.service.router.RouterValidateInterface;
import com.data.jsonserver.service.store.StoreServiceInterface;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DataStoreResources {

    private StoreServiceInterface storeService;
    private RouterValidateInterface routerValidate;

    public DataStoreResources(
            StoreServiceInterface storeService,
            RouterValidateInterface routerValidate) {
        this.storeService = storeService;
        this.routerValidate = routerValidate;
    }

    @GetMapping("/{path}")
    public ResponseEntity<JSONArray> getDataList(@PathVariable String path){
        routerValidate.validPath(path);
        JSONArray dataList = storeService.getDataList(path);
        return ResponseEntity.ok(dataList);
    }

    @GetMapping("/{path}/{id}")
    public ResponseEntity<JSONObject> getData(@PathVariable String path, @PathVariable String id){
        routerValidate.validPath(path, "{id}");
        JSONObject data = storeService.getData(path, id);
        return ResponseEntity.ok(data);
    }

    @PostMapping("/{path}")
    public ResponseEntity<JSONObject> postData(@PathVariable String path, @RequestBody JSONObject object){
        routerValidate.validPath(path, "{id}");
        JSONObject save = storeService.save(path, object);
        return ResponseEntity.ok(save);
    }
}
