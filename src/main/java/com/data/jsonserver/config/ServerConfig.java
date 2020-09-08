package com.data.jsonserver.config;

import com.data.jsonserver.service.file.FolderManagerInterface;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServerConfig {

    private FolderManagerInterface folderManager;

    public ServerConfig(FolderManagerInterface folderManager) {
        this.folderManager = folderManager;
    }

    @Bean
    public void createFolder(){
        folderManager.createRoot();
    }
}
