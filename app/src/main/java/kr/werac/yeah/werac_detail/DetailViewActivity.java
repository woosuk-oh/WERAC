package kr.werac.yeah.werac_detail;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

import kr.werac.yeah.R;
import kr.werac.yeah.data.WeracItem;
import kr.werac.yeah.manager.NetworkManager;
import kr.werac.yeah.manager.PropertyManager;
import kr.werac.yeah.werac_create.CreateDialogFragment;
import kr.werac.yeah.werac_modify.ModifyWeracActivity;
import okhttp3.Request;

public class DetailViewActivity extends AppCompatActivity {

    public static final String EXTRA_WERAC_ID = "MId";
    public static final int DONT_KNOW_WHY = 1000;
    int thisMid;
    WeracItem werac;
    MenuItem LikeMenu;
    int like;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_werac);
        werac = new WeracItem();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.back);

        thisMid = getIntent().getIntExtra(EXTRA_WERAC_ID, DONT_KNOW_WHY);

        if (savedInstanceState == null) {
            DetailWeracFragment f = DetailWeracFragment.newInstance(thisMid);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.container_detail, f);
            ft.commit();
        }

        setData();

        if(werac.getCreator().getUid() == PropertyManager.getInstance().getUser().getUid()) {
            Button btn = (Button) findViewById(R.id.btn_detail_to_modify_werac);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(DetailViewActivity.this, ModifyWeracActivity.class);
                    intent.putExtra(EXTRA_WERAC_ID, thisMid);
                    startActivity(intent);
                }
            });
            btn = (Button) findViewById(R.id.btn_detail_status_modify_werac);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CreateDialogFragment f_dialog = new CreateDialogFragment();
                    f_dialog.show(getSupportFragmentManager(), "create");
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_page, menu);
        LikeMenu =  menu.findItem(R.id.btn_like);
        LikeMenu.setIcon(R.drawable.page_heart);
        setData();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }else if(item.getItemId() == R.id.btn_like) {
            if(like == 0) {
                Toast.makeText(DetailViewActivity.this, "좋아요 되었습니다", Toast.LENGTH_SHORT).show();
                LikeMenu.setIcon(R.drawable.page_heart2);
                like = 1;
                likethis();
            }else {
                Toast.makeText(DetailViewActivity.this, "좋아요 취소되었습니다", Toast.LENGTH_SHORT).show();
                LikeMenu.setIcon(R.drawable.page_heart);
                likethis();
                like = 0;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void likethis(){
        NetworkManager.getInstance().getWeracDetailLike(DetailViewActivity.this, thisMid, new NetworkManager.OnResultListener<WeracItem>() {
            @Override
            public void onSuccess(Request request, WeracItem result) {
            }

            @Override
            public void onFail(Request request, IOException exception) {
                Toast.makeText(DetailViewActivity.this, "exception : " + exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setData() {
        NetworkManager.getInstance().getWeracDetail(DetailViewActivity.this, thisMid, new NetworkManager.OnResultListener<WeracItem>() {
            @Override
            public void onSuccess(Request request, WeracItem result) {
                if(result.getLikeList() != null){
                    werac = result;
                    for (int i = 0; i < werac.getLikeList().size(); i++) {
                        if (werac.getLikeList().get(i) == PropertyManager.getInstance().getUser().getUid()) {
                            like = 1;
                            LikeMenu.setIcon(R.drawable.page_heart2);
                        }else
                            LikeMenu.setIcon(R.drawable.page_heart);
                    }
                }else
                    LikeMenu.setIcon(R.drawable.page_heart);
            }

            @Override
            public void onFail(Request request, IOException exception) {
                Toast.makeText(DetailViewActivity.this, "exception : " + exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
