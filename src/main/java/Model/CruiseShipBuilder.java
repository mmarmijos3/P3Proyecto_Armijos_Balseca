
package Model;

/**
 *
 * @author Manfred Armijos
 */
public class CruiseShipBuilder extends VesselBuilder{
    private VesselInfo info;
    private VesselDimensions dimensions;
    private int passengerCapacity;
    private int currentPassengers = 0;

    public CruiseShipBuilder info(VesselInfo info) { 
        this.info = info; 
        return this; 
    }
    
    public CruiseShipBuilder dimensions(VesselDimensions dimensions) {
        this.dimensions = dimensions; 
        return this; 
    }
    
    public CruiseShipBuilder passengerCapacity(int cap) { 
        this.passengerCapacity = cap; 
        return this; 
    }
    
    public CruiseShipBuilder currentPassengers(int pax) { 
        this.currentPassengers = pax;
        return this; 
    }

    @Override
    public CruiseShip build() {
        return new CruiseShip(info, dimensions, passengerCapacity, currentPassengers);
    }
}
