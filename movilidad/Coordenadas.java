
public class Coordenadas
{
    private int x,y;

    /**
     * Constructor for objects of class Coordenadas
     */
    public Coordenadas(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    public int getX()
    {
        return this.x;
    }
    
    public void setX(int x)
    {
        this.x = x;
    }
    
    public int getY()
    {
        return this.y;
    }
    
    public void setY(int y)
    {
        this.y = y;
    }
 
    public double d(double A, double B){
        return Math.sqrt(Math.pow(A, this.x)+ Math.pow(B,this.y));
    }
    
    public String toString()
    {
        return "coordenadas: (" +this.x + ", " + this.y + ")";
    }
}
