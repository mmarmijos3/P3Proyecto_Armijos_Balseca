package Model;

/**
 *
 * @author Armijos Manfred, Balseca Valeska
 */

/*
Interface that declares the boarding and disembarking
methods to be implemented in the child classes
*/
public interface IDockable {
    /*
    Method for filling the boat to its maximum capacity
    */
    public void boarding();
    
    /*
    Method for completely emptying the boat
    */
    public void disembarkation();
}
