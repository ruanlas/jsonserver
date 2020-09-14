package com.data.mockapiserver.service.file;

public interface SubFolderInterface {
    void setPath(String subFolderPath);
    String getPath();
    void addSubFolder(String subFolderPath);
}
