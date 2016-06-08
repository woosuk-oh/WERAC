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
import kr.werac.yeah.data.UserResult;
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

        autologin();
    }

    public void autologin(){
        String email = PropertyManager.getInstance().getEmail();
        if (!TextUtils.isEmpty(email)) {
            String password = PropertyManager.getInstance().getPassword();
            NetworkManager.getInstance().login(this, email, password, new NetworkManager.OnResultListener<UserResult>() {
                @Override
                public void onSuccess(Request request, UserResult result) {
                    if(result.getSuccess() == 1) {
                        PropertyManager.getInstance().setLogin(true);
                        PropertyManager.getInstance().setUser(result.getUser());
                        goMainActivity();
                    }else{
                        Toast.makeText(SplashActivity.this, "저장된 아이디 없음", Toast.LENGTH_SHORT).show();
                        goLoginActivity();
                    }
                }

                @Override
                public void onFail(Request request, IOException exception) {
                    Toast.makeText(SplashActivity.this, "error : " + exception.getMessage(), Toast.LENGTH_SHORT).show();
                    goLoginActivity();
                }
            });
        }else
            goLoginActivity();
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
