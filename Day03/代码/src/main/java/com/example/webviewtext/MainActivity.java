package com.example.webviewtext;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private LollipopFixedWebView mWeb;
    private Button mWebLoad;
    private ProgressBar mBarProgress;
    private WebSettings settings;
    private Button mHtmlLoad;
    private TextView mProgress;
    private final int CODE_PERMISSION = 4;
    private final int CODE_CROP = 3;
    private boolean FLAG_PERMISSION = false;
    private List<String> list;
    private File file;
    private Uri cropUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mWebLoad = (Button) findViewById(R.id.load_Web);
        mWebLoad.setOnClickListener(this);
        mBarProgress = (ProgressBar) findViewById(R.id.progress_bar);
        mWeb = (LollipopFixedWebView) findViewById(R.id.web);
        mProgress = (TextView) findViewById(R.id.progress);
        mHtmlLoad = (Button) findViewById(R.id.load_html);
        mHtmlLoad.setOnClickListener(this);
        //6.0以后需要获取权限
        obtainPermission();
        setWeb();
//设置拦截url
        mWeb.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                //开始加载，显示进度条
                mProgress.setVisibility(View.VISIBLE);
                mBarProgress.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);
            }
        });
        mWeb.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                //进度变化
                //如果是100，代表加载完成停止显示进度条
                if (newProgress == 100) {
                    mBarProgress.setVisibility(View.GONE);
                    mProgress.setVisibility(View.GONE);
                }
                mProgress.setText(newProgress+"%");
                Log.i("进度", "onProgressChanged: " + newProgress);
                mBarProgress.setProgress(newProgress);
                super.onProgressChanged(view, newProgress);
            }


        });
        mWeb.addJavascriptInterface(new JavaScriptInterfaceClass(this),"test");

    }

    //获取权限
    private void obtainPermission() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            list = new ArrayList<>();
            if(checkSelfPermission(Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED)
            {
                list.add(Manifest.permission.CAMERA);
            }
            if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            {
                list.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
            if(list.size()!=0)
                requestPermissions(list.toArray(new String[list.size()]),CODE_PERMISSION);
        }
        else
            FLAG_PERMISSION = true;
    }

    //Webview设置属性
    private void setWeb() {
        settings = mWeb.getSettings();
        //设置与JS交互
        settings.setJavaScriptEnabled(true);
        //设置自适应屏幕
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        //设置缩放
        settings.setSupportZoom(true);//支持缩放默认为true
        settings.setBuiltInZoomControls(true);//设置内置的缩放控件。若为false，则WebView不可缩放
        settings.setDisplayZoomControls(false);//隐藏原生控件
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,  String[] permissions,  int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d("test", Arrays.toString(grantResults));
        for(int i=0;i<grantResults.length;i++) {
            if (grantResults[i] == -1)
            {
                FLAG_PERMISSION = false;
                break;
            }
        }
        FLAG_PERMISSION = true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.load_Web:
                // TODO 20/06/01
                mWeb.loadUrl("https://www.cdstm.cn/");
                break;
            case R.id.load_html:// TODO 20/06/01
                //方式2：加载apk包中的html页面
                mWeb.loadUrl("file:///android_asset/demo2.html");
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (mWeb != null && mWeb.canGoBack()) {
            mWeb.goBack();
        } else {
            super.onBackPressed();
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //         回调成功
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                // 相册选取
                case 100:
                    Uri selectedImage = data.getData();
                    String[] filePathColumns = {MediaStore.Images.Media.DATA};
                    Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
                    c.moveToFirst();
                    int columnIndex = c.getColumnIndex(filePathColumns[0]);
                    String imagePath = c.getString(columnIndex);
                    c.close();
                    cropPhoto(selectedImage);
                    break;
                case CODE_CROP:
                    mWeb.evaluateJavascript("javascript:logImgPath(\"" + cropUri.toString() + "\")", new ValueCallback<String>() {
                        @Override
                        public void onReceiveValue(String value) {
                            Toast.makeText(MainActivity.this, value, Toast.LENGTH_SHORT).show();
                        }
                    });
//                    Glide.with(this).load(cropUri).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(mPicUser);
//                    popupWindow.dismiss();
            }
        }
    }
    //剪切图片
    private void cropPhoto(Uri uri) {
        Log.d("test","uri:"+uri.toString());
        file = new File(Environment.getExternalStorageDirectory(),"cropImage"+System.currentTimeMillis()+".jpg");
//        showToast(file.getPath());
        if(file.exists())
            file.delete();
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent("com.android.camera.action.CROP");
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
        {
            intent.addFlags(FLAG_GRANT_READ_URI_PERMISSION);
        }
        cropUri = Uri.fromFile(file);
        intent.setDataAndType(uri,"image/*");
        //裁剪图片的宽高比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("crop", "true");//可裁剪
        // 裁剪后输出图片的尺寸大小
        //intent.putExtra("outputX", 400);
        //intent.putExtra("outputY", 200);
        intent.putExtra("scale", true);//支持缩放
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, cropUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());//输出图片格式
        intent.putExtra("noFaceDetection", true);//取消人脸识别
        startActivityForResult(intent, CODE_CROP);
    }
}
