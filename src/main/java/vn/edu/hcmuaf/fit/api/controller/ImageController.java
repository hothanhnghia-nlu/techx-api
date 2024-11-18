package vn.edu.hcmuaf.fit.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.api.dto.ImageDTO;
import vn.edu.hcmuaf.fit.api.model.Image;
import vn.edu.hcmuaf.fit.api.service.ImageService;

import java.util.List;

@RestController
@RequestMapping("api/v1/images")
public class ImageController {
    @Autowired
    private ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    // Create a new Image
    @PostMapping()
    public ResponseEntity<Image> createImage(@RequestParam int providerId,
                                             @RequestParam int productId,
                                             @RequestBody ImageDTO image) {
        return new ResponseEntity<>(imageService.saveImage(providerId, productId,image), HttpStatus.CREATED);
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

    // Update Image by id
    @PutMapping("{id}")
    public ResponseEntity<Image> updateImageById(@PathVariable ("id") int id,
                                                       @RequestBody ImageDTO imageDTO) {
        return new ResponseEntity<>(imageService.updateImageByID(id, imageDTO), HttpStatus.OK);
    }

    // Delete Image by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteImageById(@PathVariable ("id") int id) {
        imageService.deleteImageByID(id);
        return new ResponseEntity<>("Image " + id + " is deleted successfully!", HttpStatus.OK);
    }

}
