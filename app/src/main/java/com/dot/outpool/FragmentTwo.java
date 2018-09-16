package com.dot.outpool;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class FragmentTwo extends Fragment
{
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<ListItem>listItems;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState)
    {


        View view=inflater.inflate(R.layout.fragment_two,container,false);

        recyclerView=view.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        listItems=new ArrayList<>();
        for(int i=1;i<13;i++)
        {
            ListItem listItem=new ListItem(""+i+" AM",i+"");
            listItems.add(listItem);
        }
        for(int i=1;i<13;i++)
        {
            ListItem listItem=new ListItem(""+i+" PM",i+"");
            listItems.add(listItem);
        }
        adapter=new Adapter(listItems,view.getContext());
        recyclerView.setAdapter(adapter);
        return view;
    }
}