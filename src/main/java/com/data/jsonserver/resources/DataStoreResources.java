package com.data.jsonserver.resources;

import com.data.jsonserver.service.store.StoreServiceInterface;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DataStoreResources {

    private StoreServiceInterface storeService;

    public DataStoreResources(StoreServiceInterface storeService) {
        this.storeService = storeService;
    }

    @GetMapping("/{path}")
    public ResponseEntity<JSONArray> getDataList(@PathVariable String path){
        JSONArray dataList = storeService.getDataList(path);
        return ResponseEntity.ok(dataList);
    }

    @GetMapping("/{path}/{id}")
    public ResponseEntity<JSONObject> getData(@PathVariable String path, @PathVariable String id){
        JSONObject data = storeService.getData(path, id);
        return ResponseEntity.ok(data);
    }

    @PostMapping("/{path}")
    public ResponseEntity<JSONObject> postData(@PathVariable String path, @RequestBody JSONObject object){
        JSONObject save = storeService.save(path, object);
        return ResponseEntity.ok(save);
    }
}
