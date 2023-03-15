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
public class JsonLogic {
    public String name;
    public List<State> statesTable;
    public static List<JsonLogic> jsonLogic = new ArrayList();
    public static List<String> json = new ArrayList();
    
    public JsonLogic(String name, List<State> statesTable){
        this.name = name;
        this.statesTable = statesTable;
    }
    
   //SE AGREGAN LOS CONJUNTOS A LOS ESTADOS
    public static void conjAddition(){
        
        for(int i=0; i< jsonLogic.size(); i++){
            for(int j=0; j<jsonLogic.get(i).statesTable.size();j++){
                for(int k=0; k<jsonLogic.get(i).statesTable.get(j).theTransitions.size();k++){ 
                    for(int l=0; l<Manager.allConj.size();l++){
                        if(Manager.allConj.get(l).name.equals(jsonLogic.get(i).statesTable.get(j).theTransitions.get(k).terminal)){
                            jsonLogic.get(i).statesTable.get(j).theTransitions.get(k).accepted = Manager.allConj.get(l).validatedCh;
                        }
                    }
                }
                
            }  
        }

    }
    
    public static State finder(String name){
        State founded=null;
        for(int i=0; i<jsonLogic.size();i++){
            if(jsonLogic.get(i).name.equals(name)){
                founded = jsonLogic.get(i).statesTable.get(jsonLogic.get(i).statesTable.size()-1);
            }
        }
        return founded;
    }
    
    public static void validate(State temp, String entry, int counter, String name){
        //System.out.println(counter+","+entry+","+name);
        String tempCharacter = String.valueOf(entry.charAt(counter));
        State next = null;
        String response = "";
        
        if(temp!=null){
            for(int i=0; i<temp.theTransitions.size();i++){
                if(tempCharacter.equals("\\")){
                    counter++;
                    tempCharacter += String.valueOf(entry.charAt(counter));
                    counter++;
                }
                //System.out.println(actual.transiciones.get(i).terminal);
                //System.out.println(caracterAct);
                    if(temp.theTransitions.get(i).accepted.contains(tempCharacter)||temp.theTransitions.get(i).terminal.equals(tempCharacter)){
                        //System.out.println(actual.transiciones.get(i).terminal);
                        //System.out.println(actual.transiciones.get(i).terminal);
                        //System.out.println(caracterAct);
                        //System.out.println(count);
                        next=findState(name,temp.theTransitions.get(i).state);         
                }
            }
            if(next!=null){
                //System.out.println(siguiente.estado);
                counter++;
                validate(next,entry,counter,name);
            }else{
                if(counter==entry.length()-1){
                    entry = entry.replaceFirst(".$","");
                    json.add("{\n\"Valor\" :\""+name+"\",\n"+
                            "\"Expresion Regular\": \""+entry+"\",\n"+
                            "\"Resultado\":\"Cadena válida\"\n},\n"
                    );
                    MasterMindUI.response += "La expresión " + entry +" es válida con la expresión Regular: "+ name +"\n";
                }else{
                    entry = entry.replaceFirst(".$","");
                    /*json.add("{Valor :"+nombre+",\n"+
                            "Expresion Regular: "+entrada+",\n"+
                            "Resultado:Cadena inválida}\n"
                    );*/                    
                    MasterMindUI.response += "La expresión " + entry +" no es válida con la expresión Regular: "+ name +"\n";
                } 
            }
        }
    }
    
    public static void responseView(){
        for(int i=0; i< json.size();i++){
            System.out.println(json.get(i));
        }
    }
    
    public static State findState(String name,int state){
        State founded=null;
        for(int i=0; i<jsonLogic.size();i++){
            if(jsonLogic.get(i).name.equals(name)){
                for(int j=0; j<jsonLogic.get(i).statesTable.size();j++){
                    if(jsonLogic.get(i).statesTable.get(j).state==state){
                        founded = jsonLogic.get(i).statesTable.get(j);
                    }
                }
            }
        }
        return founded;
    }
    
    /*public static void val(){
        
        for(int i=0; i< verificador.size(); i++){
            for(int j=0; j<verificador.get(i).tablaEstados.size();j++){
                System.out.println(verificador.get(i).tablaEstados.get(j).estado + "----");
                for(int k=0; k<verificador.get(i).tablaEstados.get(j).transiciones.size();k++){ 
                    System.out.println(verificador.get(i).tablaEstados.get(j).transiciones.get(k).terminal);
                    System.out.println(verificador.get(i).tablaEstados.get(j).transiciones.get(k).estado);
                    System.out.println(verificador.get(i).tablaEstados.get(j).transiciones.get(k).aceptados);
                }
                
            }
        }
    }*/
    
    
}
