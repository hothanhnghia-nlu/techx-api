package vn.edu.hcmuaf.fit.api.service;

import org.springframework.web.multipart.MultipartFile;
import vn.edu.hcmuaf.fit.api.dto.ImageDTO;
import vn.edu.hcmuaf.fit.api.model.Image;
import vn.edu.hcmuaf.fit.api.model.Provider;

import java.io.IOException;
import java.util.List;

public interface ImageService {
    List<ImageDTO> getImages();
    Image getImageByID(Integer id);
}
