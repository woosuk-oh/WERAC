package kr.werac.yeah.manager;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.List;
import java.util.concurrent.TimeUnit;

import kr.werac.yeah.MyApplication;
import kr.werac.yeah.data.Alarms;
import kr.werac.yeah.data.Comment;
import kr.werac.yeah.data.CommentResult;
import kr.werac.yeah.data.Result;
import kr.werac.yeah.data.User;
import kr.werac.yeah.data.UserResult;
import kr.werac.yeah.data.Users;
import kr.werac.yeah.data.UsersResult;
import kr.werac.yeah.data.WeracItem;
import kr.werac.yeah.data.WeracItemResult;
import kr.werac.yeah.data.WeracItems;
import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.JavaNetCookieJar;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by dongja94 on 2016-05-09.
 */
public class NetworkManager {
    private static NetworkManager instance;

    public static NetworkManager getInstance() {
        if (instance == null) {
            instance = new NetworkManager();
        }
        return instance;
    }

    private static final int DEFAULT_CACHE_SIZE = 50 * 1024 * 1024;
    private static final String DEFAULT_CACHE_DIR = "weracapp";
    OkHttpClient mClient;

    private NetworkManager() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        Context context = MyApplication.getContext();
        CookieManager cookieManager = new CookieManager(new PersistentCookieStore(context), CookiePolicy.ACCEPT_ALL);
        builder.cookieJar(new JavaNetCookieJar(cookieManager));

        File dir = new File(context.getExternalCacheDir(), DEFAULT_CACHE_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        builder.cache(new Cache(dir, DEFAULT_CACHE_SIZE));

        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(30, TimeUnit.SECONDS);
        builder.writeTimeout(30, TimeUnit.SECONDS);

        mClient = builder.build();
    }

    public interface OnResultListener<T> {
        void onSuccess(Request request, T result);

        void onFail(Request request, IOException exception);
    }

    private static final int MESSAGE_SUCCESS = 1;
    private static final int MESSAGE_FAIL = 2;

    class NetworkHandler extends Handler {
        public NetworkHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            NetworkResult result = (NetworkResult) msg.obj;
            switch (msg.what) {
                case MESSAGE_SUCCESS:
                    result.listener.onSuccess(result.request, result.result);
                    break;
                case MESSAGE_FAIL:
                    result.listener.onFail(result.request, result.excpetion);
                    break;
            }
        }
    }

    NetworkHandler mHandler = new NetworkHandler(Looper.getMainLooper());

    static class NetworkResult<T> {
        Request request;
        OnResultListener<T> listener;
        IOException excpetion;
        T result;
    }

    Gson gson = new Gson();

    private static final String MY_SERVER = "http://52.79.178.195:3000";
    private static final String URL_LIST = MY_SERVER + "/list/%s";

    public Request getWeracList(Object tag, int tab, OnResultListener<List<WeracItem>> listener) {

        String url = String.format(URL_LIST, tab);
        Request request = new Request.Builder()
                .url(url)
                .build();

        final NetworkResult<List<WeracItem>> result = new NetworkResult<>();
        result.request = request;
        result.listener = listener;
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                result.excpetion = e;
                mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    WeracItems data = gson.fromJson(response.body().charStream(), WeracItems.class);
                    result.result = data.weracs;
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_SUCCESS, result));
                } else {
                    result.excpetion = new IOException(response.message());
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
                }
            }
        });
        return request;
    }

    private static final String URL_DETAIL = MY_SERVER + "/listdetail/%s";

    public Request getWeracDetail(Object tag, int mid, OnResultListener<WeracItem> listener) {

        String url = String.format(URL_DETAIL, mid);
        Request request = new Request.Builder()
                .url(url)
                .build();

        final NetworkResult<WeracItem> result = new NetworkResult<>();
        result.request = request;
        result.listener = listener;
        mClient.newCall(request).enqueue(new Callback() {
            @Override
                    public void onFailure(Call call, IOException e) {
                        result.excpetion = e;
                        mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {
                            String text = response.body().string();
                            WeracItemResult data = gson.fromJson(text, WeracItemResult.class);
                            result.result = data.werac;
                            mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_SUCCESS, result));
                        } else {
                    result.excpetion = new IOException(response.message());
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
                }
            }
        });
        return request;
    }

    private static final String URL_DETAIL_LIKE = MY_SERVER + "/listdetail/%s/like";

    public Request getWeracDetailLike(Object tag, int mid, OnResultListener<WeracItem> listener) {

        String url = String.format(URL_DETAIL_LIKE, mid);

        RequestBody body = new FormBody.Builder()
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        final NetworkResult<WeracItem> result = new NetworkResult<>();
        result.request = request;
        result.listener = listener;
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                result.excpetion = e;
                mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String text = response.body().string();
                    WeracItemResult data = gson.fromJson(text, WeracItemResult.class);
                    result.result = data.werac;
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_SUCCESS, result));
                } else {
                    result.excpetion = new IOException(response.message());
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
                }
            }
        });
        return request;
    }

    private static final String URL_APPLY_MC = MY_SERVER + "/apply/%s";

    public Request applyMC(Object tag, int mid, OnResultListener<Result> listener) {

        String url = String.format(URL_APPLY_MC, mid);

        RequestBody body = new FormBody.Builder()
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        final NetworkResult<Result> result = new NetworkResult<>();
        result.request = request;
        result.listener = listener;
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                result.excpetion = e;
                mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String text = response.body().string();
                    Result data = gson.fromJson(text, Result.class);
                    result.result = data;
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_SUCCESS, result));
                } else {
                    result.excpetion = new IOException(response.message());
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
                }
            }
        });
        return request;
    }

    private static final String URL_APPLY_MC_RESULT = MY_SERVER + "/apply_result/%s";

    public Request applyMCResult(Object tag, int mid, int uid, int resultMC, OnResultListener<WeracItem> listener) {

        String url = String.format(URL_APPLY_MC_RESULT, mid);

        RequestBody body = new FormBody.Builder()
                .add("uid", uid+"")
                .add("result", resultMC+"")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        final NetworkResult<WeracItem> result = new NetworkResult<>();
        result.request = request;
        result.listener = listener;
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                result.excpetion = e;
                mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String text = response.body().string();
//                    WeracItemResult data = gson.fromJson(text, WeracItemResult.class);
//                    result.result = data.werac;
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_SUCCESS, result));
                } else {
                    result.excpetion = new IOException(response.message());
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
                }
            }
        });
        return request;
    }

    private static final String URL_MC_CREATER = MY_SERVER + "/%s/%s";

    public Request getWeracMC_Create(Object tag, int who, int who_id, OnResultListener<User> listener) {

        String url = "";
        if(who == 1){
            url = String.format(URL_MC_CREATER, "mc_profile", who_id);
        }else if(who == 2) {
            url = String.format(URL_MC_CREATER, "creator_profile", who_id);
        }
        Request request = new Request.Builder()
                .url(url)
                .build();

        final NetworkResult<User> result = new NetworkResult<>();
        result.request = request;
        result.listener = listener;
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                result.excpetion = e;
                mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String text = response.body().string();
                    UserResult data = gson.fromJson(text, UserResult.class);
                    result.result = data.user;
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_SUCCESS, result));
                } else {
                    result.excpetion = new IOException(response.message());
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
                }
            }
        });
        return request;
    }

    private static final String URL_MY_PAGE = MY_SERVER + "/my_profile";

    public Request getWeracMy(Object tag, OnResultListener<User> listener) {

//        String url = String.format(URL_MY_PAGE, my_id);

        Request request = new Request.Builder()
                .url(URL_MY_PAGE)
                .build();

        final NetworkResult<User> result = new NetworkResult<>();
        result.request = request;
        result.listener = listener;
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                result.excpetion = e;
                mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String text = response.body().string();
                    UserResult data = gson.fromJson(text, UserResult.class);
                    result.result = data.user;
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_SUCCESS, result));
                } else {
                    result.excpetion = new IOException(response.message());
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
                }
            }
        });
        return request;
    }

    private static final String URL_MY_PAGE_MODIFY = MY_SERVER + "/my_profile";

    public Request getWeracMyModify(Object tag, User user, File mUploadFile, OnResultListener<User> listener) {

//        String url = String.format(URL_MY_PAGE_MODIFY, my_id);

        MultipartBody.Builder myBuilder = new MultipartBody.Builder();
        myBuilder.addFormDataPart("name", user.getName())
                .addFormDataPart("comment", user.getComment())
                .addFormDataPart("phone", user.getPhone());

        if(mUploadFile != null)
            myBuilder.addFormDataPart("profile_image", mUploadFile.getName(), RequestBody.create(MediaType.parse("image/jpeg"), mUploadFile));
        else if(user.getProfile_image() != null)
            myBuilder.addFormDataPart("profile_image", user.getProfile_image());

        RequestBody body = myBuilder
                .setType(MultipartBody.FORM)
                .build();

        Request request = new Request.Builder()
                .url(URL_MY_PAGE_MODIFY)
                .post(body)
                .build();

        final NetworkResult<User> result = new NetworkResult<>();
        result.request = request;
        result.listener = listener;
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                result.excpetion = e;
                mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String text = response.body().string();
//                    UserResult data = gson.fromJson(text, UserResult.class);
//                    result.result = data.user;
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_SUCCESS, result));
                } else {
                    result.excpetion = new IOException(response.message());
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
                }
            }
        });
        return request;
    }

    private static final String URL_CREATE_WERAC = MY_SERVER + "/create";

    public Request getWeracCreate(Object tag,
//                                  String token,
                                  File image,
                                  WeracItem werac,
                                   OnResultListener<WeracItem> listener) {

//        String url = String.format(URL_CREATE_WERAC, 2);//token
        int sch_num = 0;
        sch_num = werac.getSchedule().size();

        MultipartBody.Builder myBuilder = new MultipartBody.Builder();
        myBuilder.addFormDataPart("title", werac.getTitle())
                .addFormDataPart("title_sub", werac.getTitle_sub())
                .addFormDataPart("location_detail", werac.getLocation_detail())
                .addFormDataPart("location_area", werac.getLocation_area())
                .addFormDataPart("date", werac.getDate())
                .addFormDataPart("start_time", werac.getStart_time())
                .addFormDataPart("end_time", werac.getEnd_time())
                .addFormDataPart("fee", werac.getFee()+"")
                .addFormDataPart("limit_num", werac.getLimit_num()+"");

        if(werac.isHas_mc() == true)
            myBuilder.addFormDataPart("has_mc", "true");
        else
            myBuilder.addFormDataPart("has_mc", "false");

        if(image != null)
            myBuilder.addFormDataPart("image", image.getName(), RequestBody.create(MediaType.parse("image/jpeg"), image));

        for(int i = 0; i < sch_num; i++){
            myBuilder.addFormDataPart("schedule[]", werac.getSchedule().get(i));
        }

        RequestBody body = myBuilder
                .setType(MultipartBody.FORM)
                .build();

        Request request = new Request.Builder()
                .url(URL_CREATE_WERAC)
                .post(body)
                .build();

        final NetworkResult<WeracItem> result = new NetworkResult<>();
        result.request = request;
        result.listener = listener;
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                result.excpetion = e;
                mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String text = response.body().string();
                    WeracItem data = gson.fromJson(text, WeracItem.class);
                    result.result = data;
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_SUCCESS, result));
                } else {
                    result.excpetion = new IOException(response.message());
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
                }
            }
        });
        return request;
    }

    private static final String URL_MODIFY_WERAC = MY_SERVER + "/update/%s";

    public Request getWeracModify(Object tag,
//                                  String token,
                                  int Mid,
                                  int ChangeImageOrNot,
                                  File imageFile,
                                  WeracItem werac,
                                  OnResultListener<WeracItem> listener) {

        String url = String.format(URL_MODIFY_WERAC, Mid);//token
        int sch_num = 0;
        sch_num = werac.getSchedule().size();

        MultipartBody.Builder myBuilder = new MultipartBody.Builder();
        myBuilder.addFormDataPart("image", werac.getImage())
                .addFormDataPart("title", werac.getTitle())
                .addFormDataPart("title_sub", werac.getTitle_sub())
                .addFormDataPart("location_detail", werac.getLocation_detail())
                .addFormDataPart("location_area", werac.getLocation_area())
                .addFormDataPart("date", werac.getDate())
                .addFormDataPart("start_time", werac.getStart_time())
                .addFormDataPart("end_time", werac.getEnd_time())
                .addFormDataPart("fee", werac.getFee()+"")
                .addFormDataPart("limit_num", werac.getLimit_num()+"");

        if(werac.isHas_mc() == true)
            myBuilder.addFormDataPart("has_mc", "true");
        else
            myBuilder.addFormDataPart("has_mc", "false");

        for(int i = 0; i < sch_num; i++){
            myBuilder.addFormDataPart("schedule[]", werac.getSchedule().get(i));
        }

        if(imageFile != null)
            myBuilder.addFormDataPart("image", imageFile.getName(), RequestBody.create(MediaType.parse("image/jpeg"), imageFile));

        RequestBody body = myBuilder
            .setType(MultipartBody.FORM)
            .addFormDataPart("dummy", "dummy")
            .build();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        final NetworkResult<WeracItem> result = new NetworkResult<>();
        result.request = request;
        result.listener = listener;
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                result.excpetion = e;
                mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String text = response.body().string();
                    WeracItem data = gson.fromJson(text, WeracItem.class);
                    result.result = data;
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_SUCCESS, result));
                } else {
                    result.excpetion = new IOException(response.message());
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
                }
            }
        });
        return request;
    }

    private static final String URL_CHANGE_STATUS_WERAC = MY_SERVER + "/change/%s";

    public Request getWeracChangeStatus(Object tag,
//                                  String token,
                                  int Mid,
                                  OnResultListener<WeracItem> listener) {

        String url = String.format(URL_CHANGE_STATUS_WERAC, Mid);//token

        RequestBody body = new FormBody.Builder()
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        final NetworkResult<WeracItem> result = new NetworkResult<>();
        result.request = request;
        result.listener = listener;
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                result.excpetion = e;
                mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String text = response.body().string();
//                    WeracItem data = gson.fromJson(text, WeracItem.class);
//                    result.result = data;
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_SUCCESS, result));
                } else {
                    result.excpetion = new IOException(response.message());
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
                }
            }
        });
        return request;
    }

    private static final String URL_JOIN_WERAC = MY_SERVER + "/participate/%s";

    public Request getWeracJoin(Object tag,
//                                  String token,
                                        int Mid,
                                        OnResultListener<Result> listener) {

        String url = String.format(URL_JOIN_WERAC, Mid);//token

        RequestBody body = new FormBody.Builder()
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        final NetworkResult<Result> result = new NetworkResult<>();
        result.request = request;
        result.listener = listener;
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                result.excpetion = e;
                mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String text = response.body().string();
                    Result data = gson.fromJson(text, Result.class);
                    result.result = data;
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_SUCCESS, result));
                } else {
                    result.excpetion = new IOException(response.message());
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
                }
            }
        });
        return request;
    }

    private static final String URL_ADD_COMMENT = MY_SERVER + "/listDetail/%s/comment?content=%s";

    public Request getWeracAddComment(Object tag,
//                                  String token,
                                      int mid,
                                      Comment cmmt,
                                      OnResultListener<CommentResult> listener) {

        String url = String.format(URL_ADD_COMMENT, mid, cmmt.getContent());//token

        RequestBody body = new FormBody.Builder()
                .add("cid", cmmt.getCmmtid()+"")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        final NetworkResult<CommentResult> result = new NetworkResult<>();
        result.request = request;
        result.listener = listener;
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                result.excpetion = e;
                mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String text = response.body().string();
                    CommentResult data = gson.fromJson(text, CommentResult.class);
                    result.result = data;
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_SUCCESS, result));
                } else {
                    result.excpetion = new IOException(response.message());
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
                }
            }
        });
        return request;
    }

    private static final String URL_MODIFY_COMMENT = MY_SERVER + "/listDetail/%s/comment-edit?content=%s";

    public Request getWeracModifyComment(Object tag,
//                                  String token,
                                      int mid,
                                      Comment cmmt,
                                      OnResultListener<Comment> listener) {

        String url = String.format(URL_MODIFY_COMMENT, mid, cmmt.getContent());//token

        RequestBody body = new FormBody.Builder()
                .add("cid", cmmt.getCmmtid()+"")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        final NetworkResult<Comment> result = new NetworkResult<>();
        result.request = request;
        result.listener = listener;
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                result.excpetion = e;
                mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String text = response.body().string();
                    Comment data = gson.fromJson(text, Comment.class);
                    result.result = data;
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_SUCCESS, result));
                } else {
                    result.excpetion = new IOException(response.message());
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
                }
            }
        });
        return request;
    }

    private static final String URL_REMOVE_COMMENT = MY_SERVER + "/listDetail/%s/comment-delete";

    public Request getWeracRemoveComment(Object tag,
//                                  String token,
                                         int mid,
                                         Comment cmmt,
                                         OnResultListener<Comment> listener) {

        String url = String.format(URL_REMOVE_COMMENT, mid);

        RequestBody body = new FormBody.Builder()
                .add("cid", cmmt.getCmmtid()+"")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        final NetworkResult<Comment> result = new NetworkResult<>();
        result.request = request;
        result.listener = listener;
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                result.excpetion = e;
                mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String text = response.body().string();
                    Comment data = gson.fromJson(text, Comment.class);
                    result.result = data;
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_SUCCESS, result));
                } else {
                    result.excpetion = new IOException(response.message());
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
                }
            }
        });
        return request;
    }

    private static final String URL_LIKE_COMMENT = MY_SERVER + "/listDetail/%s/clike";

    public Request getWeracLikeComment(Object tag,
//                                  String token,
                                      int mid,
                                      String cmmtId,
                                      OnResultListener<Comment> listener) {

        String url = String.format(URL_LIKE_COMMENT, mid);//token

        RequestBody body = new FormBody.Builder()
                .add("cid", cmmtId)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        final NetworkResult<Comment> result = new NetworkResult<>();
        result.request = request;
        result.listener = listener;
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                result.excpetion = e;
                mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String text = response.body().string();
//                    Comment data = gson.fromJson(text, Comment.class);
//                    result.result = data;
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_SUCCESS, result));
                } else {
                    result.excpetion = new IOException(response.message());
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
                }
            }
        });
        return request;
    }

    private static final String URL_LOGIN = MY_SERVER + "/login";

    public Request login(Object tag,
                         String email,
                         String password,
                         String registrationToken,
                         OnResultListener<UserResult> listener) {

        RequestBody body = new FormBody.Builder()
                .add("pw", password)
                .add("email", email)
                .add("gcmtoken", registrationToken)
                .build();

        Request request = new Request.Builder()
                .url(URL_LOGIN)
                .post(body)
                .build();

        final NetworkResult<UserResult> result = new NetworkResult<>();
        result.request = request;
        result.listener = listener;
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                result.excpetion = e;
                mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String text = response.body().string();
                    UserResult data = gson.fromJson(text, UserResult.class);
                    result.result = data;
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_SUCCESS, result));
                } else {
                    result.excpetion = new IOException(response.message());
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
                }
            }
        });
        return request;
    }

    private static final String URL_LOGOUT = MY_SERVER + "/logout";

    public Request logout(Object tag, OnResultListener<User> listener) {

        RequestBody body = new FormBody.Builder()
                .build();

        Request request = new Request.Builder()
                .url(URL_LOGOUT)
                .post(body)
                .build();

        final NetworkResult<User> result = new NetworkResult<>();
        result.request = request;
        result.listener = listener;
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                result.excpetion = e;
                mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String text = response.body().string();
//                    UserResult data = gson.fromJson(text, UserResult.class);
//                    result.result = data.user;
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_SUCCESS, result));
                } else {
                    result.excpetion = new IOException(response.message());
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
                }
            }
        });
        return request;
    }

    private static final String URL_SIGN_UP = MY_SERVER + "/join";

    public Request signup(Object tag,
                          String name,
                          String email,
                          String password,
                          String phone,
                          OnResultListener<User> listener) {

        RequestBody body = new FormBody.Builder()
                .add("pw", password)
                .add("email", email)
                .add("name", name)
                .add("phone", phone)
                .build();

        Request request = new Request.Builder()
                .url(URL_SIGN_UP)
                .post(body)
                .build();

        final NetworkResult<User> result = new NetworkResult<>();
        result.request = request;
        result.listener = listener;
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                result.excpetion = e;
                mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String text = response.body().string();
                    User data = gson.fromJson(text, User.class);
                    result.result = data;
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_SUCCESS, result));
                } else {
                    result.excpetion = new IOException(response.message());
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
                }
            }
        });
        return request;
    }

    private static final String URL_ALARM = MY_SERVER + "/my_profile/alarm";

    public Request getAlarm(Object tag, OnResultListener<Alarms> listener) {

        Request request = new Request.Builder()
                .url(URL_ALARM)
                .build();

        final NetworkResult<Alarms> result = new NetworkResult<>();
        result.request = request;
        result.listener = listener;
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                result.excpetion = e;
                mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String text = response.body().string();
                    Alarms data = gson.fromJson(text, Alarms.class);
                    result.result = data;
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_SUCCESS, result));
                } else {
                    result.excpetion = new IOException(response.message());
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
                }
            }
        });
        return request;
    }

    private static final String URL_GET_GUESTS = MY_SERVER + "/listdetail/%d/guests";

    public Request getGuests(Object tag, int Mid, OnResultListener<Users> listener) {

        String url = String.format(URL_GET_GUESTS, Mid);

        Request request = new Request.Builder()
                .url(url)
                .build();

        final NetworkResult<Users> result = new NetworkResult<>();
        result.request = request;
        result.listener = listener;
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                result.excpetion = e;
                mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String text = response.body().string();
                    UsersResult data = gson.fromJson(text, UsersResult.class);
                    result.result = data.getMyusers();
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_SUCCESS, result));
                } else {
                    result.excpetion = new IOException(response.message());
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
                }
            }
        });
        return request;
    }
}