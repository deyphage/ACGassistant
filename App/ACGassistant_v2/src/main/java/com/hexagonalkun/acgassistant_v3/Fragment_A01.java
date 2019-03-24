package com.hexagonalkun.acgassistant_v3;


import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.hexagonalkun.acgassistant_v3.providers.NewsContentProvider;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_A01 extends Fragment implements AdapterView.OnItemClickListener
{

    //-------------A01參數宣告----------------------------------------------
    private static final String DB_FILE = "id8606975_acgassistant.db";
    private static final String DB_TABLE = "news";
    private static final int DBversion = 1;
    private TextView tvTitle;
    private ArrayList<String> recSet;
    private static ContentResolver mContRes;
    private String[] MYCOLUMN = new String[]{"nwsID", "nwsType", "nwsName", "nwsPic","nwsInfo"};//===============================
    public static int tcount,imageResource;
    public static String myselection = "";
    public static String myorder = "id ASC"; // 排序欄位
    public static String myargs[] = new String[]{};
    //--------------------------
    private ListView listview;
    private Intent intent = new Intent();
    //--------------------------------------------------------------------------
    public Fragment_A01()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        //findViewById寫法參考資料https://stackoverflow.com/questions/6495898/findviewbyid-in-fragment
        View view=inflater.inflate(R.layout.a0101, container, false);

        //--A01_DB載入-----------------------------------------------很重要
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads().detectDiskWrites().detectNetwork()
                .penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects().penaltyLog().penaltyDeath()
                .build());
        //---------------------------------------------------------很重要
        dbmysql();

        listview = (ListView) view.findViewById(R.id.a0101_list);
//        *********************************************

        recSet = u_selectdb(myselection, myargs, myorder);

        recSet = u_query();
        //===========取SQLite 資料=============
        List<Map<String, Object>> mList;
        mList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < recSet.size(); i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            String[] fld = recSet.get(i).split("#");

            imageResource = getResources().getIdentifier(fld[3],"drawable","com.hexagonalkun.acgassistant_v2");//20190308*****************
            item.put("a0101_img01", imageResource);
            item.put("a0101_t001", fld[2]);
            item.put("a0101_t002", fld[4]);
            item.put("a0101_t003", fld[3]);
            mList.add(item);
        }
        //=========設定listview========

        SimpleAdapter adapter = new SimpleAdapter(
                getActivity(),
                mList,
                R.layout.a0101_list_item,
                new String[]{"a0101_img01", "a0101_t001", "a0101_t002", "a0101_t003"},
                new int[]{R.id.a0101_img01, R.id.a0101_t001, R.id.a0101_t002, R.id.a0101_t003}
        );
        Point size = new Point();
        getActivity().getWindowManager().getDefaultDisplay().getSize(size);
        listview.getLayoutParams().height = (int) (size.y * 0.75);
        listview.setLayoutParams(listview.getLayoutParams());

        listview.setAdapter(adapter);
        listview.setTextFilterEnabled(true);
        listview.setOnItemClickListener(this);

        return view;
    }
    private ArrayList<String> u_selectdb(String myselection, String[] myargs, String myorder) {
        ArrayList<String> recAry = new ArrayList<String>();
        mContRes = getActivity().getContentResolver();
        Cursor c = mContRes.query(NewsContentProvider.CONTENT_URI, MYCOLUMN, null, null, null);
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
        myorder = "nwsID ASC"; // 排序欄位
        mContRes = getActivity().getContentResolver();
//        myselection = " aniName LIKE ? AND aniPic LIKE ? AND aniInfo LIKE ? ";
        myargs = new String[]{};
        Cursor c = mContRes.query(NewsContentProvider.CONTENT_URI, MYCOLUMN, myselection, myargs, myorder);
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

    // 讀取MySQL 資料
    private void dbmysql() {
        mContRes = getActivity().getContentResolver(); //
        Cursor cur = mContRes.query(NewsContentProvider.CONTENT_URI, MYCOLUMN, null, null, null);
        cur.moveToFirst(); // 一定要寫，不然會出錯
        // ---------------------------
        try {
            String result = DBConnector.executeQuery("SELECT * FROM news");
            // 選擇讀取特定欄位
            // String result = DBConnector.executeQuery("SELECT id,name FROM  member");
            /*******************************************************************************************
             * SQL 結果有多筆資料時使用JSONArray 只有一筆資料時直接建立JSONObject物件 JSONObject
             * jsonData = new JSONObject(result);
             *******************************************************************************************/
            JSONArray jsonArray = new JSONArray(result);
            // -------------------------------------------------------
            if (jsonArray.length() > 0) { // MySQL 連結成功有資料
                Uri uri = NewsContentProvider.CONTENT_URI;
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
                    // ---(2) 使用固定已知欄位---------------------------
                    // newRow.put("id", jsonData.getString("id").toString());
                    // newRow.put("name",
                    // jsonData.getString("name").toString());
                    // newRow.put("grp", jsonData.getString("grp").toString());
                    // newRow.put("address", jsonData.getString("address")
                    // .toString());
                    // -------------------加入SQLite---------------------------------------
                    mContRes.insert(NewsContentProvider.CONTENT_URI, newRow);

                }
                // ---------------------------
            } else {
                Toast.makeText(getActivity(), "主機資料庫無資料", Toast.LENGTH_LONG).show();
            }
            // --------------------------------------------------------
        } catch (Exception e) {
            // Log.e("log_tag", e.toString());
        }
        cur.close();
        //setupViewComponent();// 重構
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        Intent intent = new Intent();
        intent.setClass(getActivity(), A0102.class);
        String a0101_t001 =((TextView) view.findViewById(R.id.a0101_t001)).getText().toString();
        String a0101_t002 =((TextView) view.findViewById(R.id.a0101_t002)).getText().toString();
        String a0101_t003 =((TextView) view.findViewById(R.id.a0101_t003)).getText().toString();

        intent.putExtra("a0102_txt01",a0101_t001);//可放所有基本類別
        intent.putExtra("a0102_txt02",a0101_t002);
        intent.putExtra("a0102_img",a0101_t003);
//切換Activity
        startActivity(intent);
    }
    }

