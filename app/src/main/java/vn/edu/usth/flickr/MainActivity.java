package vn.edu.usth.flickr;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;
import com.flickr4java.flickr.photos.PhotosInterface;
import com.flickr4java.flickr.photos.SearchParameters;
import com.flickr4java.flickr.test.TestInterface;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainActivity extends AppCompatActivity {

    private TabLayout mainTabLayout;
    private ViewPager mainViewPager;

    private String apiKey = "b73450b170505cfc1020ef349a0a8cb4";
    private String sharedSecret = "679e34e410b67118";
    private String userId = "143081057@N07";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainViewPager = (ViewPager) findViewById(R.id.mainViewPager);
        addTabs(mainViewPager);

        mainTabLayout = (TabLayout) findViewById(R.id.mainTabLayout);
        mainTabLayout.setupWithViewPager(mainViewPager);

        // This will be helpful to check server is down or not
        pingTask pt = new pingTask();
        pt.execute();
    }

    public class pingTask extends AsyncTask<Void, Void, String> {

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected String doInBackground(Void... voids) {
            try {
                pingFlickrServer();
            } catch (FlickrException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private void pingFlickrServer() throws FlickrException {
        Flickr f = new Flickr(apiKey, sharedSecret, new REST());
        TestInterface test = f.getTestInterface();
        Collection results = test.echo(Collections.EMPTY_MAP);
        Log.i("ping flickr", results.toString());
    }

    private void addTabs(ViewPager vp)
    {
        ViewPagerAdapter vap = new ViewPagerAdapter(getSupportFragmentManager());
        vap.addFrag(new FeedFragment(apiKey, sharedSecret), "Newsfeed");
        vap.addFrag(new SearchFragment(apiKey, sharedSecret), "Search");
        vap.addFrag(new NotificationFragment(), "Notification");
        vap.addFrag(new ProfileFragment(apiKey, sharedSecret, userId), "Profile");        vp.setAdapter(vap);
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