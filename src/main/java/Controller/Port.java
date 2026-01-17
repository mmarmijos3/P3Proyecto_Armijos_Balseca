package Controller;

/**
 *
 * @author Armijos Manfred, Balseca Valeska
 */

//<editor-fold defaultstate="collapsed" desc="Imports">
import Model.ContainerShip;
import Model.CruiseShip;
import Model.IBillable;
import Model.MongoConnection;
import Model.Validation;
import View.ViewLogin;
import View.ViewPort;
import java.io.File;                        // Represents file system files for bill storage
import java.io.FileReader;                  // Reads text files (bills.json) character by character
import java.io.FileWriter;                  // Writes text to files (saving bills to JSON)
import java.io.IOException;                 // Handles input/output exceptions during file operations
import java.util.ArrayList;                 // Dynamic list for storing MongoDB documents and vessel data
import java.util.Arrays;                    // Utility for array operations, used for password comparison
import javax.swing.JTable;                  // Swing component for displaying vessel queue in table format
import org.bson.Document;                   // MongoDB BSON document representation for database operations
import javax.swing.table.DefaultTableModel; // Data model for JTable to manage table structure and data
import org.json.simple.JSONArray;           // JSON array structure for storing multiple bills
import org.json.simple.JSONObject;          // JSON object structure for individual bill representation
import org.json.simple.parser.JSONParser;   // Parser to read and interpret existing JSON bill files
// </editor-fold>

public class Port {
    /*
    //<editor-fold defaultstate="collapsed" desc="Atributes">
    private MongoConnection mongo = MongoConnection.getInstance();  // Singleton instance for MongoDB database operations
    private ViewPort viewPort;                                      // Reference to the main GUI window for user interactions
    private ViewLogin viewLogin;                                    // Reference to the login window for authentication

    private DefaultTableModel modelTable;                           // Data model for the vessel queue table display
    private JTable statusTable;                                     // Swing table component showing current vessel queue

    private CruiseShip cruiseShip;                                  // Instance representing a cruise ship for service operations
    private ContainerShip containerShip;                            // Instance representing a container ship for service operations

    private boolean getIsValidType = false;                         // Flag indicating if vessel type selection is valid
    private boolean isSaveable = false;                             // Flag indicating if form data is ready to be saved
    private boolean isEditable = false;                             // Flag indicating if vessel is being edited (not new)

    private String dockedType = "";                                 // Type of currently docked vessel ("CRUISE" or "CONTAINER")
    private String formType = "";                                   // Vessel type selected in the registration form

    private String imoEdit = "";                                    // IMO number of the vessel currently being edited

    private boolean vesselInService = false;                        // Flag indicating if a vessel is currently being serviced
    private boolean applyDiscount = false;                          // Flag for applying 10% discount when no loading/unloading
    // </editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructores">
    /*
    builder that only uses the ViewPort
    *//*
    public Port(ViewPort viewPort) {
        this.viewPort = viewPort;
        this.containerShip = new ContainerShip("", "", 0, "CONTAINER",0, 0);
        this.cruiseShip = new CruiseShip("", "", 0, "CRUISE",0, 0);
        this.modelTable = (DefaultTableModel) viewPort.getTblStart().getModel();
        this.statusTable = viewPort.getTblStart();
    }
    /*
    builder that only uses the ViewLogin
    *//*
    public Port(ViewLogin viewLogin) {
        this.viewLogin = viewLogin;
    }
    // </editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
/*
    private MongoConnection getMongo() {
        return mongo;
    }


    private ViewPort getViewPort() {
        return viewPort;
    }


    private ViewLogin getViewLogin() {
        return viewLogin;
    }


    private DefaultTableModel getModelTable() {
        return modelTable;
    }


    private JTable getStatusTable() {
        return statusTable;
    }


    private CruiseShip getCruiseShip() {
        return cruiseShip;
    }


    private ContainerShip getContainerShip() {
        return containerShip;
    }


    private boolean getIsValidType() {
        return getIsValidType;
    }

    private void setIsValidType(boolean getIsValidType) {
        this.getIsValidType = getIsValidType;
    }

    private boolean getIsSaveable() {
        return isSaveable;
    }

    private void setIsSaveable(boolean isSaveable) {
        this.isSaveable = isSaveable;
    }

    private boolean getIsEditable() {
        return isEditable;
    }

    private void setIsEditable(boolean isEditable) {
        this.isEditable = isEditable;
    }

    private String getDockedType() {
        return dockedType;
    }

    private void setDockedType(String dockedType) {
        this.dockedType = dockedType;
    }

    private String getFormType() {
        return formType;
    }

    private void setFormType() {
        this.formType = getViewPort().getCbxType().getSelectedItem().toString();
    }

    private String getImoEdit() {
        return imoEdit;
    }

    private void setImoEdit(int row) {
        this.imoEdit = valueFromTable(row, 1);
    }

    private boolean getVesselInService() {
        return vesselInService;
    }

    private void setVesselInService(boolean vesselInService) {
        this.vesselInService = vesselInService;
    }

    private boolean getApplyDiscount() {
        return applyDiscount;
    }

    private void setApplyDiscount(boolean applyDiscount) {
        this.applyDiscount = applyDiscount;
    }

    // </editor-fold>
   
    //<editor-fold defaultstate="collapsed" desc="Utils">
    /*
    public void fullFormVisible(){
        switch (getFormType()) {                                            // Determine which type of vessel is being registered
            case "CONTAINER" ->{                                            // For container ships, set appropriate field labels
                getViewPort().getLblFormCapacity().setText("CapacityTEU");  // Label for TEU capacity
                getViewPort().getLblFormQuantity().setText("Container");    // Label for container count
            }
            case "CRUISE" ->{                                               // For cruise ships, set appropriate field labels  
                getViewPort().getLblFormCapacity().setText("Capacity");     // Label for passenger capacity
                getViewPort().getLblFormQuantity().setText("passengers");   // Label for passenger count
            }
        default -> setIsSaveable(false);                                    // Invalid type selected, disable saving
        }
        setFormVisible(getIsValidType() || getIsSaveable());                // Show capacity/quantity fields only when valid
    }
    
    private void setFormVisible(Boolean visible){
        getViewPort().getLblFormCapacity().setVisible(visible);  // Show/hide the capacity label (TEU or passengers)
        getViewPort().getLblFormQuantity().setVisible(visible);  // Show/hide the quantity label (containers or passengers)
        getViewPort().getTxfCapacity().setVisible(visible);      // Show/hide the capacity input field
        getViewPort().getTxfQuantity().setVisible(visible);      // Show/hide the quantity input field
    }
    
    private void saveVessel(){
        if (getFormType().equals("CRUISE")){                              // Check if registering a cruise ship
            setCruiseShip();                                              // Populate cruise ship object with form data
            getMongo().create(documentCruiseShip(getCruiseShip()));       // Save cruise ship to MongoDB
        }else if (getFormType().equals("CONTAINER")){                     // Check if registering a container ship
            setContainerShip();                                           // Populate container ship object with form data
            getMongo().create(documentContainerShip(getContainerShip())); // Save container ship to MongoDB
        }
        cleanForm();                                                      // Clear all form input fields after saving
        setIsSaveable(false);                                             // Reset save flag to prevent duplicate saves
        loadTableData();                                                  // Refresh the vessel queue table with updated data from database
    }
    
    private void editVessel(){
        if (getFormType().equals("CRUISE")){                                             // Check if editing a cruise ship
            setCruiseShip();                                                             // Update cruise ship object with modified form data
            getMongo().update(getImoEdit(), documentCruiseShip(getCruiseShip()));        // Update cruise ship in MongoDB using IMO as identifier
        }else if (getFormType().equals("CONTAINER")){                                    // Check if editing a container ship
            setContainerShip();                                                          // Update container ship object with modified form data
            getMongo().update(getImoEdit(), documentContainerShip(getContainerShip()));  // Update container ship in MongoDB using IMO as identifier
        }
        cleanForm();           // Clear form fields after editing
        setIsEditable(false);  // Reset edit mode flag
        loadTableData();       // Refresh the vessel queue table with updated data
    }
      
*/


    
    /*
    Enables or disables the basic vessel form fields (name, IMO, length, type).
    Used during registration to prevent editing of validated fields.
    *//*
    private void enableFieldsForm(boolean enable){
        getViewPort().getTxfName().setEnabled(enable);
        getViewPort().getTxfImo().setEnabled(enable);
        getViewPort().getTxfLength().setEnabled(enable);
        getViewPort().getCbxType().setEnabled(enable);
    }
/*
    /*
    Populates the ContainerShip object with data from the form fields.
    Converts string inputs to appropriate data types (double, int).
    *//*
    private void setContainerShip(){
        getContainerShip().setName(getViewPort().getTxfName().getText());
        getContainerShip().setImo(getViewPort().getTxfImo().getText());
        getContainerShip().setLength(Double.parseDouble(getViewPort().getTxfLength().getText()));
        getContainerShip().setCapacityTEU(Integer.parseInt(getViewPort().getTxfCapacity().getText()));
        getContainerShip().setContainer(Integer.parseInt(getViewPort().getTxfQuantity().getText()));
    }

    /*
    Populates the CruiseShip object with data from the form fields.
    Converts string inputs to appropriate data types (double, int).
    *//*
    private void setCruiseShip(){
        getCruiseShip().setName(getViewPort().getTxfName().getText());
        getCruiseShip().setImo(getViewPort().getTxfImo().getText());
        getCruiseShip().setLength(Double.parseDouble(getViewPort().getTxfLength().getText()));
        getCruiseShip().setPassengerCapacity(Integer.parseInt(getViewPort().getTxfCapacity().getText()));
        getCruiseShip().setPassengers(Integer.parseInt(getViewPort().getTxfQuantity().getText()));
    }

    /*
    Loads vessel data from the queue table into the form for editing.
    Disables IMO and type fields since they shouldn't be changed.
    Switches to the Form tab and updates UI for edit mode.
    *//*

    private void displayInForm(int row){
        updateLabelsForm(row);                           // Copy data from table to form fields
        setFormType();                                   // Determine vessel type from selection
        getViewPort().getBtnForm().setText("Update");    // Change button text to indicate edit mode
        fullFormVisible();                               // Show capacity/quantity fields
        getViewPort().getCbxType().setEnabled(false);    // Prevent type change during edit
        getViewPort().getTxfImo().setEnabled(false);     // Prevent IMO change during edit
        getViewPort().getTplPort().setSelectedIndex(1);  // Switch to Form tab (index 1)
    }
    /*
    Resets all form fields to their default empty state.
    Used after saving, editing, or cancelling vessel registration.
    *//*
    private void cleanForm(){
        getViewPort().getTxfName().setText("");
        getViewPort().getTxfImo().setText("");
        getViewPort().getTxfLength().setText("");
        getViewPort().getCbxType().setSelectedIndex(0); // Reset to "Select" option
        getViewPort().getTxfCapacity().setText("");
        getViewPort().getTxfQuantity().setText("");
        getViewPort().getBtnForm().setText("Next");     // Reset button to default text
        enableFieldsForm(true);                         // Re-enable all form fields
        setFormVisible(false);                          // Hide capacity/quantity fields until type selected
    }

    /*
    Loads the first vessel from the queue into the service panel for docking.
    Updates all service panel labels with vessel data and prepares for operations.
    *//*
    private void updateLabelsService(){
        int row = 0;                                                        // Always get the first vessel from queue (FIFO)
        setDockedType(valueFromTable(row, 3));                              // Get vessel type from table
        getViewPort().getLblErrorDock().setText("");                        // Clear any previous error messages
        getViewPort().getLblServName().setText(valueFromTable(row, 0));     // Set vessel name
        getViewPort().getLblServImo().setText(valueFromTable(row, 1));      // Set IMO number
        getViewPort().getLblServLength().setText(valueFromTable(row, 2));   // Set vessel length
        getViewPort().getLblServType().setText(getDockedType());            // Set vessel type
        getViewPort().getLblServCapacity().setText(valueFromTable(row, 4)); // Set capacity
        getViewPort().getLblServQuantity().setText(valueFromTable(row, 5)); // Set current quantity
        updateLabelsForm(row);                                              // Also load data into form fields (for potential editing)
        updateVesselService();                                              // Create corresponding vessel object for service operations
        cleanForm();                                                        // Clear the registration form
    }

    /*
    Creates the appropriate vessel object based on the docked vessel type.
    Called after selecting a vessel for service operations.
    *//*
    private void updateVesselService(){
        switch (getDockedType()) {  // Determine which type of vessel is being serviced
            case "CRUISE" -> setCruiseShip();      // Create CruiseShip object for cruise vessels
            case "CONTAINER" -> setContainerShip(); // Create ContainerShip object for container vessels
        }
    }

    /*
    Copies data from the queue table into the form fields for viewing or editing.
    Used when editing a vessel or displaying docked vessel details.
    *//*
    private void updateLabelsForm(int row){
        getViewPort().getTxfName().setText(valueFromTable(row, 0));         // Column 0: Vessel name
        getViewPort().getTxfImo().setText(valueFromTable(row, 1));          // Column 1: IMO number
        getViewPort().getTxfLength().setText(valueFromTable(row, 2));       // Column 2: Length in meters
        getViewPort().getCbxType().setSelectedItem(valueFromTable(row, 3)); // Column 3: Vessel type
        getViewPort().getTxfCapacity().setText(valueFromTable(row, 4));     // Column 4: Capacity (TEU/passengers)
        getViewPort().getTxfQuantity().setText(valueFromTable(row, 5));     // Column 5: Current quantity
    }

    /*
     Helper method to safely retrieve cell values from the queue table.
     Converts the cell value to string to avoid null pointer exceptions.
     *//*
    private String valueFromTable(int row, int col){
        return getStatusTable().getValueAt(row, col).toString();  // Get cell value and convert to string
    }

    /*
     Updates the service panel labels to match the docked vessel type terminology.
     Changes labels between "Capacity"/"Passengers" for cruise ships and "TEU"/"Containers" for container ships.
     *//*
    private void viewDockPanel(){
        switch (getDockedType()) {
            case "CRUISE" ->{  // For cruise ships, use passenger terminology
                getViewPort().getLblServCapacityT().setText("Capacity");    // Label for max passenger capacity
                getViewPort().getLblServQuantityT().setText("Passengers");  // Label for current passenger count
            }
            case "CONTAINER" ->{  // For container ships, use container terminology
                getViewPort().getLblServCapacityT().setText("TEU");         // Label for TEU capacity
                getViewPort().getLblServQuantityT().setText("Containers");  // Label for container count
            }
        }
    }

    /*
    Resets the service panel to its default empty state after billing completion.
    Clears all vessel data, resets service selections, and hides operation panels.
    */
    /*
    private void cleanDock(){
        // Clear vessel information display labels
        getViewPort().getLblServName().setText("--");
        getViewPort().getLblServType().setText("Type");
        getViewPort().getLblServLength().setText("--");
        getViewPort().getLblServImo().setText("--");
        getViewPort().getLblServCapacity().setText("--");
        getViewPort().getLblServQuantity().setText("--");

        // Reset terminology labels to generic terms
        getViewPort().getLblServCapacityT().setText("Capacity");
        getViewPort().getLblServQuantityT().setText("Quantity");

        // Clear all service operation selections
        getViewPort().getBgpOperations().clearSelection();      // Deselect loading/unloading radio buttons
        getViewPort().getChbRefuel().setSelected(false);        // Uncheck refueling option
        getViewPort().getChbClean().setSelected(false);         // Uncheck cleaning option
        getViewPort().getChbMaintenance().setSelected(false);   // Uncheck maintenance option

        // Hide operation and payment panels
        getViewPort().getPnlOperation().setVisible(false);
        getViewPort().getPnlPayment().setVisible(false);

        // Re-enable the operate button for next vessel
        getViewPort().getBtnOperation().setEnabled(true);

        // Mark dock as available for next vessel
        setVesselInService(false);
    }
    
    /*
    Performs boarding or disembarkation operations on a cruise ship.
    Calls the vessel's boarding() method to fill to capacity, or disembarkation() to empty.
    *//*
    private void operateCruiseShip(boolean load, boolean unload){
        if(load){
            getCruiseShip().boarding();         // Fill cruise ship with passengers to maximum capacity
        }
        if(unload){
            getCruiseShip().disembarkation();   // Remove all passengers from the cruise ship
        }
    }

    /*
    Performs loading or unloading operations on a container ship.
    Calls the vessel's boarding() method to fill containers, or disembarkation() to empty.
    *//*
    private void operateContainerShip(boolean load, boolean unload){
        if(load){
            getContainerShip().boarding();          // Load containers to maximum TEU capacity
        }
        if(unload){
            getContainerShip().disembarkation();    // Unload all containers from the ship
        }
    }

    /*
    Removes the currently docked vessel from the queue after service completion.
    Always removes the first vessel (row 0) following FIFO (First In, First Out) queue logic.
    *//*
    private void undock() {                                                   
        int row = 0;                                        // Always remove the first vessel in the queue
        getMongo().deleteDocument(valueFromTable(row, 1));  // Delete by IMO number (column 1)
        loadTableData();                                    // Refresh the queue table display
    } 
    
    /*
    Loads all vessel data from the MongoDB database and populates the queue table.
    Differentiates between container ships and cruise ships based on document structure.
    Clears existing table data before loading to ensure fresh display.
    *//*
    public void loadTableData(){
        getModelTable().setRowCount(0);                     // Clear all existing rows from the table
        ArrayList<Document> documentos = getMongo().read(); // Retrieve all vessel documents from MongoDB

        for (Document doc : documentos) {                   // Process each vessel document
            Object capacity = 0;
            Object quantity = 0;

            // Determine vessel type by checking document fields
            if (doc.containsKey("containers")) {                // Container ship documents have "containers" field
                capacity = doc.getInteger("capacityTEU");       // Get TEU capacity for container ships
                quantity = doc.getInteger("containers");        // Get current container count
            } else if (doc.containsKey("passengers")) {         // Cruise ship documents have "passengers" field
                capacity = doc.getInteger("passengerCapacity"); // Get passenger capacity for cruise ships
                quantity = doc.getInteger("passengers");        // Get current passenger count
            }

            // Add vessel data as a new row in the table
            getModelTable().addRow(new Object[]{
                doc.getString("name"),   // Column 0: Vessel name
                doc.getString("imo"),    // Column 1: IMO number
                doc.getDouble("length"), // Column 2: Vessel length
                doc.getString("type"),   // Column 3: Vessel type (CONTAINER/CRUISE)
                capacity,                // Column 4: Capacity (TEU or passengers)
                quantity                 // Column 5: Current quantity (containers or passengers)
            });
        }
    }
    
    // </editor-fold>
    
    
    
    //<editor-fold defaultstate="collapsed" desc="Form">
    /*
    Handles vessel registration and editing based on current mode.
    Validates input data and determines whether to save new vessel or update existing one.
    Implements a two-step process for new vessels: basic info validation then capacity/quantity validation.
    */
    /*
    public void registerVessel(){
        if (getIsSaveable()){                                           // Second step: capacity/quantity data is ready to be saved
            if(Validation.isValidVessel(getViewPort())){                // Validate capacity and quantity fields
                saveVessel();                                           // Save the complete vessel data to database
            } 
        }else if(!getIsEditable()){                                     // First step: entering basic vessel information (not in edit mode)
            if (Validation.isValidForm(getViewPort(),getIsEditable())){ // Validate name, IMO, length, type
                setIsSaveable(true);                                    // Mark as ready for capacity/quantity input
                enableFieldsForm(false);                                // Disable basic fields to prevent changes
                setFormType();                                          // Determine vessel type from selection
                fullFormVisible();                                      // Show capacity/quantity fields with appropriate labels
                getViewPort().getBtnForm().setText("Save");             // Change button to indicate save action
            }
        }
        if(getIsEditable()){  // Editing an existing vessel
            if(Validation.isValidForm(getViewPort(),getIsEditable()) && // Validate basic fields
                Validation.isValidVessel(getViewPort())){               // Validate capacity/quantity fields
                editVessel();                                           // Update the vessel in database
            } 
        }
    }
*/
    // </editor-fold>
    
}