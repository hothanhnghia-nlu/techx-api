package vn.edu.hcmuaf.fit.api.service;

import vn.edu.hcmuaf.fit.api.dto.ProviderDTO;
import vn.edu.hcmuaf.fit.api.model.Provider;

import java.util.List;

public interface ProviderService {
    Provider saveProvider(ProviderDTO ProviderDTO);
    List<Provider> getProviders();
    Provider getProviderByID(Integer id);
    Provider updateProviderByID(Integer id, ProviderDTO ProviderDTO);
    void deleteProviderByID(Integer id);

}
