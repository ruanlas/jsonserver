package com.data.jsonserver.service.file;

public abstract class AbstractFile implements FileInterface{
    private String fileName;
    private String extension;

    public AbstractFile(String fileName, String extension) {
        this.fileName = fileName;
        this.extension = extension;
    }

    @Override
    public String fileName() {
        return fileName + "." + extension;
    }

    @Override
    public String fileName(boolean withoutExtension) {
        if (withoutExtension)
            return fileName;
        return fileName();
    }

    @Override
    public String extension() {
        return extension;
    }
}
