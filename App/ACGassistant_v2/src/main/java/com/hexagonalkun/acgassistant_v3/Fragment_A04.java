package com.hexagonalkun.acgassistant_v3;


import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hexagonalkun.acgassistant_v3.providers.WeatherContentProvider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_A04 extends Fragment
{
    private TextView city, s_temp, s_humd,s_date,s_weather;
    private Spinner mSpnName;
    int up_item = 0;
    private JSONArray jsonArray;
    private JSONObject jsonData;
    private ImageView img;
    private static ContentResolver mContRes;
    //    private static final String DB_FILE = "id8606975_acgassistant.db";
//    private static final String DB_TABLE = "weather";
//    private static final int DBversion = 1;
    private String[] MYCOLUMN = new String[]{"ID", "SiteName", "Temperature", "Moisture", "Weather", "DataCreationDate"};
    private ArrayList<String> recSet;
    private static int tcount;
    public static String myselection = "";
    public static String myorder = "id ASC"; // 排序欄位
    public static String myargs[] = new String[]{};

    public Fragment_A04()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.weather, container, false);
        //---------------------------------------------------很重要
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads().detectDiskWrites().detectNetwork()
                .penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects().penaltyLog().penaltyDeath()
                .build());
        //---------------------------------------------------------很重要
        dbmysql();
        city = (TextView) view.findViewById(R.id.city);
        s_temp = (TextView) view.findViewById(R.id.temp);
        s_humd = (TextView) view.findViewById(R.id.humd);
        s_date=(TextView)view.findViewById(R.id.date);
        s_weather=(TextView)view.findViewById(R.id.weather);
        img=(ImageView)view.findViewById(R.id.imageView);
        mSpnName = (Spinner) view.findViewById(R.id.spnName);

        recSet = u_selectdb(myselection, myargs, myorder);

        recSet = u_query();
        //===========取SQLite 資料=============

//============================================================
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item);
        for (int i = 0; i < recSet.size(); i++) {
            String[] fld = recSet.get(i).split("#");
            adapter.add(fld[1]);
        }
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpnName.setAdapter(adapter);
        mSpnName.setOnItemSelectedListener(mSpnNameOnItemSelLis);
        mSpnName.setSelection(19, true); //spinner 小窗跳到第幾筆

        return view;
    }


    //=============================================================
    private Spinner.OnItemSelectedListener mSpnNameOnItemSelLis = new Spinner.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView parent, View view, int position,
                                   long id) {
            int iSelect = mSpnName.getSelectedItemPosition(); //找到按何項
            String[] fld = recSet.get(iSelect).split("#");

            city.setText("地點:"+fld[1]);
            s_temp.setText("溫度:"+fld[2]+"度C");
            s_humd.setText("相對溼度:"+fld[3]+"%");
            s_date.setText("更新時間:"+fld[5]);
            s_weather.setText("天氣狀況:"+fld[4]);
            if(fld[4].equals("晴")){
                img.setImageResource(R.drawable.sun);
            }else if(fld[4].equals("多雲")){
                img.setImageResource(R.drawable.cloud);
            }else if(fld[4].equals("陰")){
                img.setImageResource(R.drawable.darkcloud);
            }else if(fld[4].equals("陰有雨")){
                img.setImageResource(R.drawable.darkcloudrain);
            }else if(fld[4].equals("晴有霾")){
                img.setImageResource(R.drawable.suncloud);
            }else if(fld[4].equals("多雲有靄")){
                img.setImageResource(R.drawable.cloud);
            }else if(fld[4].equals("多雲有霾")){
                img.setImageResource(R.drawable.cloud);
            }else {
                img.setImageResource(R.drawable.question);
            }
            //-------目前所選的item---
            up_item = iSelect;
            //-------------------------------
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {

            city.setText("");
            s_temp.setText("");
            s_humd.setText("");
        }
    };

    //==========================================================

    private JSONArray sortJsonArray(JSONArray jsonArray) {
        ArrayList<JSONObject> jsons =new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                jsons.add(jsonArray.getJSONObject(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Collections.sort(jsons, new Comparator<JSONObject>() {
            @Override
            public int compare(JSONObject t1, JSONObject t2) {
                String lid = "";
                String rid = "";
                try {
                    lid = t1.getString("County");
                    rid = t2.getString("County");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return lid.compareTo(rid);
            }
        });
        return new JSONArray(jsons);
    }
    private void dbmysql() {
        mContRes = getActivity().getContentResolver(); //
        Cursor cur = mContRes.query(WeatherContentProvider.CONTENT_URI, MYCOLUMN, null, null, null);
        cur.moveToFirst(); // 一定要寫，不然會出錯
        try {
            String Task_opendata = new TransTask().execute("http://opendata.epa.gov.tw/webapi/Data/ATM00698/?$select=SiteName,Temperature,Moisture,Weather,DataCreationDate&$skip=0&$top=30&format=json").get();

            // 解析 json
            jsonArray = new JSONArray(Task_opendata);
            //------JSON 排序-
            jsonArray = sortJsonArray(jsonArray);
            //-----開始逐筆轉換-----
            if (jsonArray.length() > 0) { // MySQL 連結成功有資料
                Uri uri = WeatherContentProvider.CONTENT_URI;
                mContRes.delete(uri, null, null); // 匯入前,刪除所有SQLite資料

                // ----------------------------
                // 處理JASON 傳回來的每筆資料
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonData = jsonArray.getJSONObject(i);
                    //
                    ContentValues newRow = new ContentValues();
                    // --(1) 自動取的欄位
                    // --取出 jsonObject
                    // 每個欄位("key","value")-----------------------
                    Iterator itt = jsonData.keys();
                    while (itt.hasNext()) {
                        String key = itt.next().toString();
                        String value = jsonData.getString(key); // 取出欄位的值
                        if (value == null) {
                            continue;
                        } else if ("".equals(value.trim())) {
                            continue;
                        } else {
                            jsonData.put(key, value.trim());
                        }
                        // ------------------------------------------------------------------
                        newRow.put(key, value.toString()); // 動態找出有幾個欄位
                        // -------------------------------------------------------------------
                    }
                    // -------------------加入SQLite---------------------------------------
                    mContRes.insert(WeatherContentProvider.CONTENT_URI, newRow);

                }
                // ---------------------------
            } else {
                Toast.makeText(getActivity(), "資料庫無資料", Toast.LENGTH_LONG).show();
            }
        }catch (Exception e) {
        }
        cur.close();
    }

    private ArrayList<String> u_selectdb(String myselection, String[] myargs, String myorder) {
        ArrayList<String> recAry = new ArrayList<String>();
        mContRes = getActivity().getContentResolver();
        Cursor c = mContRes.query(WeatherContentProvider.CONTENT_URI, MYCOLUMN, null, null, null);
        tcount = c.getCount();
        int columnCount = c.getColumnCount();
        while (c.moveToNext()) {
            String fldSet = "";
            for (int ii = 0; ii < columnCount; ii++)
                fldSet += c.getString(ii) + "#";
            recAry.add(fldSet);
        }
        c.close();
        return recAry;
    }

    private ArrayList<String> u_query() {
        ArrayList<String> recAry = new ArrayList<String>();
        myselection = "";
        myorder = "ID ASC"; // 排序欄位
        mContRes = getActivity().getContentResolver();
        myargs = new String[]{};
        Cursor c = mContRes.query(WeatherContentProvider.CONTENT_URI, MYCOLUMN, myselection, myargs, myorder);
        tcount = c.getCount();
        int columnCount = c.getColumnCount();
        while (c.moveToNext()) {
            String fldSet = "";
            for (int ii = 0; ii < columnCount; ii++)
                fldSet += c.getString(ii) + "#";
            recAry.add(fldSet);
        }
        c.close();
        return recAry;
    }

}
