package com.example.files;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MyAdapter extends BaseExpandableListAdapter {

    Context con;
    List<File> Folders;
    HashMap<String,List<File>> listFolders;

    public MyAdapter(Context con, List<File> folders) {
        this.con = con;
        Folders = folders;
        listFolders=getHash(folders);
    }

    private HashMap<String,List<File>> getHash(List<File> folders) {
        HashMap<String,List<File>> temp;
        temp=new HashMap<>();

        for( File a:Folders){
            temp.put(a.getName(),getRespList(a));
        }
        return temp;
    }

    private List<File> getRespList(File a) {
        List<File> temp=new ArrayList<>();
        temp= Arrays.asList(a.listFiles());
        return temp;
    }

    @Override
    public int getGroupCount() {
        return Folders.size();
    }

    @Override
    public int getChildrenCount(int i) {
        if(this.listFolders.get(this.Folders.get(i))==null){
            return 0;
        }
        return this.listFolders.get(this.Folders.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return this.Folders.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return this.listFolders.get(this.Folders.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        if(view==null){
            LayoutInflater layoutInflater=(LayoutInflater) this.con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=layoutInflater.inflate(R.layout.list_group,null);
        }
        TextView tv=view.findViewById(R.id.list_parent);
        tv.setText(Folders.get(i).getName());
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        if(view==null){
            LayoutInflater layoutInflater=(LayoutInflater) this.con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=layoutInflater.inflate(R.layout.list_item,null);
        }

        ExpandableListView expandableListView=view.findViewById(R.id.child_expand_list);
        List<File> childFolders=new ArrayList<>();
        HashMap<String,List<File>> listFolders;

        childFolders= Arrays.asList(Folders.get(i).listFiles());
        MyAdapter adapter2=new MyAdapter(con,childFolders);
        expandableListView.setAdapter(adapter2);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
