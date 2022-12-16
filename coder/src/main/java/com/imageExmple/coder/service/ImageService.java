package com.imageExmple.coder.service;

import com.imageExmple.coder.repository.ImageRepositoy;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@EnableJpaRepositories
public interface ImageService{


    String uploadImage(MultipartFile file) throws IOException;
    byte[] downloadImage(String fileName);



}
