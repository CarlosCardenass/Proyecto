/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arreglotokens;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;

/**
 *
 * @author ALUMNOS 20-2
 */
public class ArregloTokens {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String nombreArchivo;
        Scanner s = new Scanner(System.in);
        System.out.println("ingresa el nombre del archivo:");
        nombreArchivo = s.next();

        //verificar existencia
        //debe ser booleano
        metodoVerificar(nombreArchivo);

        // array list de caracteres
        ArrayList<Character> ArregloTokens = new ArrayList<Character>();

        try {
            ArregloTokens = leerArreglo(ArregloTokens, nombreArchivo);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(ArregloTokens.class.getName()).log(Level.SEVERE, null, ex);
        }

        ////PASO 3 METODO QUE ENSAMBLE LOS TOKENS
        /////Entrada recibe un arreglo de caracteres
        ////Salida un arreglo de Strings
        ////Excepciones: no hay 
        ArrayList<String> ensamblado = new ArrayList<String>();
        ensamblado = ensamblarTokens(ArregloTokens);
        
        
        ////PASO 4 ANALIZA DE TOKENS 
        
        for(int i=0; i<ensamblado.size(); i++){
            metodoAnalizador(ensamblado.get(i));
            
        }
        
        
    }
    
    ////metodo 3 analiza el array ensamblado y casifica de acuerdo al tipo
    
    public static String metodoAnalizador (String posicionArray){
        String resultado="";
        
        if(posicionArray.equals("leer")){
            print("<read>, leer");
        }else {
            print("<Desconocido"+ posicionArray);
        }
        return resultado;
    }

    public static ArrayList<String> ensamblarTokens(ArrayList<Character> ArregloTokens) {
        ArrayList<String> resultado = new ArrayList<String>();
        int contador = 0;
        String concatenado = "";
        while (contador < ArregloTokens.size()) {
            //////Si no encuenttra el espacio e blanco guarda en concatenado
            if (!ArregloTokens.get(contador).equals(' ')) {
                //concatenado=concatenado+ArregloTokens.get(contador);
                switch (ArregloTokens.get(contador)){
                    case '\n':
                        resultado.add(concatenado);
                        concatenado="";
                        contador++;
                        break;
                    case '\r':
                        resultado.add(concatenado);
                        concatenado="";
                        contador++;
                        break;
                    default:
                        concatenado+=ArregloTokens.get(contador);
                        contador++;
                        break;
                }

            } else {///// para el espacio vacio
                ///Cuando se encuena un espacio en blanco se
                //guarda toda la cadena acumulada en una
                //posicion del arreglo resultado
                resultado.add(concatenado);
                print(concatenado);
                concatenado="";
                contador++;

            }
        }
        
        ///For imprime el contenido del array resultado
        for(int i =0; i<resultado.size(); i++){
            print(resultado + "["+i+"]="+ resultado.get(i));
        }

        return resultado;
    }
        ////metodo descompone el archvo y lo guarda en un array
    public static ArrayList<Character> leerArreglo(ArrayList<Character> arregloTokens, String nombreArchivo) throws IOException {
        FileInputStream archivoEntrada = new FileInputStream(nombreArchivo);
        int atChar;
        int indiceActual = 0;
        while ((atChar = archivoEntrada.read()) != -1) {
            if (atChar != '\n' || atChar != '\t') {
                arregloTokens.add(indiceActual, (char) atChar);
                print(atChar + "-->" + (char) (atChar));
                indiceActual++;
            }

        }
        //barrido el contenido del arreglo
        for (int i = 0; i < arregloTokens.size(); i++) {
            print(i + "-->" + arregloTokens.get(i));

        }
        return arregloTokens;
    }

    public static void metodoVerificar(String nombreArchivo) {
        boolean siExiste;
        File archivo = new File(nombreArchivo);
        siExiste = archivo.exists();
        if (!siExiste) {
            print("archivo no encontrado");
        } else {
            print("Ok");
        }
    }

    public static void print(String cadena) {
        System.out.println(cadena);
    }
}
