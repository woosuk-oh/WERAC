package kr.werac.yeah.werac_detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import kr.werac.yeah.R;
import kr.werac.yeah.data.WeracItem;
import kr.werac.yeah.werac_modify.ModifyWeracActivity;

public class DetailViewActivity extends AppCompatActivity {

    public static final String EXTRA_WERAC_ID = "MId";
    public static final int DONT_KNOW_WHY = 1000;
    int thisMid;
    WeracItem werac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_werac);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        thisMid = getIntent().getIntExtra(EXTRA_WERAC_ID, DONT_KNOW_WHY);

        if (savedInstanceState == null) {
            DetailWeracFragment f = DetailWeracFragment.newInstance(thisMid);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.container_detail, f);
            ft.commit();
        }
        Button btn = (Button)findViewById(R.id.btn_detail_werac);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailViewActivity.this, ModifyWeracActivity.class);
                intent.putExtra(EXTRA_WERAC_ID, thisMid);
                startActivity(intent);
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
