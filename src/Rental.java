public class Rental {
    private String custName;
    private String email;
    private int phone;
    private String eircode;
    private int toolID;

    public Rental(String custName, String email, int phone, String eircode, int toolID) {
        setCustName(custName);
        setEmail(email);
        setPhone(phone);
        setEircode(eircode);
        setToolID(toolID);

    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public void setEircode(String eircode) {
        this.eircode = eircode;
    }

    public void setToolID(int toolID) {
        this.toolID = toolID;
    }

    public String getCustName() {
        return custName;
    }

    public String getEmail() {
        return email;
    }

    public int getPhone() {
        return phone;
    }

    public String getEircode() {
        return eircode;
    }

    public int getToolID() {
        return toolID;
    }

    public String toString() {
        return "Customer Name: " + getCustName() + "\nEmail: " + getEmail() + "\nCustomer Phone: " + getPhone() +
                "\nEircode: " + getEircode() + "\nTool ID: " + getToolID();

    }
}
