import java.util.Scanner;

public class LexAnalysis {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        boolean is_error=false;
        do{
            String s=sc.next();
            int len=s.length();
            int state = 0;
            int index=0;
            while(index<len) {
                boolean isEnd=false;
                String sub_s="";
                for (int i = index; i <= len; i++) {
                    char ch = ' ';
                    if (i < len) {
                        ch = s.charAt(i);
                    }
                    switch (state) {
                        case 0:
                            if (ch == 'v') {
                                state = 1;
                                sub_s+=ch;
                                break;
                            } else if (ch == 'm') {
                                state = 6;
                                sub_s+=ch;
                                break;
                            } else if (ch == '(') {
                                state = 10;
                                sub_s+=ch;
                                break;
                            } else if (ch == ')') {
                                state = 11;
                                sub_s+=ch;
                                break;
                            } else if (ch == '{') {
                                state = 12;
                                sub_s+=ch;
                                break;
                            } else if (ch == '}') {
                                state = 13;
                                sub_s+=ch;
                                break;
                            } else if (ch == 'a' || ch == 'b') {
                                state = 17;
                                sub_s+=ch;
                                break;
                            } else if (ch == '=') {
                                state = 18;
                                sub_s+=ch;
                                break;
                            } else if (ch == '0' || ch == '1') {
                                state = 19;
                                sub_s+=ch;
                                break;
                            } else if (ch == 'i') {
                                state = 14;
                                sub_s+=ch;
                                break;
                            } else if(ch=='e'){
                                state=21;
                                sub_s+=ch;
                                break;
                            }else if(ch=='w'){
                                state=25;
                                sub_s+=ch;
                                break;
                            }else if(ch=='<'){
                                state=30;
                                sub_s+=ch;
                                break;
                            }else if(ch=='+'){
                                state=31;
                                sub_s+=ch;
                                break;
                            }else if(ch=='*'){
                                state=32;
                                sub_s+=ch;
                                break;
                            }
                            else{
                                System.out.println("Invalid Input!");
                            }
                        case 1:
                            if (ch == 'o') {
                                state = 2;
                                sub_s+=ch;
                                break;
                            } else {
                                System.out.println("Invalid Input!");
                                break;
                            }
                        case 2:
                            if (ch == 'i') {
                                state = 4;
                                sub_s+=ch;
                            } else {
                                System.out.println("Invalid Input!");
                            }
                            break;
                        case 3:
                            //
                        case 4:
                            if (ch == 'd') {
                                state = 5;
                                sub_s+=ch;
                            } else {
                                System.out.println("Invalid Input!");
                            }
                            break;
                        case 5:
                            System.out.println("<VOID," + sub_s + ">");
                            index=i++;
                            isEnd=true;
                            state = 0;
                            break;
                        case 6:
                            if (ch == 'a') {
                                state = 7;
                                sub_s+=ch;
                            } else {
                                System.out.println("Invalid Input!");
                            }
                            break;
                        case 7:
                            if (ch == 'i') {
                                state = 8;
                                sub_s+=ch;
                            } else {
                                System.out.println("Invalid Input!");
                            }
                            break;
                        case 8:
                            if (ch == 'n') {
                                state = 9;
                                sub_s+=ch;
                            } else {
                                System.out.println("Invalid Input!");
                            }
                            break;
                        case 9:
                            System.out.println("<MAIN," + sub_s + ">");
                            state = 0;
                            index=i++;
                            isEnd=true;
                            break;
                        case 10:
                            System.out.println("<L_BRACKET," + sub_s + ">");
                            state = 0;
                            index=i++;
                            isEnd=true;
                            break;
                        case 11:
                            System.out.println("<R_BRACKET," + sub_s + ">");
                            state = 0;
                            index=i++;
                            isEnd=true;
                            break;
                        case 12:
                            System.out.println("<L_BRACE," + sub_s + ">");
                            state = 0;
                            index=i++;
                            isEnd=true;
                            break;
                        case 13:
                            System.out.println("<R_BRACE," + sub_s + ">");
                            state = 0;
                            index=i++;
                            isEnd=true;
                            break;
                        case 14:
                            if (ch == 'n') {
                                state = 15;
                                sub_s+=ch;
                            } else if(ch=='f'){
                                state= 20;
                                sub_s+=ch;
                            } else{
                                System.out.println("Invalid Input!");
                            }
                            break;
                        case 15:
                            if (ch == 't') {
                                state = 16;
                                sub_s+=ch;
                            } else {
                                System.out.println("Invalid Input!");
                            }
                            break;
                        case 16:
                            System.out.println("<INT," + sub_s + ">");
                            state = 0;
                            index=i++;
                            isEnd=true;
                            break;
                        case 17:
                            if (i == len) {
                                System.out.println("<ID," + sub_s + ">");
                                state = 0;
                                index=i++;
                                isEnd=true;
                            }else{
                                if(ch=='a'||ch=='b'){
                                    state=17;
                                    sub_s+=ch;
                                }else{
                                   System.out.println("Invalid Input!");
                                    isEnd=true;
                                    is_error=true;
                                }
                            }
                            break;
                        case 18:
                            System.out.println("<ASSIGN," + sub_s + ">");
                            state = 0;
                            index=i++;
                            isEnd=true;
                            break;
                        case 19:
                            if (i == len) {
                                System.out.println("<NUM," + sub_s + ">");
                                state = 0;
                                index=i++;
                                isEnd=true;
                            }else{
                                if(ch=='0'||ch=='1') {
                                    state = 19;
                                    sub_s += ch;
                                }else{
                                    System.out.println("Invalid Input!");
                                    isEnd=true;
                                    is_error=true;
                                }
                            }
                            break;
                        case 20:    //if-f
                            System.out.println("<IF, "+sub_s+">");
                            state=0;
                            index=i++;
                            isEnd=true;
                            break;
                        case 21:
                            if(ch=='l'){
                                state=22;
                                sub_s+=ch;
                            }else {
                                System.out.println("Invalid Input!");
                            }
                            break;
                        case 22:
                            if(ch=='s'){
                                state=23;
                                sub_s+=ch;
                            }else {
                                System.out.println("Invalid Input!");
                            }
                            break;
                        case 23:
                            if(ch=='e'){
                                state=24;
                                sub_s+=ch;
                            }else {
                                System.out.println("Invalid Input!");
                            }
                            break;
                        case 24:
                            System.out.println("<ELSE, "+sub_s+">");
                            state=0;
                            index=i++;
                            isEnd=true;
                            break;
                        case 25:
                            if(ch=='h'){
                                state=26;
                                sub_s+=ch;
                            }else {
                                System.out.println("Invalid Input!");
                            }
                            break;
                        case 26:
                            if(ch=='i'){
                                state=27;
                                sub_s+=ch;
                            }else {
                                System.out.println("Invalid Input!");
                            }
                            break;
                        case 27:
                            if(ch=='l'){
                                state=28;
                                sub_s+=ch;
                            }else {
                                System.out.println("Invalid Input!");
                            }
                            break;
                        case 28:
                            if(ch=='e'){
                                state=29;
                                sub_s+=ch;
                            }else {
                                System.out.println("Invalid Input!");
                            }
                            break;
                        case 29:
                            System.out.println("<WHILE, "+sub_s+">");
                            state=0;
                            index=i++;
                            isEnd=true;
                            break;
                        case 30:
                            System.out.println("<LESS_THAN, "+sub_s+">");
                            state=0;
                            index=i++;
                            isEnd=true;
                            break;
                        case 31:
                            System.out.println("<ADD, "+sub_s+">");
                            state=0;
                            index=i++;
                            isEnd=true;
                            break;
                        case 32:
                            System.out.println("<MUL, "+sub_s+">");
                            state=0;
                            index=i++;
                            isEnd=true;
                            break;
                        default:
                            state = 0;
                            break;
                    }
                    if(isEnd){
                        sub_s="";
                        break;
                    }
                }
                if(is_error){
                    break;
                }
            }
            if(is_error){
                break;
            }
        }while(sc.hasNext());
        System.out.println("Over!");
    }
}
