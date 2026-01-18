package Model;

/**
 * Clase padre abstracta para todas las embarcaciones.
 * Usa composición con VesselInfo y VesselDimensions.
 */
public abstract class Vessel implements IDockable {

    private final VesselInfo info;
    private final VesselDimensions dimensions;

    protected Vessel(VesselInfo info, VesselDimensions dimensions) {
        this.info = info;
        this.dimensions = dimensions;
    }

    // Delegación a VesselInfo
    public String getName()                  { return info.getName(); }
    public String getImo()                   { return info.getImo(); }
    public boolean hasAllDocumentation()     { return info.hasAllDocumentation(); }
    public void setHasAllDocumentation(boolean value) { 
        info.setHasAllDocumentation(value); 
    }

    // Delegación a VesselDimensions
    public double getLength() { return dimensions.getLength(); }
    public double getBeam()   { return dimensions.getBeam();   }
    public double getDraft()  { return dimensions.getDraft();  }


    public abstract String getVesselType();
}