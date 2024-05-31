package com.example.bagrutproject;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
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

public class EditDialogClass implements View.OnClickListener {

    Activity a;
    public Dialog d;
    Solve s;
    SolveHelper sh;
    public TextView tvTime, tvType, tvScramble, tvDate, tvDnf, tvPlus2, tvNo;
    public EditText etComment;
    public Button btnOk;
    ImageButton ibDelete;

    public void showEditDialog(Activity activity, Solve s){
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

        tvTime.setText(UtilActivity.getTimerText(s.getTime()));
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
            tvNo.setTextColor(Color.BLUE);
        }
        else if(s.getPenalty() == 1){
            tvPlus2.setTextColor(Color.BLUE);
        }
        else{
            tvDnf.setTextColor(Color.BLUE);
        }

        d.show();
    }


    @Override
    public void onClick(View view) {
        if(view == tvNo){
            tvPlus2.setTextColor(Color.BLACK);
            tvDnf.setTextColor(Color.BLACK);
            tvNo.setTextColor(Color.BLUE);
            s.setPenalty(0);
        }
        else if(view == tvPlus2){
            tvNo.setTextColor(Color.BLACK);
            tvDnf.setTextColor(Color.BLACK);
            tvPlus2.setTextColor(Color.BLUE);
            s.setPenalty(1);
        }
        else if(view == tvDnf){
            tvNo.setTextColor(Color.BLACK);
            tvPlus2.setTextColor(Color.BLACK);
            tvDnf.setTextColor(Color.BLUE);
            s.setPenalty(2);
        }
        else if(view == ibDelete){
            sh.open();
            sh.deleteByRow(s.getSolveId());
            sh.close();
            d.dismiss();
            Toast.makeText(a, "deleted", Toast.LENGTH_LONG).show();
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
