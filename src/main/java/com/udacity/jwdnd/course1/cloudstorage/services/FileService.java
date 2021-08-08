package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.File;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {
    private FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public List<File> getAllFiles(int userid){
        return this.fileMapper.getAllFiles(userid);
    }

    public int insertFile(File file) {
        if(!findFileByName(file.getFilename())){
            return this.fileMapper.insertFile(file);
        }
        else return 0;
    }

    public boolean findFileByName(String filename){
        if(this.fileMapper.getFileByName(filename) != null) return true; //File found
        else return false; //File not found
    }

    public void deleteFile(Integer fileId) {
        this.fileMapper.deleteFile(fileId);
    }

    public File getFiles(Integer userid, Integer fileId) {
        return this.fileMapper.getFile(userid, fileId);
    }
}
