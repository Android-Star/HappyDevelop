package com.example.wilsonhan.happydevelop.net;

import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by lw on 2018/1/23.
 */

public interface ApiService {
//    /**
//     * 登录
//     *
//     * @return Deferred<User>
//     */
//    @POST("doctor/doctorLogin")
//    Observable<RetrofitResBean<LoginResultBean>> doctorLogin(
//            @Query("phone") String phone,
//            @Query("passw") String pwd,
//            @Query("code") String code
//    );
//
//    /**
//     * 注册
//     *
//     * @param username   username
//     * @param password   password
//     * @param repassword repassword
//     * @return Deferred<User>
//     */
//    @POST("/user/register")
//    @FormUrlEncoded
//    Observable<DataResponse<User>> register(@Field("username") String username, @Field("password") String password, @Field("repassword") String repassword);

    /**
     * 调用：
     * RetrofitManager.createModel(ApiService.class)
     .getDoctorFastPhoneDetailById(id)
     .compose(mView.<RequestResBean<String>>bindToLife())
     .compose(RxSchedulers.<RequestResBean<String>>applySchedulers())
     .compose(new ErrorTransformer<String>())
     .subscribe(new GiraffeObserver<String>() {
    @Override public void onNext(String s) {

    }

    @Override protected void hideDialog() {

    }

    @Override protected void showDialog() {

    }

    @Override public void onError(ExceptionHandler.ResponseThrowable e) {

    }
    });
     */
}
