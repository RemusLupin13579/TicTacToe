package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

public class Permission_Util extends AppCompatActivity {
    Context context;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    public Permission_Util(Context context){
        this.context=context;
        sp = context.getSharedPreferences(context.getString(R.string.permission_preferance),Context.MODE_PRIVATE);
        editor = sp.edit();
    }


    public  void updatePermissionPreference(String permission){
        if(permission.equals("camera")){
            editor.putBoolean(context.getString(R.string.permission_camera),true);
            editor.commit();
        }
        else if(permission.equals("storage")){
            editor.putBoolean(context.getString(R.string.permission_storage),true);
            editor.commit();
        }
        else if(permission.equals("contacts")){
            editor.putBoolean(context.getString(R.string.permission_contacts),true);
            editor.commit();

        }
    }
    public boolean checkPermissionPreference(String permission){
        boolean isShown = false;
        if(permission.equals("camera")){
            isShown = sp.getBoolean(context.getString(R.string.permission_camera),false);
        }
        if(permission.equals("storage")){
            isShown = sp.getBoolean(context.getString(R.string.permission_storage),false);
        }
        if(permission.equals("contacts")){
            isShown = sp.getBoolean(context.getString(R.string.permission_contacts),false);
        }
        return  isShown;
    }

}