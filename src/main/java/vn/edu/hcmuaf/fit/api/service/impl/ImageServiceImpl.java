package vn.edu.hcmuaf.fit.api.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.hcmuaf.fit.api.dto.ImageDTO;
import vn.edu.hcmuaf.fit.api.dto.ProductDTO;
import vn.edu.hcmuaf.fit.api.dto.ProviderDTO;
import vn.edu.hcmuaf.fit.api.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.api.model.Image;
import vn.edu.hcmuaf.fit.api.model.Product;
import vn.edu.hcmuaf.fit.api.model.Provider;
import vn.edu.hcmuaf.fit.api.repository.ImageRepository;
import vn.edu.hcmuaf.fit.api.service.ImageService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ProviderServiceImpl providerService;

    @Autowired
    private Cloudinary cloudinary;

    public ImageServiceImpl(ImageRepository imageRepository, @Lazy ProviderServiceImpl providerService) {
        this.imageRepository = imageRepository;
        this.providerService = providerService;
    }

    // Save Provider Image
    public Image saveProviderImage(MultipartFile file, Provider provider) throws IOException {
        String providerName = provider.getName();
        String fileExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

        String folderPath = "src/main/resources/static/images/providers/";

        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        Path path = Paths.get(folderPath + providerName);
        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

        Map uploadResult = cloudinary.uploader().upload(file.getBytes(),
                ObjectUtils.asMap("public_id", "providers/" + providerName));

        String url = (String) uploadResult.get("url");

        Image image = new Image();
        image.setName(providerName);
        image.setUrl(url);
        image.setProvider(provider);

        return imageRepository.save(image);
    }

    @Override
    public List<ImageDTO> getImages() {
        List<Image> images = imageRepository.findAll();

        return images.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
    
    private ImageDTO convertToDTO(Image image) {
        ProviderDTO providerDTO = null;
        if (image.getProvider() != null) {
            providerDTO = new ProviderDTO(
                    image.getProvider().getId(),
                    image.getProvider().getName()
            );
        }

        ProductDTO productDTO = null;
        if (image.getProduct() != null) {
            productDTO = new ProductDTO(
                    image.getProduct().getId(),
                    image.getProduct().getName()
            );
        }

        ImageDTO imageDTO = new ImageDTO();
        imageDTO.setId(image.getId());
        imageDTO.setName(image.getName());
        imageDTO.setUrl(image.getUrl());
        imageDTO.setProvider(providerDTO);

        List<ProductDTO> list = new ArrayList<>();
        if (productDTO != null) {
            list.add(productDTO);
            imageDTO.setProducts(list);
        }

        return imageDTO;
    }

    @Override
    public Image getImageByID(Integer id) {
        return imageRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Image", "Id", id));
    }

}
