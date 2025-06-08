
public abstract class VehiculoBase extends Vehiculo
{
    private Base base;

    public VehiculoBase(Vehiculo.Tipo tipo)
    {
         super(tipo);
         this.base = null;
    }

   public void setBase(Base base)
    {
        this.base = base;
    }
    
    public Base getBase()
    {
        return this.base;
    }

}
