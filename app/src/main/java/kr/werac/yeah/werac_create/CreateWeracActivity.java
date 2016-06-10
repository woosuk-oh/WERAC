package kr.werac.yeah.werac_create;

import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import kr.werac.yeah.R;
import kr.werac.yeah.werac_detail.DetailViewActivity;

public class CreateWeracActivity extends AppCompatActivity {

    CreateWeracFragment createWeracFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_werac);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.back);


        if (savedInstanceState == null) {
            createWeracFragment = CreateWeracFragment.newInstance();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.container_create, createWeracFragment);
            ft.commit();

        Button btn = (Button)findViewById(R.id.btn_create_werac);
        btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i = createWeracFragment.sendWerac();
                    if(i == 0) {
                        Toast.makeText(CreateWeracActivity.this, "이미지를 선택해주세요", Toast.LENGTH_SHORT).show();
                    }else{
                        CreateDialogFragment f_dialog = new CreateDialogFragment();
                        Bundle args = new Bundle();
                        args.putInt(DetailViewActivity.EXTRA_WERAC_ID, createWeracFragment.getWeracId());
                        f_dialog.setArguments(args);
                        f_dialog.show(getSupportFragmentManager(), "create");
                    }
                }
            });
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
