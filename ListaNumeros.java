
import java.util.Random;
import java.util.Arrays;
/**
 * Un objeto de esta clase
 * guarda una lista de n�meros enteros
 * 
 * La clase incluye una serie de m�todos de instancia
 * para hacer operaciones sobre la lista
 * y dos  m�todos est�ticos para trabajar con
 * arrays de dos dimensiones
 *
 * @author - Asier Galisteo
 */

public class ListaNumeros {
    public static final int DIMENSION = 10;
    public static final int ANCHO_FORMATO = 6;
    public static final char CAR_CABECERA = '-';

    private static final Random generador = new Random();
    private int[] lista;
    private int pos;

    /**
     * Constructor de la clase ListaNumeros
     * Crea e inicializa adecuadamente los
     * atributos
     *
     * @param n el tama�o m�ximo de la lista
     */
    public ListaNumeros(int n) {
        lista = new int[n];
        pos = 0;
    }

    /**
     * A�ade un valor al final de la lista 
     * siempre que no est� completa
     *
     * @param numero el valor que se a�ade.  
     * @return true si se ha podido a�adir, false en otro caso
     */
    public boolean addElemento(int numero) {
        if(!estaCompleta()){
            lista[pos] = numero;
            pos++;
            return true;
        }
        return false;

    }

    /**
     * @return true si la lista est� completa, false en otro caso
     * Hacer sin if
     */
    public boolean estaCompleta() {
        return pos == lista.length;
    }

    /**
     * @return true si la lista est� vac�a, false en otro caso.
     * Hacer sin if
     */
    public boolean estaVacia() {
        return pos == 0;
    }

    /**
     * @return el n� de elementos realmente guardados en la lista
     */
    public int getTotalNumeros() {
        return pos;
    }

    /**
     * Vac�a la lista
     */
    public void vaciarLista() {
        pos = 0;
    }

    /**
     * @return una cadena con representaci�n textual de la lista 
     * (leer enunciado)
     * 
     * Si la lista est� vac�a devuelve ""
     */
    public String toString() {
        String str = "";
        if(!estaVacia()){
            str += escribirLinea();
            str += "\n";
            for (int i = 0; i < pos; i++){
                str += Utilidades.centrarNumero(lista[i], ANCHO_FORMATO); 
            }
            str += "\n";
            str += escribirLinea();
        }
        return str;
    }

    /**
     * Escribe la l�nea de puntos
     */
    private String escribirLinea()
    {
        String linea = "";
        for (int i = 1; i <= ANCHO_FORMATO * pos; i++){
            linea += CAR_CABECERA;
        }
        return linea;
    }

    /**
     * Mostrar en pantalla la lista
     */
    public void escribirLista() {
        System.out.println(this.toString());
    }

    /**
     *  
     * @return el segundo valor m�ximo en la lista
     * Cuando no haya un segundo m�ximo el m�todo 
     * devolver� el valor Integer.MIN_VALUE
     * 
     * Si lista = {21, -5, 28, -7, 28, 77, 77, -17, 21, 15, 28, 28, 77} se devuelve 28
     * Si lista = {21, -5, 28, -7, 77} se devuelve 28
     * Si lista = {77, 21} se devuelve 21
     * Si lista = {21} se devuelve Integer.MIN_VALUE
     * Si lista = {21, 21, 21, 21} se devuelve Integer.MIN_VALUE
     * 
     * No se puede usar ning�n otro array auxiliar ni hay que ordenar previamente
     * la lista
     */
    public int segundoMaximo() {       
        int primerMaximo = lista[0];
        int segundoMaximo = Integer.MIN_VALUE;
        for(int i = 1; i < pos; i++){
            if(lista[i] > primerMaximo){
                segundoMaximo = primerMaximo;
                primerMaximo = lista[i];
            }
            else if(lista[i] == primerMaximo){
                primerMaximo = lista[i];
            }
            else{
                if(lista[i] > segundoMaximo){
                    segundoMaximo = lista[i];
                }
            }
        }
        return segundoMaximo;
    }

    /**
     * El m�todo coloca los valores que son segundos m�ximos al principio de
     * la lista respetando el orden de aparici�n del resto de elementos
     * 
     * No se puede usar ning�n otro array auxiliar ni hay que ordenar previamente
     * la lista
     * 
     * Si lista = {21, -5, 28, -7, 28, 77, 77, -17, 21, 15, 28, 28, 77} 
     * lista queda  {28, 28, 28, 28, 21, -5, -7, 77, 77, -17, 21, 15, 77} y se devuelve true
     * 
     * Si lista = {77, 21} lista queda {21, 77} y se devuelve true
     * Si lista = {21} lista queda igual y se devuelve false
     * Si lista = {21, 21, 21, 21} lista queda igual y se devuelve false
     * 
     * @return true si se han colocado los segundos m�ximos
     *          false si no se han colocado los segundos m�ximos porque no hab�a ninguno
     */
    public boolean segundosMaximosAlPrincipio() {
        int segundoMaximo = segundoMaximo();
        if(segundoMaximo != Integer.MIN_VALUE){
            for(int i = 0; i < pos; i++){
                if(lista[i] == segundoMaximo){
                    int valor = lista[i];
                    System.arraycopy(lista,0,lista,1,i);
                    lista[0] = valor;
                }
            }
        }
        else{
            return false;
        }
        return true;
    }

    /**
     * @param numero b�squeda binaria de  numero en lista
     * @return devuelve -1 si no se encuentra o la posici�n en la que aparece
     *  
     * El array original lista no se modifica
     * Para ello crea  previamente una copia
     * de lista y trabaja  con la copia
     *  
     * Usa exclusivamente m�todos de la clase Arrays
     */
    public int buscarBinario(int numero) {
        int[] copia = Arrays.copyOf(lista,pos);
        Arrays.sort(copia);
        int posicion = Arrays.binarySearch(copia,numero);
        return posicion;
    }

    /**
     * 
     * @return devuelve un array bidimensional de enteros de tama�o DIMENSION
     * inicializado con valores aleatorios entre 0 y 10 inclusive
     * 
     * Estos valores van a representar el brillo de una zona del espacio
     * 
     */
    public static  int [][] crearBrillos() {
        int [][] brillos = new int [DIMENSION][DIMENSION];
        for (int fila = 0 ; fila < brillos.length; fila++){
            for(int columna = 0; columna < brillos[fila].length; columna++){
                brillos[fila][columna] = generador.nextInt(11);
            }
        }
        return brillos;
    }

    /**
     * @param  un array bidimensional brillos 
     * @return un nuevo array bidimensional de valores booleanos
     *          de las mismas dimensiones que el array brillos con
     *          valores true en las posiciones donde hay estrellas
     * 
     * Una posici�n f,c del array brillos es una estrella 
     * si la suma del valor de los brillos de sus cuatro vecinos 
     * (arriba, abajo, derecha e izquierda) es mayor que 30
     * 
     * Nota -  No hay estrellas en los bordes del array brillos
     */
    public static boolean[][] detectarEstrellas(int[][] brillos) {
        boolean [][]estrellas = new boolean [brillos.length][brillos[0].length];
        for (int fila = 1 ; fila < estrellas.length - 1; fila++){
            for(int columna = 1; columna < estrellas[fila].length - 1; columna++){
                if(mayorQue30(brillos,fila,columna)){
                    estrellas[fila][columna] = true;
                } 
            }
        }
        return estrellas;
    }
    
    /**
     * @ param la posici�n en el array
     * @return devuelve true si la suma de los vecinos es mayor que 30
     */
    private static boolean mayorQue30(int[][] brillos,int f, int c)
    {
        return brillos[f - 1][c] + brillos[f][c - 1] + brillos[f + 1][c] + brillos[f][c + 1] > 30 ;
    }

}
