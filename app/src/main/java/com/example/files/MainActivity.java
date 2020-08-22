package com.example.files;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    int STORAGE_PERMISSION_CODE = 1;


    ExpandableListView exp;
    List<File> files;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        exp = findViewById(R.id.expand_list);
        files = new ArrayList<>();

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);


//    private void initList() {
//        //get the folder names.
//        listGroup.add(getString(R.string.grp1));
//        listGroup.add(getString(R.string.grp2));
//        listGroup.add(getString(R.string.grp3));
//        listGroup.add(getString(R.string.grp4));
//        listGroup.add(getString(R.string.grp5));
//
//        String array[];
//
//        List<String> list1=new ArrayList<>();
//        array=getResources().getStringArray(R.array.gp1);
//        for(String item:array){
//            list1.add(item);
//        }
//
//        List<String> list2=new ArrayList<>();
//        array=getResources().getStringArray(R.array.gp2);
//        for(String item:array){
//            list2.add(item);
//        }
//        List<String> list3=new ArrayList<>();
//        array=getResources().getStringArray(R.array.gp3);
//        for(String item:array){
//            list3.add(item);
//        }
//
//        List<String> list4=new ArrayList<>();
//        array=getResources().getStringArray(R.array.gp4);
//        for(String item:array){
//            list4.add(item);
//        }
//
//        List<String> list5=new ArrayList<>();
//        array=getResources().getStringArray(R.array.gp5);
//        for(String item:array){
//            list5.add(item);
//        }
//
//        listItem.put(listGroup.get(0),list1);
//        listItem.put(listGroup.get(1),list2);
//        listItem.put(listGroup.get(2),list3);
//        listItem.put(listGroup.get(3),list4);
//        listItem.put(listGroup.get(4),list5);
//        adapter.notifyDataSetChanged();
//    }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==STORAGE_PERMISSION_CODE){
            if(grantResults.length>0&&grantResults[0]== PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"Permission granted",Toast.LENGTH_SHORT).show();
                File root = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
                Log.i("TAG", "Root name: "+root.getAbsolutePath());
                adapter = new MyAdapter(this, Arrays.asList(Objects.requireNonNull(root.listFiles())));
                exp.setAdapter(adapter);
            }
            else{
                Toast.makeText(this,"Permission needed",Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},STORAGE_PERMISSION_CODE);
            }
        }    }
}