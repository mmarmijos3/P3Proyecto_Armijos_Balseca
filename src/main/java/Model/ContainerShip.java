package Model;

public class ContainerShip extends Vessel {

    private final int capacityTEU;
    private int currentContainers;

    protected ContainerShip(VesselInfo info, VesselDimensions dimensions, int capacityTEU, int currentContainers) {
        super(info, dimensions);  // Llama al constructor validado de Vessel
        this.capacityTEU = capacityTEU;
        this.currentContainers = currentContainers;
    }

    @Override
    public String getVesselType() {
        return "CONTAINER";
    }

    @Override
    public void boarding() {
        currentContainers = capacityTEU;
    }

    @Override
    public void disembarkation() {
        currentContainers = 0;
    }

    public int getCapacityTEU() {
        return capacityTEU;
    }

    public int getCurrentContainers() {
        return currentContainers;
    }

    public void setCurrentContainers(int containers) {
        this.currentContainers = containers;
    }

}