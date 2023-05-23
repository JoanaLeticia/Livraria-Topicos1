package com.bookstore.eagle.service;

import java.util.List;

import com.bookstore.eagle.dto.AddressDTO;
import com.bookstore.eagle.dto.AddressResponseDTO;

public interface AddressService {

    List<AddressResponseDTO> listAddresses();

    AddressResponseDTO searchAddressById(Long id);

    AddressResponseDTO addAddress(AddressDTO addressDTO);

    AddressResponseDTO updateAddress(Long id, AddressDTO addressDTO);

    void deleteAddress(Long id);

}
