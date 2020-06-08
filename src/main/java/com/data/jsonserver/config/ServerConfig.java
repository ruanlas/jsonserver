package com.data.jsonserver.config;

import com.data.jsonserver.service.FileServiceInterface;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServerConfig {

    private FileServiceInterface fileService;

    public ServerConfig(FileServiceInterface fileService) {
        this.fileService = fileService;
    }

    @Bean
    public void createFolder(){
        fileService.createServerFolder();
    }
}
