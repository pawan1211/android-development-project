package com.example.audio;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ListView myListViewForSongs;
    String item[];
    int i;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myListViewForSongs = (ListView) findViewById(R.id.mySongListView);

        runTimePermission();
    }

    public void runTimePermission() {
        Dexter.withActivity(this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        display();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();
    }

    public ArrayList<File> findSong(File file) {
        ArrayList<File> arrayList = new ArrayList<>();
        File[] files = file.listFiles();
        for (File singleFile : files) {
            if (singleFile.isDirectory() && !singleFile.isHidden()) {
                arrayList.addAll(findSong(singleFile));
            } else {
                if (singleFile.getName().endsWith(".mp3")) {
                    arrayList.add(singleFile);
                }
            }
        }
        return arrayList;
    }

    void display() {
        final ArrayList<File> mysongs = findSong(Environment.getExternalStorageDirectory());
        item = new String[(mysongs.size())];
        for (i = 0; i < mysongs.size(); i++) {
            item[i] = mysongs.get(i).getName().toString().replace(".mp3", "");
            ArrayAdapter<String> adp = new
                    ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, item);

            myListViewForSongs.setAdapter(adp);
        }
    }
}
