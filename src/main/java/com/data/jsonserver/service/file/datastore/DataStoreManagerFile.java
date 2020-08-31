package com.data.jsonserver.service.file.datastore;

import com.data.jsonserver.service.file.FileInterface;
import com.data.jsonserver.service.file.SubFolderInterface;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class DataStoreManagerFile implements DataStoreManagerFileInterface {

    private SubFolderInterface dataStoreURIFolder;
    private FileInterface dataStoredFile;

    public DataStoreManagerFile(SubFolderInterface dataStoreURIFolder, FileInterface dataStoredFile) {
        this.dataStoreURIFolder = dataStoreURIFolder;
        this.dataStoredFile = dataStoredFile;
    }

    @Override
    public SubFolderInterface getFolderURI() {
        return dataStoreURIFolder;
    }

    @Override
    public JSONArray readFile() {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(dataStoreURIFolder.getPath() + "/" + dataStoredFile.fileName())){
            Object obj = jsonParser.parse(reader);
            JSONArray jsonArray = (JSONArray) obj;
            return jsonArray;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void writeFile(JSONArray file) {
        try (FileWriter writer = new FileWriter(dataStoreURIFolder.getPath() + "/" + dataStoredFile.fileName())) {
            writer.write(file.toJSONString());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
