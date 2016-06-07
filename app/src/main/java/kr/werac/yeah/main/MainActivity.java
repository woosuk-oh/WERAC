package kr.werac.yeah.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import kr.werac.yeah.R;
import kr.werac.yeah.SettingActivity;
import kr.werac.yeah.mypage.MyPageActivity;
import kr.werac.yeah.werac_create.CreateWeracActivity;

public class MainActivity extends AppCompatActivity {

    FragmentTabHost tabHost;
    int select_tab;
    int backButtonCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.main_profile);
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

        tabHost.addTab(tabHost.newTabSpec("0").setIndicator("전체"), AllViewFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("1").setIndicator("제안"), SuggestViewFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("2").setIndicator("참여"), JoinViewFragment.class, null);

        setTabColor();
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

        if(item.getItemId() == R.id.btn_setting){
            Intent intent = new Intent(MainActivity.this, SettingActivity.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(MainActivity.this, SettingActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void setTabColor(){

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                select_tab = Integer.parseInt(tabId);
                TextView tv = (TextView) tabHost.getTabWidget().getChildAt(select_tab).findViewById(android.R.id.title);
                tv.setTextColor(getResources().getColor(R.color.colorWerac));
                tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
                for(int i = 0; i < tabHost.getTabWidget().getChildCount() ; i++)
                {
                    if(i != select_tab) {
                        TextView tv2 = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
                        tv2.setTextColor(getResources().getColor(R.color.tab_not_checked_color));
                        tv2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
                    }
                }
            }
        });

        for(int i = 0; i < tabHost.getTabWidget().getChildCount() ; i++)
        {
            TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            if(i == 0)
                tv.setTextColor(getResources().getColor(R.color.colorWerac));
            else
                tv.setTextColor(getResources().getColor(R.color.tab_not_checked_color));
            tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
        }
    }

    @Override
    public void onBackPressed()
    {
        if(backButtonCount >= 1)
        {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            backButtonCount = 0;
        }
        else
        {
            Toast.makeText(this, "종료하시려면 한 번 더 더치해주세요", Toast.LENGTH_SHORT).show();
            backButtonCount++;
        }
    }
}
