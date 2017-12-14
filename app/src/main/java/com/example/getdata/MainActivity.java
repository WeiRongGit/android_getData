package com.example.getdata;

import android.content.SyncStatusObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //实例化发起请求的客户端
        AsyncHttpClient client = new AsyncHttpClient();
        //生成参数对象，设置参数
        RequestParams params = new RequestParams();
        params.put("materialName", "手机");

        //客户端利用get方法， url参数代表接口地址， params 为传递的方法，  AsyncHttpResponseHandler是回调
        client.get("https://www.zhtfkj.com/TfWebApi/api/Material", params, new AsyncHttpResponseHandler() {
            //"https://www.zhtfkj.com/TfWebApi/api/Material?materialName=手机"
            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                System.out.println("失败了  " + i);
            }

            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                System.out.println("成功了 " + i + "长度: " + bytes.length);

                for (Header one : headers) {
                    System.out.println("name: " + one.getName() + "val " + one.getValue());
                }


                Gson gson = new Gson();


                List<Msg> msg = gson.fromJson(new String(bytes), new TypeToken<List<Msg>>() {
                }.getType());


                for (Msg oneMsg : msg) {
                    Log.d("materialIDField: = ", oneMsg.getMaterialIDField());
                }

                try {
                    JSONArray jsonArray = new JSONArray(new String(bytes));
                    Log.d("取值", "准备取值");

                    for (int index = 0; index < jsonArray.length(); index++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(index);
                        Log.d("取值哈哈哈哈哈哈", jsonObject.toString());
                    }

//            Log.d("materialIDField","materialIDField is"+jsonObject.getString("materialIDField"));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        });

    }

    public void parseJsonWithJson(String jsonSata) {

    }
}