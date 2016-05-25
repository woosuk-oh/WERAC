package kr.werac.yeah.mypage;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import kr.werac.yeah.R;
import kr.werac.yeah.data.User;
import kr.werac.yeah.manager.NetworkManager;
import okhttp3.Request;

public class CreaterPageActivity extends AppCompatActivity {

    public static final String EXTRA_CREATER_ID = "CreaterId";
    public static final int DONT_KNOW_WHY = 1000;
    int createrId;
    ImageView iv_mc_image;
    TextView tv_mc_id;
    TextView tv_mc_comment;
    TextView tv_mc_phone;
    FragmentTabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mc_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_mcpage);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setTitle("CreaterPage");

        createrId = getIntent().getIntExtra(EXTRA_CREATER_ID, DONT_KNOW_WHY);
        Bundle args = new Bundle();
        args.putInt(EXTRA_CREATER_ID, createrId);

        iv_mc_image = (ImageView) findViewById(R.id.iv_mc_image);
        tv_mc_id = (TextView) findViewById(R.id.tv_mc_id);
        tv_mc_comment = (TextView) findViewById(R.id.tv_mc_comment);
        tv_mc_phone = (TextView) findViewById(R.id.tv_mc_phone);

        tabHost = (FragmentTabHost)findViewById(R.id.tabHost_mcpage);
        tabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("개설"), MyCreateHistoryFragment.class, args);

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

    private void initData() {

        NetworkManager.getInstance().getWeracMC(this, 2, createrId, new NetworkManager.OnResultListener<User>() {
            @Override
            public void onSuccess(Request request, User result) {
                setUser(result);
            }

            @Override
            public void onFail(Request request, IOException exception) {
                Toast.makeText(CreaterPageActivity.this, "exception : " + exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setUser(User result) {
//        iv_mc_image.setImageResource(result.getImageURL());
        tv_mc_id.setText(result.getName());
        tv_mc_comment.setText(result.getComment());
//        tv_mc_phone.setText(result.getPhone());
    }

}
