/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Analyzers;

/**
 *
 * @author Jose
 */
    public class Generator {
    public static void main(String[] args)  {
        try {
            String route="src/Analyzers/";
            String operationsJflex[]={route+"lex.jflex","-d",route};
            jflex.Main.generate(operationsJflex);
            
            String operationsCup[] =  {"-destdir", route,"-parser","Parser",route+"parser.cup"};
            java_cup.Main.main(operationsCup);
            
        } catch (Exception e) {
        }
 
    }
    
}

