package com.example.bagrutproject.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bagrutproject.R;
import com.example.bagrutproject.stats.Solve;
import com.example.bagrutproject.stats.SolveHelper;
import com.example.bagrutproject.utils.UtilText;

public class AddDialogClass implements View.OnClickListener {
    Activity a;
    public Dialog d;
    EditText et;
    Button btnOk;
    SolveHelper sh;
    String scramble, date, currentText;
    int cubeType;

    public Dialog showDialog(Activity a , int cubeType, String scramble, String date){
        this.a = a;
        d = new Dialog(a);
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        d.setContentView(R.layout.layout_add);
        d.setCancelable(true);
        d.setTitle("Add Solve Time");
        d.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        d.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        sh = new SolveHelper(a);
        this.cubeType = cubeType;
        this.scramble = scramble;
        this.date = date;
        currentText = "";

        btnOk = d.findViewById(R.id.btnOk);
        btnOk.setOnClickListener(this);
        et = d.findViewById(R.id.et);

        et.addTextChangedListener(new TextWatcher() {
            private boolean isFormatting;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
                if (!isFormatting) {
                    currentText = charSequence.toString();
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if (!isFormatting) {
                    String text = charSequence.toString();

                    if (text.length() < currentText.length()) {
                        currentText = currentText.substring(0, text.length());
                        isFormatting = true;
                        et.setText(currentText);
                        et.setSelection(currentText.length());
                        isFormatting = false;
                        return;
                    }

                    String enteredDigits = text.replaceAll("[^0-9]", "");

                    try {
                        long etTime = Long.parseLong(enteredDigits) * 10;
                        isFormatting = true;
                        currentText = UtilText.getDisplayEditText(etTime);
                        et.setText(currentText);
                        et.setSelection(currentText.length());
                        isFormatting = false;
                    } catch (NumberFormatException e) {
                        et.setText("");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Not used in this specific implementation
            }
        });

        d.show();
        return d;
    }

    @Override
    public void onClick(View view) {
        if(view == btnOk){
            try {
                if(et.getText().toString().isEmpty()){
                    Toast.makeText(a, "Please enter text", Toast.LENGTH_LONG).show();
                }
                else{
                    long solveTime = UtilText.getTimeLong(et.getText().toString());
                    if(solveTime == 0 || solveTime == -1){
                        Toast.makeText(a, "Please enter valid text", Toast.LENGTH_LONG).show();
                    }
                    else{
                        sh.open();
                        sh.createSolve(new Solve(cubeType, 0, solveTime, scramble, "", date));
                        sh.close();
                        d.dismiss();
                    }
                }
            } catch (NumberFormatException e) {
            }
        }
    }
}
