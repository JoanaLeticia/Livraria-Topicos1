package com.bookstore.eagle.dto;

import com.bookstore.eagle.model.User;

public record UserResponseDTO (
    Long id,
    String name,
    String email,
    String login
) {

    public static UserResponseDTO valueOf(User u) {
        if (u.getNaturalPerson() == null)
            return new UserResponseDTO(u.getId(), null, null, u.getLogin());
        
            return new UserResponseDTO(u.getId(),
                u.getNaturalPerson().getName(),
                u.getClient().getEmail(),
                u.getLogin());
    }

}
