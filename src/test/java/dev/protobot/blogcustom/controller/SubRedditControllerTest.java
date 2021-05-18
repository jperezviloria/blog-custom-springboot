package dev.protobot.blogcustom.controller;

import dev.protobot.blogcustom.dto.SubredditDto;
import dev.protobot.blogcustom.model.Subreddit;
import dev.protobot.blogcustom.service.implementation.SubRedditServiceImplementation;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SubRedditControllerTest {

    @Mock
    private SubRedditServiceImplementation subRedditServiceImplementation;

    private SubRedditController subRedditController;

    @BeforeEach
    void setUp() {
        subRedditController = new SubRedditController(subRedditServiceImplementation);
    }


    @Test
    @DisplayName("It should save Subreddit")
    void itShouldSaveSubreddit() {
        //given
        SubredditDto subredditDto = new SubredditDto(
                null,
                "some name to subreddit",
                "test description to this subreddit",
                null
        );
        //when
        subRedditController.saveSubreddit(subredditDto);
        //then
        ArgumentCaptor<SubredditDto> subredditDtoArgumentCaptor =
                ArgumentCaptor.forClass(SubredditDto.class);
        verify(subRedditServiceImplementation).saveSubreddit(subredditDtoArgumentCaptor.capture());
        SubredditDto capturedSubreddit = subredditDtoArgumentCaptor.getValue();
        assertThat(capturedSubreddit).isEqualTo(subredditDto);
    }

    @Test
    @DisplayName("It should get all Subreddits")
    void itShouldGetAllSubreddits() {
        //when
        subRedditController.getAllSubreddit();
        //then
        verify(subRedditServiceImplementation).getAllSubreddit();
    }

    @Test
    @DisplayName("It should get Subreddit by id")
    void itShouldGetSubredditById() {

        //given
        SubredditDto subredditDto = new SubredditDto(
                2L,
                "Whatever name",
                "A simple description",
                0
        );

        //where
        subRedditController.getSubredditById(subredditDto.getId());

        //then
        ArgumentCaptor<Subreddit> subredditDtoArgumentCaptor =
                ArgumentCaptor.forClass(Subreddit.class);

        verify(subRedditServiceImplementation)
                .getSubredditById(subredditDtoArgumentCaptor
                        .capture()
                        .getId());

        Long capturedSubreddit =
                subredditDtoArgumentCaptor
                        .getValue()
                        .getId();

        assertThat(capturedSubreddit).isEqualTo(subredditDtoArgumentCaptor);
    }
/*
    @Test
    @DisplayName("It should get Subreddit by id when Subreddit does not exist")
    void itShouldNotGetSubredditByIdWhenSubredditDoesNotExist() {

        //given
        SubredditDto subredditDto = new SubredditDto(
                66L,
                "Whatever name",
                "A simple description",
                0
        );



        //where
        subRedditController.getSubredditById(subredditDto.getId());

        //then
        verify(subRedditServiceImplementation)
                .getSubredditById(subredditDto.getId());

        Long capturedSubreddit =
                subredditDtoArgumentCaptor
                        .getValue()
                        .getId();

        assertThat(capturedSubreddit).isEqualTo(subredditDtoArgumentCaptor);
    }

 */
}