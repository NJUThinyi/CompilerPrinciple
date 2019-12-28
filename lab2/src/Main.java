import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    private static ArrayList<Integer> tokens;
    private static Stack stack = new Stack();
    private static LexAnalysis lexAnalysis = new LexAnalysis();
    private static ArrayList<String> output= new ArrayList<>();

    private static String[] generations={
        "S->id=E",
        "S->if(C){S}else{S}",
        "S->while(C){S}",
        "E->TE'",
        "E'->+TE'",
        "E'->ε",
        "T->FT'",
        "T->*FT'",
        "T'->ε",
        "F->num",
        "F->id",
        "C->F<F"
    };

    private static int[][] p_parsingTable={
        //   id  =   if  (   )   {   } else while +   * num   <   $
            { 0, -1,  1, -1, -1, -1, -1, -1,  2, -1, -1, -1, -1, -1},   //S
            { 3, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  3, -1, -1},   //E
            {-1, -1, -1, -1, -1, -1,  5, -1, -1,  4, -1, -1, -1,  5},   //E1
            { 6, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  6, -1, -1},   //T
            { 8, -1, -1, -1, -1, -1,  8, -1, -1,  8,  7,  8, -1,  8},   //T1
            {11, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 11, -1, -1},   //C
            {10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  9, -1, -1}    //F
    };


    static void parse(){
        int s1, s2; //s1栈中元素，s2序列中的元素

        while(tokens.get(0)!=Token.tokensMap.get("DOLLARS")){
            s1=stack.get();
            s2=tokens.get(0);

            if(s1>99){
                if(!generate(s1, s2)){
                    System.out.println("Error");
                    return;
                }
            }else{
                if(s1==s2){
                    stack.pop();
                    tokens.remove(0);
                }else{
                    System.out.println("Error");
                    return;
                }
            }
        }
    }

    static boolean generate(int stack_element, int queue_element){
        try{
            int column=getIndex(queue_element);
            int gi=p_parsingTable[stack_element-100][column];
//            System.out.println((stack_element - 100) + " "+ column);
            output.add(generations[gi]);
            stack.pop();
            switch (gi){
                case 0:
                    stack.push(Token.tokensMap.get("E"));
                    stack.push(Token.tokensMap.get("ASSIGN"));
                    stack.push(Token.tokensMap.get("ID"));
                    break;
                case 1:
                    stack.push(Token.tokensMap.get("R_BRACE"));
                    stack.push(Token.tokensMap.get("S"));
                    stack.push(Token.tokensMap.get("L_BRACE"));
                    stack.push(Token.tokensMap.get("ELSE"));
                    stack.push(Token.tokensMap.get("R_BRACE"));
                    stack.push(Token.tokensMap.get("S"));
                    stack.push(Token.tokensMap.get("L_BRACE"));
                    stack.push(Token.tokensMap.get("R_BRACKET"));
                    stack.push(Token.tokensMap.get("C"));
                    stack.push(Token.tokensMap.get("L_BRACKET"));
                    stack.push(Token.tokensMap.get("IF"));
                    break;
                case 2:
                    stack.push(Token.tokensMap.get("R_BRACE"));
                    stack.push(Token.tokensMap.get("S"));
                    stack.push(Token.tokensMap.get("L_BRACE"));
                    stack.push(Token.tokensMap.get("R_BRACKET"));
                    stack.push(Token.tokensMap.get("C"));
                    stack.push(Token.tokensMap.get("L_BRACKET"));
                    stack.push(Token.tokensMap.get("WHILE"));
                    break;
                case 3:
                    stack.push(Token.tokensMap.get("E'"));
                    stack.push(Token.tokensMap.get("T"));
                    break;
                case 4:
                    stack.push(Token.tokensMap.get("E'"));
                    stack.push(Token.tokensMap.get("T"));
                    stack.push(Token.tokensMap.get("ADD"));
                    break;
                case 5:
                    break;
                case 6:
                    stack.push(Token.tokensMap.get("T'"));
                    stack.push(Token.tokensMap.get("F"));
                    break;
                case 7:
                    stack.push(Token.tokensMap.get("T'"));
                    stack.push(Token.tokensMap.get("F"));
                    stack.push(Token.tokensMap.get("MUL"));
                    break;
                case 8:
                    break;
                case 9:
                    stack.push(Token.tokensMap.get("NUM"));
                    break;
                case 10:
                    stack.push(Token.tokensMap.get("ID"));
                    break;
                case 11:
                    stack.push(Token.tokensMap.get("F"));
                    stack.push(Token.tokensMap.get("LESS_THAN"));
                    stack.push(Token.tokensMap.get("F"));
                    break;
                default:
                    System.out.println("Error");
                    return false;
            }
            return true;
        }catch(Exception e){
            System.out.println("Error");
            return false;
        }
    }

    static int getIndex(int code){
        switch(code){
            case 2:
                return 0;
            case 4:
                return 1;
            case 10:
                return 2;
            case 6:
                return 3;
            case 7:
                return 4;
            case 8:
                return 5;
            case 9:
                return 6;
            case 11:
                return 7;
            case 12:
                return 8;
            case 13:
                return 9;
            case 14:
                return 10;
            case 5:
                return 11;
            case 16:
                return 12;
            default:
                return -1;
        }
    }

    static void output(){
        String filePath="./output.txt";
        try {
            File outputFile = new File(filePath);
            if (!outputFile.exists())
                outputFile.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile, false));
            for(String s:output){
                System.out.println(s);
                bw.write(s);
                bw.newLine();
            }
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        tokens=lexAnalysis.getTokens();
        tokens.add(Token.tokensMap.get("DOLLARS"));
        stack.push(Token.tokensMap.get("S"));
        parse();
        output();
    }
}
