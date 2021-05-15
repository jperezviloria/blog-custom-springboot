package dev.protobot.blogcustom.controller;

import dev.protobot.blogcustom.dto.SubredditDto;
import dev.protobot.blogcustom.dto.response.RestResponse;
import dev.protobot.blogcustom.model.Subreddit;
import dev.protobot.blogcustom.service.implementation.SubRedditServiceImplementation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subreddit")
@Slf4j
public class SubRedditController {

    private final SubRedditServiceImplementation subRedditServiceImplementation;

    @Autowired
    public SubRedditController(SubRedditServiceImplementation subRedditServiceImplementation) {
        this.subRedditServiceImplementation = subRedditServiceImplementation;
    }


    @PostMapping("/save")
    public RestResponse<SubredditDto> saveSubredditDto(@RequestBody SubredditDto subredditDto){
        SubredditDto subredditDtoResponse = subRedditServiceImplementation.saveSubredditDto(subredditDto);
        return new RestResponse<>(HttpStatus.OK, subredditDtoResponse);
    }

    @GetMapping("/getall")
    public RestResponse<List<SubredditDto>> getAllSubredditDto(){
        List<SubredditDto> allSubredditDto = subRedditServiceImplementation.getAllSubredditDto();
        return new RestResponse<>(HttpStatus.OK, allSubredditDto);
    }

    //-------------------------------------------------------


    @PostMapping("/save/2")
    public RestResponse<SubredditDto> saveSubreddit(@RequestBody SubredditDto subredditDto){
        SubredditDto subredditDtoResponse = subRedditServiceImplementation.saveSubreddit(subredditDto);
        return new RestResponse<>(HttpStatus.OK, subredditDtoResponse);
    }

    @GetMapping("/getall/2")
    public RestResponse<List<SubredditDto>> getAllSubreddit(){
        List<SubredditDto> allSubredditDto = subRedditServiceImplementation.getAllSubreddit();
        return new RestResponse<>(HttpStatus.OK, allSubredditDto);
    }

    @GetMapping("/{id}")
    public RestResponse<SubredditDto> getSubredditById(@PathVariable Long id){
        SubredditDto subredditDto = subRedditServiceImplementation.getSubredditById(id);
        return new RestResponse<>(HttpStatus.OK, subredditDto);
    }

}
