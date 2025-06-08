
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
