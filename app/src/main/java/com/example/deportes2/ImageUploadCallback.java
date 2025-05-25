package com.example.deportes2;

public interface ImageUploadCallback {
    void onSuccess(String imageUrl);
    void onFailure(String errorMessage);
}
