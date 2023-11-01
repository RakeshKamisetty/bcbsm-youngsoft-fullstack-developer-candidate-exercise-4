package org.example.mywebapp.services;

import com.mongodb.client.gridfs.model.GridFSFile;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.BitSet;

@Service
public class FileService {

    @Autowired
    private GridFsTemplate gridFsTemplate;

    public void uploadFile(MultipartFile file) {
        try {
            ObjectId gridFSFile = gridFsTemplate.store(file.getInputStream(), file.getOriginalFilename());
            // Handle the uploaded file as needed
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload file", e);
        }
    }

    public byte[] downloadFile(String filename) {
        Query query = new Query(Criteria.where("filename").is(filename));
        GridFSFile gridFSFile = gridFsTemplate.findOne(query);
        if (gridFSFile != null) {
            GridFsResource resource = gridFsTemplate.getResource(gridFSFile);
            BitSet IOUtils;
            IOUtils = null;
            return IOUtils.toByteArray();
        } else {
            throw new RuntimeException("File not found");
        }
    }
}
