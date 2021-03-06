package cn.edu.tsinghua.proxytalk;

import android.util.ArrayMap;
import android.util.Log;

import java.util.List;
import java.util.Map;

import pcg.hcit_service.AccessibilityNodeInfoRecord;
import pcg.hcit_service.MyExampleClass;

public class Alipay_Index extends ActionDrivenLayout {
    private static final String GREETING = "在你好，需要什么服务？";
    private static final String TAG  = "VOICE_Assistant";

    public Alipay_Index(MyExampleClass context, String lowLevelPageName) {
        super(context, lowLevelPageName);
    }

    @Override
    public void onLoad() {
        setThreshold(0.8f);

        registerAction(new ITaskCallback<ActionDrivenLayout.Result>() {
            @Override
            public void run(ActionDrivenLayout.Result result) { //Called when the action is matched
                proxySpeak("多少钱？");
                Map<String, String> paraValues = new ArrayMap<>();
                paraValues.put("列表朋友", "韩红萍"); // put a name of person here
                switchPages("com.eg.android.AlipayGphone-70", paraValues);
            }
        }, "testing", "transfer money", "transfer money to", "give money", "give money to");

        registerAction(new ITaskCallback<ActionDrivenLayout.Result>() {
            @Override
            public void run(ActionDrivenLayout.Result result) {
                switchPages("com.eg.android.AlipayGphone-178", null);
            }
        }, "打开朋友", "messages", "show messages", "show friends", "看朋友");

        registerAction(new ITaskCallback<ActionDrivenLayout.Result>() {
            @Override
            public void run(ActionDrivenLayout.Result result) {
                switchPages("com.eg.android.AlipayGphone-134", null);
            }
        }, "转账", "transfer", "打开转账");

        registerAction(new ITaskCallback<Result>() {
            @Override
            public void run(Result result) {
                Map<String, String> paraValues = new ArrayMap<>();
                switchPages("com.eg.android.AlipayGphone-93", paraValues);
            }
        }, "充值", "打开充值");
        registerAction(new ITaskCallback<Result>() {
            @Override
            public void run(Result result) {
                Map<String, String> paraValues = new ArrayMap<>();
                switchPages("com.eg.android.AlipayGphone-2", paraValues);
            }
        }, "扫一扫", "扫", "打开扫一扫");
        registerAction(new ITaskCallback<Result>() {
            @Override
            public void run(Result result) {
                Map<String, String> paraValues = new ArrayMap<>();
                switchPages("com.eg.android.AlipayGphone-61", paraValues);
            }
        }, "付钱", "打开付钱");

        Log.i(TAG, "In Alipay Index");
        proxySpeak(GREETING, new ITaskCallback<String>() {
            @Override
            public void run(String result) {
                listen();
                Log.i(TAG, "Greeting success");
            }
        });
    }

    @Override
    public void onChange(Map<String, List<AccessibilityNodeInfoRecord>> changeTypeToNodeList) {
    }

    @Override
    public void onListenError(String message) {
        System.err.println("An error has occurred when running voice recognition: " + message);
        Log.i(TAG, "An error has occurred when running voice recognition: " + message);
    }

    @Override
    public void onListenSuccess(String result) {
        Log.i(TAG, "No error has occurred when running voice recognition: " + result);
    }
}
