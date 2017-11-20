package source;

/**
 *
 * @author Burak DEMİRCİ
 *  141044091
 *  burakdemirci@gtu.edu.tr
 */

public abstract class Ability extends Animal{
    
    String description = " unknown abiliyt";
    Animal animal ;
    
    @Override 
    public String getDescprition (){
        return description + super.getDescprition();
    }
    
    @Override 
    public abstract double multiplier(); 
    
}
