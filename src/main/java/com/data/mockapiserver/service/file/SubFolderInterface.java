package com.data.mockapiserver.service.file;

public interface SubFolderInterface {
//    FolderInterface getRoot();
//    String getSubFolder(String subFolderPath);
//    boolean existsSubFolder(String subFolderPath);
    void setPath(String subFolderPath);
    String getPath();
    void addSubFolder(String subFolderPath);
}
