package dev.protobot.blogcustom.controller;

import dev.protobot.blogcustom.dto.request.SubRedditRequest;
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
    public RestResponse<SubRedditRequest> saveSubreddit(@RequestBody SubRedditRequest subRedditRequest){
        SubRedditRequest subRedditRequestResponse = subRedditServiceImplementation.saveSubredditDto(subRedditRequest);
        return new RestResponse<>(HttpStatus.OK, subRedditRequestResponse);
    }

    @GetMapping("/getall")
    public RestResponse<List<SubRedditRequest>> getAllSubreddit(){
        List<SubRedditRequest> allSubredditDto = subRedditServiceImplementation.getAllSubreddit();
        return new RestResponse<>(HttpStatus.OK, allSubredditDto);
    }

}
