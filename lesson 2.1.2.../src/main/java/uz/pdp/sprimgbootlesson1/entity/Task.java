package uz.pdp.sprimgbootlesson1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String text;

    @Column
    private String examples;

    @Column
    private String solution;

    @Column
    private boolean hasStar=true;

    @ManyToOne
    private Category category;

}
