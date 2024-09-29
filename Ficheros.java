package Entrega;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Ficheros {
    private String rutaUrls = "C:/Users/peiol/eclipse-workspace/EntregaEDA/src/datuak-2024-2025/index-2024-25"; // Ruta de lectura de URLs
    private String rutaEnlaces = "C:/Users/peiol/eclipse-workspace/EntregaEDA/src/datuak-2024-2025/pld-arcs-1-N-2024-25"; // Ruta de lectura de enlaces
    private String rutaPalabras = "C:/Users/peiol/eclipse-workspace/EntregaEDA/src/datuak-2024-2025/words.txt";
   
    
    
    public HashMap<Integer, String> cargarPaginas() {
        HashMap<Integer, String> hashMapPaginas = new HashMap<>();
        try (Scanner entrada = new Scanner(new FileReader(rutaUrls))) {
            while (entrada.hasNextLine()) {
                String linea = entrada.nextLine();
                String[] partes = linea.split(" ::: ");
                int index = Integer.parseInt(partes[0].trim());
                String url = partes[1].trim();
                hashMapPaginas.put(index, url);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hashMapPaginas; // Retornamos el HashMap de páginas
    }
    
    public ArrayList<String> guardarUrlLista(){
    	ArrayList<String> listaUrl = new ArrayList<>();
    	try (Scanner entrada = new Scanner(new FileReader(rutaUrls))){
    		  while (entrada.hasNextLine()) {
                  String linea = entrada.nextLine();
                  String[] partes = linea.split(" ::: ");
                  int index = Integer.parseInt(partes[0].trim());
                  String url = partes[1].trim();
                  listaUrl.add(url);
              }
          } catch (IOException e) {
              e.printStackTrace();
          }
		return listaUrl;}

    public HashMap<Integer, ArrayList<Integer>> cargarEnlaces() {
        HashMap<Integer, ArrayList<Integer>> hashMapEnlaces = new HashMap<>();
        try (Scanner entrada = new Scanner(new FileReader(rutaEnlaces))) {
            while (entrada.hasNextLine()) {
                String linea = entrada.nextLine();
                String[] partes = linea.split(" >+ | #+"); // Separar por los delimitadores
                int index = Integer.parseInt(partes[0].trim());

                // Crear una lista para los enlaces
                ArrayList<Integer> enlaces = new ArrayList<>();
                for (int i = 1; i < partes.length; i++) {
                    if (!partes[i].trim().isEmpty()) {
                        int enlace = Integer.parseInt(partes[i].trim());
                        enlaces.add(enlace);
                    }
                }
                hashMapEnlaces.put(index, enlaces);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hashMapEnlaces; // Retornamos el HashMap de enlaces
    }
    
    public ArrayList<String> cargarPalabras(){
    	ArrayList<String> listaPalabras = new ArrayList<>();
    	 try (Scanner entrada = new Scanner(new FileReader(rutaPalabras))){
    		 while(entrada.hasNextLine()) {
    			 String palabra = entrada.nextLine();
    			 listaPalabras.add(palabra);
    			 //System.out.println(palabra);
    		 }
    	 }catch (IOException e) {
             e.printStackTrace();
         }
		return listaPalabras;
    	
    }

    public void guardarHashMapEnArchivo(HashMap<Integer, String> datos) {
        String rutaEscritura = "C:/Users/peiol/eclipse-workspace/EntregaEDA/src/datuak-2024-2025/datosCreados/hasMapWeb"; // Ruta de escritura
        try (FileWriter writer = new FileWriter(rutaEscritura)) {
            for (Map.Entry<Integer, String> entry : datos.entrySet()) {
                writer.write(entry.getKey() + " ::: " + entry.getValue() + "\n");
            }
            System.out.println("Archivo guardado exitosamente en " + rutaEscritura);
        } catch (IOException e) {
            System.err.println("Error al intentar guardar el archivo: " + e.getMessage());
        }
    }
    public void guardarPaginasEnArchivo(HashMap<Integer, String> paginas) {
        String rutaEscritura = "C:/Users/peiol/eclipse-workspace/EntregaEDA/src/datuak-2024-2025/datosCreados/hasMapWeb";  // Ajustar con la ruta real del archivo
        
        try (FileWriter writer = new FileWriter(rutaEscritura)) {
            for (HashMap.Entry<Integer, String> entry : paginas.entrySet()) {
                writer.write(entry.getKey() + " ::: " + entry.getValue() + "\n");
            }
            System.out.println("El HashMap de URLs se ha guardado en el archivo con éxito.");
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo de URLs: " + e.getMessage());
        }
    }

    // Método para guardar el HashMap de enlaces en un archivo
    public void guardarEnlacesEnArchivo(HashMap<Integer, ArrayList<Integer>> enlacesPaginas) {
        String rutaEscritura = "C:/Users/peiol/eclipse-workspace/EntregaEDA/src/datuak-2024-2025/datosCreados/hasMapEnlaces";  // Ajustar con la ruta real del archivo
        
        try (FileWriter writer = new FileWriter(rutaEscritura)) {
            for (HashMap.Entry<Integer, ArrayList<Integer>> entry : enlacesPaginas.entrySet()) {
                writer.write(entry.getKey() + " >>>> " + entry.getValue().toString().replaceAll("[\\[\\],]", "") + "\n");
            }
            System.out.println("El HashMap de enlaces se ha guardado en el archivo con éxito.");
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo de enlaces: " + e.getMessage());
        }
    }
    
    public void guardarListaOrdenada(ArrayList<String> listaUrl) {
        String rutaEscritura = "C:/Users/peiol/eclipse-workspace/EntregaEDA/src/datuak-2024-2025/datosCreados/listaOrdenada";  // Ajustar con la ruta real del archivo
        
        try (FileWriter writer = new FileWriter(rutaEscritura)) {
            // Recorrer la lista de URLs
            for (String url : listaUrl) {
                writer.write(url + "\n");  // Escribir cada URL en una línea nueva
            }
            System.out.println("La lista de URLs se ha guardado en el archivo con éxito.");
        } catch (IOException e) {
            System.out.println("Error al guardar la lista de URLs: " + e.getMessage());
        }
    }
}
