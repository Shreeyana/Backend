package nce.majorproject.context;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Context {
    private Long id;
    private String userType;
    private String userName;
    private String fullName;

}
