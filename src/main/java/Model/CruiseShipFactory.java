package Model;


public class CruiseShipFactory extends VesselFactory {

    @Override
    public Vessel createVessel(VesselInfo info, VesselDimensions dimensions, int capacity, int quantity) {

        return new CruiseShipBuilder()
                .info(info)
                .dimensions(dimensions)
                .passengerCapacity(capacity)
                .currentPassengers(quantity)
                .build();
    }
}