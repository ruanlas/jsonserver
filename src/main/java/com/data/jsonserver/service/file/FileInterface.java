package com.data.jsonserver.service.file;

public interface FileInterface {
    String fileName();
    String fileName(boolean withoutExtension);
    String extension();
}
