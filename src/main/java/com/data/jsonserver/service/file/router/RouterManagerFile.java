package com.data.jsonserver.service.file.router;

import com.data.jsonserver.service.file.FileInterface;
import com.data.jsonserver.service.file.FileServiceInterface;
import com.data.jsonserver.service.file.FolderInterface;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class RouterManagerFile implements FileServiceInterface<JSONObject> {

    private FolderInterface routerFolder;
    private FileInterface routerFile;

    public RouterManagerFile(FolderInterface routerFolder, FileInterface routerFile) {
        this.routerFolder = routerFolder;
        this.routerFile = routerFile;
    }

    @Override
    public JSONObject readFile() {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(routerFolder.getPath() + "/" + routerFile.fileName())){
            Object obj = jsonParser.parse(reader);
            JSONObject object = (JSONObject)obj;
            return object;
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
    public void writeFile(JSONObject file) {
        try (FileWriter writer = new FileWriter(routerFolder.getPath() + "/" + routerFile.fileName())) {
            writer.write(file.toJSONString());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
