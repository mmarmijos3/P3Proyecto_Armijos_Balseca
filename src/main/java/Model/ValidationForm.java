
package Model;


/**
 *
 * @author Manfred Armijos
 */
public class ValidationForm {
        //FORM VALIDATION
/*
    private static boolean validateNameForm(ViewPort view) {
        boolean success = true;
        String name = view.getTxfName().getText();

        if (name.isEmpty()) {
            view.getLblErrorFormName().setText("Name is required");
            success = false;
        } else if (!Validation.validateText(name)) {
            view.getLblErrorFormName().setText("Invalid name, letters only");
            success = false;
        } else {
            view.getLblErrorFormName().setText("");
        }
        return success;
    }

    private static boolean validateImoForm(ViewPort view) {
        boolean success = true;
        String imo = view.getTxfImo().getText();

        if (imo.isEmpty()) {
            view.getLblErrorFormIMO().setText("IMO number is required");
            success = false;
        } else if (!validateIMO(imo)) {
            view.getLblErrorFormIMO().setText("Invalid IMO number");
            success = false;
        }else if(isIMOinQueue(view)){
            view.getLblErrorFormIMO().setText("IMO is in Queue");
            success = false;
        }else {
            view.getLblErrorFormIMO().setText("");
        }
        return success;
    }
    
    private static boolean isIMOinQueue(ViewPort view){
        boolean success;
        String imo = view.getTxfImo().getText();
        success = !(MongoConnection.getInstance().searchDocument(imo) == null);
        return success && !isEdition();
    }

    private static boolean validateLengthForm(ViewPort view) {
        boolean success = true;
        String length = view.getTxfLength().getText();

        if (length.isEmpty()) {
            view.getLblErrorFormLength().setText("Length is required");
            success = false;
        } else if (!validateLength(length)) {
            view.getLblErrorFormLength().setText("Invalid vessel length");
            success = false;
        } else {
            view.getLblErrorFormLength().setText("");
        }
        return success;
    }

    private static boolean validateTypeForm(ViewPort view) {
        boolean success = true;
        String type = view.getCbxType().getSelectedItem().toString();

        if (type.equals("Select")) {
            view.getLblErrorFormType().setText("Please select a vessel type");
            success = false;
        } else {
            view.getLblErrorFormType().setText("");
        }
        return success;
    }

    private static boolean validateTeuForm(ViewPort view) {
        boolean success = true;
        String teu = view.getTxfCapacity().getText();

        if (teu.isEmpty()) {
            view.getLblErrorFormCapacity().setText("TEU capacity is required");
            success = false;
        } else if (!validateTEU(teu)) {
            view.getLblErrorFormCapacity().setText("Invalid TEU capacity");
            success = false;
        } else {
            view.getLblErrorFormCapacity().setText("");
            setCapacity(Integer.parseInt(teu));
            setCapacityOk(true);
        }
        return success;
    }

    private static boolean validateCapacityPassengerForm(ViewPort view) {
        boolean success = true;
        String capacityPassenger = view.getTxfCapacity().getText();

        if (capacityPassenger.isEmpty()) {
            view.getLblErrorFormCapacity().setText("Passenger capacity is required");
            success = false;
        } else if (!validateCapacityPassenger(capacityPassenger)) {
            view.getLblErrorFormCapacity().setText("Invalid passenger capacity");
            success = false;
        } else {
            view.getLblErrorFormCapacity().setText("");
            setCapacity(Integer.parseInt(capacityPassenger));
            setCapacityOk(true);
        }
        return success;
    }

    private static boolean validateQuantityForm(ViewPort view) {
        boolean success = true;
        String quantity = view.getTxfQuantity().getText();

        if (quantity.isEmpty()) {
            view.getLblErrorFormQuantity().setText("Value is required");
            success = false;
        } else if (!getCapacityOk()) {
            view.getLblErrorFormQuantity().setText("Previous capacity error");
            success = false;
        } else if (!validateQuantity(quantity, getCapacity())) {
            view.getLblErrorFormQuantity().setText("Invalid quantity value");
            success = false;
        } else {
            view.getLblErrorFormQuantity().setText("");
        }
        return success;
    }

    //PUBLIC VALIDATION METHODS

    public static boolean isValidForm(ViewPort view, boolean edit) {
        setEdition(edit);
        boolean success = true;
        success &= validateNameForm(view);
        success &= validateImoForm(view);
        success &= validateLengthForm(view);
        success &= validateTypeForm(view);
        return success;
    }

    public static boolean isValidVessel(ViewPort view) {
        boolean success = true;
        success &= (validateTeuForm(view) || validateCapacityPassengerForm(view));
        success &= validateQuantityForm(view);
        return success;
    }
*/
}
