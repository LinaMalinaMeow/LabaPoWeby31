package test;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "point")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "uid", nullable = false)
    private int id;
    @Column(name = "x", nullable = false)
    private String x;
    @Column(name = "y", nullable = false)
    private String y;
    @Column(name = "r", nullable = false)
    private String r;
    @Column(name = "result", nullable = false)
    private String result;
}
