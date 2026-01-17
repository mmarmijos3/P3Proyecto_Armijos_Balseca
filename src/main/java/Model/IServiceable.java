package Model;

/**
 *
 * @author Manfred Armijos
 */
public interface IServiceable {
    void requestService(String serviceType, double quantity);
    double getServiceCost(String serviceType);
    void completeService();
}
