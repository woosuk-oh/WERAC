package kr.werac.yeah.mypage;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import kr.werac.yeah.R;
import kr.werac.yeah.data.User;
import kr.werac.yeah.manager.NetworkManager;
import okhttp3.Request;

public class MyPageModifyActivity extends AppCompatActivity {

    int myId;
    CircleImageView iv_my_image;
    EditText tv_my_id;
    EditText tv_my_comment;
    EditText tv_my_phone;
    TextView btn_modify_profile;
    User user;
    File mUploadFile;
    FragmentTabHost tabHost;
    int select_tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page_modify);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_mypage);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.back);

        iv_my_image = (CircleImageView) findViewById(R.id.iv_my_image_m);
        iv_my_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/jpeg");
                startActivityForResult(intent, 1);
            }
        });
        tv_my_id = (EditText) findViewById(R.id.tv_my_id_m);
        tv_my_comment = (EditText) findViewById(R.id.tv_my_comment_m);
        tv_my_phone = (EditText) findViewById(R.id.tv_my_phone_m);
        btn_modify_profile = (TextView) findViewById (R.id.btn_modify_profile_m);
        btn_modify_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUser();
                modifyMyInfo();
                Intent intent = new Intent(MyPageModifyActivity.this, MyPageActivity.class);
                startActivity(intent);
                finish();
            }
        });

        myId = 2;

        initData();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            String[] projection = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(uri, projection, null, null, null);
            if (c.moveToNext()) {
                String path = c.getString(c.getColumnIndex(MediaStore.Images.Media.DATA));
                mUploadFile = new File(path);
                Glide.with(iv_my_image.getContext()).load(path).into(iv_my_image);
            }
        }
        return;
    }

    private void initData() {

        NetworkManager.getInstance().getWeracMy(this, new NetworkManager.OnResultListener<User>() {
            @Override
            public void onSuccess(Request request, User result) {
                setUser(result);
                user = result;
            }

            @Override
            public void onFail(Request request, IOException exception) {
                Toast.makeText(MyPageModifyActivity.this, "exception : " + exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void modifyMyInfo() {

        NetworkManager.getInstance().getWeracMyModify(this, user, mUploadFile, new NetworkManager.OnResultListener<User>() {
            @Override
            public void onSuccess(Request request, User result) {
                Toast.makeText(MyPageModifyActivity.this, "수정되었습니다" , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(Request request, IOException exception) {
                Toast.makeText(MyPageModifyActivity.this, "exception : " + exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setUser(User result) {
        if(result.getProfile_image() != null)
            Glide.with(iv_my_image.getContext()).load(result.getProfile_image()).into(iv_my_image);
        tv_my_id.setText(result.getName());
        if(result.getComment() != null)
            tv_my_comment.setText(result.getComment());
        if(result.getPhone() != null)
            tv_my_phone.setText(result.getPhone());
    }

    private User getUser() {
//        iv_mc_image.setImageResource(result);
        user.setName(tv_my_id.getText().toString());
        user.setComment(tv_my_comment.getText().toString());
        user.setPhone(tv_my_phone.getText().toString());
        return user;
    }
}
