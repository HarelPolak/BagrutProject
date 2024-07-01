package com.example.bagrutproject.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class UtilDialogs {
    public static void showDeleteConfirmationDialog(Context context, final Runnable onDeleteConfirmed) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Are you sure you want to delete this solve?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onDeleteConfirmed.run();
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
}
