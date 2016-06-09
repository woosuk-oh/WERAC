package kr.werac.yeah.mypage;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import kr.werac.yeah.R;
import kr.werac.yeah.data.User;
import kr.werac.yeah.manager.NetworkManager;
import kr.werac.yeah.werac_modify.ModifyWeracFragment;
import okhttp3.Request;

public class MyPageActivity extends AppCompatActivity {

    int myId;
    CircleImageView iv_my_image;
    TextView tv_my_id;
    TextView tv_my_comment;
    TextView tv_my_phone;
    ImageButton btn_modify_profile;
    FragmentTabHost tabHost;
    int select_tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_mypage);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.back);

        iv_my_image = (CircleImageView) findViewById(R.id.iv_my_image);
        tv_my_id = (TextView) findViewById(R.id.tv_my_id);
        tv_my_comment = (TextView) findViewById(R.id.tv_my_comment);
        tv_my_phone = (TextView) findViewById(R.id.tv_my_phone);
        btn_modify_profile = (ImageButton) findViewById(R.id.btn_modify_profile);
        btn_modify_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyPageActivity.this, MyPageModifyActivity.class);
                startActivity(intent);
                finish();
            }
        });

        tabHost = (FragmentTabHost)findViewById(R.id.tabHost_mypage);

        tabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        tabHost.addTab(tabHost.newTabSpec("0").setIndicator("개설"), MyCreateHistoryFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("1").setIndicator("참여"), MyJoinHistoryFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("2").setIndicator("진행"), MyMCHistoryFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("3").setIndicator("관심"), MyLikeHistoryFragment.class, null);

        myId = 2;

        setTabColor();
        initData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(item.getItemId() == android.R.id.home){
            finish();
        }else if(item.getItemId() == R.id.btn_push_alarm){
            Intent intent = new Intent(MyPageActivity.this, AlarmActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void initData() {

        NetworkManager.getInstance().getWeracMy(this, new NetworkManager.OnResultListener<User>() {
            @Override
            public void onSuccess(Request request, User result) {
                setUser(result);
            }

            @Override
            public void onFail(Request request, IOException exception) {
                Toast.makeText(MyPageActivity.this, "exception : " + exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setUser(User result) {
        if(result.getProfile_image() != null)
            Glide.with(iv_my_image.getContext()).load(result.getProfile_image()).into(iv_my_image);
        else
            iv_my_image.setImageResource(R.drawable.profile_default);
        tv_my_id.setText(result.getName());
        if(result.getComment() != null)
            tv_my_comment.setText(result.getComment());
        if(result.getPhone() != null)
            tv_my_phone.setText(result.getPhone());
    }

    public void setTabColor(){

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                select_tab = Integer.parseInt(tabId);
                TextView tv = (TextView) tabHost.getTabWidget().getChildAt(select_tab).findViewById(android.R.id.title);
                tv.setTextColor(getResources().getColor(R.color.colorWerac));
                tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
                for(int i = 0; i < tabHost.getTabWidget().getChildCount() ; i++)
                {
                    if(i != select_tab) {
                        TextView tv2 = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
                        tv2.setTextColor(getResources().getColor(R.color.tab_not_checked_color));
                        tv2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
                    }
                }
            }
        });

        for(int i = 0; i < tabHost.getTabWidget().getChildCount() ; i++)
        {
            TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            if(i == 0)
                tv.setTextColor(getResources().getColor(R.color.colorWerac));
            else
                tv.setTextColor(getResources().getColor(R.color.tab_not_checked_color));
            tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
        }
    }
}
