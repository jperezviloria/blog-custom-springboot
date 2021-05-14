package dev.protobot.blogcustom.service.implementation;

import dev.protobot.blogcustom.dto.SubredditDto;
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
    public SubredditDto saveSubredditDto(SubredditDto subredditDto){
        SubredditDto subredditDtoSaved = subredditRepository.saveSubredditDto(
                subredditDto.getName(),
                subredditDto.getDescription());
        //subRedditRequest.setId(save.getId());
        return subredditDtoSaved;
    }

    @Transactional(readOnly = true)
    public List<SubredditDto> getAllSubredditDto (){
        return subredditRepository.getAllSubredditDto();
    }

    //-------------------------------------------------------

    @Transactional
    public SubredditDto saveSubreddit(SubredditDto subredditDto){
        SubredditDto subredditDtoSaved = subredditRepository.saveSubredditDto(
                subredditDto.getName(),
                subredditDto.getDescription());
        //subRedditRequest.setId(save.getId());
        return subredditDtoSaved;
    }

    @Transactional(readOnly = true)
    public List<SubredditDto> getAllSubreddit (){
        return subredditRepository.getAllSubredditDto();
    }

    private Subreddit mapSubredditRequest(SubredditDto subredditDto) {
        return Subreddit.builder().name(subredditDto.getName())
                .description(subredditDto.getDescription())
                .build();
    }

}
