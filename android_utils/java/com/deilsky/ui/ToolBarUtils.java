package com.tereda.seizetreasure.ui;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by 帷幕 on 2017/7/12.
 */

public class ToolBarUtils {
    public static ToolBarUtils utils;
    private static AppCompatActivity activity;
    private static Toolbar bar;

    public static ToolBarUtils getInstance(AppCompatActivity mContext, int id) {
        utils = new ToolBarUtils();
        activity = mContext;
        bar = (Toolbar) activity.findViewById(id);
        return utils;
    }

    private void ToolBarUtils() {
    }

    public static ToolBarUtils setIcon(int id, int res) {
        ((ImageView) bar.findViewById(id)).setImageResource(res);
        return utils;
    }

    public static ToolBarUtils setTitle(int id, String str) {
        ((TextView) bar.findViewById(id)).setText(str);
        return utils;
    }

    public static ToolBarUtils setTitleRes(int id, int res) {
        ((TextView) bar.findViewById(id)).setText(activity.getString(res));
        return utils;
    }

    public static ToolBarUtils show(int id) {
        bar.findViewById(id).setVisibility(View.VISIBLE);
        return utils;
    }

    public static ToolBarUtils hidden(int id) {
        bar.findViewById(id).setVisibility(View.INVISIBLE);
        return utils;
    }
    public static ToolBarUtils gone(int id) {
        bar.findViewById(id).setVisibility(View.GONE);
        return utils;
    }

    public static ToolBarUtils setOnClick(int id, View.OnClickListener listener) {
        bar.findViewById(id).setOnClickListener(listener);
        return utils;
    }

    public static ToolBarUtils setOnClick(int[] ids, View.OnClickListener listener) {
        for (int id : ids) {
            bar.findViewById(id).setOnClickListener(listener);
        }
        return utils;
    }

    public void build() {
        activity.setSupportActionBar(bar);
    }
}
