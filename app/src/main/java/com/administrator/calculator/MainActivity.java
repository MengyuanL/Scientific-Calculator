package com.administrator.calculator;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.administrator.calculator.StringToArithmetic;

import java.text.NumberFormat;
import java.util.Stack;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_sin;     //sin运算按钮
    Button btn_cos;     //cos运算按钮
    Button btn_tan;     //tan运算按钮
    Button btn_img;     //显示图像按钮
    Button btn_left;    //左括号按钮
    Button btn_right;   //右括号按钮
    Button btn_0;       //0数字按钮
    Button btn_1;       //1数字按钮
    Button btn_2;       //2数字按钮
    Button btn_3;       //3数字按钮
    Button btn_4;       //4数字按钮
    Button btn_5;       //5数字按钮
    Button btn_6;       //6数字按钮
    Button btn_7;       //7数字按钮
    Button btn_8;       //8数字按钮
    Button btn_9;       //9数字按钮
    Button btn_point;
    Button btn_clear;   //清屏按钮
    Button btn_del;     //删除按钮
    Button btn_equal;   //等于按钮
    Button btn_plus;    //加号按钮
    Button btn_minus;   //减号按钮
    Button btn_mul;     //乘号按钮
    Button btn_div;     //除号按钮
    EditText et_input;  //显示屏
    boolean needclear;  //判断是否为空
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //实例化按钮
        btn_sin=(Button)findViewById(R.id.btn_sin);
        btn_cos=(Button)findViewById(R.id.btn_cos);
        btn_tan=(Button)findViewById(R.id.btn_tan);
        btn_img=(Button)findViewById(R.id.btn_graph);
        btn_left=(Button)findViewById(R.id.btn_left);
        btn_right=(Button)findViewById(R.id.btn_right);
        btn_0 = (Button) findViewById(R.id.btn_0);
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_5 = (Button) findViewById(R.id.btn_5);
        btn_6 = (Button) findViewById(R.id.btn_6);
        btn_7 = (Button) findViewById(R.id.btn_7);
        btn_8 = (Button) findViewById(R.id.btn_8);
        btn_9 = (Button) findViewById(R.id.btn_9);
        btn_point=(Button)findViewById(R.id.btn_point);
        btn_clear = (Button) findViewById(R.id.btn_clear);
        btn_del = (Button) findViewById(R.id.btn_del);
        btn_equal = (Button) findViewById(R.id.btn_equal);
        btn_plus = (Button) findViewById(R.id.btn_plus);
        btn_minus = (Button) findViewById(R.id.btn_minus);
        btn_mul = (Button) findViewById(R.id.btn_mul);
        btn_div = (Button) findViewById(R.id.btn_div);
        et_input = (EditText) findViewById(R.id.et_showview);
        //设置按钮点击事件
        btn_sin.setOnClickListener(this);
        btn_cos.setOnClickListener(this);
        btn_tan.setOnClickListener(this);
        btn_img.setOnClickListener(this);
        btn_left.setOnClickListener(this);
        btn_right.setOnClickListener(this);
        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_point.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        btn_equal.setOnClickListener(this);
        btn_plus.setOnClickListener(this);
        btn_minus.setOnClickListener(this);
        btn_mul.setOnClickListener(this);
        btn_div.setOnClickListener(this);
    }

    //重写点击事件方法
    @Override
    public void onClick(View v) {
        String str=et_input.getText().toString();
        switch (v.getId()){
            case R.id.btn_graph:
                Intent intent=new Intent(MainActivity.this,GraphActivity.class);
                startActivity(intent);
            case   R.id.btn_sin:
            case   R.id.btn_cos:
            case   R.id.btn_tan:
            case   R.id.btn_left:
            case   R.id.btn_right:
            case   R.id.btn_0:
            case   R.id.btn_1:
            case   R.id.btn_2:
            case   R.id.btn_3:
            case   R.id.btn_4:
            case   R.id.btn_5:
            case   R.id.btn_6:
            case   R.id.btn_7:
            case   R.id.btn_8:
            case   R.id.btn_9:
            case R.id.btn_point:
            case R.id.btn_plus:
            case R.id.btn_minus:
            case R.id.btn_mul:
            case R.id.btn_div:
                if(needclear){
                    needclear=false;
                    str="";
                    et_input.setText("");
                }
                et_input.setText(str+((Button)v).getText());
                break;

            case R.id.btn_clear:
                if(needclear)
                    needclear=false;
                str="";
                et_input.setText("");
                break;
            case R.id.btn_del: //判断是否为空，然后在进行删除
                if(needclear){
                    needclear=false;
                    str="";
                    et_input.setText("");
                }
                else if(str!=null&&!str.equals("")){
                    et_input.setText(str.substring(0,str.length()-1));
                }
                break;
            case R.id.btn_equal: //单独运算最后结果
                et_input.setText(String.valueOf(StringToArithmetic.StringToArithmetic(str)));
                break;
        }
    }
}

