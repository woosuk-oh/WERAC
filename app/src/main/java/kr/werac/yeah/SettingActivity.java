package kr.werac.yeah;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import kr.werac.yeah.data.User;
import kr.werac.yeah.login.LoginActivity;
import kr.werac.yeah.main.MainActivity;
import kr.werac.yeah.manager.NetworkManager;
import kr.werac.yeah.manager.PropertyManager;
import okhttp3.Request;

public class SettingActivity extends AppCompatActivity {

    TextView text_logout;
    Switch switch_push;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_setting);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.back);

        text_logout = (TextView)findViewById(R.id.text_logout);
        text_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetworkManager.getInstance().logout(this, new NetworkManager.OnResultListener<User>(){
                    @Override
                    public void onSuccess(Request request, User result) {
                        PropertyManager.getInstance().setEmail("");
                        PropertyManager.getInstance().setLogin(false);
                        PropertyManager.getInstance().setPassword("");
                        PropertyManager.getInstance().setPush("true");
                        PropertyManager.getInstance().setRegistrationToken("");
                        Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFail(Request request, IOException exception) {
                        Toast.makeText(SettingActivity.this, "error : " + exception.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        switch_push = (Switch)findViewById(R.id.switch_push);

        if(PropertyManager.getInstance().getPush().equals("false")) {
            switch_push.setChecked(false);
        }else{
            switch_push.setChecked(true);
        }

        switch_push.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    PropertyManager.getInstance().setPush("true");
                else
                    PropertyManager.getInstance().setPush("false");

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
