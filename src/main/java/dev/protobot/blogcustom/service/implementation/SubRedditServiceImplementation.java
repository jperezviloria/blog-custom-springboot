package dev.protobot.blogcustom.service.implementation;

import dev.protobot.blogcustom.dto.SubredditDto;
import dev.protobot.blogcustom.exceptions.SpringRedditException;
import dev.protobot.blogcustom.exceptions.SubredditNotFoundException;
import dev.protobot.blogcustom.mapper.SubredditMapper;
import dev.protobot.blogcustom.model.Subreddit;
import dev.protobot.blogcustom.respository.SubredditRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SubRedditServiceImplementation {

    private final SubredditRepository subredditRepository;
    private final SubredditMapper subredditMapper;


    @Autowired
    public SubRedditServiceImplementation(SubredditRepository subredditRepository,
                                          SubredditMapper subredditMapper) {
        this.subredditRepository = subredditRepository;
        this.subredditMapper = subredditMapper;
    }

    @Transactional
    public SubredditDto saveSubreddit(SubredditDto subredditDto){
        Subreddit subredditMapped = subredditMapper.mapDtoToSubreddit(subredditDto);

        Subreddit subredditSaved = subredditRepository.saveSubreddit(
                subredditMapped.getName(),
                subredditMapped.getDescription());
        //subRedditRequest.setId(save.getId());
        return subredditDto;
    }

    @Transactional(readOnly = true)
    public List<SubredditDto> getAllSubreddit (){
        List<SubredditDto> allStudents = subredditRepository.getAllSubreddit()
                .stream()
                .map(subredditMapper::mapSubredditToDto)
                .collect(Collectors.toList());
        System.out.println(allStudents);
        return allStudents;
    }

    public SubredditDto getSubredditById(Long id){
        Subreddit subredditOptional = subredditRepository.getSubredditById(id)
                .orElseThrow(() -> new SubredditNotFoundException("Not subreddit found with this id"));
        return subredditMapper.mapSubredditToDto(subredditOptional);


    }

}
