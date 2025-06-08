
public class MotoMayor extends Vehiculo
{
    // instance variables - replace the example below with your own
    private Coordenadas coordenadas;

    /**
     * Constructor for objects of class Moto
     */
    public MotoMayor(Coordenadas coordenadas)
    {
        // initialise instance variables
        super(Tipo.MOTO_MAYOR);
        this.coordenadas = coordenadas;
      
    }
    
    public void actualizarBateria(float consumo)
    {
        consumo *= 0.25;
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
        return (this.coordenadas == null? "En mantenimieto": this.coordenadas) + " " +  super.toString();
    }

}
