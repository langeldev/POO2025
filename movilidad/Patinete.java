
/**
 * Write a description of class Patinete here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Patinete extends VehiculoBase
{
     
    /**
     * Constructor for objects of class Patinete
     */
    public Patinete()
    {
        super(Tipo.PATINETE);
    }
    
    public void actualizarBateria(float consumo)
    {
        consumo *= 0.5;
        super.actualizarBateria(consumo);
    }    
    
        
    public String toString()
    {
        return super.toString();
    }
    
    
    
    
    
    
    
    
    
}
