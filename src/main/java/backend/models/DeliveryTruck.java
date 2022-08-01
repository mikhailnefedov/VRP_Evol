package backend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class DeliveryTruck extends Genome{
    private int genomeNumber;
    private boolean isLastTruck;

    @Override
    public String toString() {
        return "Truck{" + genomeNumber + '}';
    }
}
