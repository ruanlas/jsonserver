package com.data.jsonserver.config;

import com.data.jsonserver.service.file.FolderManagerInterface;
import com.data.jsonserver.service.fileserviceold.FileServiceInterface;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServerConfig {

    private FileServiceInterface fileService;
    private FolderManagerInterface folderManager;

    public ServerConfig(FileServiceInterface fileService, FolderManagerInterface folderManager) {
        this.fileService = fileService;
        this.folderManager = folderManager;
    }

    @Bean
    public void createFolder(){
        folderManager.createRoot();
    }
}
