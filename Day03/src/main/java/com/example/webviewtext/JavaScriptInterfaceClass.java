package com.example.webviewtext;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;
import android.webkit.JavascriptInterface;

public class JavaScriptInterfaceClass {
    Context context;

    public JavaScriptInterfaceClass(Context context) {
        this.context = context;
    }

    @JavascriptInterface
    public void toCampture(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
      MainActivity activity=  (MainActivity)context;
      activity.startActivityForResult(intent,100);
    }
}
