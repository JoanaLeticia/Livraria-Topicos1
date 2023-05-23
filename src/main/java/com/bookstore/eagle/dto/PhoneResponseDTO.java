package com.bookstore.eagle.dto;

public class PhoneResponseDTO {
    private Long id;
    private int ddd;
    private String number;

    public PhoneResponseDTO(Long id, int ddd, String number) {
        this.id = id;
        this.ddd = ddd;
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDdd() {
        return ddd;
    }

    public void setDdd(int ddd) {
        this.ddd = ddd;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}
