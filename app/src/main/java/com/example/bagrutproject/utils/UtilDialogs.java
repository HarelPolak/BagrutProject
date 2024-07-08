package com.example.bagrutproject.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

import com.example.bagrutproject.core.MainActivity;
import com.example.bagrutproject.dialogs.AddDialogClass;
import com.example.bagrutproject.dialogs.EditDialogClass;
import com.example.bagrutproject.stats.Solve;
import com.google.android.material.internal.ManufacturerUtils;

public class UtilDialogs {
    public static void showConfirmationDialog(Context context, final Runnable onConfirmed, String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(msg)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onConfirmed.run();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public static Dialog showAddDialog(Activity a, int cubeType, String scramble, String date){
        AddDialogClass addDialogClass = new AddDialogClass();
        Dialog dialog = addDialogClass.showDialog(a, cubeType, scramble, date);
        return dialog;
    }

    public static Dialog showEditDialog(Activity activity, Solve solve){
        EditDialogClass editDialogClass = new EditDialogClass();
        Dialog dialog = editDialogClass.showDialog(activity, solve);
        return dialog;
    }
}
