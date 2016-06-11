package kr.werac.yeah.mypage;

import android.os.Bundle;
import android.os.Process;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import kr.werac.yeah.R;
import kr.werac.yeah.data.User;
import kr.werac.yeah.data.WeracItem;
import kr.werac.yeah.manager.NetworkManager;
import kr.werac.yeah.manager.PropertyManager;
import kr.werac.yeah.werac_detail.DetailViewActivity;
import okhttp3.Request;

public class MCPageActivity extends AppCompatActivity {

    public static final String EXTRA_MC_ID = "MCId";
    public static final int DONT_KNOW_WHY = 1000;
    int mcId, mId, uId;
    CircleImageView iv_mc_image;
    TextView tv_mc_id;
    TextView tv_mc_comment;
    TextView tv_mc_phone;
    FragmentTabHost tabHost;
    ImageView tv_mc_accept;
    int applyMCResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mc_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_mcpage);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.back);

        mcId = getIntent().getIntExtra(EXTRA_MC_ID, DONT_KNOW_WHY);
        mId = getIntent().getIntExtra(DetailViewActivity.EXTRA_WERAC_ID, DONT_KNOW_WHY);
        uId = getIntent().getIntExtra(CreaterPageActivity.EXTRA_CREATER_ID, DONT_KNOW_WHY);
        Bundle args = new Bundle();
        args.putInt(EXTRA_MC_ID, mcId);
        args.putInt(DetailViewActivity.EXTRA_WERAC_ID, mId);

        iv_mc_image = (CircleImageView) findViewById(R.id.iv_mc_image);
        tv_mc_id = (TextView) findViewById(R.id.tv_mc_id);
        tv_mc_comment = (TextView) findViewById(R.id.tv_mc_comment);
        tv_mc_accept = (ImageView) findViewById(R.id.tv_mc_accept);
        if(uId == PropertyManager.getInstance().getUser().getUid() && mId != 1000){
            tv_mc_accept.setVisibility(View.VISIBLE);
        }else
            tv_mc_accept.setVisibility(View.INVISIBLE);
        tv_mc_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyMCResult = 1;
                applyResult(applyMCResult);
                finish();
            }
        });

        tabHost = (FragmentTabHost)findViewById(R.id.tabHost_mcpage);
        tabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("진행"), MyMCHistoryFragment.class, args);

        TextView tv = (TextView) tabHost.getTabWidget().getChildAt(0).findViewById(android.R.id.title);
        tv.setTextColor(getResources().getColor(R.color.colorWerac));
        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);

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

        NetworkManager.getInstance().getWeracMC_Create(this, 1, mcId, new NetworkManager.OnResultListener<User>() {
            @Override
            public void onSuccess(Request request, User result) {
                setUser(result);
            }

            @Override
            public void onFail(Request request, IOException exception) {
                Toast.makeText(MCPageActivity.this, "exception : " + exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setUser(User result) {
        if(result.getProfile_image() != null)
            Glide.with(iv_mc_image.getContext()).load(result.getProfile_image()).into(iv_mc_image);
        else
            iv_mc_image.setImageResource(R.drawable.profile_default);
        tv_mc_id.setText(result.getName());
        if(result.getComment() != null)
            tv_mc_comment.setText(result.getComment());
        else
            tv_mc_comment.setText("안녕하세요. " + result.getName() + "입니다.");
//        tv_mc_phone.setText(result.getPhone());
    }

    public void applyResult(int TapplyMCResult){
        NetworkManager.getInstance().applyMCResult(this, mId, mcId, TapplyMCResult, new NetworkManager.OnResultListener<WeracItem>() {
            @Override
            public void onSuccess(Request request, WeracItem result) {
            }

            @Override
            public void onFail(Request request, IOException exception) {
                Toast.makeText(MCPageActivity.this, "exception : " + exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
