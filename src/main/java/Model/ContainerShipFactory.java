package Model;


public class ContainerShipFactory extends VesselFactory {

    @Override
    public Vessel createVessel(VesselBuilder builder) {

        return builder.build();
        
    }
}