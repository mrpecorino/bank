package sunhillbank;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Owner{
    private String firstName;
    private String lastName;
    private String address;
}