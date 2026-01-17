package Model;

/**
 *
 * @author Armijos Manfred, Balseca Valeska
 */

/*
functional interface to implement
your method using lambda functions
*/
@FunctionalInterface
public interface IBillable {
    /*
    method that calculates the boat's bill
    according to the services provided
    */
    public double bill();
}
