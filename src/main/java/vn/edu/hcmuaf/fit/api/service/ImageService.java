package vn.edu.hcmuaf.fit.api.service;

import vn.edu.hcmuaf.fit.api.dto.ImageDTO;
import vn.edu.hcmuaf.fit.api.model.Image;

import java.util.List;

public interface ImageService {
    Image saveImage(int providerId, int productId, ImageDTO imageDTO);
    List<ImageDTO> getImages();
    Image getImageByID(Integer id);
    Image updateImageByID(Integer id, ImageDTO imageDTO);
    void deleteImageByID(Integer id);

}
