/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exregan;

import Analyzers.Parser;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.WindowConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author Jose
 */
public class MasterMindUI {
    /*Muy importante se instancia nuestra clase TNode, en una
    variable, que nos servirá como un manejador de la misma.*/
    //public static TNodeDAO tNodeDaoHandler=TNodeDAO.getInstance();
    
    static Files_Manipulation filesManipulationHandler = new Files_Manipulation();
    
    public static Error_DAO errorDaoHandler=Error_DAO.getInstance();
    public static java.util.List<Node> regularExpressions = new ArrayList();
    public static java.util.List<Manager> conjs;
    public static String wordsList;
    
    /*Variable g
    lobal, área de texto que mostrará el texto del archivo 
    de entrada*/
    public static  JTextArea textEdition1;
    /*Variable global, área de texto que mostrará todo lo relacionado
    a las salidas*/
    public static  JTextArea textEdition2;
    /*Un simple Scroll para el área de texto 1 (Entrada).*/
    public static JScrollPane scrollEdition1;
    /*Un simple Scroll para el área de texto 2 (Salidas).*/
    public static JScrollPane scrollEdition2;
    /*Un simple Scroll para el área archivos disponibles*/
    public static JScrollPane treeScroll;
    public static StackList ch;
    /*Un simple JTree que muestra los archivos generados*/
    public static JTree archivesTree = new JTree();
    /*Un simple JLabel que mostrará la imágen seleccionada*/
    public static JLabel tempImageLabel= new JLabel();
    /*Muy importante se instancia nuestra clase FilesManipulation, en una
    variable, que nos servirá como un manejador de la misma.*/
    //public static FilesManipulation filesManipulationHanlder =  FilesManipulation.getInstance();
    public static java.util.List<Statement> statements = new ArrayList();
    public static String response="";
    public static int math =0;
    public static void main(String[] args) {
        
    }
    public static void pMasterMindFrame() throws IOException {
            
        //Creamos unos tipos de letra, que nos servirán más adelante
        Font font =new Font("Arial",Font.BOLD,36);
        Font font2 =new Font("Helvetica",Font.BOLD,20);
        Font font3 =new Font("Helvetica",Font.BOLD,40);
        
        
        //=========================Creación del Frame del Admin==============================
        
        //Se crea el frame y se le agrega un título
        JFrame managerFrame = new JFrame("ExpAnalyzer----Jose Lopez");
        managerFrame.setLayout(null);
        
        //Se hace visible el frame
        managerFrame.setVisible(true);
        // Se restringe al frame a no poder cambiar de tamaño
        managerFrame.setResizable(true);
        //Se le coloca un color de fondo al frame
        managerFrame.getContentPane().setBackground(Color.ORANGE);
        //Se le indica al frame que hacer en caso de que se cierre el mismo,
        //en este caso la aplicación termina su ejecución
        managerFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // Con esto obtenemos las dimensiones de resolución de pantlla
        Toolkit myScreen= Toolkit.getDefaultToolkit();
        Dimension sizeScreen = myScreen.getScreenSize();
        // Guardamos la altura de la dimension de pantalla
        int h= sizeScreen.height;
        // Guardamos la anchura de la dimension de pantalla
        int w= sizeScreen.width;
        //Le agreamos un tamaño al frame admin
        managerFrame.setSize(1300,850);
        //Le agreamos una locación al frame admin
        managerFrame.setLocation(w/4,h/4);
        //Le agreamos un  ícono al frame admin
        Image myIcon= myScreen.getImage("iconoLogin.png");
        managerFrame.setIconImage(myIcon);
        
        
        //=========================Creación de los  Botones del frame admin=========================
        //Creamos un botón de Abrir documento
        JButton openButton = new JButton("Abrir");
        openButton.setLayout(null);
        openButton.setVisible(true);
        openButton.setBounds(10, 10, 120, 60);
        openButton.setBackground(Color.green);
        openButton.setForeground(Color.blue);
        openButton.setFont(new Font("cooper black",3,20));
        
        openButton.addMouseListener(new MouseAdapter()  
                {  
                    public void mouseClicked(MouseEvent e)  
                    {  
                        
                        JFileChooser browser = new JFileChooser();
                        browser.showOpenDialog(openButton);
                        File file = browser.getSelectedFile();
                        if(file!=null){
                            textEdition1.setText(filesManipulationHandler.readFile(file.getAbsolutePath()));
                        }
                    }
            }); 
        managerFrame.add(openButton);
        
        
        //Creamos un botón de Guardar
        JButton saveButton = new JButton("Guardar");
        saveButton.setLayout(null);
        saveButton.setVisible(true);
        saveButton.setBounds(140, 10, 180, 60);
        saveButton.setBackground(Color.green);
        saveButton.setForeground(Color.blue);
        saveButton.setFont(new Font("cooper black",3,20));
        
        saveButton.addMouseListener(new MouseAdapter()  
                {  
                    public void mouseClicked(MouseEvent e){  
                        try {
                            filesManipulationHandler.saveFile(textEdition1.getText());
                        } catch (IOException ex) {
                            Logger.getLogger(MasterMindUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        
                    }
            }); 
        managerFrame.add(saveButton);
        
        
        
        
        //Creamos un botón de Guardar Como
        JButton saveAsButton = new JButton("Guardar Como");
        saveAsButton.setLayout(null);
        saveAsButton.setVisible(true);
        saveAsButton.setBounds(330, 10, 200, 60);
        saveAsButton.setBackground(Color.green);
        saveAsButton.setForeground(Color.blue);
        saveAsButton.setFont(new Font("cooper black",3,20));
        saveAsButton.addMouseListener(new MouseAdapter()  
                {  
                    public void mouseClicked(MouseEvent e)  
                    {  
                        JFileChooser browser = new JFileChooser();
                        browser.setCurrentDirectory(new File("."));
                        browser.setDialogTitle("Select the directory of the new file!!");
                        if(browser.showOpenDialog(saveAsButton) == JFileChooser.APPROVE_OPTION){
                            File SelectBinder = browser.getSelectedFile();
                            System.out.print(filesManipulationHandler.isExpExtension(SelectBinder.getAbsolutePath()));
                            if(filesManipulationHandler.isExpExtension(SelectBinder.getAbsolutePath())){
                                filesManipulationHandler.newFileCreation(SelectBinder.getAbsolutePath(),textEdition1.getText());
                                filesManipulationHandler.route=SelectBinder.getAbsolutePath();
                            }else{
                                System.out.println("Archive not found");
                            }
                        }
                        
                    }
            }); 
        managerFrame.add(saveAsButton);
        
        //Creamos un botón de Nuevo archivo, vaciará el área de texto de edición
        JButton newButton = new JButton("Nuevo Archivo");
        newButton.setLayout(null);
        newButton.setVisible(true);
        newButton.setBounds(540, 10, 250, 60);
        newButton.setBackground(Color.green);
        newButton.setForeground(Color.blue);
        newButton.setFont(new Font("cooper black",3,20));
        
        newButton.addMouseListener(new MouseAdapter()  
                {  
                    public void mouseClicked(MouseEvent e){  
                        textEdition1.setText("");
                        
                        
                    }
            }); 
        managerFrame.add(newButton);
        
        
        //Creamos un botón de Generación de Reportes
        JButton reportsButton = new JButton("Generar Reportes");
        reportsButton.setLayout(null);
        reportsButton.setVisible(true);
        reportsButton.setBounds(800, 10, 250, 60);
        reportsButton.setBackground(Color.green);
        reportsButton.setForeground(Color.blue);
        reportsButton.setFont(new Font("cooper black",3,20));
        
         reportsButton.addMouseListener(new MouseAdapter()  
                {  
                    public void mouseClicked(MouseEvent e4)  
                    {  
                        conjs = new ArrayList();
                        ch = new StackList();
                        Files_Manipulation writer = new Files_Manipulation();

                        wordsList = "";

                        try {
                            String content = textEdition1.getText();
                            Analyzers.Parser parser;

                            parser = new Analyzers.Parser(new Analyzers.Lexical(new StringReader(content)));
                            parser.parse();

                        } catch (Exception e) {
                        }

                        for(int i=0; i<regularExpressions.size(); i++){
                            String fileName=regularExpressions.get(i).expresionName;
                            
                            java.util.List<Following> followings = new ArrayList(); 
                            Node.theFollowing = followings;
                            Node node = new Node(regularExpressions.get(i),new Node(null,null,"#",regularExpressions.get(i).id+1,"N",
                            Integer.toString(regularExpressions.get(i).getUltimate()),Integer.toString(regularExpressions.get(i).getUltimate()),regularExpressions.get(i).getUltimate())
                            ,".",0,"N","","",0);
                            Parser.root.treeTravel(node);

                            //Crear tabla de siguientes
                            Files_Manipulation.createD(Following.tabulateTheFollowing(followings,fileName),"C:/Users/Jose/Desktop/OLC_P1_ExpAnalyzer-main/ExpAnalyzer/Reportes/SIGUIENTES/"+fileName);

                            //Calcular estados en base a la raíz del arbol
                            State.defineStates(new State(0,node.previous,node.previous.split(","),null));
                            State.usedStates = new ArrayList();
                            
                            java.util.List<State> states = new ArrayList(); 
                            states = State.theStates;
                            //Creación de la tabla de estados

                            Files_Manipulation.createD(State.statesTabulation(states,fileName),"C:/Users/Jose/Desktop/OLC_P1_ExpAnalyzer-main/ExpAnalyzer/Reportes/TRANSICIONES/"+fileName);
                            //AFD
                            Files_Manipulation.createD(State.afdGraph(states),"C:/Users/Jose/Desktop/OLC_P1_ExpAnalyzer-main/ExpAnalyzer/Reportes/AFD/"+fileName);

                            Files_Manipulation.afndCreation(Node.dotAfd, fileName);
                            Node.dotAfd ="";
                            State.theStates.clear(); 
                            Node.terminalList = new ArrayList();

                            //AST
                            writer.treeCreation(node, fileName+Integer.toString(i));
                        }
                        regularExpressions.clear();
                        textEdition2.setText("Autómatas graficados");
                        
                    }
            }); 
        managerFrame.add(reportsButton);
        
        //Se crea el objeto de área de texto
        textEdition1 = new javax.swing.JTextArea();
        textEdition1.setFont(font2);
        textEdition1.setVisible(true);
        //Se crea el objeto de la barra scroll
        scrollEdition1 = new JScrollPane(textEdition1);
        scrollEdition1.setBounds(10, 100, 800, 400);
        scrollEdition1.setVisible(true);
        scrollEdition1.setViewportView(textEdition1);
        //Se agrega el scroll con el text area dentro de él al frame.
        managerFrame.add(scrollEdition1);
        
        //label que indica el área de texto de salidas
        JLabel outsLabel = new JLabel("Salidas: ");
        outsLabel.setLayout(null);
        outsLabel.setVisible(true);
        outsLabel.setForeground(Color.BLACK);
        outsLabel.setBounds(10,600,250,30);
        outsLabel.setFont(font3);
        managerFrame.add(outsLabel);
        
        //Se crea el objeto de área de texto de salidas
        textEdition2 = new javax.swing.JTextArea();
        textEdition2.setFont(font2);
        textEdition2.setVisible(true);
        //Se crea el objeto de la barra scroll
        scrollEdition2 = new JScrollPane(textEdition2);
        scrollEdition2.setBounds(10, 675, 800, 200);
        scrollEdition2.setVisible(true);
        scrollEdition2.setViewportView(textEdition2);
        //Se agrega el scroll con el text area dentro de él al frame.
        managerFrame.add(scrollEdition2);
        
        
        //Creamos un botón para analizar el archivo de entrada
        JButton analyzeButton = new JButton("Analizar Entrada");
        analyzeButton.setLayout(null);
        analyzeButton.setVisible(true);
        analyzeButton.setBounds(275, 515, 250, 60);
        analyzeButton.setBackground(Color.green);
        analyzeButton.setForeground(Color.blue);
        analyzeButton.setFont(new Font("cooper black",3,20));
        
         analyzeButton.addMouseListener(new MouseAdapter()  
                {  
                    public void mouseClicked(MouseEvent e4)  
                    {  
                        try {
                            String importrantContent = textEdition1.getText();
                            Analyzers.Parser parser;
                            parser = new Analyzers.Parser(new Analyzers.Lexical(new StringReader(importrantContent)));
                            parser.parse();
            
                        } catch (Exception e) {
                        }
        
        
                        for(int i=0; i<regularExpressions.size(); i++){
                            String fileName=regularExpressions.get(i).expresionName;
                            //System.out.println(ExpsRegs.get(i).enumerador);
                            java.util.List<Following> theFollowing = new ArrayList(); 
                            Node.theFollowing = theFollowing;
                            Node node = new Node(regularExpressions.get(i),new Node(null,null,"#",regularExpressions.get(i).id+1,"N",
                            Integer.toString(regularExpressions.get(i).getUltimate()),Integer.toString(regularExpressions.get(i).getUltimate()),regularExpressions.get(i).getUltimate())
                            ,".",0,"N","","",0);
                            Parser.root.treeTravel(node);
                            Following.tabulateTheFollowing(theFollowing,fileName);
            
            
                            //CALCULAR ESTADOS MANDANDO LA RAIZ DEL ARBOL COMO ESTADO INICIAL
                            State.defineStates(new State(0,node.previous,node.previous.split(","),null));
                            State.usedStates = new ArrayList();

                            java.util.List<State> theStates = new ArrayList(); 
                            theStates = State.theStates;
                            JsonLogic spy = new JsonLogic(fileName,new ArrayList());
                            for(int j=0 ; j<theStates.size();j++){
                                spy.statesTable.add(theStates.get(j));
                            }
                            JsonLogic.jsonLogic.add(spy);
                            State.theStates.clear(); 
                            Node.terminalList = new ArrayList();
                        }

                        regularExpressions.clear();
                        JsonLogic.conjAddition();
        
                        for(int i=0; i<statements.size();i++){
                            statements.get(i).entry=statements.get(i).entry.replace("\"","");
                            JsonLogic.validate(JsonLogic.finder(statements.get(i).name), statements.get(i).entry,0,statements.get(i).name);

                        }
                        if(JsonLogic.json.size()!=0){
                            Files_Manipulation.jsonCreation(Integer.toString(math));
                        }
        
        
                        textEdition2.setText(response);
                        response="";
                        statements.clear();
                        JsonLogic.jsonLogic = new ArrayList();
                        math++;
                        
                    }
            }); 
        managerFrame.add(analyzeButton);
        
        
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("root");
        archivesTree.setModel(new DefaultTreeModel(rootNode));
        //archivesStructure();
        //archivesTree.setBounds(875, 150, 250, 500);
        archivesTree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                
            }
        });
        archivesStructure();
        //Se crea el objeto de la barra scroll
        treeScroll = new JScrollPane(archivesTree);
        treeScroll.setBounds(840, 150, 250, 500);
        treeScroll.setVisible(true);
        treeScroll.setViewportView(archivesTree);
        managerFrame.add(treeScroll);
    
        //label que indica el arbol de reportes
        JLabel reportsLabel = new JLabel("Reportes: ");
        reportsLabel.setLayout(null);
        reportsLabel.setVisible(true);
        reportsLabel.setForeground(Color.BLACK);
        reportsLabel.setBounds(840,90,250,60);
        reportsLabel.setFont(font3);
        managerFrame.add(reportsLabel);
        
        
        JButton errorButton = new JButton("Html Errores");
        errorButton.setLayout(null);
        errorButton.setVisible(true);
        errorButton.setBounds(1060, 10, 200, 60);
        errorButton.setBackground(Color.green);
        errorButton.setForeground(Color.blue);
        errorButton.setFont(new Font("cooper black",3,20));
        
        errorButton.addMouseListener(new MouseAdapter()  
                {  
                    public void mouseClicked(MouseEvent e)  
                    {  
                        
                        System.out.println("Errores:");
                        errorDaoHandler.errorsReport();
                    }
            }); 
        managerFrame.add(errorButton);
        
        
        
        managerFrame.repaint();
            
            
            
            
        }
    
    
    
    public static void archivesStructure(){
        
        //Rutas de las carpetas que estarán en el JTree
        String[] routes = {"C:/Users/Jose/Documents/GitHub/OLC1_Proyecto1_1S2023_202100308_C/EXREGAN/Reportes/AFND_202100308","C:/Users/Jose/Documents/GitHub/OLC1_Proyecto1_1S2023_202100308_C/EXREGAN/Reportes/ARBOLES_202100308","C:/Users/Jose/Documents/GitHub/OLC1_Proyecto1_1S2023_202100308_C/EXREGAN/Reportes/SIGUIENTES_202100308","C:/Users/Jose/Documents/GitHub/OLC1_Proyecto1_1S2023_202100308_C/EXREGAN/Reportes/TRANSICIONES_202100308","C:/Users/Jose/Documents/GitHub/OLC1_Proyecto1_1S2023_202100308_C/EXREGAN/Reportes/AFD_202100308"};
        //Nodo raíz que lleva el título "Reportes"
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Reportes");
        //Por cada ruta:
        for(int i=0; i<routes.length; i++){
            //Se crea un nuevo contenedor
            File container = new File(routes[i]);
            //Cada contenedor tendrá una lista
            String[] list = container.list();
            DefaultMutableTreeNode containerSon = new DefaultMutableTreeNode(routes[i]);
            if(list!=null){
            for (int j=0; j< list.length; j++) {
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(list[j]);
                    containerSon.add(node);
                }
            }
            root.add(containerSon);
            }
        DefaultTreeModel model =  new DefaultTreeModel(root);
        archivesTree.setModel(model);
            
    }
    
    
        
}
       
        
