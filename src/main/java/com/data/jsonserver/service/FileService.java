package com.data.jsonserver.service;

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
        } catch (IOException e) {
            throw new FileServerNotCreatedException("Não foi possível criar a pasta do servidor");
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
