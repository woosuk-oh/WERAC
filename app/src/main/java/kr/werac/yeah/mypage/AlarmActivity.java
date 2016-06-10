package kr.werac.yeah.mypage;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import kr.werac.yeah.MyApplication;
import kr.werac.yeah.R;
import kr.werac.yeah.data.Alarm;
import kr.werac.yeah.data.Alarms;
import kr.werac.yeah.manager.NetworkManager;
import kr.werac.yeah.werac_detail.DetailViewActivity;
import okhttp3.Request;

public class AlarmActivity extends AppCompatActivity {

    RecyclerView rv_alram;
    AlarmAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_alarm);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.back);

        rv_alram = (RecyclerView)findViewById(R.id.rv_alarm);

        mAdapter = new AlarmAdapter();
        mAdapter.setOnItemClickListener(new AlarmHolder.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Alarm alarm) {
                if(alarm.getStatus() == 1) {
                    Intent intent = new Intent(AlarmActivity.this, MCPageActivity.class);
                    intent.putExtra(DetailViewActivity.EXTRA_WERAC_ID, alarm.getMid());
                    intent.putExtra(MCPageActivity.EXTRA_MC_ID, alarm.getUid());
                    startActivity(intent);
                }else if(alarm.getStatus() == 2) {
                    Intent intent = new Intent(AlarmActivity.this, DetailViewActivity.class);
                    intent.putExtra(DetailViewActivity.EXTRA_WERAC_ID, alarm.getMid());
                    startActivity(intent);
                }else if(alarm.getStatus() == 3) {
                    Intent intent = new Intent(AlarmActivity.this, DetailViewActivity.class);
                    intent.putExtra(DetailViewActivity.EXTRA_WERAC_ID, alarm.getMid());
                    startActivity(intent);
                }else if(alarm.getStatus() == 4) {
                    Intent intent = new Intent(AlarmActivity.this, DetailViewActivity.class);
                    intent.putExtra(DetailViewActivity.EXTRA_WERAC_ID, alarm.getMid());
                    startActivity(intent);
                }
            }
        });

        rv_alram.setAdapter(mAdapter);
        rv_alram.setLayoutManager(new LinearLayoutManager(MyApplication.getContext()));

        initAlarm();
    }

    public void initAlarm(){
        NetworkManager.getInstance().getAlarm(this, new NetworkManager.OnResultListener<Alarms>() {
            @Override
            public void onSuccess(Request request, Alarms result) {
                mAdapter.clear();
                mAdapter.equalAll(result.getAlarms());
            }

            @Override
            public void onFail(Request request, IOException exception) {
                Toast.makeText(MyApplication.getContext(), "exception : " + exception.getMessage(), Toast.LENGTH_SHORT).show();
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
