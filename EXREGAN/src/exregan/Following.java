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
public class Following {
    public String terminal;
    public String hashtag;
    public List<String> theFollowing = new ArrayList(); 
    
    public Following(String terminal, String hashtag){
        this.terminal = terminal;
        this.hashtag = hashtag;
    }
    //Función para poder tabular los siguientes en graphviz
    public static String tabulateTheFollowing(List<Following> follwingList, String name){
        //Variable para que guarde todos los teminales
        String followingDot="";
        //Ciclo para guardar en el string el código para graficar
        for(int i=follwingList.size()-1; i>-1;i--){
            follwingList.get(i).terminal=follwingList.get(i).terminal.replace("{", "\\{");;
            follwingList.get(i).terminal=follwingList.get(i).terminal.replace("}", "\\}");;
            followingDot+="<tr><td>"+ follwingList.get(i).terminal +"</td>\n";
            followingDot+="<td>"+ follwingList.get(i).hashtag +"</td>\n";
            followingDot+="<td>"+ follwingList.get(i).theFollowing +"</td></tr>\n";
        }
        /*Se crea una tabla o al menos su estructura , y luego en las filas se
        le agregará lo obtenido en la variable followingDot*/
        String table="digraph G{\n" +
        "graph [fontsize=50 labelloc=\"t\" label=\"\" splines=true overlap=false rankdir = \"LR\"];\n" +
        "\n" +
        "\"state5\" [ style = \"filled\" penwidth = 1 fillcolor=\"red\" fontname = \"Showcard Gothic\" shape = \"folder\" label =\n" +
        "<<table border=\"0\" cellborder=\"1\" cellpadding=\"7\" bgcolor=\"blue\">\n" +
        "<tr><td bgcolor=\"red\" align=\"center\" colspan=\"3\"><font color=\"blue\">"+name+"</font></td></tr>\n" +
        "\n" +
        "<tr>\n" +"<td align=\"left\">Terminal</td>\n" +"<td align=\"left\">Leaf</td>\n" +"<td align=\"left\">The Following</td>\n" +
                "</tr>"+followingDot+"</table>>];}";
        
        //System.out.println(table);
        return table;
    }
    
    public static void addFollowing(String nodes, String theFollowing){
        //Se declaran 2 listas
        String[] nodesList;
        String[] followingList;
        //Las listas  se llenan spliteando los parámetros de tipo string.
        nodesList = nodes.split(",");
        followingList = theFollowing.split(",");
        
        for(int i=0; i<nodesList.length ; i++){
            for(int j=0;j<Node.theFollowing.size();j++){
                //Se verifica coincidencia con algú nodo
                if(Node.theFollowing.get(j).hashtag.equals(nodesList[i])){
                    //Si lo verifica, se le agregan los siguientes al nodo.
                    for(int s=0;s<followingList.length;s++){
                        //Se verifica que no se repita el siguiente
                        if(!Node.theFollowing.get(j).theFollowing.contains(followingList[s])){
                            //Si no se repite se procede agregar el siguiente
                            Node.theFollowing.get(j).theFollowing.add(followingList[s]);
                        }
                    }
                }
            }
        }

    }
    
    
    
}