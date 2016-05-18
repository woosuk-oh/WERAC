package kr.werac.yeah.werac;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import kr.werac.yeah.R;
import kr.werac.yeah.data.WeracItem;

public class CreateWeracActivity extends AppCompatActivity {

    RecyclerView listView;
    CreateWeracAdapter mAdapter;
    public static final String EXTRA_WERAC_ID = "WeracId";
    String weracId;
    GridLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_werac);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        listView = (RecyclerView)findViewById(R.id.rv_list_create);
        mAdapter = new CreateWeracAdapter();
        listView.setAdapter(mAdapter);
        mLayoutManager = new GridLayoutManager(this, 1);
        listView.setLayoutManager(mLayoutManager);

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
