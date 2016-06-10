package kr.werac.yeah.werac_detail;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.IOException;

import kr.werac.yeah.MyApplication;
import kr.werac.yeah.R;
import kr.werac.yeah.data.Users;
import kr.werac.yeah.manager.NetworkManager;
import okhttp3.Request;

public class GuestListActivity extends AppCompatActivity {

    RecyclerView rv_guests_list;
    GuestListItemAdapter mAdapter;
    int weracId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_guest);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.back);

        rv_guests_list = (RecyclerView)findViewById(R.id.rv_guests_list);
        mAdapter = new GuestListItemAdapter();

        rv_guests_list.setAdapter(mAdapter);
        rv_guests_list.setLayoutManager(new LinearLayoutManager(MyApplication.getContext()));
        weracId = getIntent().getIntExtra(DetailViewActivity.EXTRA_WERAC_ID, DetailViewActivity.DONT_KNOW_WHY);

        initGuests();
    }

    public void initGuests(){
        NetworkManager.getInstance().getGuests(MyApplication.getContext(), weracId, new NetworkManager.OnResultListener<Users>() {
            @Override
            public void onSuccess(Request request, Users result) {
                mAdapter.clear();
                mAdapter.equalAll(result.getUsers());
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
