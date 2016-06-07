package kr.werac.yeah.manager;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.List;
import java.util.concurrent.TimeUnit;

import kr.werac.yeah.MyApplication;
import kr.werac.yeah.R;
import kr.werac.yeah.data.Comment;
import kr.werac.yeah.data.User;
import kr.werac.yeah.data.UserResult;
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

    private static final String URL_Detail = MY_SERVER + "/listdetail/%s";

    public Request getWeracDetail(Object tag, int mid, OnResultListener<WeracItem> listener) {

        String url = String.format(URL_Detail, mid);
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

    private static final String URL_Detail_like = MY_SERVER + "/listdetail/%s/like";

    public Request getWeracDetailLike(Object tag, int mid, OnResultListener<WeracItem> listener) {

        String url = String.format(URL_Detail_like, mid);

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

    private static final String URL_ADD_COMMENT = MY_SERVER + "/listDetail/%s/comment?content=%s";

    public Request getWeracAddComment(Object tag,
//                                  String token,
                                      int mid,
                                      String cmmt,
                                      OnResultListener<Comment> listener) {

        String url = String.format(URL_ADD_COMMENT, mid, cmmt);//token

        RequestBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("dummy", "dummy")
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

    private static final String URL_SIGN_IN = MY_SERVER + "/login";

    public Request signin(Object tag,
                          String email,
                          String password,
                          OnResultListener<User> listener) {

        RequestBody body = new FormBody.Builder()
                .add("pw", password)
                .add("email", email)
                .build();

        Request request = new Request.Builder()
                .url(URL_SIGN_IN)
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
}