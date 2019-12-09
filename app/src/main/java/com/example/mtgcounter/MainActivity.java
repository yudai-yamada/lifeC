package com.example.mtgcounter;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {

    private TextView player1Point;
    private TextView player1PointListText1;
    private TextView player1PointListText2;
    private TextView player1PointListText3;
    private TextView player1PointListText4;
    private TextView player1PointListText5;
    private TextView player1PointCul;
    private Button player1minusButton;
    private Button player1plusButton;
    private String player1PointList1 = "";
    private String player1PointList2 = "";
    private String player1PointList3 = "";
    private String player1PointList4 = "";
    private String player1PointList5 = "";

    private TextView player2Point;
    private TextView player2PointListText1;
    private TextView player2PointListText2;
    private TextView player2PointListText3;
    private TextView player2PointListText4;
    private TextView player2PointListText5;
    private TextView player2PointCul;
    private Button player2minusButton;
    private Button player2plusButton;
    private String player2PointList1 = "";
    private String player2PointList2 = "";
    private String player2PointList3 = "";
    private String player2PointList4 = "";
    private String player2PointList5 = "";

    private Button player1DrainButton;
    private Button player2DrainButton;

    private int player1ListCount = 0;
    private int player2ListCount = 0;


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

        player1Point = findViewById(R.id.player1_point);
        player2Point = findViewById(R.id.player2_point);

        player1PointCul = findViewById(R.id.player1_cul_point);
        player2PointCul = findViewById(R.id.player2_cul_point);

        player1PointListText1 = findViewById(R.id.player1_List_text1);
        player1PointListText2 = findViewById(R.id.player1_List_text2);
        player1PointListText3 = findViewById(R.id.player1_List_text3);
        player1PointListText4 = findViewById(R.id.player1_List_text4);
        player1PointListText5 = findViewById(R.id.player1_List_text5);

        player2PointListText1 = findViewById(R.id.player2_List_text1);
        player2PointListText2 = findViewById(R.id.player2_List_text2);
        player2PointListText3 = findViewById(R.id.player2_List_text3);
        player2PointListText4 = findViewById(R.id.player2_List_text4);
        player2PointListText5 = findViewById(R.id.player2_List_text5);

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

                                player1PointListText1.setText("");
                                player1PointListText2.setText("");
                                player1PointListText3.setText("");
                                player1PointListText4.setText("");
                                player1PointListText5.setText("");
                                player1PointList1 = "";
                                player1PointList2 = "";
                                player1PointList3 = "";
                                player1PointList4 = "";
                                player1PointList5 = "";
                                player1ListCount = 0;

                                player2PointListText1.setText("");
                                player2PointListText2.setText("");
                                player2PointListText3.setText("");
                                player2PointListText4.setText("");
                                player2PointListText5.setText("");
                                player2PointList1 = "";
                                player2PointList2 = "";
                                player2PointList3 = "";
                                player2PointList4 = "";
                                player2PointList5 = "";
                                player2ListCount = 0;
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .show();

            }

        });



    }
    class MyTimerTaskP1 extends TimerTask {

        @Override
        public void run() {
            // mHandlerを通じてUI Threadへ処理をキューイング
            mHandler.post( new Runnable() {
                public void run() {
                    if(player1ListCount < 10){
                        player1PointList1 = player1PointList1 + "\n" + player1Point.getText().toString();
                        player1PointListText1.setText(player1PointList1);
                        player1ListCount++;
                    }else if (player1ListCount < 20){
                        player1PointList2 = player1PointList2 + "\n" + player1Point.getText().toString();
                        player1PointListText2.setText(player1PointList2);
                        player1ListCount++;
                    }else if (player1ListCount < 30){
                        player1PointList3 = player1PointList3 + "\n" + player1Point.getText().toString();
                        player1PointListText3.setText(player1PointList3);
                        player1ListCount++;
                    }else if (player1ListCount < 40){
                        player1PointList4 = player1PointList4 + "\n" + player1Point.getText().toString();
                        player1PointListText4.setText(player1PointList4);
                        player1ListCount++;
                    }else if (player1ListCount < 50){
                        player1PointList5 = player1PointList5 + "\n" + player1Point.getText().toString();
                        player1PointListText5.setText(player1PointList5);
                        player1ListCount++;
                    }else {
                        player1ListCount = 0;
                        player1PointList1="";
                        player1PointList2="";
                        player1PointList3="";
                        player1PointList4="";
                        player1PointList5="";
                        player1PointList1 = player1PointList1 + "\n" + player1Point.getText().toString();
                        player1PointListText1.setText(player1PointList1);
                        player1ListCount++;
                    }

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
                    if(player2ListCount < 10){
                        player2PointList1 = player2PointList1 + "\n" + player2Point.getText().toString();
                        player2PointListText1.setText(player2PointList1);
                        player2ListCount++;
                    }else if (player2ListCount < 20){
                        player2PointList2 = player2PointList2 + "\n" + player2Point.getText().toString();
                        player2PointListText2.setText(player2PointList2);
                        player2ListCount++;
                    }else if (player2ListCount < 30){
                        player2PointList3 = player2PointList3 + "\n" + player2Point.getText().toString();
                        player2PointListText3.setText(player2PointList3);
                        player2ListCount++;
                    }else if (player2ListCount < 40){
                        player2PointList4 = player2PointList4 + "\n" + player2Point.getText().toString();
                        player2PointListText4.setText(player2PointList4);
                        player2ListCount++;
                    }else if (player2ListCount < 50){
                        player2PointList5 = player2PointList5 + "\n" + player2Point.getText().toString();
                        player2PointListText5.setText(player2PointList5);
                        player2ListCount++;
                    }else {
                        player2ListCount = 0;
                        player2PointList1="";
                        player2PointList2="";
                        player2PointList3="";
                        player2PointList4="";
                        player2PointList5="";
                        player2PointList1 = player2PointList1 + "\n" + player2Point.getText().toString();
                        player2PointListText1.setText(player2PointList1);
                        player2ListCount++;
                    }

                    player2PointCul.setText("");
                    mTimerP2.cancel();
                    mTimerP2 = null;

                }
            });
        }
    }
}



