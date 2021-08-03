package portfolioManagement;

import java.util.Objects;

public class SupplyPoint {

    private String ean;
    private Energy energy;
    private String supplierName;

    public SupplyPoint(String ean, String supplierName, Energy energy){
        this.ean = ean;
        this.supplierName = supplierName;
        this.energy = energy;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ean, energy, supplierName);
    }

    public Energy getEnergy() {
        return energy;
    }

    public String getEan() {
        return ean;
    }

    public String getSupplierName() {
        return supplierName;
    }
}
