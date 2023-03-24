/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exregan;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author Jose
 */
public class Search {
    public Search(){}
    public int rightId;
    public int leftId;
    public int tempId;
    public int auxId;
    public List<Manager> conjManager = new ArrayList<Manager>();
    
    public void add(Manager conj){
        conjManager.add(conj);
    }
    
    
    public void prefixConversor(Stack stack){
        String graphCode="";
        Member m;
        int counter = 1;
        String temp="";
        
        String right="";
        String left="";
        String helper="";



        while(stack.empty()==false){
            m = (Member)stack.pop();
            m.id = counter++;
            if(m.name.equals("conjunto")||m.name.equals("cadena")){

                if (right.equals("") && left.equals("")){
                    right = m.value;
                    temp = right;
                    rightId = m.id;
                    tempId = rightId;
                }else if(left.equals("") && !right.equals("")){
                    left = m.value;
                    temp=left;
                    leftId = m.id;
                    tempId = leftId;
                }else if(helper.equals("") && !left.equals("") && !right.equals("")){
                    helper = right;
                    auxId = rightId;
                    right = left;
                    left = m.value;
                    temp = left;
                    rightId = leftId;
                    leftId = m.id;
                    tempId = leftId;
                }
            }else if(m.value.equals("|")||m.value.equals(".")){

                if(!helper.equals("") && left.equals("") ){
                    right = right +m.value+ helper;
                    temp = right;
                    rightId = m.id;
                    tempId = rightId;
                    auxId = 0;
                    helper = "";
                }else{
                    right = left + m.value + right;
                    left = "";
                    temp = right;
                    leftId = 0;
                    rightId = m.id;
                    tempId = rightId;
                }
            }else if(m.value.equals("*")||m.value.equals("+")||m.value.equals("?")){
 
                if(left.equals(temp)){
                    left = "("+left+")" + m.value;
                    temp = left;
                    leftId = m.id;
                    tempId = leftId;
                    
                }else if(right.equals(temp)){
                    right = "("+right+")" + m.value;
                    temp = right;
                    rightId = m.id;
                    tempId = rightId;
                }
            }
         
        }
        System.out.println(graphCode);
        System.out.println(right);

    }
    
}
