package com.gexton.mvpdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements MainActivityPresenter.View {
    private MainActivityPresenter presenter;
    private TextView tvName;
    private EditText edtName, edtEmail;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainActivityPresenter(this);
        tvName = findViewById(R.id.tvName);
        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        initProgressBar();

        edtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                presenter.updateFullName(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                hideProgressBar();
            }
        });

        edtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                presenter.updateEmail(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                hideProgressBar();
            }
        });
    }

    private void initProgressBar() {
        progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleSmall);
        progressBar.setIndeterminate(true);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(Resources.getSystem().getDisplayMetrics().widthPixels, 250);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        this.addContentView(progressBar, params);
        showProgressBar();
    }

    @Override
    public void updateUserInfoTextView(String info) {
        tvName.setText(info);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }
}