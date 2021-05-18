package dev.protobot.blogcustom.controller;

import dev.protobot.blogcustom.service.PostService;
import dev.protobot.blogcustom.service.implementation.PostServiceImplementation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import dev.protobot.blogcustom.model.Post;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class PostControllerTest {





    private static PostController postController;

    @Autowired
    public PostControllerTest(PostController postController) {
        this.postController = postController;
    }

    //Mocks
    @Mock
    private PostServiceImplementation postServiceImplementation;

    //Test data  and setup
    private static Post post = new Post(
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null

    );


    @BeforeTestClass
    public void setup(){
        postController = new PostController(postServiceImplementation);
    }

    @Test
    public void contextLoad(){
        Assertions.assertThat(postController).isNotNull();
    }

    @Test
    public void testGetAllPost(){

    }

}
