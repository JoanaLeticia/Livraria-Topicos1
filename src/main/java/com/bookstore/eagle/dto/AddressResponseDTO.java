package com.bookstore.eagle.dto;

import com.bookstore.eagle.model.Address;

public record AddressResponseDTO(
    Long id,
    boolean main,
    String street,
    String number,
    String adjunct,
    String neighborhood,
    String zip
) {

    public AddressResponseDTO(Address address) {
        this(address.getId(),
        address.isMain(),
        address.getStreet(),
        address.getNumber(),
        address.getAdjunct(),
        address.getNeighborhood(),
        address.getZip());
    }

}
