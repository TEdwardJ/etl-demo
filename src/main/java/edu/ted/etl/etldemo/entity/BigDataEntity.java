package edu.ted.etl.etldemo.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "etl_stage")
@Data
public class BigDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_etl_stage")
    @SequenceGenerator(name = "seq_etl_stage", sequenceName = "seq_etl_stage", allocationSize = 20)
    private long id;

    @Column(name = "updatedOn")
    private LocalDateTime updatedOn = LocalDateTime.now();

    @Column(name = "textData")
    private String textData;

}
