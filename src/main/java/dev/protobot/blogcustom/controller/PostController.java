package dev.protobot.blogcustom.controller;

import dev.protobot.blogcustom.dto.PostRequest;
import dev.protobot.blogcustom.dto.PostResponse;
import dev.protobot.blogcustom.dto.response.RestResponse;
import dev.protobot.blogcustom.service.implementation.PostServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    private final PostServiceImplementation postService;

    @Autowired
    public PostController(PostServiceImplementation postService) {
        this.postService = postService;
    }

    @PostMapping
    public RestResponse<Void> createPost(@RequestBody PostRequest postRequest) {
        postService.save(postRequest);
        return new RestResponse<>(HttpStatus.CREATED, null);
    }

    @GetMapping
    public RestResponse<List<PostResponse>> getAllPosts() {
        return new RestResponse<>(HttpStatus.OK, postService.getAllPosts());
    }

    @GetMapping("/{id}")
    public RestResponse<PostResponse> getPostById(@PathVariable Long id) {
        return new RestResponse<>(HttpStatus.OK, postService.getPost(id));
    }

    @GetMapping("by-subreddit/{id}")
    public RestResponse<List<PostResponse>> getPostsBySubreddit(Long id) {
        return new RestResponse<>(HttpStatus.OK, postService.getPostsBySubreddit(id));
    }

    @GetMapping("by-user/{name}")
    public RestResponse<List<PostResponse>> getPostsByUsername(@PathVariable String name) {
        return new RestResponse<>(HttpStatus.OK, postService.getPostsByUsername(name));
    }

}
