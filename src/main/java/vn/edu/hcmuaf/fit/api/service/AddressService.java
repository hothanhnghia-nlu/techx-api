package vn.edu.hcmuaf.fit.api.service;

import vn.edu.hcmuaf.fit.api.dto.AddressDTO;
import vn.edu.hcmuaf.fit.api.dto.AddressDTO;
import vn.edu.hcmuaf.fit.api.model.Address;

import java.util.List;

public interface AddressService {
    Address saveAddress(int userId, AddressDTO addressDTO);
    List<AddressDTO> getAllAddresses();
    List<AddressDTO> getAddressesByUser();
    Address getAddressByID(Integer id);
    Address updateAddressByID(Integer id, AddressDTO addressDTO);
    void deleteAddressByID(Integer id);

}
