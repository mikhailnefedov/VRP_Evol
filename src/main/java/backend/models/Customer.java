package backend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Customer extends Genome {
    private int genomeNumber;
    private Double x;
    private Double y;

    public double computeDistance(Customer predecessor) {
        Double predecessorX = predecessor.getX();
        Double predecessorY = predecessor.getY();

        double tmp = Math.pow(x - predecessorX, 2) + Math.pow(y - predecessorY, 2);
        return Math.sqrt(tmp);
    }

    public double computeDistance(double xCoord, double yCoord) {
        double tmp = Math.pow(x - xCoord, 2) + Math.pow(y - yCoord, 2);
        return Math.sqrt(tmp);
    }

    @Override
    public String toString() {
        return "Cust {" + genomeNumber + " ,x: " + x + " ,y: " + y + '}';
    }
}
