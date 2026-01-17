package Model;

/**
 * Fábrica abstracta (Factory Method) para crear embarcaciones.
 */
public abstract class VesselFactory {

    public abstract Vessel createVessel(VesselInfo info, VesselDimensions dimensions, int capacity, int quantity);

    public static VesselFactory getFactory(String vesselType) {
        return switch (vesselType.toUpperCase()) {
            case "CONTAINER" -> new ContainerShipFactory();
            case "CRUISE"    -> new CruiseShipFactory();
            default -> throw new IllegalArgumentException("Tipo no soportado: " + vesselType);
        };
    }
}