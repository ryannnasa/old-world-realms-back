public class UnitType {
    private int idUnitType;
    private String unitTypeName;

    public UnitType() {}

    public UnitType(int idUnitType, String unitTypeName) {
        this.idUnitType = idUnitType;
        this.unitTypeName = unitTypeName;
    }

    public int getIdUnitType() {return idUnitType;}
    public void setIdUnitType(int idUnitType) {this.idUnitType = idUnitType;}

    public String getUnitTypeName() {return unitTypeName;}
    public void setUnitTypeName(String unitTypeName) {this.unitTypeName = unitTypeName;}
}