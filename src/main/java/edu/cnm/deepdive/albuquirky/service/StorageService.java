package edu.cnm.deepdive.albuquirky.service;

import java.io.IOException;
import org.springframework.core.io.Resource;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

  String store(MultipartFile file) throws IOException, HttpMediaTypeNotAcceptableException;

  Resource retrieve(String reference) throws IOException;

  boolean delete(String reference)
      throws IOException, UnsupportedOperationException, SecurityException;

}
