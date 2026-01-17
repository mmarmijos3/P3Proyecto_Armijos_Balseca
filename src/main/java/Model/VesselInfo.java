
package Model;

/**
 *
 * @author Manfred Armijos
 */
public class VesselInfo {
    private final String name;
    private final String imo;
    private boolean hasAllDocumentation;

    public VesselInfo(String name, String imo, boolean hasAllDocumentation) {
        this.name = name;
        this.imo = imo;
        this.hasAllDocumentation = hasAllDocumentation;
    }

    public String getName() {
        return name;
    }

    public String getImo() {
        return imo;
    }

    public boolean hasAllDocumentation() {
        return hasAllDocumentation;
    }

    public void setHasAllDocumentation(boolean hasAllDocumentation) {
        this.hasAllDocumentation = hasAllDocumentation;
    }
}
