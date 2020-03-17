package com.example.wilsonhan.happydevelop.net;

import com.example.wilsonhan.happydevelop.constants.NetConstants;
import com.example.wilsonhan.happydevelop.net.request.CheckInfoRequest;
import com.example.wilsonhan.happydevelop.net.request.ClearPhoneFlagRequest;
import com.example.wilsonhan.happydevelop.net.request.LoginInfoRequest;
import com.example.wilsonhan.happydevelop.net.request.LoginRequest;
import com.example.wilsonhan.happydevelop.net.request.MarkBlackRequest;
import com.example.wilsonhan.happydevelop.net.request.MarkInvalidRequest;
import com.example.wilsonhan.happydevelop.net.request.MarkVirtualRequest;
import com.example.wilsonhan.happydevelop.net.request.RecordSearchRequest;
import com.example.wilsonhan.happydevelop.net.request.UserMsgRequest;
import com.example.wilsonhan.happydevelop.net.response.CheckInfoResponse;
import com.example.wilsonhan.happydevelop.net.response.ConfigResponse;
import com.example.wilsonhan.happydevelop.net.response.GetApkVersionResponse;
import com.example.wilsonhan.happydevelop.net.response.ImageResponse;
import com.example.wilsonhan.happydevelop.net.response.KnowledgeResponse;
import com.example.wilsonhan.happydevelop.net.response.NumberInfoResponse;
import com.example.wilsonhan.happydevelop.net.response.RecordDetailResponse;
import com.example.wilsonhan.happydevelop.net.response.RecordListResponse;
import com.example.wilsonhan.happydevelop.net.response.TaskListResponse;
import com.example.wilsonhan.happydevelop.net.response.TaskSecondBean;
import io.reactivex.Observable;
import java.util.List;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * 请求方法类
 */
public interface ApiService {
  /**
   * get请求方式
   *
   * @Query 问号后面的参数，形成单个查询参数, 将接口url中追加类似于"page=1"的字符串,形成提交给服务器端的参数,
   * 主要用于Get请求数据，用于拼接在拼接在url路径后面的查询参数，一个@Query相当于拼接一个参数（也可用于其他请求方式）
   * @Path 网址中的参数, 主要用于占位符形式的url（也可用于其他请求方式）
   * @QueryMap 相当于多个@Query，主要用于拼接参数比较多时，替代@Query使用
   *
   * post请求方式  结合@Field或@FieldMap做表单提交
   * @FormUrlEncoded post请求必须要申明该注解
   * @Part 一个实体
   * @Field 用于Post请求, 提交单个数据, 然后要加@FormUrlEncoded
   * @Body 会将请求参数放到请求体中，所以适用于POST请求@Body标签不能和@FormUrlEncoded或@Multipart标签同时使用，会报错
   *
   * put请求方式
   * @Body 相当于多个@Field,以对象的方式提交
   * @Streaming:用于下载大文件
   * @Header,@Headers、加请求头
   */
  //获取置业顾问列表和配置信息（首页登录列表和用户信息保存编辑时候需要调用）
  @GET(NetConstants.GET_DEVICE_CONFIG_INFO)
  Observable<BaseResponse<ConfigResponse>> getDeviceConfig(@Path("deviceCode") String deviceCode);

  //登录
  @POST(NetConstants.USER_LOGIN) Observable<BaseResponse<String>> userLogin(
      @Body LoginRequest request);

  //登录轨迹上报
  @POST(NetConstants.USER_LOGIN_INFO) Observable<BaseResponse<String>> userLoginInfo(
      @Body LoginInfoRequest request);

  //保存用户名片信息
  @POST(NetConstants.SAVE_USER_INFO) Observable<BaseResponse<String>> saveUserInfo(
      @Body UserMsgRequest request);

  //根据号码获取用户信息
  @GET(NetConstants.GET_USER_INFO) Observable<BaseResponse<UserMsgRequest>> getUserInfo(
      @Path("deviceCode") String deviceCode, @Path("phone") String phone);

  //获取号码信息
  @GET(NetConstants.GET_NUMBER_INFO) Observable<BaseResponse<NumberInfoResponse>> getNumberInfo(
      @Path("deviceCode") String deviceCode, @Path("phone") String phone);

  //标记黑名单
  @POST(NetConstants.MARK_BLACK) Observable<BaseResponse<String>> markBlack(
      @Body MarkBlackRequest request);

  //标记虚拟号码
  @POST(NetConstants.MARK_VIRTUAL) Observable<BaseResponse<String>> markVirtual(
      @Body MarkVirtualRequest request);

  //清除标记
  @POST(NetConstants.CLEAR_MARK) Observable<BaseResponse<Boolean>> removePhoneNumberFlag(
      @Body ClearPhoneFlagRequest request);

  //标记无效
  @POST(NetConstants.MARK_INVALID) Observable<BaseResponse<String>> markInvalid(
      @Body MarkInvalidRequest request);

  //判断给定号码是否在黑名单中
  @GET(NetConstants.CHECK_NUM_BLACK) Observable<BaseResponse<Boolean>> checkNumBlack(
      @Path("deviceCode") String deviceCode, @Path("phone") String phone);

  //设备获取自检信息
  @GET(NetConstants.GET_CHECK_INFO) Observable<BaseResponse<List<CheckInfoResponse>>> getCheckInfo(
      @Path("deviceCode") String deviceCode);

  //体检信息上报
  @POST(NetConstants.SAVE_CHECK_INFO) Observable<BaseResponse<String>> saveCheckInfo(
      @Body CheckInfoRequest request);

  //查看详细信息
  @GET(NetConstants.GET_CALL_RECORD_DETAIL)
  Observable<BaseResponse<RecordDetailResponse>> getCallRecordDetail(
      @Path("deviceCode") String deviceCode, @Path("phone") String phone);

  //查询通话记录
  @POST(NetConstants.GET_CALL_RECORD)
  Observable<BaseResponse<List<RecordListResponse>>> getCallRecord(
      @Body RecordSearchRequest request);

  //保存通话记录
  //通话类型 (0 来电已接 1去电已接 2 来电未接 3去电未接 4来电拒接)
  @POST(NetConstants.SAVE_CALL_RECORD) Observable<BaseResponse<String>> saveCallRecord(
      @Body RequestBody body);

  //获取任务列表
  //orderBy 0：升序 1：降序
  @GET(NetConstants.GET_TASK_LIST) Observable<BaseResponse<TaskListResponse>> getTaskList(
      @Query("pageNum") int pageNum, @Query("pageSize") int pageSize,
      @Query("salerId") String salerId, @Query("orderBy") int orderBy);

  //更新任务状态
  @FormUrlEncoded @POST(NetConstants.UPDATE_TASK_STATE)
  Observable<BaseResponse<TaskSecondBean>> updateTaskState(@Field("taskId") long taskId,
      @Field("customerPhone") String customerPhone, @Field("customerStatus") int customerStatus);

  //获取单个任务号码信息
  @GET(NetConstants.GET_FIRST_TASK_INFO) Observable<BaseResponse<TaskSecondBean>> getTaskFirstInfo(
      @Query("taskId") long taskId);

  @Streaming @GET Observable<ResponseBody> downLoadFile(@Url String fileUrl,
      @Header("Range") String range);

  //检查apk更新
  @GET(NetConstants.GET_NEW_APK_VERSION)
  Observable<BaseResponse<GetApkVersionResponse>> checkApkVersion();

  //检查服务是否到期
  @GET(NetConstants.CHECK_SERVICE_VALID) Observable<BaseResponse<Boolean>> checkServiceValid(
      @Path("deviceCode") String deviceCode);

  //检查设备是否已经绑定到指定项目
  @GET(NetConstants.CHECK_DEVICE_BIND) Observable<BaseResponse<Boolean>> checkDeviceBind(
      @Path("imei") String imei);

  //获取知识库信息
  @GET(NetConstants.GET_KNOWLEDGE_MSG)
  Observable<BaseResponse<List<KnowledgeResponse>>> getKnowledgeMsg();

  //获取知识库信息
  @GET(NetConstants.GET_UPDATE_IMAGE) Observable<BaseResponse<ImageResponse>> getUpdateImage();
}
