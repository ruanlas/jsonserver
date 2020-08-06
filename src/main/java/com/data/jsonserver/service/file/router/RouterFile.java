package com.data.jsonserver.service.file.router;

import com.data.jsonserver.service.file.FileInterface;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RouterFile implements FileInterface {

    private String routerFileName;
    private String routerFileExtension;

    public RouterFile(
            @Value("${router.filename}")String routerFileName,
            @Value("${router.fileextension}") String routerFileExtension) {
        this.routerFileName = routerFileName;
        this.routerFileExtension = routerFileExtension;
    }

    @Override
    public String fileName() {
        return routerFileName + "." + routerFileExtension;
    }

    @Override
    public String fileName(boolean withoutExtension) {
        if (withoutExtension)
            return routerFileName;
        return fileName();
    }

    @Override
    public String extension() {
        return routerFileExtension;
    }
}
