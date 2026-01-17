package Model;

/**
 * Fábrica abstracta (Factory Method) para crear embarcaciones.
 */
public abstract class VesselFactory {
    public abstract Vessel createVessel(VesselBuilder builder);
}