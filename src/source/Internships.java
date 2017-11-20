package source;

/**
 *
 * @author Burak DEMİRCİ
 *  141044091
 *  burakdemirci@gtu.edu.tr
 */
public class Internships extends Ability{
    
    Animal animal;
    String description = " Internships";
   
    public Internships(Animal ani){
         this.animal=ani;
    }
    
    @Override 
    public String getDescprition (){
        return description + animal.getDescprition();
    }
    
    @Override
    public double multiplier() {
       return 1.5*animal.multiplier();
    }
    
    
}
