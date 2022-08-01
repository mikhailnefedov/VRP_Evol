package backend.helper;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Tuple<T1, T2> {
    private T1 left;
    private T2 right;
}
