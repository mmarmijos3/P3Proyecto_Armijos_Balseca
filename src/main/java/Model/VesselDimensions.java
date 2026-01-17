
package Model;

/**
 *
 * @author Manfred Armijos
 */
public class VesselDimensions {
    private final double length;  // eslora (LOA)
        private final double beam;    // manga (ancho)
        private final double draft;   // calado (profundidad)

        public VesselDimensions(double length, double beam, double draft) {
            this.length = length;
            this.beam = beam;
            this.draft = draft;
        }

        public double getLength() { return length; }
        public double getBeam()   { return beam;   }
        public double getDraft()  { return draft;  }

}
