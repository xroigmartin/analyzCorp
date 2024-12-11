package xroigmartin.analyzcorp_backend.shared;

import lombok.experimental.UtilityClass;
import net.datafaker.Faker;

@UtilityClass
public class DataFaker {

    public static Faker faker(){
        return new Faker();
    }
}
