package kr.werac.yeah.werac_modify;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import kr.werac.yeah.R;
import kr.werac.yeah.werac_create.CreateDialogFragment;
import kr.werac.yeah.werac_detail.DetailModifyDialog;
import kr.werac.yeah.werac_detail.DetailViewActivity;

public class ModifyWeracActivity extends AppCompatActivity {

    int thisMid;
    ModifyWeracFragment modifyWeracFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_werac);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        thisMid = getIntent().getIntExtra(DetailViewActivity.EXTRA_WERAC_ID, DetailViewActivity.DONT_KNOW_WHY);

        if (savedInstanceState == null) {
            modifyWeracFragment = ModifyWeracFragment.newInstance(thisMid);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.container_create, modifyWeracFragment);
            ft.commit();

            Button btn = (Button) findViewById(R.id.btn_create_werac);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DetailModifyDialog f = new DetailModifyDialog();
                    f.show(getSupportFragmentManager(), "modify");
                    modifyWeracFragment.modifyWerac();
                    finish();
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
