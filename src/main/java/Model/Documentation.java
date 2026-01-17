
package Model;

import org.bson.Document;

/**
 *
 * @author Manfred Armijos
 */

// Crear un interface de adapter y override de DocumentVessel en clases adapter (3+ clases)
public class Documentation {
    
    public Document documentContainerShip(ContainerShip containerShip) {
        return new Document("name", containerShip.getName())            // Vessel name field
                .append("imo", containerShip.getImo())                  // International Maritime Organization number
                .append("length", containerShip.getLength())            // Vessel length in meters
                .append("type", containerShip.getVesselType())                // Vessel type (always "CONTAINER")
                .append("capacityTEU", containerShip.getCapacityTEU())  // Maximum TEU (Twenty-foot Equivalent Unit) capacity
                .append("containers", containerShip.getCurrentContainers());    // Current number of containers on board
    }

    public Document documentCruiseShip(CruiseShip cruiseShip) {
        return new Document("name", cruiseShip.getName())                       // Vessel name field
                .append("imo", cruiseShip.getImo())                             // International Maritime Organization number
                .append("length", cruiseShip.getLength())                       // Vessel length in meters
                .append("type", cruiseShip.getVesselType())                           // Vessel type (always "CRUISE")
                .append("passengerCapacity", cruiseShip.getPassengerCapacity()) // Maximum passenger capacity
                .append("passengers", cruiseShip.getCurrentPassengers());              // Current number of passengers on board
    }
    
    public void saludo(){
        System.out.println("Hello");
    }
}
