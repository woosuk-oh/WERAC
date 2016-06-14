package kr.werac.yeah.main;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import kr.werac.yeah.MyApplication;
import kr.werac.yeah.R;

public class ImageViewActivity extends AppCompatActivity {

    ImageView iv_iv;
    String whatImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_iv);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.back);

        iv_iv = (ImageView)findViewById(R.id.iv_iv);
        whatImage = getIntent().getStringExtra("whatImage");

        float scale = MyApplication.getContext().getResources().getDisplayMetrics().density;
        if(whatImage.equals("mc")){
            Glide.with(this).load(R.drawable.image_mc_explain).override((int)(360*scale), (int)(360 * 2.33 * scale)).into(iv_iv);
        }else {
            Glide.with(this).load(R.drawable.image_other_explain).override((int)(360*scale), (int)(360 * 3.98 * scale)).into(iv_iv);
        }

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
