package com.data.jsonserver.service.file.router;

import com.data.jsonserver.service.file.AbstractFolder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RouterFolder extends AbstractFolder {

    private String routerFolderPath;

    public RouterFolder(@Value("${server.folder.router}") String routerFolderPath) {
        this.routerFolderPath = routerFolderPath;
    }

    @Override
    public String failCreateFolderMessage() {
        return "Não foi possível criar a pasta de rotas";
    }

    @Override
    public String getPath() {
        return routerFolderPath;
    }
}
