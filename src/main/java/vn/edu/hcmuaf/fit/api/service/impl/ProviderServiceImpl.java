package vn.edu.hcmuaf.fit.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.api.dto.ProviderDTO;
import vn.edu.hcmuaf.fit.api.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.api.model.Provider;
import vn.edu.hcmuaf.fit.api.repository.ProviderRepository;
import vn.edu.hcmuaf.fit.api.service.ProviderService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProviderServiceImpl implements ProviderService {
    @Autowired
    private ProviderRepository providerRepository;

    public ProviderServiceImpl(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    @Override
    public Provider saveProvider(ProviderDTO providerDTO) {
        Provider provider = new Provider();
        provider.setId(providerDTO.getId());
        provider.setName(providerDTO.getName());
        provider.setStatus((byte) 1);
        provider.setCreatedAt(LocalDateTime.now());

        return providerRepository.save(provider);
    }

    @Override
    public List<Provider> getProviders() {
        return providerRepository.findAll();
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
