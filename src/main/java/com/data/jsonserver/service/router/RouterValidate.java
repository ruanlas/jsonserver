package com.data.jsonserver.service.router;

import org.springframework.stereotype.Component;

@Component
public class RouterValidate implements RouterValidateInterface{
    @Override
    public void validPath(String... paths) {
        for (String path : paths) {
            System.out.println(path);
        }
    }
}
