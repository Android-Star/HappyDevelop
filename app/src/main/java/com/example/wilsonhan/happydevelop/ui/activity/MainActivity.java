package com.example.wilsonhan.happydevelop.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.wilsonhan.happydevelop.R;
import com.example.common.basebean.BaseResponseBean;
import com.example.common.basebean.ResponseCallBack;
import com.example.wilsonhan.happydevelop.bean.UserBean;
import com.google.gson.Gson;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ggg on 2018/8/11.
 */

public class MainActivity extends AppCompatActivity {
  private TextView tv_test;
  private List<UserBean> userBeans = new ArrayList<>();

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    tv_test = findViewById(R.id.tv_test);

    for (int i = 0; i < 5; i++) {
      UserBean userBean = new UserBean();
      userBean.setUserName("张三" + i);
      userBean.setUserPhone("3546321635654");
      userBean.setUserEmail("254656413546");
      userBeans.add(userBean);
    }

    UserBean userBean = new UserBean();
    userBean.setUserName("张三" );
    userBean.setUserPhone("3546321635654");
    userBean.setUserEmail("254656413546");

    BaseResponseBean<UserBean> baseResponseBean = new BaseResponseBean<>();
    baseResponseBean.setCode("200");
    baseResponseBean.setMsg("执行成功");
    baseResponseBean.setResult(userBean);

    //String str = new Gson().toJson(baseResponseBean);
    //tv_test.setText(str);
    String testStr = new Gson().toJson(baseResponseBean);

    //tv_test.setText(testStr);
    //BaseResponseBean<List<UserBean>> responseBean =
    //    new Gson().fromJson(testStr, new TypeToken<BaseResponseBean<List<UserBean>>>() {
    //    }.getType());

    BaseResponseBean<UserBean> something = getSomething(new ResponseCallBack<UserBean>() {
      @Override public void onResponse(BaseResponseBean<UserBean> response) {

      }
    }, testStr);
    tv_test.setText(something.toString());
  }

  public BaseResponseBean<UserBean> getSomething(ResponseCallBack<UserBean> callBack,
      String testStr) {

    BaseResponseBean<UserBean> result = new Gson().fromJson(testStr, getResponseType(callBack));
    if (result != null) {
      callBack.onResponse(result);
    }
    return result;
  }

  public static <T> Type getResponseType(ResponseCallBack<T> callBack) {
    Method[] methods = callBack.getClass().getMethods();
    for (Method method : methods) {
      if (method.getName().contains("onResponse")) {
        return method.getGenericParameterTypes()[0];
      }
    }
    return null;
  }
}
