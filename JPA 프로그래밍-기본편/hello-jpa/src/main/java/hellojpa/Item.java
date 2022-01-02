package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
//@Inheritance(strategy = InheritanceType.JOINED)
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //insert문 한개, join 필요없다. 단순하다.
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) //item table이 존재X
@DiscriminatorColumn //single_table에서는 없어도 자동으로 dtype 추가된다.
public abstract class Item {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private int price;
}
