package com.administrator.calculator;
import android.app.AlertDialog;
import android.util.Log;
import java.util.Stack;
import java.util.regex.Pattern;



//将中缀表达式字符串转换为后缀表达式
public class StringToArithmetic {
    public StringToArithmetic() {

    }

    //将中缀表达式字符串计算得到结果
    public static String StringToArithmetic(String string) {
        return suffixToArithmetic(infixToSuffix(string));
    }

    static  Stack<String> str_num= new Stack<String>(); //操作数栈,初始化


    // 将中缀表达式转换为后缀表达式
    public static String infixToSuffix(String exp) {
        // 创建操作符堆栈
        Stack<Character> s = new Stack<Character>();
        s.push('#');
        // 要输出的后缀表达式字符串
        String suffix = "";
        int length = exp.length(); // 输入的中缀表达式的长度
        for (int i = 0; i < length&&exp!=null; i++) {
            char temp;// 临时字符变量
            double sinx;
            double cosx;
            double tanx;
            String num="";
            // 获取该中缀表达式的每一个字符并进行判断
            char ch = exp.charAt(i);
            switch (ch) {
                case 's':
                    if(exp.charAt(i+1)=='i'&&exp.charAt(i+2)=='n'){
                        for(int x=i+3;(exp.charAt(x)>='0'&&exp.charAt(x)<='9')&&(x<length);x++){
                            num+=exp.charAt(x);
                        }
                        sinx=Math.sin(Double.parseDouble(num));
                        suffix+=String.valueOf(sinx)+'1';
                    }
//                    if((exp.charAt(i-2)=='c'&&exp.charAt(i-1)=='o')&&i>=2){
//                        continue;
//                    }
                case 'i':
                case 'n':
                    continue;
                case 'c':
                    if(exp.charAt(i+1)=='o'&&exp.charAt(i+2)=='s'){
                        for(int x=i+3;(exp.charAt(x)>='0'&&exp.charAt(x)<='9')&&(x<length);x++){
                            num+=exp.charAt(x);
                        }
                        cosx=Math.cos(Double.parseDouble(num.toString()));
                        suffix+=String.valueOf(cosx)+'1';
                    }
                case 'o':continue;
                case 't':
                    if(exp.charAt(i+1)=='a'&&exp.charAt(i+2)=='n'){
                        for(int x=i+3;(exp.charAt(x)>='0'&&exp.charAt(x)<='9')&&(x<length);x++){
                            num+=exp.charAt(x);
                        }
                        tanx=Math.tan(Double.parseDouble(num.toString()));
                        suffix+=String.valueOf(tanx)+'1';
                    }

                case '(':
                case '+':
                case '-':
                case '×':
                case '÷':
                    for (temp = s.pop(); icp(ch) <= isp(temp);temp=s.pop()) {
                        suffix+=temp;
                    }
                    s.push(temp);
                    s.push(ch);
                    break;
                // 如果碰到的是右括号，则距离栈顶的第一个左括号上面的所有运算符弹出栈并抛弃左括号
                case ')':
                    for(temp=s.pop();temp!='(';temp=s.pop()){
                        suffix+=temp;
                    }
                    break;
                // 默认情况，如果读取到的是数字，则直接送至输出序列
                default:
                    if(i==length-1){
                        suffix+=ch+" ";
                    }
                    else if((exp.charAt(i+1)=='+'||exp.charAt(i+1)=='-'||exp.charAt(i+1)=='×'||exp.charAt(i+1)=='÷'||exp.charAt(i+1)==')'))
                        suffix+=ch+" ";
                        else
                            suffix+=ch;


                    break;
            }

        }
        // 如果堆栈不为空，则把剩余运算符一次弹出，送至输出序列
        while (s.peek()!='#') {
            suffix += s.pop();
        }
        Log.d("suffix",suffix.toString());
        return suffix;
    }

    public static String suffixToArithmetic(String exp) {
        String num="";
        for (int i = 0; i < exp.length(); i++) {
            // 获取该中缀表达式的每一个字符并进行判断
            char ch= exp.charAt(i);
            // 如果遇到了数字则直接进栈
            if((ch>='0'&&ch<='9')) {
                num+=ch;
            }
            else if(ch==' '){
                str_num.push(num.toString());
                num="";
            }
            // 如果是运算符，则弹出栈顶的两个数进行计算
            else if(isOperator(ch)) {
                double d2 = Double.parseDouble(str_num.pop().toString());
                double d1 = Double.parseDouble(str_num.pop().toString());
                double result=0;
                switch (ch){
                    case '+':
                        result=d1+d2;
                        break;
                    case '-':
                        result=d1-d2;
                        break;
                    case '×':
                        result=d1*d2;
                        break;
                    case '÷':
                        if (d2 == 0){
                            Log.d("warning","警告：分母不能为0！");
                        }
                        result=d1/d2;

                        break;
                    default:
                        break;
                }
                String value =String.valueOf(result);
                if (value.indexOf(".")>0){
                    value = value.replaceAll("0+?$", "");//去掉多余的0
                    value = value.replaceAll("[.]$", "");//如最后一位是.则去掉
                }
                str_num.push(value);  //操作后的结果入栈
            }
            else {
                Character c4 = (Character) ch;
                num += c4;
                // st.push(String.valueOf(c)); //数字入栈

            }
        }
        return str_num.pop();
    }


//判断栈外优先级的函数
    private static int icp(char cout) {
        switch (cout) {
            case '#':
                return 0;
            case '(':
                return 7;
            case '×':
            case '÷':
                return 4;
            case '+':
            case '-':
                return 2;
            case ')':
                return 1;
            default:
                return 0;
        }
    }

    //判断栈内优先级的函数
    private static int isp(char cin) {
        switch (cin) {
            case '#':
                return 0;
            case '(':
                return 1;
            case '×':
            case '÷':
                return 5;
            case '+':
            case '-':
                return 3;
            case ')':
                return 7;
            default:
                return 0;
        }
    }

    //判断是否为操作符的函数
    private static boolean isOperator(char c){
        if ('+' ==c||'-' == c||'÷'==c||'×'==c ){
            return true;
        }
        return false;
    }
}
