package com.example.user.asignment2_1;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by USER on 25-Apr-18.
 */

public class RegisterUserFragment  extends Fragment {
    View vRegisterUser;

    EditText fnamein, snamein, addin, postin, mobin, emailin, userin, passin;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        vRegisterUser = inflater.inflate(R.layout.fragment_reg_user, container, false);
        //fnamein = (EditText)vRegisterUser

        return vRegisterUser;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);

    }

}
