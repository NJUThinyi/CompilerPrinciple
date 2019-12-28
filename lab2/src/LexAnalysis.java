import java.io.*;
import java.util.ArrayList;

public class LexAnalysis {
    private ArrayList<Character> input=new ArrayList<>();
    private ArrayList<Integer> tokens=new ArrayList<>();
    private ArrayList<String> strs=new ArrayList<>();
    private Token token=new Token();

    ArrayList<Integer> getTokens(){
        getInput();
        input2Tokens();
        return tokens;
    }

    void getInput(){
        String fileName="./input.txt";
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileName))));
            String line = null;
            char[] tmp = null;
            while ((line = br.readLine()) != null) {
                tmp = line.toCharArray();
                for (int i = 0; i < tmp.length; i++) {
                    if(tmp[i]==' '||tmp[i]=='\t'){
                        input.add('%');
                        continue;
                    }
                    input.add(tmp[i]);
                }
            }
            br.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    void input2Tokens(){
        int state = 0;
        boolean is_error = false;
        String sub_s = "";
        boolean isEnd = false;
        for(int i=0;i<=input.size();i++) {
            char ch = '%';
            if(i<input.size()) {
                ch = input.get(i);
            }
            switch (state) {
                case 0:
                    if (ch == 'v') {
                        state = 1;
                        sub_s += ch;
                        break;
                    } else if (ch == 'm') {
                        state = 6;
                        sub_s += ch;
                        break;
                    } else if (ch == '(') {
                        state = 10;
                        sub_s += ch;
                        break;
                    } else if (ch == ')') {
                        state = 11;
                        sub_s += ch;
                        break;
                    } else if (ch == '{') {
                        state = 12;
                        sub_s += ch;
                        break;
                    } else if (ch == '}') {
                        state = 13;
                        sub_s += ch;
                        break;
                    } else if (ch == 'a' || ch == 'b') {
                        state = 17;
                        sub_s += ch;
                        break;
                    } else if (ch == '=') {
                        state = 18;
                        sub_s += ch;
                        break;
                    } else if (ch == '0' || ch == '1') {
                        state = 19;
                        sub_s += ch;
                        break;
                    } else if (ch == 'i') {
                        state = 14;
                        sub_s += ch;
                        break;
                    } else if (ch == 'e') {
                        state = 21;
                        sub_s += ch;
                        break;
                    } else if (ch == 'w') {
                        state = 25;
                        sub_s += ch;
                        break;
                    } else if (ch == '<') {
                        state = 30;
                        sub_s += ch;
                        break;
                    } else if (ch == '+') {
                        state = 31;
                        sub_s += ch;
                        break;
                    } else if (ch == '*') {
                        state = 32;
                        sub_s += ch;
                        break;
                    }else if(ch == '%'){
                        break;
                    }
                    else {
                        System.out.println("Invalid Input!");
                        is_error=true;
                        break;
                    }
                case 1:
                    if (ch == 'o') {
                        state = 2;
                        sub_s += ch;
                        break;
                    } else {
                        System.out.println("Invalid Input!");
                        is_error=true;
                        break;
                    }
                case 2:
                    if (ch == 'i') {
                        state = 4;
                        sub_s += ch;
                    } else {
                        System.out.println("Invalid Input!");
                        is_error=true;
                    }
                    break;
                case 3:
                    //
                case 4:
                    if (ch == 'd') {
                        state = 5;
                        sub_s += ch;
                    } else {
                        System.out.println("Invalid Input!");
                        is_error=true;
                    }
                    break;
                case 5:
                    tokens.add(token.tokensMap.get("VOID"));
                    strs.add(sub_s);
                    System.out.println("<VOID," + sub_s + ">");
                    isEnd = true;
                    state = 0;
                    i--;
                    break;
                case 6:
                    if (ch == 'a') {
                        state = 7;
                        sub_s += ch;
                    } else {
                        System.out.println("Invalid Input!");
                        is_error=true;
                    }
                    break;
                case 7:
                    if (ch == 'i') {
                        state = 8;
                        sub_s += ch;
                    } else {
                        System.out.println("Invalid Input!");
                        is_error=true;
                    }
                    break;
                case 8:
                    if (ch == 'n') {
                        state = 9;
                        sub_s += ch;
                    } else {
                        System.out.println("Invalid Input!");
                        is_error=true;
                    }
                    break;
                case 9:
                    tokens.add(token.tokensMap.get("MAIN"));
                    strs.add(sub_s);
                    System.out.println("<MAIN," + sub_s + ">");
                    state = 0;
                    i--;
                    isEnd = true;
                    break;
                case 10:
                    tokens.add(token.tokensMap.get("L_BRACKET"));
                    strs.add(sub_s);
                    System.out.println("<L_BRACKET," + sub_s + ">");
                    state = 0;
                    i--;
                    isEnd = true;
                    break;
                case 11:
                    tokens.add(token.tokensMap.get("R_BRACKET"));
                    strs.add(sub_s);
                    System.out.println("<R_BRACKET," + sub_s + ">");
                    state = 0;
                    i--;
                    isEnd = true;
                    break;
                case 12:
                    tokens.add(token.tokensMap.get("L_BRACE"));
                    strs.add(sub_s);
                    System.out.println("<L_BRACE," + sub_s + ">");
                    state = 0;
                    i--;
                    isEnd = true;
                    break;
                case 13:
                    tokens.add(token.tokensMap.get("R_BRACE"));
                    strs.add(sub_s);
                    System.out.println("<R_BRACE," + sub_s + ">");
                    state = 0;
                    i--;
                    isEnd = true;
                    break;
                case 14:
                    if (ch == 'n') {
                        state = 15;
                        sub_s += ch;
                    } else if (ch == 'f') {
                        state = 20;
                        sub_s += ch;
                    } else {
                        System.out.println("Invalid Input!");
                        is_error=true;
                    }
                    break;
                case 15:
                    if (ch == 't') {
                        state = 16;
                        sub_s += ch;
                    } else {
                        System.out.println("Invalid Input!");
                        is_error=true;
                    }
                    break;
                case 16:
                    tokens.add(token.tokensMap.get("INT"));
                    strs.add(sub_s);
                    System.out.println("<INT," + sub_s + ">");
                    state = 0;
                    i--;
                    isEnd = true;
                    break;
                case 17:
                    if (ch=='%') {
                        tokens.add(token.tokensMap.get("ID"));
                        strs.add(sub_s);
                        System.out.println("<ID," + sub_s + ">");
                        state = 0;
                        i--;
                        isEnd = true;
                    } else {
                        if (ch == 'a' || ch == 'b') {
                            state = 17;
                            sub_s += ch;
                        } else {
                            System.out.println("Invalid Input!");
                            isEnd = true;
                            is_error = true;
                        }
                    }
                    break;
                case 18:
                    tokens.add(token.tokensMap.get("ASSIGN"));
                    strs.add(sub_s);
                    System.out.println("<ASSIGN," + sub_s + ">");
                    state = 0;
                    i--;
                    isEnd = true;
                    break;
                case 19:
                    if (ch == '%') {
                        tokens.add(token.tokensMap.get("NUM"));
                        strs.add(sub_s);
                        System.out.println("<NUM," + sub_s + ">");
                        state = 0;
                        i--;
                        isEnd = true;
                    } else {
                        if (ch == '0' || ch == '1') {
                            state = 19;
                            sub_s += ch;
                        } else {
                            System.out.println("Invalid Input!");
                            isEnd = true;
                            is_error = true;
                        }
                    }
                    break;
                case 20:    //if-f
                    tokens.add(token.tokensMap.get("IF"));
                    strs.add(sub_s);
                    System.out.println("<IF, " + sub_s + ">");
                    state = 0;
                    i--;
                    isEnd = true;
                    break;
                case 21:
                    if (ch == 'l') {
                        state = 22;
                        sub_s += ch;
                    } else {
                        System.out.println("Invalid Input!");
                        is_error=true;
                    }
                    break;
                case 22:
                    if (ch == 's') {
                        state = 23;
                        sub_s += ch;
                    } else {
                        System.out.println("Invalid Input!");
                        is_error=true;
                    }
                    break;
                case 23:
                    if (ch == 'e') {
                        state = 24;
                        sub_s += ch;
                    } else {
                        System.out.println("Invalid Input!");
                        is_error=true;
                    }
                    break;
                case 24:
                    tokens.add(token.tokensMap.get("ELSE"));
                    strs.add(sub_s);
                    System.out.println("<ELSE, " + sub_s + ">");
                    state = 0;
                    i--;
                    isEnd = true;
                    break;
                case 25:
                    if (ch == 'h') {
                        state = 26;
                        sub_s += ch;
                    } else {
                        System.out.println("Invalid Input!");
                        is_error=true;
                    }
                    break;
                case 26:
                    if (ch == 'i') {
                        state = 27;
                        sub_s += ch;
                    } else {
                        System.out.println("Invalid Input!");
                        is_error=true;
                    }
                    break;
                case 27:
                    if (ch == 'l') {
                        state = 28;
                        sub_s += ch;
                    } else {
                        System.out.println("Invalid Input!");
                        is_error=true;
                    }
                    break;
                case 28:
                    if (ch == 'e') {
                        state = 29;
                        sub_s += ch;
                    } else {
                        System.out.println("Invalid Input!");
                        is_error=true;
                    }
                    break;
                case 29:
                    tokens.add(token.tokensMap.get("WHILE"));
                    strs.add(sub_s);
                    System.out.println("<WHILE, " + sub_s + ">");
                    state = 0;
                    i--;
                    isEnd = true;
                    break;
                case 30:
                    tokens.add(token.tokensMap.get("LESS_THAN"));
                    strs.add(sub_s);
                    System.out.println("<LESS_THAN, " + sub_s + ">");
                    state = 0;
                    i--;
                    isEnd = true;
                    break;
                case 31:
                    tokens.add(token.tokensMap.get("ADD"));
                    strs.add(sub_s);
                    System.out.println("<ADD, " + sub_s + ">");
                    state = 0;
                    i--;
                    isEnd = true;
                    break;
                case 32:
                    tokens.add(token.tokensMap.get("MUL"));
                    strs.add(sub_s);
                    System.out.println("<MUL, " + sub_s + ">");
                    state = 0;
                    i--;
                    isEnd = true;
                    break;
                default:
                    state = 0;
                    break;
            }
            if (isEnd) {
                sub_s = "";
                isEnd=false;
            }
            if(is_error){
                break;
            }
        }
    }
}
