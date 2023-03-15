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
public class State {
    
    public int state;
    public String conj;
    public String[] conjLiist;
    public List<Transition> theTransitions = new ArrayList();
    
    public static List<State> theStates = new ArrayList();
    public static List<String> usedStates = new ArrayList();
    public List<String> usedTransitions = new ArrayList();
    
    
    public State(int state, String conj, String[] conjLiist, List<Transition> theTransitions){
        this.state = state;
        this.conj = conj;
        this.conjLiist = conjLiist;
        this.theTransitions = theTransitions = new ArrayList();
    }
    
    
    public static void defineStates(State temp){
        String[] sig;
        //int count=actual.estado;
        int count=temp.state;
        if(!usedStates.contains(temp.conj)){
            usedStates.add(temp.conj);
            List<String> newState=null; 
            count++;
            for(int i=0; i<temp.conjLiist.length;i++){
                for(int j=0; j<Node.terminalList.size();j++){
                    newState = new ArrayList(); 
                    for(int t=0; t<Node.theFollowing.size();t++){
                        
                        if(temp.conjLiist[i].equals(Node.theFollowing.get(t).hashtag) &&
                           Node.terminalList.get(j).equals(Node.theFollowing.get(t).terminal)){
                           //System.out.println(Node.theFollowing.get(t).terminal);
                            
                           //AGREGA TODOS LOS SIGUIENTES AL ESTADO NUEVO
                           for(int k=0; k<Node.theFollowing.get(t).theFollowing.size();k++){
                               
                               if(!newState.contains(Node.theFollowing.get(t).theFollowing.get(k))){
                                    
                                    newState.add(Node.theFollowing.get(t).theFollowing.get(k));
                               }
                           }
                           if(Node.theFollowing.get(t).terminal.equals("#")){
                               temp.theTransitions.add(new Transition(count,"#"));
                               //System.out.println(Node.misSiguientes.get(t).terminal);
                           }///////////////////////////////////////////
                        }
                    }
                    if(!newState.isEmpty()){
                        
                        if(!temp.usedTransitions.contains(Node.terminalList.get(j))){
                            if(usedStates.contains(String.join(",", newState))){
                                temp.usedTransitions.add(Node.terminalList.get(j));
                                temp.theTransitions.add(new Transition(usedStates.indexOf(String.join(",", newState)),Node.terminalList.get(j)));
                            }else{
                                temp.usedTransitions.add(Node.terminalList.get(j));
                                temp.theTransitions.add(new Transition(count,Node.terminalList.get(j)));
                            }
                        }
                        defineStates(new State(count,String.join(",", newState),String.join(",", newState).split(","),null));
                    }   
                }
                
            }
            theStates.add(temp);
        }
    }
    
    public static String afdGraph(List<State> theStates){
        String dot_afd="";
        int accepted = 0;
        for(int i=theStates.size()-1; i>-1; i--){
            for(int j=theStates.get(i).theTransitions.size()-1; j>-1;j--){
                if(!theStates.get(i).theTransitions.get(j).terminal.equals("#")){
                    dot_afd+="S" + theStates.get(i).state+"->"; 
                    dot_afd+="S"+theStates.get(i).theTransitions.get(j).state+"[label=\""+theStates.get(i).theTransitions.get(j).terminal+"\"];\n";
                }
                if(!theStates.get(i).theTransitions.get(j).terminal.equals("#")){
                    accepted=theStates.get(i).theTransitions.get(j).state;
                }
            }
        }
        
        dot_afd = "digraph finite_state_machine {\n" +
        "rankdir=LR;\n" +
        "size=\"8,5\"\n" +
        "node [shape = doublecircle];S"+accepted+";\n" +
        "node [shape = circle];"+dot_afd+"}";
        //System.out.println(dot_afd);
        return dot_afd;
    }
    
    public static String statesTabulation(List<State> states, String name){
        String graphCode="";
        
        String terminals="";
        for(int t=Node.terminalList.size()-1; t>0; t--){
            if(!Node.terminalList.get(t).equals("#")){
                terminals+="<td>"+Node.terminalList.get(t)+"</td>\n";
            }
        }
        String[] transition = null;
        for(int i=theStates.size()-1; i>-1; i--){
            graphCode+="<tr><td>S"+theStates.get(i).state+"("+theStates.get(i).conj+")</td>\n";
            transition = new String[Node.terminalList.size()];
            
            
            for(int t=Node.terminalList.size()-1; t>0; t--){
               
                for(int j=theStates.get(i).theTransitions.size()-1; j>-1;j--){
                    if(Node.terminalList.get(t).equals(theStates.get(i).theTransitions.get(j).terminal)){
                        transition[t] = Integer.toString(theStates.get(i).theTransitions.get(j).state);
                    }
                }        
            }
            for(int s=transition.length-1;s>0;s--){
                if(transition[s]!=null){
                    graphCode+="<td>S"+transition[s]+"</td>\n";
                }else{
                    graphCode+="<td>---</td>\n";
                }
            }
            
            graphCode=graphCode +"</tr>\n";
        }
        
        
        
        String afd="digraph finite_state_machine {\n" +
        "rankdir=LR;\n" +
        "size=\"8,5\"\n" +
        "node [shape = doublecircle]; LR_0 LR_3 LR_4 LR_8;\n" +
        "node [shape = circle];";
             
        String table = "digraph G{\n" +
        "graph [fontsize=30 labelloc=\"t\" label=\"\" splines=true overlap=false rankdir = \"LR\"];\n" +
        "\n" +
        "\"state5\" [ style = \"filled\" penwidth = 1 fillcolor=\"red\" fontname = \"Courier New\" shape = \"Mrecord\" label =\n" +
        "<<table border=\"0\" cellborder=\"1\" cellpadding=\"3\" bgcolor=\"blue\">\n" +
        "<tr><td bgcolor=\"black\" align=\"center\" colspan=\""+Integer.toString(Node.terminalList.size())+"\"><font color=\"white\">"+name+"</font></td></tr>\n" +
        "\n" +
        "<tr>\n" +
        "<td align=\"left\">Estado</td>\n" +
        terminals+
        "</tr>\n" +
        "\n" +
        "\n" +
        graphCode+"\n </table>>];}";
        //System.out.println(tabla);
        return table;
    }
   
    
    public static void statesView(List<State> states, String name){
        System.out.println(name);
        for(int i=0; i<states.size(); i++){
            System.out.println(states.get(i).state);
        
        }
    }
    
}