package model;


import java.time.LocalDate;

public class ProductoOferta {
    private int id;
    private int idProducto;
    private int idOferta;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    // Constructor vacío
    public ProductoOferta() {}

    // Constructor con parámetros
    public ProductoOferta(int id, int idProducto, int idOferta, LocalDate fechaInicio, LocalDate fechaFin) {
        this.id = id;
        this.idProducto = idProducto;
        this.idOferta = idOferta;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdOferta() {
        return idOferta;
    }

    public void setIdOferta(int idOferta) {
        this.idOferta = idOferta;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    @Override
    public String toString() {
        return "ProductoOferta{" +
                "id=" + id +
                ", idProducto=" + idProducto +
                ", idOferta=" + idOferta +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                '}';
    }
}
