/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exregan;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/**
 *
 * @author Jose
 */
public class Files_Manipulation {
     public String route;
     public Files_Manipulation(){}
     private static Files_Manipulation instance;
     /*Función que devuelve la instancia de esta clase para trabajarla con 
    un manejador, y así poder trasladar información de manera eficiente.*/
     public static Files_Manipulation getInstance() {
        if(instance == null) {
            instance = new Files_Manipulation();            
        }
        return instance;
    }
     /*Función que sirve para leer el archivo de entrada, el parámetro, que 
    recibe, es una ruta de un fileChooser, y como resultado nos devuelve
    el texto del archivo de entrada para poder mostrarlo en el área de texto.*/
    public String readFile(String route){
        String tempText = "";
        String line = "";
        this.route = route;
        try{
            BufferedReader bf = new BufferedReader(new FileReader(route));
            while(((line=bf.readLine())!=null)){
                tempText += line+"\n";
            }
        }catch(Exception e){
            System.out.println(e.toString());
        }
        return tempText;
    }
    
    
    /*Método guardar como, en pocas palabras recibe la ruta de donde se quiere
    guardar el archivo, además como el texto que se desea guardar en el*/
    public void newFileCreation(String route,String updatedContent){
        File file;
        try{
        file = new File(route);
        if(file.createNewFile()){
            FileWriter fileW=new FileWriter(file);
            BufferedWriter bufferedF=new BufferedWriter(fileW);
            PrintWriter printW=new PrintWriter(bufferedF);
            try{
                printW.write(updatedContent);
                printW.close();
                bufferedF.close();
            }catch(Exception e){
                System.out.println(e.toString());
            }
            //Si todo sale bien, se crea un nuevo archivo.
            System.out.println("¡New File was created!");
        }
        }catch(Exception e){
           System.out.println(e);
        }
    }
    
    public static void jsonCreation(String name){
        String content ="";
        for(int i=0; i<JsonLogic.json.size(); i++){
            content+=JsonLogic.json.get(i);
        }
}
    }
