package Entidad;

public class Ventas {
    int idven;
    int idnrocta;
    int idu;
    String codven;
    String fecven;
    double monven;
    int estven;

    public Ventas() {
    }

    public Ventas(int idven, int idnrocta, int idu, String codven, String fecven, double monven, int estven) {
        this.idven = idven;
        this.idnrocta = idnrocta;
        this.idu = idu;
        this.codven = codven;
        this.fecven = fecven;
        this.monven = monven;
        this.estven = estven;
    }

    public int getIdven() {
        return idven;
    }

    public void setIdven(int idven) {
        this.idven = idven;
    }

    public int getIdnrocta() {
        return idnrocta;
    }

    public void setIdnrocta(int idnrocta) {
        this.idnrocta = idnrocta;
    }

    public int getIdu() {
        return idu;
    }

    public void setIdu(int idu) {
        this.idu = idu;
    }

    public String getCodven() {
        return codven;
    }

    public void setCodven(String codven) {
        this.codven = codven;
    }

    public String getFecven() {
        return fecven;
    }

    public void setFecven(String fecven) {
        this.fecven = fecven;
    }

    public double getMonven() {
        return monven;
    }

    public void setMonven(double monven) {
        this.monven = monven;
    }

    public int getEstven() {
        return estven;
    }

    public void setEstven(int estven) {
        this.estven = estven;
    }
    
    
}
