package Entrega;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ProgramaPrincipal {
	
	static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        // Crear una instancia de la clase Ficheros  
        Ficheros ficheros = new Ficheros();
        
        // Cargar las p�ginas desde los archivos
        //HashMap<Integer, String> paginas = ficheros.cargarPaginas();
        //HashMap<Integer, ArrayList<Integer>> enlaces = ficheros.cargarEnlaces();
       // ArrayList<String> listaPalabras = ficheros.cargarPalabras();
        ArrayList<String> listaUrl = ficheros.guardarUrlLista();
        ArrayList<String> listaOrdenada= obtenerListaOrdenada(listaUrl);
        // Guardar el HashMap de URLs en el archivo (opcional)
        //ficheros.guardarHashMapEnArchivo(paginas);
        
        // Buscar p�gina en el HashMap
       // buscarPaginaPorIndice(paginas);
        //buscarEnlace(enlaces);
       // anadirPaginaYEnlaces(paginas, enlaces, ficheros);
        //eliminarPagina(paginas, enlaces, ficheros);
       // obtenerListaOrdenada(listaPalabras);
        Imprimir(listaOrdenada);
        //ficheros.cargarPalabras();
        //comprobarPalabras(listaPalabras, paginas);
    }

    public static Pagina buscarPaginaPorIndice(HashMap<Integer, String> paginas) {
        System.out.println("Introduce el �ndice de la p�gina que quieres buscar: ");
        int indice = scanner.nextInt();
        
        // Verificar si el �ndice existe en el HashMap
        if (paginas.containsKey(indice)) {
            String url = paginas.get(indice);
            Pagina pagina = new Pagina(indice, url, new ArrayList<>()); // Crear el objeto Pagina con la URL y el �ndice
            System.out.println("P�gina encontrada: " + url);
            return pagina; // Se encontr� la p�gina
        } else {
            System.out.println("No se encontr� ninguna p�gina con ese �ndice.");
        }
        return null; // No se encontr� ninguna p�gina
    }
    
    

    public static Pagina buscarEnlace(HashMap<Integer, ArrayList<Integer>> enlaces) {
        System.out.println("Introduce el �ndice de la p�gina que quieres saber los enlaces: ");
        int indice = scanner.nextInt();
        
        // Verificar si el �ndice existe en el HashMap
        if (enlaces.containsKey(indice)) {
            ArrayList<Integer> lista = enlaces.get(indice);
            Pagina pagina = new Pagina(indice, null, new ArrayList<>()); // Crear el objeto Pagina con la URL y el �ndice
            System.out.println("Enlaces encontrados: " + lista);
            return pagina; // Se encontr� la p�gina
        } else {
            System.out.println("No se encontr� ninguna p�gina con ese �ndice.");
        }
        return null; // No se encontr� ninguna p�gina
    }
    
    public static Pagina buscarPaginaPorIndice(HashMap<Integer, String> paginas, HashMap<Integer, ArrayList<Integer>> enlacesPaginas) {
        System.out.println("Introduce el �ndice de la p�gina que quieres buscar: ");
        int indice = scanner.nextInt();
        
        // Verificar si el �ndice existe en el HashMap de p�ginas
        if (paginas.containsKey(indice)) {
            String url = paginas.get(indice);
            ArrayList<Integer> enlaces = enlacesPaginas.getOrDefault(indice, new ArrayList<>()); // Obtener los enlaces si existen
            Pagina pagina = new Pagina(indice, url, enlaces); // Crear el objeto Pagina con la URL, �ndice y enlaces
            return pagina; // Se encontr� la p�gina
        } else {
            System.out.println("No se encontr� ninguna p�gina con ese �ndice.");
        }
        return null; // No se encontr� ninguna p�gina
    }

    // M�todo para a�adir una nueva p�gina web junto con sus enlaces
    public static void anadirPaginaYEnlaces(HashMap<Integer, String> paginas, HashMap<Integer, ArrayList<Integer>> enlacesPaginas, Ficheros ficheros) {

        int nuevoIndice ;
         // Consumir el salto de l�nea
        
        while (true) {
            System.out.println("Introduce el �ndice de la nueva p�gina: ");
            nuevoIndice = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de l�nea
            
            if (paginas.containsKey(nuevoIndice)) {
                System.out.println("El �ndice ya existe. Por favor, introduce un �ndice diferente.");
            } else {
                break; // Salir del bucle si el �ndice no existe
            }
        }

        System.out.println("Introduce la URL de la nueva p�gina: ");
        String nuevaUrl = scanner.nextLine();
        
        // A�adir la nueva p�gina al HashMap
        paginas.put(nuevoIndice, nuevaUrl);
        
        // Inicializar la lista de enlaces para la nueva p�gina
        ArrayList<Integer> nuevosEnlaces = new ArrayList<>();

        System.out.println("�Quieres a�adir enlaces a esta p�gina? (s/n)");
        String respuesta = scanner.nextLine().toLowerCase();

        if (respuesta.equals("s")) {
            System.out.println("Introduce los enlaces (�ndices de otras p�ginas) separados por espacios: ");
            String[] enlacesInput = scanner.nextLine().split(" ");
            
            for (String enlaceStr : enlacesInput) {
                try {
                    int enlace = Integer.parseInt(enlaceStr);
                    nuevosEnlaces.add(enlace);
                } catch (NumberFormatException e) {
                    System.out.println("Enlace inv�lido: " + enlaceStr);
                }
            }
        }

        // A�adir los nuevos enlaces al HashMap
        enlacesPaginas.put(nuevoIndice, nuevosEnlaces);
        
        // Guardar los cambios en el archivo
        ficheros.guardarPaginasEnArchivo(paginas);
        ficheros.guardarEnlacesEnArchivo(enlacesPaginas);
        System.out.println("Nueva p�gina a�adida con �xito: " + nuevoIndice + " -> " + nuevaUrl);
        System.out.println("Enlaces a�adidos: " + nuevosEnlaces);
    }
    
    public static void eliminarPagina(HashMap<Integer, String> paginas, HashMap<Integer, ArrayList<Integer>> enlacesPaginas, Ficheros ficheros) {
    	
        int indiceEliminar ;
       
    	 while (true) {
             System.out.println("Introduce el �ndice de la p�gina que quieres eliminar: ");
             indiceEliminar = scanner.nextInt();
             scanner.nextLine(); // Consumir el salto de l�nea
             
             if (!paginas.containsKey(indiceEliminar)) {
                 System.out.println("El �ndice no existe. Por favor, introduce un �ndice diferente.");
             } else {
                 break; // Salir del bucle si el �ndice no existe
             }
         }

        // Eliminar la p�gina del HashMap de URLs
        paginas.remove(indiceEliminar);
        
        // Eliminar los enlaces de la p�gina en el HashMap de enlaces
        enlacesPaginas.remove(indiceEliminar);
        
        // Guardar los cambios en los archivos
        ficheros.guardarPaginasEnArchivo(paginas);
        ficheros.guardarEnlacesEnArchivo(enlacesPaginas);
        
        System.out.println("P�gina con �ndice " + indiceEliminar + " eliminada exitosamente.");
    }
    
    public static ArrayList<Pagina> comprobarPalabras(ArrayList<String> listaPalabras, HashMap<Integer,String> paginas) {
    	System.out.println("Introduce una palabra para buscar en las URLs:");
        String palabraBuscar = scanner.nextLine();

        ArrayList<Pagina> paginasConPalabra = new ArrayList<>(); // Lista para almacenar las p�ginas encontradas

        // Verificar si la palabra est� en la lista
        if (listaPalabras.contains(palabraBuscar)) {
          
        	// Buscar en el HashMap las p�ginas cuya URL contenga la palabra
            for (Map.Entry<Integer, String> entry : paginas.entrySet()) {
                String url = entry.getValue();  // Obtener la URL (valor del HashMap)
                if (url.contains(palabraBuscar)) {
                    System.out.println("P�gina encontrada con la palabra: " + url);
                    Pagina pagina = new Pagina(entry.getKey(), url, null); // Crear un objeto P�gina con el �ndice y la URL
                    paginasConPalabra.add(pagina);  // A�adir a la lista de resultados
                }
                }
                if (paginasConPalabra.isEmpty()) {
                    System.out.println("La palabra est� en la lista de palabras, pero no se encontr� en ninguna URL.");
            }
        } else {
            System.out.println("La palabra no est� en la lista de palabras.");
        }

        return paginasConPalabra; // Devolvemos todas las p�ginas encontradas
    }
    
    public static ArrayList<String> obtenerListaOrdenada(ArrayList<String> listaUrl) {
        // Crear una copia de la lista original
        ArrayList<String> listaOrdenada = new ArrayList<>(listaUrl);

        // Implementaci�n del Quick Sort dentro de un solo m�todo
        quickSort(listaOrdenada, 0, listaOrdenada.size() - 1);

        return listaOrdenada;  // Devolver la lista ordenada
    }

    private static void quickSort(ArrayList<String> lista, int bajo, int alto) {
        if (bajo < alto) {
            // Elegir el pivote y particionar la lista
            String pivote = lista.get((bajo + alto) / 2);  // Elegir el pivote en el medio
            int i = bajo;
            int j = alto;

            while (i <= j) {
                // Encuentra un elemento mayor o igual que el pivote desde la izquierda
                while (lista.get(i).compareTo(pivote) < 0) {
                    i++;
                }
                // Encuentra un elemento menor o igual que el pivote desde la derecha
                while (lista.get(j).compareTo(pivote) > 0) {
                    j--;
                }
                // Intercambia elementos y avanza los �ndices
                if (i <= j) {
                    String temp = lista.get(i);
                    lista.set(i, lista.get(j));
                    lista.set(j, temp);
                    i++;
                    j--;
                }
            }

            // Llama recursivamente a quickSort en las particiones
            if (bajo < j) {
                quickSort(lista, bajo, j);
            }
            if (i < alto) {
                quickSort(lista, i, alto);
            }
        }
    }
    
    public static void Imprimir(ArrayList<String>listaOrdenada) {
    	for (String a : listaOrdenada) {
            System.out.println(a); // Escribir cada URL en una l�nea nueva
        }
    }
		
}
   


