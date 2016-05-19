package kr.werac.yeah.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.Toast;

import kr.werac.yeah.R;
import kr.werac.yeah.SettingActivity;
import kr.werac.yeah.mypage.MyPageActivity;
import kr.werac.yeah.werac.CreateWeracActivity;

public class MainActivity extends AppCompatActivity {

    FragmentTabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("WERAC");
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_my_calendar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyPageActivity.class);
                startActivity(intent);
            }
        });
        //setSupportActionBar(toolbar);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.hideOverflowMenu();
//        View childView = LayoutInflater.from(this).inflate(R.layout.view_toolbar, null);
//        toolbar.addView(childView);

        ImageView ImageBtn = (ImageView)findViewById(R.id.ImageBtn1);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateWeracActivity.class);
                startActivity(intent);
            }
        });

        tabHost = (FragmentTabHost)findViewById(R.id.tabHost);

        tabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("전체"), AllViewFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("제안"), SuggestViewFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("참여"), JoinViewFragment.class, null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action ar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        if(item.getItemId() == R.id.action_addItem){
            Intent intent = new Intent(MainActivity.this, SettingActivity.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(MainActivity.this, SettingActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
