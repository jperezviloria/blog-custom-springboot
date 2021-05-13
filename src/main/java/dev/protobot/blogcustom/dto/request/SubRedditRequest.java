package dev.protobot.blogcustom.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "subredditdto")
public class SubRedditRequest {

    @Id
    private Long id;
    private String name;
    private String description;

    @Column(name = "numberofposts")
    private Integer numberOfPost;
}
