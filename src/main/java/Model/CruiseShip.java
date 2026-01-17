package Model;

public class CruiseShip extends Vessel {

    private final int passengerCapacity;
    private int currentPassengers;

    protected CruiseShip(VesselInfo info, VesselDimensions dimensions, int passengerCapacity, int currentPassengers) {
        super(info, dimensions);
        this.passengerCapacity = passengerCapacity;
        this.currentPassengers = currentPassengers;
    }

    @Override
    public String getVesselType() {
        return "CRUISE";
    }

    @Override
    public void boarding() {
        currentPassengers = passengerCapacity;
    }

    @Override
    public void disembarkation() {
        currentPassengers = 0;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public int getCurrentPassengers() {
        return currentPassengers;
    }

    public void setCurrentPassengers(int passengers) {
        this.currentPassengers = passengers;
    }

}
