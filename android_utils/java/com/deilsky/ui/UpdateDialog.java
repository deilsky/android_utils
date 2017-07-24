package com.deilsky.ui;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.allen.library.SuperTextView;
import com.daimajia.numberprogressbar.NumberProgressBar;
import com.deilsky.R;

/**
 * Created by 帷幕 on 2017/7/12.
 */

public class UpdateDialog extends DialogFragment implements View.OnClickListener {
    private SuperTextView tip, cancel, enter;
    private TextView content,finish;
    private NumberProgressBar bar;
    private DialogFragmentListener listener;
    public interface DialogFragmentListener{
        public void cancel();
        public void enter(NumberProgressBar bar,TextView finish);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        return inflater.inflate(R.layout.update_layout, null);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void initComponent() {
        tip = (SuperTextView) getView().findViewById(R.id.update_tip);
        cancel = (SuperTextView) getView().findViewById(R.id.update_cancel);
        enter = (SuperTextView) getView().findViewById(R.id.update_enter);
        bar = (NumberProgressBar) getView().findViewById(R.id.number_progress_bar);
        content = (TextView) getView().findViewById(R.id.update_content);
        finish = (TextView) getView().findViewById(R.id.finish_tip);
        cancel.setOnClickListener(this);
        enter.setOnClickListener(this);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initComponent();
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            dialog.getWindow().setLayout((int) (dm.widthPixels), (int) (dm.widthPixels * 0.5));
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.update_cancel:
                listener.cancel();
                getDialog().dismiss();
                break;
            case R.id.update_enter:
                content.setVisibility(View.INVISIBLE);
                bar.setVisibility(View.VISIBLE);
                listener.enter(bar,finish);
                break;
        }
    }
    public void setOnButtonClickListener(DialogFragmentListener listener) {
        this.listener = listener;
    }
}
