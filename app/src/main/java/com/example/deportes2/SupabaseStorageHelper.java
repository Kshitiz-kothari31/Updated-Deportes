package com.example.deportes2;
//
//import android.util.Log;
//import java.io.File;
//import io.supabase.SupabaseClient;
//
//public class SupabaseStorageHelper {
//
//    private static final String BUCKET_NAME = "public"; // Your Supabase bucket name
//    private final SupabaseClient supabaseClient;
//
//    public SupabaseStorageHelper(StorageClient client) {
//        this.storageClient = client;
//    }
//
//    public interface UploadCallback {
//        void onSuccess(String imageUrl);
//        void onFailure(Exception e);
//    }
//
//    public void uploadImage(File imageFile, String userId, UploadCallback callback) {
//        String path = "posts/" + userId + "/" + System.currentTimeMillis() + "_" + imageFile.getName();
//
//        supabaseClient.storage()
//                .from(BUCKET_NAME)
//                .upload(path, imageFile, null)
//                .thenAccept(response -> {
//                    if (response.isSuccessful()) {
//                        String publicUrl = supabaseClient.storage()
//                                .from(BUCKET_NAME)
//                                .getPublicUrl(path);
//                        callback.onSuccess(publicUrl);
//                    } else {
//                        callback.onFailure(new Exception(response.getError().toString()));
//                    }
//                })
//                .exceptionally(e -> {
//                    callback.onFailure(new Exception(e));
//                    return null;
//                });
//    }
//}
