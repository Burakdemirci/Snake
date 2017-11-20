package source;


/**
 *
 * @author Burak DEMİRCİ
 *  141044091
 *  burakdemirci@gtu.edu.tr
 */
public class ForeignLanguages extends Ability{

    String description = " Foreign Languages";
    Animal animal ;
    
    public ForeignLanguages (Animal ani)
    {
        this.animal = ani;
    }    
    
    @Override 
    public String getDescprition (){
        return description + animal.getDescprition();
    }
    
    @Override
    public double multiplier() {
       
        return 2*animal.multiplier();
    }
    
}
