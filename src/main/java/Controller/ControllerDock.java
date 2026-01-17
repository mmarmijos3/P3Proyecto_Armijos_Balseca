
package Controller;
/*
import Model.IBillable;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
*/
/**
 *
 * @author Manfred Armijos
 */
public class ControllerDock {
    //<editor-fold defaultstate="collapsed" desc="Service">
    
    //<editor-fold defaultstate="collapsed" desc="Dock">
    
    /*
    Docks the first vessel in the queue for service operations.
    Checks if queue is empty or dock is busy before proceeding.
    Updates the service panel with vessel details and enables operations.
    */
    /*
    public void dockVessel(){
        if(getModelTable().getRowCount() == 0){                     // Check if vessel queue is empty
            getViewPort().getLblErrorDock().setText("Empty queue.");// Show queue empty error
        }else if(getVesselInService()){                             // Check if another vessel is already being serviced
            getViewPort().getLblErrorDock().setText("busy dock");   // Show dock busy error
        }else{                                                      // Dock is available and queue has vessels
            updateLabelsService();                                  // Load first vessel data into service panel
            setVesselInService(true);                               // Mark dock as occupied
            viewDockPanel();                                        // Update panel labels based on vessel type
            getViewPort().getPnlOperation().setVisible(getVesselInService());  // Show operations panel
        }
    }
    */
    // </editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Operations">
    
    /*
    Calculates service charges for the docked vessel based on selected operations.
    Uses lambda expressions with IBillable interface for vessel-specific billing logic.
    Applies 10% discount if no loading/unloading operations are selected.
    Calculates IVA (15%) and displays the complete billing breakdown.
    */
    /*
    public void operations() {
        // Get selected operations from UI components
        boolean loading = getViewPort().getRbtLoading().isSelected();
        boolean unloading = getViewPort().getRbtUnloading().isSelected();
        boolean refuel = getViewPort().getChbRefuel().isSelected();
        boolean clean = getViewPort().getChbClean().isSelected();
        boolean maintenance = getViewPort().getChbMaintenance().isSelected();

        setApplyDiscount(!(loading || unloading));  // Apply 10% discount if no loading/unloading
        double subtotal = 0;
        double discount = 0;
        double iva;
        double total;

        // Lambda expression for container ship billing calculations
        IBillable billContainerShip = () -> {
            double totalLocal = 0;
            totalLocal += getContainerShip().getLength() * 2.5;       // Dock usage fee: $2.5 per meter
            totalLocal += getContainerShip().getContainer() * 2.75;  // Container handling: $2.75 per container
            if (refuel) totalLocal += 1500;                           // Refueling service
            if (clean) totalLocal += 1200;                            // Cleaning service
            if (maintenance) totalLocal += 2500;                      // Maintenance service
            return totalLocal;
        };

        // Lambda expression for cruise ship billing calculations  
        IBillable billCruiseShip = () -> {
            double totalLocal = 0;
            totalLocal += getCruiseShip().getLength() * 4;          // Dock usage fee: $4 per meter
            totalLocal += getCruiseShip().getPassengers() * 1.30;   // Passenger tax: $1.30 per passenger
            if (refuel) totalLocal += 3000;                         // Refueling service
            if (clean) totalLocal += 2000;                          // Cleaning service
            if (maintenance) totalLocal += 3200;                    // Maintenance service
            return totalLocal;
        };

        // Calculate charges based on vessel type
        switch (getDockedType()) {
            case "CRUISE" -> {
                operateCruiseShip(loading, unloading);  // Perform passenger operations
                subtotal = billCruiseShip.bill();       // Calculate cruise ship charges
            }
            case "CONTAINER" -> {
                operateContainerShip(loading, unloading); // Perform container operations
                subtotal = billContainerShip.bill();      // Calculate container ship charges
            }
        }

        // Apply 10% discount if applicable
        if (getApplyDiscount()) {
            discount = subtotal * 0.10;
            subtotal -= discount;
        }

        // Calculate IVA (15%) and total
        iva = subtotal * 0.15;
        total = subtotal + iva;

        // Display billing results in payment panel
        getViewPort().getBtnOperation().setEnabled(false);                                  // Disable operation button
        getViewPort().getLblSubtotal().setText(String.format("%.2f", subtotal + discount)); // Original subtotal
        getViewPort().getLblDiscount().setText(String.format("%.2f", discount));            // Discount amount
        getViewPort().getLblSubtotal2().setText(String.format("%.2f", subtotal));           // Subtotal after discount
        getViewPort().getLblIVA().setText(String.format("%.2f", iva));                      // IVA (15%)
        getViewPort().getLblTotal().setText(String.format("%.2f", total));                  // Final total
        getViewPort().getPnlPayment().setVisible(true);                                     // Show payment panel
    }
    */
    // </editor-fold>    
    
    //<editor-fold defaultstate="collapsed" desc="Billing">
    public void bill() {
/*
        File file = new File("bills.json");

        JSONObject root = new JSONObject();
        JSONArray bills = new JSONArray();

        // read file if exist
        if (file.exists()) {
            try (FileReader reader = new FileReader(file)) {
                JSONParser parser = new JSONParser();
                root = (JSONObject) parser.parse(reader);
                JSONArray readBills = (JSONArray) root.get("bills");
                if (readBills != null) {
                    bills = readBills;
                }
            } catch (Exception e) {
                //error de file
            }
        }

        // create bill
        JSONObject bill = new JSONObject();
        bill.put("imo", getViewPort().getLblServImo().getText());
        bill.put("name", getViewPort().getLblServName().getText());
        bill.put("type", getViewPort().getLblServType().getText());
        bill.put("subtotal", getViewPort().getLblSubtotal().getText());
        bill.put("discount", getViewPort().getLblDiscount().getText());
        bill.put("iva", getViewPort().getLblIVA().getText());
        bill.put("total", getViewPort().getLblTotal().getText());

        // add bill
        bills.add(bill);
        root.put("bills", bills);

        // Save file
        try (FileWriter f = new FileWriter(file)) {
            f.write(root.toJSONString());
            cleanDock();
            undock();
        } catch (IOException e) {
            //save error
        }
    }
*/
    // </editor-fold> 
    
    // </editor-fold> 
}}
