
package Controller;

import org.bson.Document;

/**
 *
 * @author Manfred Armijos
 */
public class ControllerPort {
    //<editor-fold defaultstate="collapsed" desc="Status">
    
    /*
    Searches for a vessel in the queue by IMO number and displays it in the table.
    Shows error messages if IMO is empty or not found in the database.
    *//*
    public void searchVessel(){
        String imo = getViewPort().getTxfSearch().getText();                    // Get IMO number from search field

        if (imo.isEmpty()){  // Check if search field is empty
            getViewPort().getLblErrorSearch().setText("Enter IMO to search");   // Show empty field error
        }else{
            Document doc = getMongo().searchDocument(imo);                      // Search for vessel in MongoDB by IMO
            if (doc != null){                                                   // Vessel found
                Object capacity = 0;
                Object quantity = 0;
                getModelTable().setRowCount(0);                                 // Clear table to show only the searched vessel
                if (doc.containsKey("containers")) {                            // Check if it's a container ship
                    capacity = doc.getInteger("capacityTEU");
                    quantity = doc.getInteger("containers");
                } else if (doc.containsKey("passengers")) {                     // Check if it's a cruise ship
                    capacity = doc.getInteger("passengerCapacity");
                    quantity = doc.getInteger("passengers");
                }
                getModelTable().addRow(new Object[]{                            // Add the found vessel to table
                    doc.getString("name"),
                    doc.getString("imo"),
                    doc.getDouble("length"),
                    doc.getString("type"),
                    capacity,
                    quantity
                });
                getViewPort().getLblErrorSearch().setText("");                  // Clear any previous error
            }else{  // Vessel not found
                getViewPort().getLblErrorSearch().setText("IMO not found");     // Show not found error
            }
        }  
    }

    /*
    Clears the search field and reloads the full vessel queue.
    Resets the table to show all vessels after a search operation.
    *//*
    public void cleanSearch(){
        getViewPort().getLblErrorSearch().setText("");  // Clear search error message
        getViewPort().getTxfSearch().setText("");       // Clear search input field
        loadTableData();                                // Reload all vessels from database
    }

    /*
    Prepares the selected vessel for editing by loading its data into the form.
    Enables edit mode and switches to the form tab.
    *//*
    public void Edit() {                                                 
        int row = getStatusTable().getSelectedRow();  // Get the selected table row

        if (row == -1) {  // No row selected
            // --
        } else {
            setIsEditable(true);     // Enable edit mode
            setIsValidType(true);    // Mark form type as valid
            setImoEdit(row);         // Store IMO of vessel being edited
            displayInForm(row);      // Load vessel data into form fields
        }
    }

    /*
    Deletes the selected vessel from the queue and database.
    Requires a vessel to be selected in the table.
    */
    /*
    public void deleteVessel() {                                                   
        int row = getStatusTable().getSelectedRow();  // Get the selected table row

        if (row == -1) {  // No row selected
            // --
        } else {
            getMongo().deleteDocument(valueFromTable(row, 1));  // Delete vessel by IMO (column 1)
            loadTableData();  // Refresh table after deletion
            // --
        }
    }
/*
    /*
    Clears all vessels from the queue (deletes entire collection).
    */
    /*
    public void clearQueue(){
        getMongo().deleteCollection();  // Delete all documents from the collection
        loadTableData();  // Refresh empty table
    }
*/
    /*
    Drops the entire database, including all collections.
    */
    /*
    public void dropDatabase(){
        getMongo().deleteDatabase();  // Delete the entire database
        loadTableData();  // Refresh empty table
    }
    */
    
    // </editor-fold>
}
