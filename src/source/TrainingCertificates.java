package source;
/**
 *
 * @author Burak DEMİRCİ
 *  141044091
 *  burakdemirci@gtu.edu.tr
 */
public class TrainingCertificates extends Ability{
    
    Animal animal;
    String description = " Training Certificates";
   
    public TrainingCertificates(Animal ani){
         this.animal=ani;
    }
    
    @Override 
    public String getDescprition (){
        return description + animal.getDescprition();
    }
    
    @Override
    public double multiplier() {
       return 3*animal.multiplier();
    }
    
}
