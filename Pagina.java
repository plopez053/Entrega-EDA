package Entrega;

import java.util.ArrayList;

public class Pagina {

    private int index; 
    private String url;
    private ArrayList<Integer> enlaces;  

    public Pagina(int pIndex, String pUrl, ArrayList<Integer> pEnlaces) {
        this.index = pIndex;
        this.url = pUrl;
       if (pEnlaces != null) {
    this.enlaces = pEnlaces;
} else {
    this.enlaces = new ArrayList<>();
}
    }

    public int getIndex() {
        return this.index;
    }

    public String getUrl() {
        return this.url;
    }

    public ArrayList<Integer> getEnlaces() {
        return this.enlaces;
    }

    
}
