package com.imageExmple.coder.serviceImpl;

import com.imageExmple.coder.model.Image;
import com.imageExmple.coder.repository.ImageRepositoy;
import com.imageExmple.coder.service.ImageService;
import com.imageExmple.coder.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepositoy repo;


    @Override
    public String uploadImage(MultipartFile file) throws IOException {
        Image image =  repo.save(Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes())).build());

        if(image != null){
            return "file upload success : " + file.getOriginalFilename();
        }
        return null;
    }

    @Override
    public byte[] downloadImage(String fileName) {
        Optional<Image> dbImageData = repo.findByName(fileName);
        byte[] images = ImageUtils.decompressImage(dbImageData.get().getImageData());
        return images;
    }


}
