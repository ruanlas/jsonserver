package com.data.mockapiserver.service.file.router;

import com.data.mockapiserver.service.exception.Error;
import com.data.mockapiserver.service.file.FileInterface;
import com.data.mockapiserver.service.file.ManagerFileInterface;
import com.data.mockapiserver.service.file.FolderInterface;
import com.data.mockapiserver.service.file.exception.FileNotFoundServerException;
import com.data.mockapiserver.service.file.exception.ParseError;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class RouterManagerFile implements ManagerFileInterface<JSONObject> {

    private FolderInterface routerFolder;
    private FileInterface routerFile;

    public RouterManagerFile(FolderInterface routerFolder, FileInterface routerFile) {
        this.routerFolder = routerFolder;
        this.routerFile = routerFile;
    }

    @Override
    public JSONObject readFile() throws FileNotFoundServerException {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(routerFolder.getPath() + "/" + routerFile.fileName())){
            Object obj = jsonParser.parse(reader);
            JSONObject object = (JSONObject)obj;
            return object;
        } catch (FileNotFoundException e) {
            throw new FileNotFoundServerException();
        } catch (IOException e) {
            throw new Error(e.getMessage());
        } catch (ParseException e) {
            throw new ParseError(e.getMessage());
        }
    }

    @Override
    public void writeFile(JSONObject file) {
        try (FileWriter writer = new FileWriter(routerFolder.getPath() + "/" + routerFile.fileName())) {
            writer.write(file.toJSONString());
            writer.flush();
        } catch (IOException e) {
            throw new Error(e.getMessage());
        }
    }
}
