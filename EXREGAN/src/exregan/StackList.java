/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exregan;

/**
 *
 * @author Jose
 */
import java.util.Stack;
public class StackList {
    Stack stack = new Stack();

    public Stack getStack() {
        return stack;
    }
    public void InsertElement(Member member){
        stack.push(member);
    }
    
    public String getElements(){
        String message="";
        Member member;
        while(stack.empty()==false){
            member = (Member)stack.pop();
            message+=" "+member.getValue();
        }
        return message;
    }
    
    public void emptyStackRightNow(){
        //Mientras la pila no esté vacía
        while(stack.empty()==false){
            //Eliminará un elemento
            stack.pop();
        }
    }
}
