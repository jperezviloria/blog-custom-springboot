package dev.protobot.blogcustom.service.implementation;

import dev.protobot.blogcustom.dto.SubredditDto;
import dev.protobot.blogcustom.exceptions.SpringRedditException;
import dev.protobot.blogcustom.exceptions.SubredditNotFoundException;
import dev.protobot.blogcustom.mapper.SubredditMapper;
import dev.protobot.blogcustom.model.Subreddit;
import dev.protobot.blogcustom.respository.SubredditRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SubRedditServiceImplementationTest {


    @Mock private SubredditRepository subredditRepository;
    @Mock private SubredditMapper subredditMapper;

    private SubRedditServiceImplementation subRedditServiceImplementation;

    @BeforeEach
    void setUp() {
        subRedditServiceImplementation = new SubRedditServiceImplementation(subredditRepository, subredditMapper);
    }

    /**
     * HAPPY CASE
     * ----------------------------------------
     * Created by: Julio Perez Viloria
     * Date of creation: 17/05/2021
     */
    @Test
    @Disabled
    @DisplayName("It should get all Subreddits")
    void saveSubreddit() {
    }


    /**
     * HAPPY CASE
     * ----------------------------------------
     * Created by: Julio Perez Viloria
     * Date of creation: 17/05/2021
     */
    @Test
    @Disabled
    @DisplayName("It should get Subreddit by id")
    void getAllSubreddit() {
    }

    /**
     * HAPPY CASE
     * ----------------------------------------
     * Created by: Julio Perez Viloria
     * Date of creation: 17/05/2021
     */

    @Test
    @DisplayName("It should get Subreddit by id when Subreddit does not exist")
    void itShouldGetSubredditByIdWhenSubredditDoesExist(){
        //given
        SubredditDto subredditDto = new SubredditDto(
                66L,
                "some name to subreddit",
                "test description to this subreddit",
                null
        );

        given(subredditRepository.getSubredditById(anyLong()))
                .willReturn(null);
        //when
        //then
        assertThatThrownBy(() -> subRedditServiceImplementation.getSubredditById(subredditDto.getId()))
                .isInstanceOf(SubredditNotFoundException.class)
                .hasMessageContaining("Not subreddit found with this id");

        verify(subredditRepository, never()).getSubredditById(anyLong());
    }

    @Test
    @DisplayName("It should get Subreddit by id when Subreddit does not exist")
    void itShouldNotGetSubredditByIdWhenSubredditDoesNotExist() {
        //given
        SubredditDto subredditDto = new SubredditDto(
                66L,
                "some name to subreddit",
                "test description to this subreddit",
                null
        );

        given(subredditRepository.getSubredditById(anyLong()))
                .willThrow(new NullPointerException());
        //when
        //then
        assertThatThrownBy(() -> subRedditServiceImplementation.getSubredditById(subredditDto.getId()))
                .isInstanceOf(SubredditNotFoundException.class)
                .hasMessageContaining("Not subreddit found with this id");

        verify(subredditRepository, never()).getSubredditById(anyLong());
    }

    @Test
    @DisplayName(" 2It should get Subreddit by id when Subreddit does not exist")
    void itShouldNotGetSubredditByIdWhenSubredditDoesNotExist2() {
        //given
        //when
        when(subredditRepository.getSubredditById(anyLong())).thenThrow(new NullPointerException());
        //then
        subRedditServiceImplementation.getSubredditById(anyLong());

    }


    /**
     * Expected Exceptions
     * ----------------------------------------
     * Created by: Julio Perez Viloria
     * Date of creation: 17/05/2021
     */
    @Test
    @DisplayName(" 3It should get Subreddit by id when Subreddit does not exist")
    void itShouldNotGetSubredditByIdWhenSubredditDoesNotExist3() {
        //given
        //when
//        SubredditDto subredditById = subRedditServiceImplementation.getSubredditById(anyLong());
//        assertNotNull(subredditById);
        //then
        doThrow(new SubredditNotFoundException("Not subreddit found with this id"))
                .when(subredditRepository)
                .getSubredditById(anyLong());

        subRedditServiceImplementation.getSubredditById(anyLong());
    }
}