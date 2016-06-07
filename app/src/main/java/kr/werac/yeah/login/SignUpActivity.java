package kr.werac.yeah.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import kr.werac.yeah.R;
import kr.werac.yeah.data.User;
import kr.werac.yeah.manager.NetworkManager;
import kr.werac.yeah.manager.PropertyManager;
import okhttp3.Request;

public class SignUpActivity extends AppCompatActivity {

    EditText et_signup_name;
    EditText et_signup_email;
    EditText et_signup_password;
    EditText et_signup_phone;
    String email, password, name, phone;
    Button btn_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        et_signup_name = (EditText)findViewById(R.id.et_signup_name);
        et_signup_email = (EditText)findViewById(R.id.et_signup_email);
        et_signup_password = (EditText)findViewById(R.id.et_signup_password);
        et_signup_phone = (EditText)findViewById(R.id.et_signup_phone);
        btn_signup = (Button)findViewById(R.id.btn_signup);

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = et_signup_name.getText().toString();
                email = et_signup_email.getText().toString();
                password = et_signup_password.getText().toString();
                phone = et_signup_phone.getText().toString();

                if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()
                        || TextUtils.isEmpty(password) || password.length() < 3) {
                    Toast.makeText(SignUpActivity.this, "invalid value", Toast.LENGTH_SHORT).show();
                    return;
                }
                signup();
            }
        });
    }

    public void signup(){
        NetworkManager.getInstance().signup(this, name, email, password, phone,
                new NetworkManager.OnResultListener<User>(){
                    @Override
                    public void onSuccess(Request request, User result) {
                        PropertyManager.getInstance().setLogin(true);
                        PropertyManager.getInstance().setUser(result);
                        PropertyManager.getInstance().setEmail(email);
                        PropertyManager.getInstance().setPassword(password);

                        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFail(Request request, IOException exception) {
                        Toast.makeText(SignUpActivity.this, "error : " + exception.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
