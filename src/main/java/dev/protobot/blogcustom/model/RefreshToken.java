package dev.protobot.blogcustom.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "refreshtoken")
public class RefreshToken {
    @Id
    private Long id;
    private String token;
    @Column(name = "createddate")
    private Instant createdDate;
}
