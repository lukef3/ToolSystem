import java.io.Serializable;

public class Tool implements Serializable {
    private int id;
    private String toolType;
    private String toolManufacturer;
    private float toolRate;
    private String toolDesc;
    private String toolStatus;
    private static int counter;

    public Tool(){
        this("Not Available", "Not Available", 0.0f, "Not Available");
    }

    public Tool(String toolType, String toolManufacturer, float toolRate, String toolDesc){
        setToolType(toolType);
        setToolManufacturer(toolManufacturer);
        setToolRate(toolRate);
        setToolDesc(toolDesc);
        setToolStatus("IN");
        incrementCounter();
        setId(counter);
    }
    private static void incrementCounter(){
        counter++;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setToolType(String toolType) {
        this.toolType = toolType;
    }
    public void setToolManufacturer(String toolManufacturer) {
        this.toolManufacturer = toolManufacturer;
    }
    public void setToolRate(float toolRate) {
        this.toolRate = toolRate;
    }
    public void setToolDesc(String toolDesc) {
        this.toolDesc = toolDesc;
    }
    public void setToolStatus(String toolStatus) {
        this.toolStatus = toolStatus;
    }

    public int getId() {
        return id;
    }
    public String getToolType() {
        return toolType;
    }
    public String getToolManufacturer() {
        return toolManufacturer;
    }
    public float getToolRate() {
        return toolRate;
    }
    public String getToolDesc() {
        return toolDesc;
    }
    public String getToolStatus() {
        return toolStatus;
    }

    public String toString() {
        return "Tool ID: " + getId() + "\nTool Type: " + getToolType() + "\nTool Manufacturer: " + getToolManufacturer() +
                "\nTool Description: " + getToolDesc() + "\nTool Rate: €" + getToolRate() + "\nTool Status: " + getToolStatus();
    }
}
