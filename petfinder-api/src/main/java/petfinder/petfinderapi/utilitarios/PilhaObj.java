package petfinder.petfinderapi.utilitarios;

public class PilhaObj<T>{
    
    // attributes
    private T[] items;
    private int top;

    // constructor
    public PilhaObj(int length) {
        this.items = (T[]) new Object[length];
        this.top = -1;
    }
    public PilhaObj() {
        this(10);
    }

    // methods

    // show all
    public void showAllValues() {
        for (int i = 0; i <= this.top; i++) {
            System.out.println(items[i]);
        }
    }

    // push
    public void push(T item) {
        if(isNotFull()) {
            items[++top] = item;
        } else {   
            throw new RuntimeException("Pilha cheia!");
        }
    }

    // removendo último elemento
    public T pop() {
        if(isNotEmpty()) {
            return items[top--];
        } 
        
        throw new RuntimeException("Pilha vazia");
    }

    public T peek() {
        if (isNotEmpty()) {
            return items[top];
        }

        throw new IllegalStateException();
    }

    public String toString() {

        String accumulator = "";

        for (int i = 0; i <= top; i++) {
            accumulator += items[i].toString();
        }

        return accumulator;
    }

    public String toReverseString() {

        String accumulator = "";

        for (int i = top; i >= 0; i--) {
            accumulator += items[i].toString();
        }

        return accumulator;
    }

    // returns true if vector is an palindrome
    public boolean isPalindrome() {
        
        PilhaObj<T> myStack = toStack(items);
        
        for (int i = 0; i < Math.floor(top / 2); i++) {
            if (items[i].equals(myStack.pop())) {
                return false;
            }
        }
        
        return true;
    }

    // returns convert int vector to Integer Stack
    public PilhaObj<T> toStack(T[] vector) {
        PilhaObj<T> myStack = new PilhaObj<T>(vector.length);

        for (int i = 0; i < top; i++) {
            myStack.push(vector[i]);
        }

        return myStack;
    }

    // boolean methods
    public boolean isFull() {
        return this.top == items.length - 1;
    }
    public boolean isNotFull() {
        return !isFull();
    }
    public boolean isEmpty() {
        return this.top == -1;
    }
    public boolean isNotEmpty() {
        return !isEmpty();
    }
    public boolean isNotEmptyOrFull() {
        return isNotEmpty() || isNotFull();
    }
}
