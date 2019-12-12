package com.example.lifecounter;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {

    private TextView player1Point;

    private TextView player1PointCul;
    private Button player1minusButton;
    private Button player1plusButton;
    private TextView player1PoisonPoint;


    private TextView player2Point;

    private TextView player2PointCul;
    private Button player2minusButton;
    private Button player2plusButton;
    private TextView player2PoisonPoint;

    private Button player1DrainButton;
    private Button player2DrainButton;

    private LinearLayout PoisonScreen1;
    private LinearLayout PoisonScreen2;


    static HistoryListAdapter1 listAdapter1;
    ListView historyListView1;
    static List<History> historyList1 = new ArrayList<History>();

    static HistoryListAdapter2 listAdapter2;
    ListView historyListView2;
    static List<History> historyList2 = new ArrayList<History>();


    private Button resetButton;

    MyTimerTaskP2 timerTaskP2 = null;       //onClickメソッドでインスタンス生成
    Timer mTimerP2   = null;            //onClickメソッドでインスタンス生成
    Handler mHandler = new Handler();   //UI Threadへのpost用ハンドラ
    MyTimerTaskP1 timerTaskP1 = null;       //onClickメソッドでインスタンス生成
    Timer mTimerP1   = null;            //onClickメソッドでインスタンス生成

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setScreenMain();

    }
    private void setScreenMain(){
        setContentView(R.layout.activity_main);

        historyListView1 = (ListView)findViewById(R.id.player1ListView);
        listAdapter1 = new HistoryListAdapter1();
        historyListView1.setAdapter(listAdapter1);

        historyListView2 = (ListView)findViewById(R.id.player2ListView);
        listAdapter2 = new HistoryListAdapter2();
        historyListView2.setAdapter(listAdapter2);

        player1Point = findViewById(R.id.player1_point);
        player2Point = findViewById(R.id.player2_point);

        player1PointCul = findViewById(R.id.player1_cul_point);
        player2PointCul = findViewById(R.id.player2_cul_point);

        player1PoisonPoint = findViewById(R.id.player1_poison_text);
        player2PoisonPoint = findViewById(R.id.player2_poison_text);
        PoisonScreen1 = findViewById(R.id.poisonScreen1);
        PoisonScreen2 = findViewById(R.id.poisonScreen2);

        player1DrainButton = findViewById(R.id.drain_button1);
        player1DrainButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                player1plusButton.callOnClick();
                player2minusButton.callOnClick();
            }

        });

        player2DrainButton = findViewById(R.id.drain_button2);
        player2DrainButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                player2plusButton.callOnClick();
                player1minusButton.callOnClick();
            }

        });

        player1minusButton = findViewById(R.id.player1_minus_button);
        player1minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int point = Integer.valueOf(player1Point.getText().toString());
                point -= 1;
                player1Point.setText(String.valueOf(point));
                if(mTimerP1 == null){
                    player1PointCul.setText("-1");
                    //タイマーの初期化処理
                    timerTaskP1 = new MyTimerTaskP1();
                    mTimerP1 = new Timer(true);
                    mTimerP1.schedule(timerTaskP1,2000);

                }else{
                    mTimerP1.cancel();
                    mTimerP1 = null;
                    timerTaskP1 = new MyTimerTaskP1();
                    mTimerP1 = new Timer(true);
                    int culpoint = Integer.valueOf(player1PointCul.getText().toString());
                    player1PointCul.setText(String.valueOf(culpoint - 1));
                    //タイマーの初期化処理
                    mTimerP1.schedule(timerTaskP1,2000);
                }
            }
        });
        player1plusButton = findViewById(R.id.player1_plus_button);
        player1plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int point = Integer.valueOf(player1Point.getText().toString());
                point += 1;
                player1Point.setText(String.valueOf(point));
                if(mTimerP1 == null){
                    player1PointCul.setText("1");
                    //タイマーの初期化処理
                    timerTaskP1 = new MyTimerTaskP1();
                    mTimerP1 = new Timer(true);
                    mTimerP1.schedule(timerTaskP1,2000);

                }else{
                    mTimerP1.cancel();
                    mTimerP1 = null;
                    timerTaskP1 = new MyTimerTaskP1();
                    mTimerP1 = new Timer(true);
                    int culpoint = Integer.valueOf(player1PointCul.getText().toString());
                    player1PointCul.setText(String.valueOf(culpoint + 1));
                    //タイマーの初期化処理
                    mTimerP1.schedule(timerTaskP1,2000);
                }
            }
        });

        player2minusButton = findViewById(R.id.player2_minus_button);
        player2minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int point = Integer.valueOf(player2Point.getText().toString());
                point -= 1;
                player2Point.setText(String.valueOf(point));
                if(mTimerP2 == null){
                    player2PointCul.setText("-1");
                    //タイマーの初期化処理
                    timerTaskP2 = new MyTimerTaskP2();
                    mTimerP2 = new Timer(true);
                    mTimerP2.schedule(timerTaskP2,2000);

                }else{
                    mTimerP2.cancel();
                    mTimerP2 = null;
                    timerTaskP2 = new MyTimerTaskP2();
                    mTimerP2 = new Timer(true);
                    int culpoint = Integer.valueOf(player2PointCul.getText().toString());
                    player2PointCul.setText(String.valueOf(culpoint - 1));
                    //タイマーの初期化処理
                    mTimerP2.schedule(timerTaskP2,2000);
                }
            }
        });
        player2plusButton = findViewById(R.id.player2_plus_button);
        player2plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int point = Integer.valueOf(player2Point.getText().toString());
                point += 1;
                player2Point.setText(String.valueOf(point));
                if(mTimerP2 == null){
                    player2PointCul.setText("1");
                    //タイマーの初期化処理
                    timerTaskP2 = new MyTimerTaskP2();
                    mTimerP2 = new Timer(true);
                    mTimerP2.schedule(timerTaskP2,2000);

                }else{
                    mTimerP2.cancel();
                    mTimerP2 = null;
                    timerTaskP2 = new MyTimerTaskP2();
                    mTimerP2 = new Timer(true);
                    int culpoint = Integer.valueOf(player2PointCul.getText().toString());
                    player2PointCul.setText(String.valueOf(culpoint + 1));
                    //タイマーの初期化処理
                    mTimerP2.schedule(timerTaskP2,2000);
                }
            }
        });

        Button redoButton1 = findViewById(R.id.redo_button1);
        redoButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(historyList1 == null || historyList1.size() == 0){
                }else if(historyList1.size() == 1){
                    historyList1.remove(historyList1.size() - 1);
                    listAdapter1.notifyDataSetChanged();
                    player1Point.setText("20");
                } else {
                    historyList1.remove(historyList1.size() - 1);
                    listAdapter1.notifyDataSetChanged();
                    History hist = historyList1.get(historyList1.size() - 1);
                    player1Point.setText(String.valueOf(hist.getPoint()));
                }
            }
        });
        Button redoButton2 = findViewById(R.id.redo_button2);
        redoButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(historyList2 == null || historyList2.size() == 0){
                }else if(historyList2.size() == 1){
                    historyList2.remove(historyList2.size() - 1);
                    listAdapter2.notifyDataSetChanged();
                    player2Point.setText("20");
                } else {
                    historyList2.remove(historyList2.size() - 1);
                    listAdapter2.notifyDataSetChanged();
                    History hist = historyList2.get(historyList2.size() - 1);
                    player2Point.setText(String.valueOf(hist.getPoint()));
                }
            }
        });

        resetButton = findViewById(R.id.reset_button);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //アラートダイアログ表示
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("リセット")
                        .setMessage("リセットしますか？")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                player1Point.setText("20");
                                player2Point.setText("20");
                                player1PoisonPoint.setText("0");
                                player2PoisonPoint.setText("0");
                                historyList1.clear();
                                historyList2.clear();
                                listAdapter1.notifyDataSetChanged();
                                listAdapter2.notifyDataSetChanged();
                                PoisonScreen1.setVisibility(View.GONE);
                                PoisonScreen2.setVisibility(View.GONE);

                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .show();

            }

        });
        Button player1PoisonPlusButton = findViewById(R.id.poison_plus_button1);
        player1PoisonPlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player1PoisonPoint.setText(String.valueOf(Integer.valueOf(player1PoisonPoint.getText().toString()) + 1));
            }
        });
        Button player1PoisonMinusButton = findViewById(R.id.poison_minus_button1);
        player1PoisonMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player1PoisonPoint.setText(String.valueOf(Integer.valueOf(player1PoisonPoint.getText().toString()) - 1));
            }
        });
        Button player2PoisonPlusButton = findViewById(R.id.poison_plus_button2);
        player2PoisonPlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player2PoisonPoint.setText(String.valueOf(Integer.valueOf(player2PoisonPoint.getText().toString()) + 1));
            }
        });
        Button player2PoisonMinusButton = findViewById(R.id.poison_minus_button2);
        player2PoisonMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player2PoisonPoint.setText(String.valueOf(Integer.valueOf(player2PoisonPoint.getText().toString()) - 1));
            }
        });
        Button PoisonButton1 = findViewById(R.id.poison_button1);
        PoisonButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PoisonScreen1.getVisibility() == View.GONE){
                    PoisonScreen1.setVisibility(View.VISIBLE);
                }else{
                    PoisonScreen1.setVisibility(View.GONE);
                }

            }
        });
        Button PoisonButton2 = findViewById(R.id.poison_button2);
        PoisonButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PoisonScreen2.getVisibility() == View.GONE){
                    PoisonScreen2.setVisibility(View.VISIBLE);
                }else{
                    PoisonScreen2.setVisibility(View.GONE);
                }
            }
        });



    }
    class MyTimerTaskP1 extends TimerTask {

        @Override
        public void run() {
            // mHandlerを通じてUI Threadへ処理をキューイング
            mHandler.post( new Runnable() {
                public void run() {
                    History hist = new History(
                            Integer.valueOf(player1PointCul.getText().toString()),
                            Integer.valueOf(player1Point.getText().toString())
                    );
                    historyList1.add(hist);
                    listAdapter1.notifyDataSetChanged();
                    historyListView1.setSelection(historyList1.size() - 1);
                    player1PointCul.setText("");
                    mTimerP1.cancel();
                    mTimerP1 = null;

                }
            });
        }
    }
    class MyTimerTaskP2 extends TimerTask {

        @Override
        public void run() {
            // mHandlerを通じてUI Threadへ処理をキューイング
            mHandler.post( new Runnable() {
                public void run() {
                    History hist = new History(
                            Integer.valueOf(player2PointCul.getText().toString()),
                            Integer.valueOf(player2Point.getText().toString())
                    );
                    historyList2.add(hist);
                    listAdapter2.notifyDataSetChanged();
                    historyListView2.setSelection(historyList2.size() - 1);
                    player2PointCul.setText("");
                    mTimerP2.cancel();
                    mTimerP2 = null;
                }
            });
        }
    }
    private class HistoryListAdapter1 extends BaseAdapter {
        @Override
        public int getCount() {
            return historyList1.size();
        }

        @Override
        public Object getItem(int position) {
            return historyList1.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView changeTextView;
            TextView pointTextView;
            View v = convertView;
            if(v==null){
                LayoutInflater inflater =
                        (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = inflater.inflate(R.layout.row, null);
            }
            History history = (History) getItem(position);
            if(history != null){
                changeTextView = (TextView)v.findViewById(R.id.change);
                pointTextView = (TextView)v.findViewById(R.id.point);

                changeTextView.setText(String.valueOf(history.getChange()));
                pointTextView.setText(String.valueOf(history.getPoint()));
                v.setTag(R.id.change, history);
            }
            return v;
        }
    }
    private class HistoryListAdapter2 extends BaseAdapter {
        @Override
        public int getCount() {
            return historyList2.size();
        }

        @Override
        public Object getItem(int position) {
            return historyList2.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView changeTextView;
            TextView pointTextView;
            View v = convertView;
            if(v==null){
                LayoutInflater inflater =
                        (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = inflater.inflate(R.layout.row, null);
            }
            History history = (History) getItem(position);
            if(history != null){
                changeTextView = (TextView)v.findViewById(R.id.change);
                pointTextView = (TextView)v.findViewById(R.id.point);

                changeTextView.setText(String.valueOf(history.getChange()));
                pointTextView.setText(String.valueOf(history.getPoint()));
                v.setTag(R.id.point, history);
            }
            return v;
        }
    }
}



