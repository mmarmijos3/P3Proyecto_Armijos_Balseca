package Model;


public class ContainerShipBuilder {
    private VesselInfo info;
    private VesselDimensions dimensions;
    private int capacityTEU;
    private int currentContainers = 0;

    public ContainerShipBuilder info(VesselInfo info) {
        this.info = info; 
        return this; 
    }
    
    public ContainerShipBuilder dimensions(VesselDimensions dimensions) {
        this.dimensions = dimensions; 
        return this; 
    }
    
    public ContainerShipBuilder capacityTEU(int teu) { 
        this.capacityTEU = teu; 
        return this; 
    }
    
    public ContainerShipBuilder currentContainers(int containers) { 
        this.currentContainers = containers; 
        return this; 
    }

    public ContainerShip build() {
        return new ContainerShip(info, dimensions, capacityTEU, currentContainers);
    }
}
