package com.data.jsonserver.service.fileserviceold;

import com.data.jsonserver.service.exception.*;
import com.data.jsonserver.service.exception.Error;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileService implements FileServiceInterface{
//    https://howtodoinjava.com/library/json-simple-read-write-json-examples/
    @Value("${file.name}")
    private String fileName;
    @Value("${folder.server.name}")
    private String folderServerName;

    @Override
    public void createServerFolder() {
        try {
            Path folder = Paths.get(
                    getFolderServerName()
                ).toAbsolutePath().normalize();
            Files.createDirectories(folder);
            initializeFolders();
        } catch (IOException e) {
            throw new FileServerNotCreatedException("Não foi possível criar a pasta do servidor");
        }
    }

    private void initializeFolders(){
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(getFolderServerName() + "/routes.json"))
        {
            Object obj = jsonParser.parse(reader);
            JSONObject object = (JSONObject)obj;
            JSONArray routes = (JSONArray)object.get("routes");
            System.out.println(routes.toJSONString());
            System.out.println(routes.size());
            routes.forEach( i -> {
                try {
                    Path folder = Paths.get(
                            getFolderServerName()+"/"+i
                    ).toAbsolutePath().normalize();
                    Files.createDirectories(folder);


                    try (FileWriter writer = new FileWriter(getFolderServerName()+"/"+i +"/data.json")) {
                        JSONObject obj1 = new JSONObject();
                        obj1.put("teste", "teste");
                        writer.write(obj1.toJSONString());
                        writer.flush();
                    }
                    System.out.println("pasta criada");
                    System.out.println(i);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public JSONObject getDataById(String id) {
        JSONArray list = getDataList();
        Object[] objects = list.toArray();
        for (Object o: objects) {
            JSONObject json = (JSONObject) o;
            if (json.get("id").equals(id)){
                return json;
            }
        }
        throw new DataNotFoundException("Não foi encontrado objeto com o id [" + id + "]");
    }

    @Override
    public JSONArray getDataList() {
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(getPathToFile()))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONArray dataList = (JSONArray) obj;
            return dataList;
        } catch (FileNotFoundException e) {
            throw new FileServerNotFoundException("Não foi encontrado o arquivo na pasta do servidor. Info: [" + e.getMessage()+"]");
        } catch (IOException e) {
            throw new Error(e.getMessage());
        } catch (ParseException e) {
            throw new ParseError(e.getMessage());
        }
    }

    @Override
    public String getFolderServerName() {
        return folderServerName;
    }

    @Override
    public String getFilename() {
        return fileName;
    }

    @Override
    public String getPathToFile() {
        return getFolderServerName() + "/" + getFilename();
    }
}
