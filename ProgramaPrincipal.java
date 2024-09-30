package Entrega;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ProgramaPrincipal {
	
	static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        Ficheros ficheros = new Ficheros();
        
        // Cargar las páginas desde los archivos
        //HashMap<Integer, String> paginas = ficheros.cargarPaginas();
        //HashMap<Integer, ArrayList<Integer>> enlaces = ficheros.cargarEnlaces();
       // ArrayList<String> listaPalabras = ficheros.cargarPalabras();
        ArrayList<String> listaUrl = ficheros.guardarUrlLista();
        ArrayList<String> listaOrdenada= obtenerListaOrdenada(listaUrl);
        // Guardar el HashMap de URLs en el archivo (opcional)
        //ficheros.guardarHashMapEnArchivo(paginas);
        
        // Buscar página en el HashMap
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
        System.out.println("Introduce el índice de la página que quieres buscar: ");
        int indice = scanner.nextInt();
        
        if (paginas.containsKey(indice)) {
            String url = paginas.get(indice);
            Pagina pagina = new Pagina(indice, url, new ArrayList<>()); // Crear el objeto Pagina con la URL y el índice
            System.out.println("Página encontrada: " + url);
            return pagina; // Se encontró la página
        } else {
            System.out.println("No se encontró ninguna página con ese índice.");
        }
        return null; 
    }
    
    

    public static Pagina buscarEnlace(HashMap<Integer, ArrayList<Integer>> enlaces) {
        System.out.println("Introduce el índice de la página que quieres saber los enlaces: ");
        int indice = scanner.nextInt();
        if (enlaces.containsKey(indice)) {
            ArrayList<Integer> lista = enlaces.get(indice);
            Pagina pagina = new Pagina(indice, null, new ArrayList<>()); // Crear el objeto Pagina con la URL y el índice
            System.out.println("Enlaces encontrados: " + lista);
            return pagina; // Se encontró la página
        } else {
            System.out.println("No se encontró ninguna página con ese índice.");
        }
        return null; // No se encontró ninguna página
    }
    
    public static Pagina buscarPaginaPorIndice(HashMap<Integer, String> paginas, HashMap<Integer, ArrayList<Integer>> enlacesPaginas) {
        System.out.println("Introduce el índice de la página que quieres buscar: ");
        int indice = scanner.nextInt();
        if (paginas.containsKey(indice)) {
            String url = paginas.get(indice);
            ArrayList<Integer> enlaces = enlacesPaginas.getOrDefault(indice, new ArrayList<>());
            Pagina pagina = new Pagina(indice, url, enlaces); 
            return pagina; 
        } else {
            System.out.println("No se encontró ninguna página con ese índice.");
        }
        return null; 
    }
    public static void anadirPaginaYEnlaces(HashMap<Integer, String> paginas, HashMap<Integer, ArrayList<Integer>> enlacesPaginas, Ficheros ficheros) {

        int nuevoIndice ;
         while (true) {
            System.out.println("Introduce el índice de la nueva página: ");
            nuevoIndice = scanner.nextInt();
            scanner.nextLine(); 
            
            if (paginas.containsKey(nuevoIndice)) {
                System.out.println("El índice ya existe. Por favor, introduce un índice diferente.");
            } else {
                break; 
            }
        }

        System.out.println("Introduce la URL de la nueva página: ");
        String nuevaUrl = scanner.nextLine();
        paginas.put(nuevoIndice, nuevaUrl);
        ArrayList<Integer> nuevosEnlaces = new ArrayList<>();
        System.out.println("¿Quieres añadir enlaces a esta página? (s/n)");
        String respuesta = scanner.nextLine().toLowerCase();
	 if (respuesta.equals("s")) {
            System.out.println("Introduce los enlaces (índices de otras páginas) separados por espacios: ");
            String[] enlacesInput = scanner.nextLine().split(" ");
            
	for (String enlaceStr : enlacesInput) {
                try {
                    int enlace = Integer.parseInt(enlaceStr);
                    nuevosEnlaces.add(enlace);
                } catch (NumberFormatException e) {
                    System.out.println("Enlace inválido: " + enlaceStr);
                }
            }
        }
        enlacesPaginas.put(nuevoIndice, nuevosEnlaces);
        
        ficheros.guardarPaginasEnArchivo(paginas);
        ficheros.guardarEnlacesEnArchivo(enlacesPaginas);
    }
    
    public static void eliminarPagina(HashMap<Integer, String> paginas, HashMap<Integer, ArrayList<Integer>> enlacesPaginas, Ficheros ficheros) {
    	
        int indiceEliminar ;
       
    	 while (true) {
             System.out.println("Introduce el índice de la página que quieres eliminar: ");
             indiceEliminar = scanner.nextInt();
             scanner.nextLine(); 
             
             if (!paginas.containsKey(indiceEliminar)) {
                 System.out.println("El índice no existe. Por favor, introduce un índice diferente.");
             } else {
                 break; 
             }
         }
        paginas.remove(indiceEliminar);
        enlacesPaginas.remove(indiceEliminar);
        ficheros.guardarPaginasEnArchivo(paginas);
        ficheros.guardarEnlacesEnArchivo(enlacesPaginas);
    }
    
    public static ArrayList<Pagina> comprobarPalabras(ArrayList<String> listaPalabras, HashMap<Integer,String> paginas) {
    	System.out.println("Introduce una palabra para buscar en las URLs:");
        String palabraBuscar = scanner.nextLine();

        ArrayList<Pagina> paginasConPalabra = new ArrayList<>(); 
        if (listaPalabras.contains(palabraBuscar)) {
            for (Map.Entry<Integer, String> entry : paginas.entrySet()) {
                String url = entry.getValue();  
                if (url.contains(palabraBuscar)) {
                    System.out.println("Página encontrada con la palabra: " + url);
                    Pagina pagina = new Pagina(entry.getKey(), url, null);
                    paginasConPalabra.add(pagina);  
                }
                }
                if (paginasConPalabra.isEmpty()) {
                    System.out.println("La palabra está en la lista de palabras, pero no se encontró en ninguna URL.");
            }
        } else {
            System.out.println("La palabra no está en la lista de palabras.");
        }
        return paginasConPalabra; 
    }
    
    public static ArrayList<String> obtenerListaOrdenada(ArrayList<String> listaUrl) {
        ArrayList<String> listaOrdenada = new ArrayList<>(listaUrl);
        quickSort(listaOrdenada, 0, listaOrdenada.size() - 1);
        return listaOrdenada;  // Devolver la lista ordenada
    }

    private static void quickSort(ArrayList<String> lista, int bajo, int alto) {
        if (bajo < alto) {
            String pivote = lista.get((bajo + alto) / 2);  
            int i = bajo;
            int j = alto;
            while (i <= j) {
                while (lista.get(i).compareTo(pivote) < 0) {
                    i++;
                }
                while (lista.get(j).compareTo(pivote) > 0) {
                    j--;
                }
                if (i <= j) {
                    String temp = lista.get(i);
                    lista.set(i, lista.get(j));
                    lista.set(j, temp);
                    i++;
                    j--;
                }
            }
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
            System.out.println(a);
        }
    }
		
}
   


