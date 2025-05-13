
/**
 * Write a description of class Patinete here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Patinete extends Vehiculo
{
    
    private static float tarifa;
    
     /**
     * Constructor for objects of class Patinete
     */
    public Patinete()
    {
        super(Tipo.PATINETE);
    }
    
    public Patinete(Estado estado)
    {
        super(estado);
    }


    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void actualizarBateria() 
    {
        float a = (float)(0.5 * 30);
        super.actualizarBateria(a);
    }
    
    public void actualizarBateria(float consumo)
    {
        consumo *= 0.5;
        super.actualizarBateria(consumo);
    }
    
    
    public static void setTarifa(float tarifa){
        tarifa = tarifa;
    }
    
   
    
    public String toString()
    {
        return /*this.base.ocupados() +*/ super.toString();
    }
    
    
    
    
    
    
    
    
    
}
