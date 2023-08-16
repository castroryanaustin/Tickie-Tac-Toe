package com.mangostynn.ticktactoe;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;

public class WinDialog extends Dialog {
    private Button button;

    public WinDialog(@NonNull Context context,String message,MainActivity mainActivity) {
        super(context);
        this.message = message;
        this.mainActivity = mainActivity;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(  WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.win_dialog_layout);

        final TextView dialogMessage = findViewById(R.id.dialogMessage);
        dialogMessage.setText(this.message);

        final Button startAgainBtn = findViewById(R.id.startAgainBtn);
        startAgainBtn.setOnClickListener(e->{
            mainActivity.restartMatch();
            dismiss();
        });
    }
    private final String message;
    private final MainActivity mainActivity;
}
