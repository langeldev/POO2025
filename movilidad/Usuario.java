public class Usuario
{
    
    private boolean premium;
    public Usuario()
    {
     this.premium = false;
    }

    public boolean isPremium()
    {
        return this.premium;
    }
    
    public void setPremium(boolean premium)
    {
        this.premium = premium;
    }
}
