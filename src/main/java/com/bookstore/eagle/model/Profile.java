package com.bookstore.eagle.model;

public enum Profile {
    ADMIN(1, "Administrator"),
    USER(2, "User");

    private int id;

    private String label;

    Profile(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static Profile valueOf(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;
        for (Profile profile : Profile.values()) {
            if (id.equals(profile.getId()))
                return profile;
        }
        throw new IllegalArgumentException("Invalid id:" + id);
    }
}
