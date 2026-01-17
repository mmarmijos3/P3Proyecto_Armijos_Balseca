package Model;


public class CruiseShipFactory extends VesselFactory {

    @Override
    public Vessel createVessel(VesselBuilder builder) {

        return builder.build();
    }
}