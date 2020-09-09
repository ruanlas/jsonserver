package com.data.mockapiserver.service.file;

import com.data.mockapiserver.service.file.exception.FileNotFoundServerException;

//    https://howtodoinjava.com/library/json-simple-read-write-json-examples/
public interface FileServiceInterface<F> {
    F readFile() throws FileNotFoundServerException;
    void writeFile(F file);
}
