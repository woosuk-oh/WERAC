package kr.werac.yeah.login;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.MenuItem;
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
    String where, fb_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_signup);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.back);


        et_signup_name = (EditText)findViewById(R.id.et_signup_name);
        et_signup_email = (EditText)findViewById(R.id.et_signup_email);
        et_signup_password = (EditText)findViewById(R.id.et_signup_password);
        et_signup_phone = (EditText)findViewById(R.id.et_signup_phone);
        btn_signup = (Button)findViewById(R.id.btn_signup);
        where = getIntent().getStringExtra("Where");
        fb_id = getIntent().getStringExtra("fb_id");
        if(where.equals("login")){
            et_signup_email.setVisibility(View.INVISIBLE);
            et_signup_password.setVisibility(View.INVISIBLE);
        }


        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = et_signup_name.getText().toString();
                email = et_signup_email.getText().toString();
                password = et_signup_password.getText().toString();
                phone = et_signup_phone.getText().toString();

                if(!where.equals("login")){
                    if (TextUtils.isEmpty(email)) {
                        Toast.makeText(SignUpActivity.this, "이메일을 입력해주세요", Toast.LENGTH_SHORT).show();
                        return;
                    }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        Toast.makeText(SignUpActivity.this, "이메일을 형식이 아닙니다", Toast.LENGTH_SHORT).show();
                        return;
                    }else if (TextUtils.isEmpty(password)) {
                        Toast.makeText(SignUpActivity.this, "패스워드를 입력해주세요", Toast.LENGTH_SHORT).show();
                        return;
                    }else if (password.length() < 3) {
                        Toast.makeText(SignUpActivity.this, "패스워드를 4자 이상 입력해주세요", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(SignUpActivity.this, "이름을 입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }else if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(SignUpActivity.this, "핸드폰 번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                signup();
            }
        });
    }

    public void signup(){
        NetworkManager.getInstance().signup(this, fb_id, name, email, password, phone,
                new NetworkManager.OnResultListener<User>(){
                    @Override
                    public void onSuccess(Request request, User result) {
                        PropertyManager.getInstance().setLogin(true);
                        PropertyManager.getInstance().setUser(result);
                        PropertyManager.getInstance().setPush("true");
                        PropertyManager.getInstance().setEmail(email);
                        PropertyManager.getInstance().setPassword(password);

                        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFail(Request request, IOException exception) {
                        Toast.makeText(SignUpActivity.this, "error : " + exception.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            Intent myIntent = new Intent(SignUpActivity.this, LoginActivity.class);
            startActivity(myIntent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
