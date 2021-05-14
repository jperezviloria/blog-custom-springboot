package dev.protobot.blogcustom.service.implementation;

import dev.protobot.blogcustom.dto.request.SubRedditRequest;
import dev.protobot.blogcustom.model.Subreddit;
import dev.protobot.blogcustom.respository.SubredditRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class SubRedditServiceImplementation {

    private final SubredditRepository subredditRepository;

    @Autowired
    public SubRedditServiceImplementation(SubredditRepository subredditRepository) {
        this.subredditRepository = subredditRepository;
    }


    @Transactional
    public SubRedditRequest saveSubredditDto(SubRedditRequest subRedditRequest){
        SubRedditRequest subredditDtoSaved = subredditRepository.saveSubredditDto(
                subRedditRequest.getName(),
                subRedditRequest.getDescription());
        //subRedditRequest.setId(save.getId());
        return subredditDtoSaved;
    }

    @Transactional(readOnly = true)
    public List<SubRedditRequest> getAllSubredditDto (){
        return subredditRepository.getAllSubredditDto();
    }

    private Subreddit mapSubredditRequest(SubRedditRequest subRedditRequest) {
        return Subreddit.builder().name(subRedditRequest.getName())
                .description(subRedditRequest.getDescription())
                .build();
    }

}
