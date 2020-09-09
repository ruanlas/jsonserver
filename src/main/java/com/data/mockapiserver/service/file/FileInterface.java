package com.data.mockapiserver.service.file;

public interface FileInterface {
    String fileName();
    String fileName(boolean withoutExtension);
    String extension();
}
