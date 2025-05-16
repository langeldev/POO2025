
/**
 * Write a description of class Bicicleta here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bicicleta extends  VehiculoBase
{
 
    /**
     * Constructor for objects of class Bicicleta
     */
    public Bicicleta()
    {
        super(Tipo.BICICLETA);
    }
    
    
    public void actualizarBateria(float consumo)
    {
        consumo *= 1;
        super.actualizarBateria(consumo);
    }

    public String toString()
    {
        return super.toString();
    }
    
}
