/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exregan;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jose
 */
public class Node {
    public Node leftTag;
    public Node rightTag;
    public String value;
    public int id;
    
    //VARIABLES PARA RECORRER EL ARBOL
    public String voidability;
    public String previous;
    public String following;
    public int counter;
    public int ultimate;
    
    //VARIABLES PARA ENCONTRAR EL AFND
    public String tempNode1;
    public String tempNode2;
    public static String dotAfd="";
    public static int countAfd=0;
    
    //VARIABLES PARA GUARDAR EL NOMBRE DE LA EXPRESION Y LA EXPRESION EN INFIJO
    public String expresionName;
    public String infixExpresion;
    
    public static int last;
    public static List<Following> theFollowing;
    public static List<String>terminalList=new ArrayList();
    

    public int getUltimate() {
        return ultimate;
    }

    public void setUltimate(int ultimate) {
        this.ultimate = ultimate;
    }
    
    public Node(Node leftTag, Node rightTag, String value, int id, String voidability, String previous, String following,int counter){
        this.leftTag=leftTag;
        this.rightTag=rightTag;
        this.value=value;
        this.id=id;
        this.voidability = voidability;
        this.previous = previous;
        this.following = following;
        this.counter=counter;
    }
    public static void treeTravel(Node temp){
        
        if(temp.following.equals("") && temp.previous.equals("")){
            if(temp.value.equals(".")||temp.value.equals("|")){
                treeTravel(temp.rightTag);
                treeTravel(temp.leftTag);
                
                if(temp.value.equals("|")){
                    temp.value ="\\"+temp.value;
                    if(temp.leftTag.voidability.equals("N")&& temp.rightTag.voidability.equals("N")){
                        temp.voidability="N";
                        temp.previous= temp.leftTag.previous + "," + temp.rightTag.previous;
                        temp.following= temp.leftTag.following + "," + temp.rightTag.following;
                    }else{
                        temp.previous= temp.leftTag.previous + "," + temp.rightTag.previous;
                        temp.following=temp.leftTag.following + "," + temp.rightTag.following;
                    }
                    temp.tempNode1 = Integer.toString(countAfd++);
                    dotAfd += temp.tempNode1 + "[label=\"\"]";
                    temp.tempNode2 = Integer.toString(countAfd++);
                    dotAfd += temp.tempNode2 + "[label=\"\"]";
                    dotAfd += temp.tempNode1 +" -> "+ temp.leftTag.tempNode1 + " [label=ε]\n";
                    dotAfd += temp.tempNode1 +" -> "+ temp.rightTag.tempNode1 + " [label=ε]\n";
                    dotAfd += temp.leftTag.tempNode2 +" -> "+ temp.tempNode2 + " [label=ε]\n"; 
                    dotAfd += temp.rightTag.tempNode2 +" -> "+ temp.tempNode2 + " [label=ε]\n"; 
                }else if(temp.value.equals(".")){
                    if(temp.leftTag.voidability.equals("A") && temp.rightTag.voidability.equals("A")){
                        temp.voidability="A";
                        temp.previous= temp.leftTag.previous + "," + temp.rightTag.previous;
                        temp.following = temp.leftTag.following + "," + temp.rightTag.following;
                    }else{
                        if(temp.leftTag.voidability.equals("A")){
                            temp.previous = temp.leftTag.previous + "," + temp.rightTag.previous;
                        }
                        else{
                            temp.previous = temp.leftTag.previous;
                        }   
                        if(temp.rightTag.voidability.equals("A")){
                            temp.following= temp.leftTag.following + "," + temp.rightTag.following;
                        }else{
                            temp.following = temp.rightTag.following;
                        }
                    }
                    Following.addFollowing(temp.leftTag.following,temp.rightTag.previous);
                    temp.tempNode1 = temp.leftTag.tempNode1;
                    temp.tempNode2 = temp.rightTag.tempNode2;
                    dotAfd += temp.leftTag.tempNode2 +" -> "+ temp.rightTag.tempNode1 + " [label=ε]\n";
                }
            }else{
                treeTravel(temp.leftTag);
                temp.previous=temp.leftTag.previous;
                temp.following=temp.leftTag.following;
                
                if(temp.value.equals("*") || temp.value.equals("+")){
                    Following.addFollowing(temp.following,temp.previous);
                    temp.tempNode1 = Integer.toString(countAfd++);
                    dotAfd += temp.tempNode1 + "[label=\"\"]";
                    temp.tempNode2 = Integer.toString(countAfd++);
                    dotAfd += temp.tempNode2 + "[label=\"\"]";
                    if(temp.value.equals("*")){
                        dotAfd += temp.tempNode1 +" -> "+ temp.tempNode2 + " [label=ε]\n";
                        dotAfd += temp.leftTag.tempNode2 +" -> "+ temp.tempNode2 + " [label=ε]\n";
                        dotAfd += temp.tempNode1 +" -> "+ temp.leftTag.tempNode1 + " [label=ε]\n";
                        dotAfd += temp.leftTag.tempNode2 +" -> "+ temp.leftTag.tempNode1 + " [label=ε]\n";
                    }else{
                        dotAfd += temp.leftTag.tempNode2 +" -> "+ temp.tempNode2 + " [label=ε]\n";
                        dotAfd += temp.tempNode1 +" -> "+ temp.leftTag.tempNode1 + " [label=ε]\n";
                        dotAfd += temp.leftTag.tempNode2 +" -> "+ temp.leftTag.tempNode1 + " [label=ε]\n";
                    }
                }
                
                if(temp.value.equals("?")){
                    temp.tempNode1 = Integer.toString(countAfd++);
                    dotAfd += temp.tempNode1 + "[label=\"\"]";
                    temp.tempNode2 = Integer.toString(countAfd++);
                    dotAfd += temp.tempNode2 + "[label=\"\"]";
                    
                    dotAfd += temp.tempNode1 +" -> "+ temp.tempNode2 + " [label=ε]\n";
                    dotAfd += temp.leftTag.tempNode2 +" -> "+ temp.tempNode2 + " [label=ε]\n";
                    dotAfd += temp.tempNode1 +" -> "+ temp.leftTag.tempNode1 + " [label=ε]\n";
                }
            }
        }else{
            if(temp.leftTag==null && temp.rightTag==null){
                Following nextNode = new Following(temp.value,Integer.toString(temp.counter));
                theFollowing.add(nextNode);
                temp.value = temp.value.replace("{", "\\{");
                temp.value = temp.value.replace("}", "\\}");
                if(!terminalList.contains(temp.value)){
                    terminalList.add(temp.value);
                }
                temp.tempNode1 = Integer.toString(countAfd++);
                dotAfd += temp.tempNode1 + "[label=\"\"]";
                temp.tempNode2 = Integer.toString(countAfd++);
                dotAfd += temp.tempNode2 + "[label=\"\"]";
                
                if(temp.value.equals("#")){
                    dotAfd += temp.tempNode1 +" -> "+ temp.tempNode2 + " [label=ε]\n";
                    dotAfd += temp.tempNode2 + "[shape = doublecircle];\n";
                }else{
                    dotAfd += temp.tempNode1 +" -> "+ temp.tempNode2 + " [label=\""+temp.value+"\"]\n";
                }
            }

        }
    }
    
    
    public String getCode(){
        String tag="";
        
        if(leftTag==null && rightTag==null){
            tag = "nodo"+id+"[label=\""+previous+"|{"+voidability+"|"+value+"|id: "+counter+"}|{"+ following +"}\"];\n";
        }else{
            tag = "nodo"+id+"[label=\""+previous+"|{"+voidability+"|"+value+"|id: "+counter+"}|{"+ following +"}\"];\n";
        }
        //CREACION DE HOJA CON HIJOS
        if(leftTag !=null){
            tag+=leftTag.getCode()+"nodo"+id+"->nodo"+leftTag.id+";\n";
        }
        if(rightTag!=null){
            tag+=rightTag.getCode()+"nodo"+id+"->nodo"+rightTag.id+";\n";
        }
        return tag;
    }
    
    
    public String getAFND(){
        String tag="";
        
        if(leftTag==null && rightTag==null){
            tag = "nodo"+id+"[label=\""+previous+"|{"+voidability+"|"+value+"|id: "+counter+"}|{"+ following +"}\"];\n";
        }else{
            tag = "nodo"+id+"[label=\""+previous+"|{"+voidability+"|"+value+"|id: "+counter+"}|{"+ following +"}\"];\n";
        }
        //CREACION DE HOJA CON HIJOS
        if(leftTag !=null){
            tag+=leftTag.getCode()+"nodo"+id+"->nodo"+leftTag.id+";\n";
        }
        if(rightTag!=null){
            tag+=rightTag.getCode()+"nodo"+id+"->nodo"+rightTag.id+";\n";
        }
        return tag;
    }
}