package com.example.bagrutproject.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.bagrutproject.R;
import com.example.bagrutproject.stats.Solve;
import com.example.bagrutproject.stats.SolveHelper;
import com.example.bagrutproject.utils.UtilDialogs;

public class EditDialogClass implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    Activity a;
    public Dialog d;
    Solve s;
    SolveHelper sh;
    public TextView tvTime, tvType, tvScramble, tvDate;
    public RadioGroup radioGroup;
    public RadioButton rbNoPenalty, rbPlus2, rbDnf;
    public EditText etComment;
    public Button btnOk;
    ImageButton ibDelete, ibShare;

    public Dialog showDialog(Activity activity, Solve s){
        a = activity;
        d = new Dialog(a);
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        d.setContentView(R.layout.layout_edit);
        d.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        d.setCancelable(false);

        this.s = s;
        sh = new SolveHelper(activity);
        etComment = d.findViewById(R.id.etCommentDialog);
        tvTime = d.findViewById(R.id.tvTimeDialog);
        tvType = d.findViewById(R.id.tvTypeDialog);
        tvScramble = d.findViewById(R.id.tvScrambleDialog);
        tvDate = d.findViewById(R.id.tvDateDialog);
        radioGroup = d.findViewById(R.id.radioGroup);
        rbNoPenalty = d.findViewById(R.id.rbNoPenalty);
        rbPlus2 = d.findViewById(R.id.rbPlus2);
        rbDnf = d.findViewById(R.id.rbDnf);
        radioGroup.setOnCheckedChangeListener(this);
        btnOk = d.findViewById(R.id.btnOkDialog);
        btnOk.setOnClickListener(this);
        ibDelete = d.findViewById(R.id.ibDeleteDialog);
        ibDelete.setOnClickListener(this);
        ibShare = d.findViewById(R.id.ibShare);
        ibShare.setOnClickListener(this);

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
            rbNoPenalty.setChecked(true);
        }
        else if(s.getPenalty() == 1){
            rbPlus2.setChecked(true);
        }
        else{
            rbDnf.setChecked(true);
        }

        d.show();
        return d;
    }


    @Override
    public void onClick(View view) {
        if (view == ibDelete) {
            UtilDialogs.showConfirmationDialog(a, new Runnable() {
                @Override
                public void run() {
                    sh.open();
                    sh.deleteByRow(s.getSolveId());
                    sh.close();
                    d.dismiss();
                }
            }, "Are you sure you want to delete this solve?");
        }
        else if(view == ibShare){
            String message = "Created by Cubix Timer: \n" + s.getDisplayPenaltyText() + " \n" + s.getScramble() + " \n" + s.getDate();

            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, message);
            sendIntent.setType("text/plain");

            Intent shareIntent = Intent.createChooser(sendIntent, null);
            if (sendIntent.resolveActivity(a.getPackageManager()) != null) {
                a.startActivity(shareIntent);
            }
        }
        else if(view == btnOk){
            s.setComment(String.valueOf(etComment.getText()));
            sh.open();
            sh.updateByRow(s);
            sh.close();
            d.dismiss();
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        RadioButton radioButton = d.findViewById(i);
        if(radioButton != null){
            if(radioButton == rbNoPenalty){
                s.setPenalty(0);
            }
            else if(radioButton == rbPlus2){
                s.setPenalty(1);
            }
            else if(radioButton == rbDnf){
                s.setPenalty(2);
            }
            tvTime.setText(s.getDisplayPenaltyText());
        }
    }
}
