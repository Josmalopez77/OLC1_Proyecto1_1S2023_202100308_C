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
public class Manager {
    public String name;
    //String Expresion;
    public List<String> validatedCh = new ArrayList();
    
    
   
    public static List<Manager> allConj = new ArrayList();
    
    public Manager(String name, List<String> validatedCh){
        this.name = name;
        this.validatedCh = validatedCh;
    }
    
    //CREA EL CONJUNTO Y LO ALMACENA EN LA LISTA GLOBAL DE CONJUNTOS
    public static void rangeConj(String name, String left, String right){ 
        name = name.replace("{", "\\{");
        name = name.replace("}", "\\}");
        List<String> vCh = new ArrayList();
        int min = left.charAt(0);
        int max = right.charAt(0);
        
        for(int i=min; i<=max; i++){
            if(i<65 || i>122){
                vCh.add(Character.toString((char)i));
            }
        }
        
        allConj.add(new Manager(name,vCh));
    }
    //CREA EL CONJUNTO DE CARACTERES SEPARADOS POR COMAS
    public static void commasConj(String name, String characthers){
        name = name.replace("{", "\\{");
        name = name.replace("}", "\\}");
        String[] characters = characthers.split(",");
        List<String> vCh = new ArrayList();
        
        
        for(int i=0; i<characters.length;i++){
            vCh.add(characters[i]);
        }
        allConj.add(new Manager(name,vCh));
    }
    
    //CREA LOS CONJUNTOS ENTRE LETRAS Y NUMEROS NORMALES
    public static void numberLetterManage(String name, String left, String right){ 
        name = name.replace("{", "\\{");
        name = name.replace("}", "\\}");
        List<String> valCh = new ArrayList();
        int inferior = left.charAt(0);
        int superior = right.charAt(0);
        
        for(int i=inferior; i<=superior; i++){
            valCh.add(Character.toString((char)i));
        }
        
        allConj.add(new Manager(name,valCh));
    }
    
}
