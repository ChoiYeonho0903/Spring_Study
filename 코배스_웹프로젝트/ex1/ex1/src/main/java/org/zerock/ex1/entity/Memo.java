package org.zerock.ex1.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table
@Getter @Setter
@ToString
public class Memo {

    @Id @GeneratedValue
    private Long id;

    @Column(length = 200, nullable = false)
    private String memoText;

    public Memo() {
    }

    public Memo(String memoText) {
        this.memoText = memoText;
    }
}
