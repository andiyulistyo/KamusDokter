package com.wenda.kamus.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ConvertImage {

    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

}
