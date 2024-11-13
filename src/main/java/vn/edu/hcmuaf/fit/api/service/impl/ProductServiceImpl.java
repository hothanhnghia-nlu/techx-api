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
import vn.edu.hcmuaf.fit.api.repository.ProductRepository;
import vn.edu.hcmuaf.fit.api.repository.ProviderRepository;
import vn.edu.hcmuaf.fit.api.service.ProductService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProviderRepository providerRepository;
    @Autowired
    private ProviderServiceImpl providerService;

    public ProductServiceImpl(ProductRepository productRepository, ProviderRepository providerRepository) {
        this.productRepository = productRepository;
        this.providerRepository = providerRepository;
    }

    @Override
    public Product saveProduct(int providerId, ProductDTO productDTO) {
        Provider provider = providerRepository.findById(providerId).orElseThrow(() ->
                new ResourceNotFoundException("Provider", "Id", productDTO.getId()));

        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setProvider(provider);
        product.setOriginalPrice(productDTO.getOriginalPrice());
        product.setNewPrice(productDTO.getNewPrice());
        product.setQuantity(productDTO.getQuantity());
        product.setColor(productDTO.getColor());
        product.setScreen(productDTO.getScreen());
        product.setOperatingSystem(productDTO.getOperatingSystem());
        product.setCamera(productDTO.getCamera());
        product.setCpu(productDTO.getCpu());
        product.setRam(productDTO.getRam());
        product.setStorage(productDTO.getStorage());
        product.setBattery(productDTO.getBattery());
        product.setDescription(productDTO.getDescription());
        product.setStatus((byte) 1);
        product.setCreatedAt(LocalDateTime.now());

        return productRepository.save(product);
    }

    @Override
    public List<ProductDTO> getProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private ProductDTO convertToDTO(Product product) {
        ProviderDTO providerDTO = null;
        ImageDTO imageDTO;
        List<ImageDTO> imageDTOList = new ArrayList<>();

        if (product.getProvider() != null) {
            providerDTO = new ProviderDTO(
                    product.getProvider().getId(),
                    product.getProvider().getName()
            );
        }

        if (product.getImages() != null) {
            for (Image image : product.getImages()) {
                imageDTO = new ImageDTO(
                        image.getId(),
                        image.getName(),
                        image.getUrl()
                );
                imageDTOList.add(imageDTO);
            }
        }

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setProvider(providerDTO);
        productDTO.setOriginalPrice(product.getOriginalPrice());
        productDTO.setNewPrice(product.getNewPrice());
        productDTO.setQuantity(product.getQuantity());
        productDTO.setColor(product.getColor());
        productDTO.setScreen(product.getScreen());
        productDTO.setOperatingSystem(product.getOperatingSystem());
        productDTO.setCamera(product.getCamera());
        productDTO.setCpu(product.getCpu());
        productDTO.setRam(product.getRam());
        productDTO.setStorage(product.getStorage());
        productDTO.setBattery(product.getBattery());
        productDTO.setDescription(product.getDescription());
        productDTO.setStatus((product.getStatus()));
        productDTO.setCreatedAt(product.getCreatedAt());
        productDTO.setUpdatedAt(product.getUpdatedAt());
        productDTO.setImages(imageDTOList);

        return productDTO;
    }

    @Override
    public Product getProductByID(Integer id) {
        return productRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product", "Id", id));
    }

    @Override
    public Product updateProductByID(Integer id, ProductDTO productDTO) {
        Product existingProduct = productRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product", "Id", id));

        Product product = getProductByID(id);
        Provider provider = providerService.getProviderByID(product.getProvider().getId());

        existingProduct.setName(productDTO.getName() != null ? productDTO.getName() : existingProduct.getName());
        existingProduct.setProvider(productDTO.getProvider() != null ? provider : existingProduct.getProvider());
        existingProduct.setOriginalPrice(productDTO.getOriginalPrice() != 0 ? productDTO.getOriginalPrice() : existingProduct.getOriginalPrice());
        existingProduct.setNewPrice(productDTO.getNewPrice() != 0 ? productDTO.getNewPrice() : existingProduct.getNewPrice());
        existingProduct.setQuantity(productDTO.getQuantity() != 0 ? productDTO.getQuantity() : existingProduct.getQuantity());
        existingProduct.setColor(productDTO.getColor() != null ? productDTO.getColor() : existingProduct.getColor());
        existingProduct.setScreen(productDTO.getScreen() != null ? productDTO.getScreen() : existingProduct.getScreen());
        existingProduct.setOperatingSystem(productDTO.getOperatingSystem() != null ? productDTO.getOperatingSystem() : existingProduct.getOperatingSystem());
        existingProduct.setCamera(productDTO.getCamera() != null ? productDTO.getCamera() : existingProduct.getCamera());
        existingProduct.setCpu(productDTO.getCpu() != null ? productDTO.getCpu() : existingProduct.getCpu());
        existingProduct.setRam(productDTO.getRam() != null ? productDTO.getRam() : existingProduct.getRam());
        existingProduct.setStorage(productDTO.getStorage() != null ? productDTO.getStorage() : existingProduct.getStorage());
        existingProduct.setBattery(productDTO.getBattery() != null ? productDTO.getBattery() : existingProduct.getBattery());
        existingProduct.setDescription(productDTO.getDescription() != null ? productDTO.getDescription() : existingProduct.getDescription());
        existingProduct.setStatus(productDTO.getStatus() != 0 ? productDTO.getStatus() : existingProduct.getStatus());
        existingProduct.setUpdatedAt(LocalDateTime.now());

        return productRepository.save(existingProduct);
    }

    @Override
    public void deleteProductByID(Integer id) {
        productRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product", "Id", id));

        productRepository.deleteById(id);
    }
}
