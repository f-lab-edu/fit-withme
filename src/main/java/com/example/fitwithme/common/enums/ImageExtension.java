package com.example.fitwithme.common.enums;

public enum ImageExtension {
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    GIF("gif");

    ImageExtension(String imageExtension) { this.imageExtension = imageExtension; }

    private final String imageExtension;

    public String getImageExtension() { return imageExtension; }

    public boolean matches(String ext) {
        return this.imageExtension.equalsIgnoreCase(ext);
    }

    @Override
    public String toString() {
        return this.imageExtension;
    }

}