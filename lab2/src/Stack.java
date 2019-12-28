import java.util.ArrayList;

public class Stack {

    private ArrayList<Integer> stack;

    public Stack(){
        stack = new ArrayList<Integer>();
        stack.add(Token.tokensMap.get("DOLLARS"));
    }

    public void push(int t){
        stack.add(t);
    }

    public void pop(){
        stack.remove(stack.size() - 1);
    }

    public int get(){
        return stack.get(stack.size() - 1);
    }

}

