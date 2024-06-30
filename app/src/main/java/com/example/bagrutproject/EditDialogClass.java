package com.example.bagrutproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

public class EditDialogClass implements View.OnClickListener {

    Activity a;
    public Dialog d;
    Solve s;
    SolveHelper sh;
    public TextView tvTime, tvType, tvScramble, tvDate, tvDnf, tvPlus2, tvNo;
    public EditText etComment;
    public Button btnOk;
    ImageButton ibDelete;

    public Dialog showEditDialog(Activity activity, Solve s){
        a = activity;
        d = new Dialog(a);
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        d.setContentView(R.layout.layout_edit);
        d.setCancelable(false);

        this.s = s;
        sh = new SolveHelper(activity);
        etComment = d.findViewById(R.id.etCommentDialog);
        tvTime = d.findViewById(R.id.tvTimeDialog);
        tvType = d.findViewById(R.id.tvTypeDialog);
        tvScramble = d.findViewById(R.id.tvScrambleDialog);
        tvDate = d.findViewById(R.id.tvDateDialog);
        tvDnf = d.findViewById(R.id.tvDnfDialog);
        tvDnf.setOnClickListener(this);
        tvPlus2 = d.findViewById(R.id.tvPlus2Dialog);
        tvPlus2.setOnClickListener(this);
        tvNo = d.findViewById(R.id.tvNoDialog);
        tvNo.setOnClickListener(this);
        btnOk = d.findViewById(R.id.btnOkDialog);
        btnOk.setOnClickListener(this);
        ibDelete = d.findViewById(R.id.ibDeleteDialog);
        ibDelete.setOnClickListener(this);

        tvTime.setText(s.getDisplayPenaltyText());
        if(s.getCubeType() == 0)
            tvType.setText("2x2");
        else if(s.getCubeType() == 1)
            tvType.setText("3x3");
        if(s.getCubeType() == 2)
            tvType.setText("4x4");
        tvScramble.setText(s.getScramble());
        tvDate.setText(s.getDate());
        etComment.setText(s.getComment());
        if(s.getPenalty() == 0){
            tvNo.setTextColor(ContextCompat.getColor(a, R.color.orange_100));
        }
        else if(s.getPenalty() == 1){
            tvPlus2.setTextColor(ContextCompat.getColor(a, R.color.orange_100));
        }
        else{
            tvDnf.setTextColor(ContextCompat.getColor(a, R.color.orange_100));
        }

        d.show();
        return d;
    }


    @Override
    public void onClick(View view) {
        if(view == tvNo){
            s.setPenalty(0);
            tvTime.setText(s.getDisplayPenaltyText());
            tvPlus2.setTextColor(ContextCompat.getColor(a, R.color.gray_100));
            tvDnf.setTextColor(ContextCompat.getColor(a, R.color.gray_100));
            tvNo.setTextColor(ContextCompat.getColor(a, R.color.orange_100));
        }
        else if(view == tvPlus2){
            s.setPenalty(1);
            tvTime.setText(s.getDisplayPenaltyText());
            tvNo.setTextColor(ContextCompat.getColor(a, R.color.gray_100));
            tvDnf.setTextColor(ContextCompat.getColor(a, R.color.gray_100));
            tvPlus2.setTextColor(ContextCompat.getColor(a, R.color.orange_100));
        }
        else if(view == tvDnf){
            s.setPenalty(2);
            tvTime.setText(s.getDisplayPenaltyText());
            tvNo.setTextColor(ContextCompat.getColor(a, R.color.gray_100));
            tvPlus2.setTextColor(ContextCompat.getColor(a, R.color.gray_100));
            tvDnf.setTextColor(ContextCompat.getColor(a, R.color.orange_100));
        }
        else if (view == ibDelete) {
            UtilActivity.showDeleteConfirmationDialog(a, new Runnable() {
                @Override
                public void run() {
                    sh.open();
                    sh.deleteByRow(s.getSolveId());
                    sh.close();
                    d.dismiss();
                }
            });
        }
        else if(view == btnOk){
            s.setComment(String.valueOf(etComment.getText()));
            sh.open();
            sh.updateByRow(s);
            sh.close();
            d.dismiss();
        }
    }
}
