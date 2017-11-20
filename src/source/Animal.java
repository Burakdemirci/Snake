package source;

/**
 *
 * @author Burak DEMİRCİ
 *  141044091
 *  burakdemirci@gtu.edu.tr
 */

public abstract class Animal {
    String description = "An animal";
    
    public String getDescprition (){
        return description;
    }
    public abstract double multiplier(); 
}
