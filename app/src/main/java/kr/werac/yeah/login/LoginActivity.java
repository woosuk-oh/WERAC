package kr.werac.yeah.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;

import kr.werac.yeah.R;
import kr.werac.yeah.data.User;
import kr.werac.yeah.data.UserResult;
import kr.werac.yeah.main.MainActivity;
import kr.werac.yeah.manager.NetworkManager;
import kr.werac.yeah.manager.PropertyManager;
import okhttp3.Request;

public class LoginActivity extends AppCompatActivity {

    EditText et_email;
    EditText et_password;
    ImageButton ibtn_login;
    ImageButton ibtn_sign_up;
    int backButtonCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_email = (EditText)findViewById(R.id.et_email);
        et_password = (EditText)findViewById(R.id.et_password);
        ibtn_login = (ImageButton)findViewById(R.id.ibtn_login);
        ibtn_sign_up = (ImageButton)findViewById(R.id.ibtn_sign_up);

        ibtn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            final String email = et_email.getText().toString();
            final String password = et_password.getText().toString();
            if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()
                    || TextUtils.isEmpty(password) || password.length() < 3){
                Toast.makeText(LoginActivity.this, "올바른 형식을 입력해주세요", Toast.LENGTH_SHORT).show();
            }else {
                NetworkManager.getInstance().login(this, email, password, PropertyManager.getInstance().getRegistrationToken(),
                    new NetworkManager.OnResultListener<UserResult>() {
                        @Override
                        public void onSuccess(Request request, UserResult result) {
                            if (result.getSuccess() == 1) {
                                PropertyManager.getInstance().setLogin(true);
                                PropertyManager.getInstance().setUser(result.getUser());
                                if (!PropertyManager.getInstance().getPush().equals("true") && !PropertyManager.getInstance().getPush().equals("false")) {
                                    PropertyManager.getInstance().setPush("true");
                                }
                                PropertyManager.getInstance().setEmail(email);
                                PropertyManager.getInstance().setPassword(password);

                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else
                                Toast.makeText(LoginActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
                        }


                        @Override
                        public void onFail(Request request, IOException exception) {
                            Toast.makeText(LoginActivity.this, "error : " + exception.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        ibtn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, AgreementActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        if(backButtonCount >= 1)
        {
//            Intent intent = new Intent(Intent.ACTION_MAIN);
//            intent.addCategory(Intent.CATEGORY_HOME);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);
            System.exit(0);
        }else
        {
            Toast.makeText(this, "종료하시려면 한 번 더 더치해주세요", Toast.LENGTH_SHORT).show();
            backButtonCount++;
        }
    }
}
