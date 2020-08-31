package com.data.jsonserver.service.file.router;

import com.data.jsonserver.service.file.AbstractFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RouterFile extends AbstractFile {

    public RouterFile(
            @Value("${router.filename}")String routerFileName,
            @Value("${router.fileextension}") String routerFileExtension) {
        super(routerFileName, routerFileExtension);
    }

}
