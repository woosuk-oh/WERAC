package kr.werac.yeah;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;

import java.io.IOException;

import kr.werac.yeah.data.User;
import kr.werac.yeah.login.LoginActivity;
import kr.werac.yeah.main.MainActivity;
import kr.werac.yeah.manager.NetworkManager;
import kr.werac.yeah.manager.PropertyManager;
import okhttp3.Request;

/**
 * Created by Tacademy on 2016-05-16.
 */
public class SplashActivity extends AppCompatActivity {

    Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        AccessToken token = AccessToken.getCurrentAccessToken();
//        if (token == null) {
//            mHandler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
//                    finish();
//                }
//            }, 2000);
//        } else {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    autologin();
                }
            }, 2000);
        }

    public void autologin(){
        String email = PropertyManager.getInstance().getEmail();
        if (!TextUtils.isEmpty(email)) {
            String password = PropertyManager.getInstance().getPassword();
            NetworkManager.getInstance().signin(this, email, password, new NetworkManager.OnResultListener<User>() {
                @Override
                public void onSuccess(Request request, User result) {
                    PropertyManager.getInstance().setLogin(true);
                    PropertyManager.getInstance().setUser(result);
                    goMainActivity();
                }

                @Override
                public void onFail(Request request, IOException exception) {
                    Toast.makeText(SplashActivity.this, "error : " + exception.getMessage(), Toast.LENGTH_SHORT).show();
                    goLoginActivity();
                }
            });
        }
    }

    private void goMainActivity() {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }

    private void goLoginActivity() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
            }
        }, 2000);
    }
}
