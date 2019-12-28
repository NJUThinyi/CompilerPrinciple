import java.util.HashMap;
import java.util.Map;

public class Token {
    public static Map<String, Integer> tokensMap=new HashMap<>();
    public Token(){
        tokensMap.put("VOID", 0);
        tokensMap.put("MAIN", 1);
        tokensMap.put("ID", 2);
        tokensMap.put("INT", 3);
        tokensMap.put("ASSIGN", 4);
        tokensMap.put("NUM", 5);
        tokensMap.put("L_BRACKET", 6);
        tokensMap.put("R_BRACKET", 7);
        tokensMap.put("L_BRACE", 8);
        tokensMap.put("R_BRACE", 9);
        tokensMap.put("IF", 10);
        tokensMap.put("ELSE", 11);
        tokensMap.put("WHILE", 12);
        tokensMap.put("ADD", 13);
        tokensMap.put("MUL", 14);
        tokensMap.put("LESS_THAN", 15);
        tokensMap.put("DOLLARS", 16);

        tokensMap.put("S", 100);
        tokensMap.put("E", 101);
        tokensMap.put("E'", 102);
        tokensMap.put("T", 103);
        tokensMap.put("T'", 104);
        tokensMap.put("C", 105);
        tokensMap.put("F", 106);
    }
}
