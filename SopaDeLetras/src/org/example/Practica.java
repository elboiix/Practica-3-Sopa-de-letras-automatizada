package org.example;
import java.util.Arrays;
import java.util.Scanner;
public class Practica {
    public void Ejercicio(){

        Scanner entrada = new Scanner(System.in);

        String filas,columnas, palabra;
        int filas_int,columnas_int, letras_fila_acertadas, letras_columna_acertadas;

        MainBucle:
        while (true) {
            try{
                System.out.println("Introduce el número de filas:");
                filas = entrada.nextLine();
                filas_int = Integer.parseInt(filas);
                System.out.println("Introduce el número de columnas:");
                columnas = entrada.nextLine();
                columnas_int = Integer.parseInt(columnas);
                                                        //Pido el numero en string con nextline ya que si me ponen dos numero separados por un espacio al pasarlo a int da error y el catch lo pilla.

                if (filas_int <= 0 || columnas_int <= 0){
                    System.out.println("ERORR. Introduce el número de filas/columnas correctamente...");
                    continue;
                }                                       //Me aseguro que el número es mayor a 0.

            }catch (NumberFormatException err) {
                System.out.println("ERROR. Introduce el número de filas/columnas correctamente...");
                continue;
            }

            String sopa[][] = new String[filas_int][columnas_int];  //Creo la matriz de sopa.

            for (int i = 0; i < sopa.length; i++){
                System.out.println("Introduce las letras de la fila " + (i + 1) + ":");
                String fila_vector = entrada.nextLine();
                if (!fila_vector.matches("[A-Za-z]+") ){     //Me aseguro que son solo letras mediante un .matches().
                    System.out.println("ERROR. Introduce solo letras...");
                    continue MainBucle;
                }
                String fila_vector_split[] = fila_vector.split("");   //Paso cada fila introducida a vector.

                if(fila_vector_split.length != columnas_int){
                    System.out.println("ERORR. Introduce datos válidos: " + filas_int + " letras.");
                    continue MainBucle;
                }
                for(int j = 0; j < sopa[i].length; j++){    //Paso cada vector a fila de la matriz.
                    sopa[i][j] = fila_vector_split[j];
                }
            }

            for(String[] fila : sopa){
                for(String columna : fila){       //Muestro la matriz en pantalla.
                    System.out.print(columna + " ");
                }
                System.out.println();
            }

            System.out.println("Introduce la palabra a buscar:");
            palabra = entrada.nextLine();                                    //Pido la palabra a buscar y la paso a vector.

            if (!palabra.matches("[A-Za-z]+")){
                System.out.println("ERROR. Introduce solo letras...");
                continue;
            }

            if (palabra.length() > columnas_int &&  palabra.length() > filas_int){
                System.out.println("ERORR. La longitud de la palabra buscada supera los limites de la sopa.");
                continue;
            }

            String palabra_split[] = palabra.split("");

            for (int i = 0; i < sopa.length; i++){
                for (int j = 0; j < sopa[i].length; j++){
                    if (sopa[i][j].equalsIgnoreCase(palabra_split[0])){       //Con estos bucles voy mirando los valores de cada fila y si en alguna fila es igual el valor a la inicial de la palabra, y si es igual va a comprobar que hay espacio a su derecha, si lo hay va a ir comparando cada letra y si acierta en todos los valores dice la posición.
                        if (sopa[i].length - j >= palabra_split.length){
                            letras_fila_acertadas = 0;
                            for (int f = 0; f < palabra_split.length; f++){
                                if (palabra_split[f].equalsIgnoreCase(sopa[i][j + f])){
                                    letras_fila_acertadas++;
                                } else {
                                    break;
                                }
                            }
                            if (letras_fila_acertadas == palabra_split.length){
                                System.out.println("Encontrada!!! En la posición " + i + " " + j); //Si acierta en todas las letras te dice la posición y te saca.
                                return;
                            }
                        }
                    }
                }
            }
            for (int j = 0; j < sopa[0].length; j++){
                for (int i = 0; i < sopa.length; i++){
                    if (sopa[i][j].equalsIgnoreCase(palabra_split[0])){
                        if (sopa.length - i >= palabra_split.length){
                            letras_columna_acertadas = 0;
                            for (int c = 0; c < palabra_split.length; c++){
                                if (palabra_split[c].equalsIgnoreCase(sopa[i + c][j])){             //Aqui hace lo mismo que los anteriores bucles anidados pero aqui comprueba si la palabra esta en forma de columna.
                                    letras_columna_acertadas++;
                                } else{
                                    break;
                                }
                            }
                            if (letras_columna_acertadas == palabra_split.length){
                                System.out.println("Encontrada!!! En la posición " + i + " " + j);  //Si acierta en todas las letras te dice la posición y te saca.
                                return;
                            }
                        }
                    }
                }
            }
            System.out.println("No se ha encontrado la palabra buscada");     //Si no cumple ningun if de los anteriores el programa dice que no hay palabra y te saca.
            return;
        }
    }
}
