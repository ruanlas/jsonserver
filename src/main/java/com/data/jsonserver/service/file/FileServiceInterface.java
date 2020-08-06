package com.data.jsonserver.service.file;

//    https://howtodoinjava.com/library/json-simple-read-write-json-examples/
public interface FileServiceInterface<F> {
    F readFile();
    void writeFile(F file);
}
