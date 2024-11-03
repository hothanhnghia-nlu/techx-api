package vn.edu.hcmuaf.fit.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.api.dto.ImageDTO;
import vn.edu.hcmuaf.fit.api.dto.ProductDTO;
import vn.edu.hcmuaf.fit.api.dto.ProviderDTO;
import vn.edu.hcmuaf.fit.api.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.api.model.Image;
import vn.edu.hcmuaf.fit.api.model.Product;
import vn.edu.hcmuaf.fit.api.model.Provider;
import vn.edu.hcmuaf.fit.api.repository.ImageRepository;
import vn.edu.hcmuaf.fit.api.repository.ProductRepository;
import vn.edu.hcmuaf.fit.api.repository.ProviderRepository;
import vn.edu.hcmuaf.fit.api.service.ImageService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ProviderRepository providerRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProviderServiceImpl providerService;
    @Autowired
    private ProductServiceImpl productService;

    public ImageServiceImpl(ImageRepository imageRepository, ProviderRepository providerRepository, ProductRepository productRepository) {
        this.imageRepository = imageRepository;
        this.providerRepository = providerRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Image saveImage(int providerId, int productId, ImageDTO imageDTO) {
        Provider provider = providerRepository.findById(providerId).orElseThrow(() ->
                new ResourceNotFoundException("Provider", "Id", imageDTO.getId()));

        Product product = productRepository.findById(productId).orElseThrow(() ->
                new ResourceNotFoundException("Provider", "Id", imageDTO.getId()));

        Image image = new Image();
        image.setId(imageDTO.getId());
        image.setName(imageDTO.getName());
        image.setUrl(imageDTO.getUrl());
        image.setProvider(provider);
        image.setProduct(product);

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

    @Override
    public Image updateImageByID(Integer id, ImageDTO imageDTO) {
        Image existingImage = imageRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Image", "Id", id));

        Image image = getImageByID(id);
        Provider provider = providerService.getProviderByID(image.getProvider().getId());
        Product product = productService.getProductByID(image.getProvider().getId());

        existingImage.setName(imageDTO.getName() != null ? imageDTO.getName() : existingImage.getName());
        existingImage.setUrl(imageDTO.getUrl() != null ? imageDTO.getUrl() : existingImage.getUrl());
        existingImage.setProvider(imageDTO.getProvider() != null ? provider : existingImage.getProvider());
        existingImage.setProduct(imageDTO.getProducts() != null ? product : existingImage.getProduct());

        return imageRepository.save(existingImage);
    }

    @Override
    public void deleteImageByID(Integer id) {
        imageRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Image", "Id", id));

        imageRepository.deleteById(id);
    }
}
