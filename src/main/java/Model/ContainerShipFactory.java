package Model;


public class ContainerShipFactory extends VesselFactory {

    @Override
    public Vessel createVessel(VesselInfo info, VesselDimensions dimensions, int capacity, int quantity) {

        return new ContainerShipBuilder()
                .info(info)
                .dimensions(dimensions)
                .capacityTEU(capacity)
                .currentContainers(quantity)
                .build();
    }
}