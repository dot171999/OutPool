package com.dot.outpool;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class FragmentThree extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_three,container,false);
    }
}