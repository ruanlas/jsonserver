package com.data.jsonserver.resources;

import com.data.jsonserver.service.fileserviceold.FileServiceInterface;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataResources {

    private FileServiceInterface fileService;

    public DataResources(FileServiceInterface fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/data")
    public ResponseEntity<JSONArray> getDataList(){
        JSONArray list = fileService.getDataList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/data/{id}")
    public ResponseEntity<JSONObject> getData(@PathVariable String id){
        JSONObject data = fileService.getDataById(id);
        return ResponseEntity.ok(data);
    }
}
