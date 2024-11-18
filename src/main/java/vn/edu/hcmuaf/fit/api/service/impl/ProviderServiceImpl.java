package vn.edu.hcmuaf.fit.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.hcmuaf.fit.api.dto.ImageDTO;
import vn.edu.hcmuaf.fit.api.dto.ProductDTO;
import vn.edu.hcmuaf.fit.api.dto.ProviderDTO;
import vn.edu.hcmuaf.fit.api.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.api.model.Image;
import vn.edu.hcmuaf.fit.api.model.Product;
import vn.edu.hcmuaf.fit.api.model.Provider;
import vn.edu.hcmuaf.fit.api.model.Provider;
import vn.edu.hcmuaf.fit.api.repository.ImageRepository;
import vn.edu.hcmuaf.fit.api.repository.ProviderRepository;
import vn.edu.hcmuaf.fit.api.service.ImageService;
import vn.edu.hcmuaf.fit.api.service.ProviderService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProviderServiceImpl implements ProviderService {
    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    @Lazy
    private ImageServiceImpl imageService;

    public ProviderServiceImpl(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    @Override
    @Transactional
    public Provider saveProvider(ProviderDTO providerDTO, MultipartFile imageFile) throws IOException {
        Provider provider = new Provider();
        provider.setId(providerDTO.getId());
        provider.setName(providerDTO.getName());
        provider.setStatus((byte) 1);
        provider.setCreatedAt(LocalDateTime.now());

        if (imageFile != null && !imageFile.isEmpty()) {
            Image image = imageService.saveProviderImage(imageFile, provider);
            provider.setImage(image);
        }

        return providerRepository.save(provider);
    }

    @Override
    public List<ProviderDTO> getProviders() {
        List<Provider> providers = providerRepository.findAll();

        return providers.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private ProviderDTO convertToDTO(Provider provider) {
        ImageDTO imageDTO = null;
        if (provider.getImage() != null) {
            imageDTO = new ImageDTO(
                    provider.getImage().getId(),
                    provider.getImage().getName(),
                    provider.getImage().getUrl()
            );
        }

        ProviderDTO providerDTO = new ProviderDTO();
        providerDTO.setId(provider.getId());
        providerDTO.setName(provider.getName());
        providerDTO.setImage(imageDTO);

        return providerDTO;
    }

    @Override
    public Provider getProviderByID(Integer id) {
        return providerRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Provider", "Id", id));
    }

    @Override
    public Provider updateProviderByID(Integer id, ProviderDTO providerDTO) {
        Provider existingProvider = providerRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Provider", "Id", id));

        existingProvider.setName(providerDTO.getName() != null ? providerDTO.getName() : existingProvider.getName());
        existingProvider.setStatus(providerDTO.getStatus() != 0 ? providerDTO.getStatus() : existingProvider.getStatus());
        existingProvider.setUpdatedAt(LocalDateTime.now());

        return providerRepository.save(existingProvider);
    }

    @Override
    public void deleteProviderByID(Integer id) {
        providerRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Provider", "Id", id));

        providerRepository.deleteById(id);
    }
}
