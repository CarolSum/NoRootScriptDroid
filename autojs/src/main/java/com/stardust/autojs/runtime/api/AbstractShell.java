package com.stardust.autojs.runtime.api;

import android.text.TextUtils;

import com.stardust.util.ScreenMetrics;

import java.util.Arrays;

/**
 * Created by Stardust on 2017/4/24.
 */

public abstract class AbstractShell {


    public static class Result {
        public int code = -1;
        public String error;
        public String result;

        @Override
        public String toString() {
            return "ShellResult{" +
                    "code=" + code +
                    ", error='" + error + '\'' +
                    ", result='" + result + '\'' +
                    '}';
        }
    }

    private static final String COMMAND_SU = "su";
    private static final String COMMAND_SH = "sh";
    static final String COMMAND_EXIT = "exit\n";
    static final String COMMAND_LINE_END = "\n";


    private int mTouchDevice;
    private int mScreenHeight, mScreenWidth;

    private boolean mRoot;

    public AbstractShell() {
        this(false);
    }

    public AbstractShell(boolean root) {
        mRoot = root;
        init(root ? COMMAND_SU: COMMAND_SH);
    }

    public boolean isRoot() {
        return mRoot;
    }

    protected abstract void init(String initialCommand);

    public abstract void exec(String command);

    public abstract void exit();

    public void SetTouchDevice(int touchDevice) {
        mTouchDevice = touchDevice;
    }

    public void SendEvent(int type, int code, int value){
        SendEvent(mTouchDevice, type, code, value);
    }

    public void SendEvent(int device, int type, int code, int value){
        exec(TextUtils.join("", new Object[]{"sendevent /dev/input/event", device, " ", type, " ", code, " ", value}));
    }

    public void SetScreenScale(int width, int height){
        mScreenWidth = width;
        mScreenHeight = height;
    }

    public void Touch(int x, int y){
        TouchX(x);
        TouchY(y);
    }

    public void TouchX(int x) {
        SendEvent(mTouchDevice, 3, 53, scaleX(x));
    }

    private int scaleX(int x) {
        int screenWidth = ScreenMetrics.getScreenWidth();
        if(screenWidth == mScreenWidth){
            return x;
        }
        return x * screenWidth / mScreenWidth;
    }

    public void TouchY(int y) {
        SendEvent(mTouchDevice, 3, 54, scaleY(y));
    }

    private int scaleY(int y) {
        int screenHeight = ScreenMetrics.getScreenHeight();
        if(screenHeight == mScreenHeight){
            return y;
        }
        return y * screenHeight / mScreenHeight;
    }


    public void KeyCode(int keyCode) {
        exec("input keyevent " + keyCode);
    }

    public void KeyCode(String keyCode) {
        exec("input keyevent " + keyCode);
    }

    public void Home() {
        KeyCode(3);
    }

    public void Back() {
        KeyCode(4);
    }

    public void Power() {
        KeyCode(26);
    }

    public void Up() {
        KeyCode(19);
    }

    public void Down() {
        KeyCode(20);
    }

    public void Left() {
        KeyCode(21);
    }

    public void Right() {
        KeyCode(22);
    }

    public void OK() {
        KeyCode(23);
    }

    public void VolumeUp() {
        KeyCode(24);
    }

    public void VolumeDown() {
        KeyCode(25);
    }

    public void Menu() {
        KeyCode(1);
    }

    public void Camera() {
        KeyCode(27);
    }

    public void Text(String text) {
        exec("input text " + text);
    }

    public abstract void exitAndWaitFor();
}
