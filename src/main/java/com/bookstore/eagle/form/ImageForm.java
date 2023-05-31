package com.bookstore.eagle.form;

import jakarta.ws.rs.FormParam;
import org.jboss.resteasy.annotations.providers.multipart.PartType;

public class ImageForm {
    @FormParam("image_name")
    private String imageName;

    @FormParam("image")
    @PartType("application/octet-stream")
    private byte[] image;

    public ImageForm() {

    }

    public ImageForm(String imageName, byte[] image) {
        this.imageName = imageName;
        this.image = image;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    

}
