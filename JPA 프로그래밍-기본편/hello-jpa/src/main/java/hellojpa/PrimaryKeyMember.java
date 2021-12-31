package hellojpa;

import javax.persistence.Entity;
import javax.persistence.Id;

//@Entity
public class PrimaryKeyMember {

    @Id
    private Long id;

    private String name;

}
