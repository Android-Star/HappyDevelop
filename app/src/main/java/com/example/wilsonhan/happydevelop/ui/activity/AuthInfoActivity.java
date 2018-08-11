package com.example.wilsonhan.happydevelop.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.common.base.BaseActivity;
import com.example.common.commonutils.DisplayUtil;
import com.example.wilsonhan.happydevelop.R;
import com.example.wilsonhan.happydevelop.utils.BottomDialog;
import com.example.wilsonhan.happydevelop.utils.PhotoUtils;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;


public class AuthInfoActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.auth_front_img)
    ImageView mAuthFrontImg;
    @Bind(R.id.auth_back_img)
    ImageView mAuthBackImg;
    @Bind(R.id.auth_person_img)
    ImageView mAuthPersonImg;
    @Bind(R.id.name_edit_text)
    EditText mNameEdit;
    @Bind(R.id.number_edit_text)
    EditText mNumberEdit;

    private BottomDialog.Builder mBuilder;
    private final int RESULT_IMG = 1001;//相册选择图片请求码
    private final int RESULT_CAMERA = 1002;//拍照请求码
    private final int RESULT_CROP = 1003;//剪裁请求码
    private final int REQUEST_CAMERA = 2001;//拍照权限请求码
    private final int REQUEST_IMG = 2002;//打开相册权限请求码
    private Uri mCameraUri;//拍照的照片存储路径
    private Uri mCropUri;//剪裁的图片保存路径
    private final String FRONT = "front";//身份证正面
    private final String BACK = "back";//身份证背面
    private final String PERSON = "person";//手持身份证
    private String mImgType;
    private List<SoftReference<Bitmap>> mBitmapList = new ArrayList<>();//身份证照片

    @Override
    public int getLayoutId() {
        return R.layout.activity_auth_info;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void setListener() {
        mAuthFrontImg.setOnClickListener(this);
        mAuthBackImg.setOnClickListener(this);
        mAuthPersonImg.setOnClickListener(this);

        mNameEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mNumberEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.auth_front_img:
                mImgType = FRONT;
                showSelectDialog();
                break;
            case R.id.auth_back_img:
                mImgType = BACK;
                showSelectDialog();
                break;
            case R.id.auth_person_img:
                mImgType = PERSON;
                showSelectDialog();
                break;
            default:
                break;
        }
    }

    private void showSelectDialog() {
        if (mBuilder == null) {
            mBuilder = new BottomDialog.Builder(this, new BottomDialog.Builder.ClickButtonListener() {
                @Override
                public void clickImg() {
                    mBuilder.dismissDialog();
                    openPic();
                }

                @Override
                public void clickCamera() {
                    mBuilder.dismissDialog();
                    openCamera();
                }
            });
        }
        mBuilder.create().showDialog();
    }

    private void openPic() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_IMG);
        } else {
            PhotoUtils.selectPic(this, RESULT_IMG);
        }
    }

    private void openCamera() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, REQUEST_IMG);
        } else {
            mCameraUri = PhotoUtils.createCameraUri(this);
            PhotoUtils.openCamera(this, mCameraUri, RESULT_CAMERA);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_IMG:
                if (grantResults.length > 0 && PhotoUtils.permissionsGranted(grantResults)) {
                    PhotoUtils.selectPic(this, RESULT_IMG);
                } else {
                    //权限拒绝
                }
                break;
            case REQUEST_CAMERA:
                if (grantResults.length > 0 && PhotoUtils.permissionsGranted(grantResults)) {
                    PhotoUtils.openCamera(this, mCameraUri, RESULT_CAMERA);
                } else {
                    //权限拒绝
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case RESULT_IMG:
                    if (data != null) {
                        Uri uri = data.getData();
                        mCropUri = PhotoUtils.createCropUri(this);
                        PhotoUtils.cropImg(this, uri, mCropUri, DisplayUtil.dp2px(181),
                                DisplayUtil.dp2px(125), DisplayUtil.dp2px(181),
                                DisplayUtil.dp2px(125), RESULT_CROP);
                    }
                    break;
                case RESULT_CAMERA:
                    mCropUri = PhotoUtils.createCropUri(this);
                    PhotoUtils.cropImg(this, mCameraUri, mCropUri, DisplayUtil.dp2px(181),
                            DisplayUtil.dp2px(125), DisplayUtil.dp2px(181),
                            DisplayUtil.dp2px(125), RESULT_CROP);
                    break;
                case RESULT_CROP:
                    Bitmap bitmap = PhotoUtils.getBitmapFromUri(this, mCropUri);
                    if (bitmap != null) {
                        setImg(bitmap);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private void setImg(Bitmap bitmap) {
        if (FRONT.equals(mImgType)) {
            if (mAuthFrontImg != null) {
                mAuthFrontImg.setImageBitmap(bitmap);
            }
        } else if (BACK.equals(mImgType)) {
            if (mAuthBackImg != null) {
                mAuthBackImg.setImageBitmap(bitmap);
            }
        } else if (PERSON.equals(mImgType)) {
            if (mAuthPersonImg != null) {
                mAuthPersonImg.setImageBitmap(bitmap);
            }
        }
    }


}
