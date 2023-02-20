package com.backend.restaurant_service.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

public class CloudinaryInstance {

    private static Cloudinary cloudinary;

    private CloudinaryInstance(){}

    public static Cloudinary getInstance(){
        if(cloudinary==null){
            cloudinary=new Cloudinary(ObjectUtils.asMap(
                    "cloud_name", "dryrxauqv", // insert here you cloud name
                    "api_key", "131868383939429", // insert here your api code
                    "api_secret", "YSkGNNLo6dedxYg4gjpXpQpwzKE")); // insert here your api secret
        }

        return cloudinary;
    }
}
