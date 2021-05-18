package dev.protobot.blogcustom.service.implementation;

import dev.protobot.blogcustom.dto.PostRequest;
import dev.protobot.blogcustom.dto.PostResponse;
import dev.protobot.blogcustom.exceptions.PostNotFoundException;
import dev.protobot.blogcustom.exceptions.SubredditNotFoundException;
import dev.protobot.blogcustom.mapper.PostMapper;
import dev.protobot.blogcustom.model.Post;
import dev.protobot.blogcustom.model.Subreddit;
import dev.protobot.blogcustom.model.User;
import dev.protobot.blogcustom.respository.PostRepository;
import dev.protobot.blogcustom.respository.SubredditRepository;
import dev.protobot.blogcustom.respository.UserRepository;
import dev.protobot.blogcustom.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Transactional
@Service
@Slf4j
public class PostServiceImplementation implements PostService {

    private final PostRepository postRepository;
    private final SubredditRepository subredditRepository;
    private final UserRepository userRepository;
    private final AuthServiceImplementation authServiceImplementation;
    private final PostMapper postMapper;

    @Autowired
    public PostServiceImplementation(PostRepository postRepository,
                                     SubredditRepository subredditRepository,
                                     UserRepository userRepository,
                                     AuthServiceImplementation authServiceImplementation,
                                     PostMapper postMapper) {
        this.postRepository = postRepository;
        this.subredditRepository = subredditRepository;
        this.userRepository = userRepository;
        this.authServiceImplementation = authServiceImplementation;
        this.postMapper = postMapper;
    }


    public void save(PostRequest postRequest) {
        Subreddit subreddit = subredditRepository.getSubredditByName(postRequest.getSubredditName())
                .orElseThrow(() -> new SubredditNotFoundException(postRequest.getSubredditName()));
        User currentUser = authServiceImplementation.getCurrentUser();
        System.out.println(currentUser);
        Post postMapped = postMapper.map(postRequest, currentUser);
        postRepository.savePost(
                postMapped.getPostName(),
                postMapped.getDescription(),
                postMapped.getUrl(),
                postMapped.getUser().getUserId(),
                postMapped.getCreatedDate(),
                subreddit.getId(),
                postMapped.getVoteCount());

    }

    @Transactional(readOnly = true)
    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id.toString()));
        return postMapper.mapToDto(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsBySubreddit(Long subredditId) {
        Subreddit subreddit = subredditRepository.findById(subredditId)
                .orElseThrow(() -> new SubredditNotFoundException(subredditId.toString()));
        List<Post> posts = postRepository.getPostByIdSubreddit(subreddit.getId());
        return posts.stream().map(postMapper::mapToDto).collect(toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return postRepository.getPostByUsername(user.getUsername())
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }
}
