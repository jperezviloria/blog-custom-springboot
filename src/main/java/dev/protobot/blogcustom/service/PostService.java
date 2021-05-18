package dev.protobot.blogcustom.service;

import dev.protobot.blogcustom.dto.PostRequest;
import dev.protobot.blogcustom.dto.PostResponse;

import java.util.List;

public interface PostService {

    void save(PostRequest postRequest);

    PostResponse getPost(Long id);

    List<PostResponse> getAllPosts();

    List<PostResponse> getPostsBySubreddit(Long subredditId);

    List<PostResponse> getPostsByUsername(String username);
}
