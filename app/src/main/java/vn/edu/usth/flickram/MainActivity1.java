//package vn.edu.usth.flickram;
//
//import androidx.appcompat.app.AppCompatActivity;
//import android.Manifest;
//import android.os.Bundle;
//import android.os.Environment;
//
//import androidx.core.content.ContextCompat;
//import androidx.recyclerview.widget.GridLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.util.Log;
//import android.widget.Toast;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import java.io.File;
//import java.io.IOException;
//
//import com.gun0912.tedpermission.PermissionListener;
//import com.gun0912.tedpermission.TedPermission;
//
//public class MainActivity extends AppCompatActivity {
//
//    private final String DIRECTORY = Environment.getExternalStorageDirectory().toString();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
////        requirePermission();
//
//        ArrayList<String> mUrl = new ArrayList<>(Arrays.asList(
//                "https://i.imgur.com/zuG2bGQ.jpg",
//                "https://i.imgur.com/ovr0NAF.jpg",
//                "https://i.imgur.com/n6RfJX2.jpg",
//                "https://i.imgur.com/qpr5LR2.jpg",
//                "https://i.imgur.com/pSHXfu5.jpg",
//                "https://i.imgur.com/3wQcZeY.jpg",
//                "https://i.imgur.com/zuG2bGQ.jpg",
//                "https://i.imgur.com/ovr0NAF.jpg",
//                "https://i.imgur.com/n6RfJX2.jpg",
//                "https://i.imgur.com/qpr5LR2.jpg",
//                "https://i.imgur.com/pSHXfu5.jpg",
//                "https://i.imgur.com/3wQcZeY.jpg",
//                "https://i.imgur.com/zuG2bGQ.jpg",
//                "https://i.imgur.com/ovr0NAF.jpg",
//                "https://i.imgur.com/n6RfJX2.jpg",
//                "https://i.imgur.com/qpr5LR2.jpg",
//                "https://i.imgur.com/pSHXfu5.jpg",
//                "https://i.imgur.com/3wQcZeY.jpg",
//                "https://i.imgur.com/zuG2bGQ.jpg",
//                "https://i.imgur.com/ovr0NAF.jpg",
//                "https://i.imgur.com/n6RfJX2.jpg",
//                "https://i.imgur.com/qpr5LR2.jpg",
//                "https://i.imgur.com/pSHXfu5.jpg",
//                "https://i.imgur.com/3wQcZeY.jpg"
//        ));
//
//
//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
//        recyclerView.setHasFixedSize(true);
//        GalleryAdapter adapter = new GalleryAdapter(this, mUrl);
//        recyclerView.setAdapter(adapter);
//    }
//
////    @Override
//    private void requirePermission(){
//        PermissionListener permissionListener = new PermissionListener(){
//            @Override
//            public void onPermissionGranted(){
//                Toast.makeText(MainActivity.this, "Permission Granted",
//                        Toast.LENGTH_SHORT).show();
//            }
//
//
//            @Override
//            public void onPermissionDenied(ArrayList<String> deniedPermissions){
//                Toast.makeText(MainActivity.this,
//                        "Permission Denied\n" + deniedPermissions.toString(),
//                        Toast.LENGTH_SHORT).show();
//            }
//        };
//
//        new TedPermission(this)
//                .setPermissionListener(permissionListener)
//                .setDeniedMessage("If you reject permission, you cannot use this service\n," +
//                        "Please turn on permissions at [Setting] > [Permission]")
//                .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE)
//                .check();
//    }
//}