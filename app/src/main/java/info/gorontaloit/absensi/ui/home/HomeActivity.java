package info.gorontaloit.absensi.ui.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import info.gorontaloit.absensi.R;
import info.gorontaloit.absensi.ui.SiskoPagerAdapter;
import info.gorontaloit.absensi.ui.jadwal.JadwalFragment;
import info.gorontaloit.absensi.ui.profile.ProfileFragment;
import info.gorontaloit.absensi.utils.SharedPreference;

public class HomeActivity extends AppCompatActivity {

    MenuItem prevMenuItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        JadwalFragment jf = new JadwalFragment();
        SiskoPagerAdapter siskoPagerAdapter = new SiskoPagerAdapter(getSupportFragmentManager(), 0);
        siskoPagerAdapter.addFragment(new HomeFragment(),"Home");
        siskoPagerAdapter.addFragment(jf, "Jadwal");
        siskoPagerAdapter.addFragment(new ProfileFragment(),"Profil");
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(siskoPagerAdapter);
        BottomNavigationView bnv = findViewById(R.id.bottom_nav_view);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home :
                        viewPager.setCurrentItem(0);
                        return true;
                    case R.id.action_schedule :
                        viewPager.setCurrentItem(1);
                        jf.setTitle(siskoPagerAdapter.getPageTitle(1).toString());
                        return true;
                    case R.id.action_profile :
                        viewPager.setCurrentItem(2);
                        return true;
                }
                return false;
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                } else {
                    bnv.getMenu().getItem(0).setChecked(false);
                }

                bnv.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bnv.getMenu().getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}
