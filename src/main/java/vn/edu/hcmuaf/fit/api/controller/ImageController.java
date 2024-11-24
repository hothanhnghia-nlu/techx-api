package vn.edu.hcmuaf.fit.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.hcmuaf.fit.api.dto.ImageDTO;
import vn.edu.hcmuaf.fit.api.model.Image;
import vn.edu.hcmuaf.fit.api.model.Provider;
import vn.edu.hcmuaf.fit.api.service.ImageService;
import vn.edu.hcmuaf.fit.api.service.ProviderService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/images")
public class ImageController {
    @Autowired
    private ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    // Get all Image
    @GetMapping
    public List<ImageDTO> getAllImages() {
        return imageService.getImages();
    }

    // Get Image by id
    @GetMapping("{id}")
    public ResponseEntity<Image> getImageById(@PathVariable ("id") int id) {
        return new ResponseEntity<>(imageService.getImageByID(id), HttpStatus.OK);
    }

}
