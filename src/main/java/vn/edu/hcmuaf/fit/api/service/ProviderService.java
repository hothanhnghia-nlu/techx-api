package vn.edu.hcmuaf.fit.api.service;

import org.springframework.web.multipart.MultipartFile;
import vn.edu.hcmuaf.fit.api.dto.ProviderDTO;
import vn.edu.hcmuaf.fit.api.model.Provider;

import java.io.IOException;
import java.util.List;

public interface ProviderService {
    Provider saveProvider(ProviderDTO ProviderDTO, MultipartFile imageFile) throws IOException;
    List<ProviderDTO> getProviders();
    Provider getProviderByID(Integer id);
    Provider updateProviderByID(Integer id, ProviderDTO ProviderDTO, MultipartFile imageFile) throws IOException;
    void deleteProviderByID(Integer id);

}
