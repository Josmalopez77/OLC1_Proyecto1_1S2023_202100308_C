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
        System.out.println("ENTRAMOS A JSOPNCREATION");
        String content ="";
        for(int i=0; i<JsonLogic.json.size(); i++){
            content+=JsonLogic.json.get(i);
        }
        
        
        try {
            FileWriter file = new FileWriter("C:/Users/Jose/Documents/GitHub/OLC1_Proyecto1_1S2023_202100308_C/EXREGAN/Reportes/SALIDAS_202100308/salida"+name+".json");
            file.write("[\n"+content+"]");
            file.flush();
            file.close();
            JsonLogic.json.clear();

        } catch (IOException e) {
            System.out.println("No se pudo generar el archivo JSON");
                //manejar error
        }
        
        
        
    }
    
    public static void afndCreation(String dot, String name){
        FileWriter fw = null;
        PrintWriter pw = null;
        try {
            fw = new FileWriter("C:/Users/Jose/Documents/GitHub/OLC1_Proyecto1_1S2023_202100308_C/EXREGAN/Reportes/AFND_202100308/" + name + ".dot");
            pw = new PrintWriter(fw);
            pw.println("digraph G{");
            pw.println("rankdir=LR");
            pw.println("node[shape=circle]");
            pw.println("size=\"15\"");
            pw.println(dot);
            pw.println("}");
        } catch (Exception e) {
            System.out.println("error, no se realizo el archivo");
            System.out.println(e);
        } finally {
            try {
                if (null != fw) {
                    fw.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        digraph("C:/Users/Jose/Documents/GitHub/OLC1_Proyecto1_1S2023_202100308_C/EXREGAN/Reportes/AFND_202100308/" +name);
    }
    public boolean isExpExtension(String route){
        int index = route.lastIndexOf(".");
        boolean isExp=false;
        if(route.substring(index+1).toString().equals("exp")){
            isExp = true;
        }
        return isExp;
    }
    public void treeCreation(Node dot, String name){
        FileWriter fw = null;
        PrintWriter pw = null;
        try {
            fw = new FileWriter("C:/Users/Jose/Documents/GitHub/OLC1_Proyecto1_1S2023_202100308_C/EXREGAN/Reportes/ARBOLES_202100308/" + name + ".dot");
            pw = new PrintWriter(fw);
            pw.println("digraph G{");
            pw.println("rankdir=UD");
            pw.println("node[shape=record]");
            pw.println("concentrate=true");
            pw.println(dot.getCode());
            pw.println("}");
        } catch (Exception e) {
            System.out.println("error, no se realizo el archivo");
        } finally {
            try {
                if (null != fw) {
                    fw.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        digraph("C:/Users/Jose/Documents/GitHub/OLC1_Proyecto1_1S2023_202100308_C/EXREGAN/Reportes/ARBOLES_202100308/" +name);
    }
    
    public static void digraph(String name){
            try {

              String dotPath = "C:\\Program Files\\Graphviz\\bin\\dot.exe";

              String fileInputPath =  name + ".dot";
              String fileOutputPath =  name + ".png";
              System.out.println(fileInputPath);
              System.out.println(fileOutputPath);
              String tParam = "-Tpng";
              String tOParam = "-o";

              String[] cmd = new String[5];
              cmd[0] = dotPath;
              cmd[1] = tParam;
              cmd[2] = fileInputPath;
              cmd[3] = tOParam;
              cmd[4] = fileOutputPath;

              Runtime rt = Runtime.getRuntime();

              rt.exec( cmd );
                

            } catch (Exception ex) {
              ex.printStackTrace();
            } finally {
            }
    }
    public static void createD(String dot, String name){
        FileWriter fw = null;
        PrintWriter pw = null;
        try {
            fw = new FileWriter(name + ".dot");
            pw = new PrintWriter(fw);
            pw.println(dot);
        } catch (Exception e) {
            System.out.println("error, no se realizo el archivo");
            System.out.println(e);
        } finally {
            try {
                if (null != fw) {
                    fw.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        digraph(name);
    }
    
    /*Método para guardar el archivo actual con el texto actualizado,
    tomar en cuenta que no se crea un archivo nuevo, solo lo guarda.*/
    public void saveFile(String updatedContent) throws IOException{
        File file=new File(this.route);
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
    }
    
    
}

