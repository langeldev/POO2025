
/**
 * Write a description of class Vehiculo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Vehiculo
{
    // instance variables - replace the example below with your own
    private double bateria;
    
    public enum Estado {LIBRE, OCUPADO, FALLO, RESERVADO};
    public enum Tipo {
        PATINETE("Patinete"),BICICLETA("Bicicleta"), MOTO("Moto");
        private String nombre;
        Tipo(String nombre) {this.nombre = nombre;}
        public String toString(){return nombre;}
};
    private Estado estado = Estado.LIBRE;
    private Tipo tipo;
    
    public Vehiculo(Tipo tipo)
    {
       this.bateria = 100;
       this.tipo = tipo;
    }
    
     public Vehiculo(Estado estado)
    {
       this.estado = estado;
    }
    
    
    public void setBateria(float bateria)
    {
        this.bateria = bateria;
    }
    
    
    public double getBateria()
    {
        return this.bateria;
    }
    
    public void actualizarBateria(double consumo)
    {
        this.bateria = (this.bateria - consumo > 0) ? this.bateria - consumo: 0;
    }
    
    public Estado getEstado()
    {
        return estado;
    }
    
    public void setEstado(Estado estado)
    {
        this.estado = estado;
    }
    
    public Tipo getTipo()
    {
        return this.tipo;
    }
    
    public String toString()
    {
        return "Tipo vehiculo: " + this.tipo +" | Estado: " + this.estado + " | Bateria: "+this.bateria + "%";
    }
    
}
