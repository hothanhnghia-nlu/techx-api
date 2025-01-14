package vn.edu.hcmuaf.fit.api.service;

import vn.edu.hcmuaf.fit.api.dto.AddressDTO;
import vn.edu.hcmuaf.fit.api.dto.AddressDTO;
import vn.edu.hcmuaf.fit.api.model.Address;

import java.util.List;

public interface AddressService {
    Address saveAddress(AddressDTO addressDTO);

    List<AddressDTO> getAllAddresses();

    List<AddressDTO> getAddressesByUser();
    AddressDTO getAddressDefault();

    AddressDTO getAddressByID(Integer id);

    Address updateAddressByID(Integer id, AddressDTO addressDTO);

    void deleteAddressByID(Integer id);

    Address updateAddress(Address address);
}
