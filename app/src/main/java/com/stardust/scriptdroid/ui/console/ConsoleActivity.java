package com.stardust.scriptdroid.ui.console;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.stardust.scriptdroid.ui.BaseActivity;
import com.jraska.console.Console;
import com.stardust.scriptdroid.R;

/**
 * Created by Stardust on 2017/2/12.
 */

public class ConsoleActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpUI();
    }

    private void setUpUI() {
        setContentView(R.layout.activity_console);
        setToolbarAsBack(getString(R.string.text_log));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_console, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Console.clear();
        return super.onOptionsItemSelected(item);
    }

    public static class ConsoleView extends Console {

        public ConsoleView(Context context) {
            super(context);
            init();
        }

        public ConsoleView(Context context, AttributeSet attrs) {
            super(context, attrs);
            init();
        }

        public ConsoleView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            init();
        }

        public ConsoleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
            super(context, attrs, defStyleAttr, defStyleRes);
            init();
        }

        private void init() {
            findViewById(R.id.console_scroll_view).setBackgroundColor(Color.WHITE);
            ((TextView) findViewById(R.id.console_text)).setTextIsSelectable(true);
        }

    }

}
