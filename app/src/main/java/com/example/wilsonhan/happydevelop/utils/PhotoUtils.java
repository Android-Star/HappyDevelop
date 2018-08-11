package com.example.wilsonhan.happydevelop.utils;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

import java.io.File;
import java.io.IOException;

public class PhotoUtils {

    /**
     * 检查申请权限结果
     * @param grantResults
     * @return
     */
    public static boolean permissionsGranted(int[] grantResults){
        for (int result : grantResults){
            if (result == PackageManager.PERMISSION_DENIED){
                return false;
            }
        }
        return true;
    }

    /**
     * 创建拍照照片保存路径Uri
     */
    public static Uri createCameraUri(Context context){
        Uri uri = null;
        File file = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
            file = new File(Environment.getExternalStorageDirectory().getPath()+ File.separator +"cameraPic");
        } else {
            file = new File(context.getFilesDir().getPath() + File.separator + "cameraPic");
        }
        if (file != null) {
            if (!file.exists()){
                file.mkdir();
            }
            File output = new File(file, System.currentTimeMillis() + ".png");
            try {
                if (output.exists()) {
                    output.delete();
                } else {
                    output.createNewFile();
                }
                uri = Uri.fromFile(output);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return uri;
    }

    /**
     * 创建剪裁照片保存路径Uri
     */
    public static Uri createCropUri(Context context){
        Uri uri = null;
        File file = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
            file = new File(Environment.getExternalStorageDirectory().getPath()
                    + File.separator +"cameraPic"+ File.separator +"cropPic");
        } else {
            file = new File(context.getFilesDir().getPath()
                    + File.separator +"cameraPic"+ File.separator +"cropPic");
        }
        if (file != null) {
            if (!file.exists()){
                file.mkdir();
            }
            File output = new File(file, System.currentTimeMillis() + ".png");
            try {
                if (output.exists()) {
                    output.delete();
                } else {
                    output.createNewFile();
                }
                uri = Uri.fromFile(output);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return uri;
    }

    /**
     * 打开相册
     */
    public static void selectPic(Activity activity, int requestCode) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 打开照相机
     */
    public static void openCamera(Activity activity, Uri picUri, int requestCode){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, picUri);
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 打开剪裁
     */
    public static void cropImg(Activity activity, Uri uri, Uri saveUri,int aspectX, int aspectY, int width, int height, int requestCode) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        intent.setDataAndType(uri, "image/*");
        //裁剪图片的宽高比例
        intent.putExtra("aspectX", aspectX);
        intent.putExtra("aspectY", aspectY);
        intent.putExtra("crop", "true");//可裁剪
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", width);
        intent.putExtra("outputY", height);
        intent.putExtra("scale", true);//是否缩放（去黑边）
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, saveUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());//输出图片格式
        intent.putExtra("noFaceDetection", true);//取消人脸识别
        activity.startActivityForResult(intent, requestCode);
    }

    public static Bitmap getBitmapFromUri(Context context, Uri uri){
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.
                    getBitmap(context.getContentResolver(), uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public static Bitmap getImgBitmap(Context context, Uri uri, String selection) {
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(uri, filePathColumn, selection ,null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picPath = cursor.getString(columnIndex);
        Bitmap bitmap = BitmapFactory.decodeFile(picPath);
        cursor.close();
        return bitmap;
    }

    public static Bitmap getBitmap(Context context, Uri uri){
        Bitmap bitmap = null;
        if (Build.VERSION.SDK_INT >= 19){
            if (DocumentsContract.isDocumentUri(context, uri)){
                String docId = DocumentsContract.getDocumentId(uri);
                if ("com.android.providers.media.documents".equals(uri.getAuthority())){
                    String id = docId.split(":")[1];
                    String selection = MediaStore.Images.Media._ID + "=" +id;
                    bitmap = getImgBitmap(context, uri, selection);
                } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())){
                    Uri contentUri = ContentUris.withAppendedId(
                            Uri.parse("content://downloads/public_downloads"),
                            Long.valueOf(docId));
                    bitmap = getImgBitmap(context, contentUri, null);
                }
            }
        } else {
            bitmap = getImgBitmap(context, uri, null);
        }
        return bitmap;
    }
}
