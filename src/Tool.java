public class Tool {
    private static int id;
    private String toolType;
    private String toolManufacturer;
    private float toolRate;
    private String toolDesc;
    private String toolStatus;

    public Tool(){
        this("Not Available", "Not Available", 0.0f, "Not Available");
    }

    public Tool(String toolType, String toolManufacturer, float toolRate, String toolDesc){
        setId(id);
        setToolType(toolType);
        setToolManufacturer(toolManufacturer);
        setToolRate(toolRate);
        setToolDesc(toolDesc);
        setToolStatus("IN");
    }

    public void setId(int id) {
        this.id++;
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

    @Override
    public String toString() {
        return "Tool ID: " + getId() + "\nTool Type: " + getToolType() + "\nTool Manufacturer: " + getToolManufacturer() +
                "\nTool Description: " + getToolDesc() + "\nTool Rate: " + getToolRate() + "\nTool Status: " + getToolStatus();
    }
}
