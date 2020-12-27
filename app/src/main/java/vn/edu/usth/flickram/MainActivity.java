package vn.edu.usth.flickram;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout mainTabLayout;
    private ViewPager mainViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        FeedFragment feedFrag = new FeedFragment();
//        getSupportFragmentManager().beginTransaction().add(R.id.container, feedFrag, null).commit();

        mainViewPager = (ViewPager) findViewById(R.id.mainViewPager);
        addTabs(mainViewPager);

        mainTabLayout = (TabLayout) findViewById(R.id.mainTabLayout);
        mainTabLayout.setupWithViewPager(mainViewPager);
    }

    private void addTabs(ViewPager vp)
    {
        ViewPagerAdapter vap = new ViewPagerAdapter(getSupportFragmentManager());
        vap.addFrag(new FeedFragment(), "Newsfeed");
        vap.addFrag(new SearchFragment(), "Search");
        vap.addFrag(new NotificationFragment(), "Notification");
        vap.addFrag(new ProfileFragment(), "Profile");
        vp.setAdapter(vap);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter
    {
        private final List fragmentList = new ArrayList<>();
        private final List fragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager man)
        {
            super(man);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return (Fragment) fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        public void addFrag(Fragment frag, String title)
        {
            fragmentList.add(frag);
            fragmentTitleList.add(title);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return (CharSequence) fragmentTitleList.get(position);
        }
    }
}