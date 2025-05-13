
/**
 * Write a description of class Moto here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Moto extends Vehiculo
{
    // instance variables - replace the example below with your own
    private int cilindrada;
    private Coordenadas coordenadas;

    public Moto()
    {
         super(Tipo.MOTO);
    }
    
    /**
     * Constructor for objects of class Moto
     */
    public Moto(Coordenadas coordenadas)
    {
        // initialise instance variables
        super(Tipo.MOTO);
        this.coordenadas = coordenadas;
      
    }
    
    public void actualizarBateria(float consumo)
    {
        consumo *= 0.4;
        super.actualizarBateria(consumo);
    }
    
    public void setCoordenadas(Coordenadas coordenadas)
    {
        this.coordenadas = coordenadas;
    }
    
    
    public Coordenadas getCoordenadas()
    {
        return this.coordenadas;
    }
    
    public String toString()
    {
        return this.coordenadas + " " +  super.toString();
    }

  
}
