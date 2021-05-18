package dev.protobot.blogcustom.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Entity
//@Table(name = "subredditdto")


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubredditDto {

    //@Id
    private Long id;
    private String name;
    private String description;

    //@Column(name = "numberofposts")
    private Integer numberOfPost;
}
