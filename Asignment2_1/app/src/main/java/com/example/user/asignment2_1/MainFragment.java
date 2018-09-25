package com.example.user.asignment2_1;

import android.media.Image;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by USER on 25-Apr-18.
 */

public class MainFragment extends Fragment {
    View vMain;
    TextView tv_smart, to, welcome;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState)
    {
        vMain = inflater.inflate(R.layout.fragment_main, container, false);
        tv_smart = (TextView)vMain.findViewById(R.id.smarter);
        to = (TextView)vMain.findViewById(R.id.to);
        welcome = tv_smart = (TextView)vMain.findViewById(R.id.welcome);
        ImageView smile = (ImageView)vMain.findViewById(R.id.imageView2);
        return vMain;
    }

}
