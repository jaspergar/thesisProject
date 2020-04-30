package com.example.enomfinal.fragments.DialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.enomfinal.R;

public class UserTypeFragment extends DialogFragment {
int position = 0; //default selected position

    public interface UserTypeListener{
     void onPositiveButtonClicked(String[] list,int position);
     void onNegativeButtonClicked();
    }

    UserTypeListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
            try {
                mListener = (UserTypeListener) context;
            }catch (Exception e){
              throw new ClassCastException(getActivity().toString()+ " You must Select a User Type");
            }

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final String[] list = getActivity().getResources().getStringArray(R.array.User_Type);

        builder.setTitle("What kind of user are you?")
                .setSingleChoiceItems(list, position, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                      position = i;
                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mListener.onPositiveButtonClicked(list,position);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mListener.onNegativeButtonClicked();
                    }
                });
        return builder.create();
    }
}
