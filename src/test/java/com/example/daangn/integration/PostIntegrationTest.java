package com.example.daangn.integration;

import com.example.daangn.dto.SignupRequestDto;
import com.example.daangn.model.*;
import com.example.daangn.repository.post.PostRepository;
import com.example.daangn.repository.post.PostRepositoryImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import static org.junit.jupiter.api.Assertions.*;

//테스트 환경에서 스프링 동작을 통해 필요한 의존성 제공
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// test 클래스 당 인스턴스 생성
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//Test의 실행 순서를 보장하기 위함
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PostIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostRepositoryImpl postRepositoryImpl;
    private HttpHeaders headers;
    private ObjectMapper mapper = new ObjectMapper();
    private String user1Token;
    private String user2Token;
    private String user3Token;
    private SignupRequestDto user1 = SignupRequestDto.builder()
            .nickname("당근러")
            .username("user1")
            .password("123")
            .confirmPassword("123")
            .build();

    private SignupRequestDto user2 = SignupRequestDto.builder()
            .nickname("만물상")
            .username("user2")
            .password("123")
            .build();

    private SignupRequestDto user3 = SignupRequestDto.builder()
            .nickname("짠돌이")
            .username("user3")
            .password("123")
            .build();

    private PostDto post1 = PostDto.builder()
            .title("맥북 판매해요")
            .category(CategoryEnum.DIGITAL)
            .price(800000)
            .area(AreaEnum.BUKGU)
            .content("얼마 안씀")
            .imageUrl("mac.png")
            .state(StateEnum.SALE)
            .build();

    private PostDto post2 = PostDto.builder()
            .title("아이옷 판매해요")
            .category(CategoryEnum.KID)
            .price(800000)
            .area(AreaEnum.BUKGU)
            .content("얼마 안씀")
            .imageUrl("mac.png")
            .state(StateEnum.SALE)
            .build();

    private PostDto post3 = PostDto.builder()
            .title("핸드폰 판매해요")
            .category(CategoryEnum.DIGITAL)
            .price(800000)
            .area(AreaEnum.NAMGU)
            .content("얼마 안씀")
            .imageUrl("mac.png")
            .state(StateEnum.RESERVED)
            .build();

    @BeforeEach //각각의 테스트가 실행되기전에 실행 됨 <-> @BeforeAll
    public void setup() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @Nested
    @DisplayName("User1의 CRUD")
    class User1Scenario {
        @Test
        @Order(1)
        @DisplayName("유저1 등록")
        void test1() throws JsonProcessingException {
            // given

            String requestBody = mapper.writeValueAsString(user1);
            HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

            // when
            ResponseEntity<ResultDto> response = restTemplate.postForEntity(
                    "/api/signup",
                    request,
                    ResultDto.class
            );

            // then
            assertEquals(HttpStatus.OK, response.getStatusCode());

            ResultDto resultDto = response.getBody();
            assertNotNull(resultDto);
            assertTrue(resultDto.response);
            assertEquals(resultDto.message, "회원가입 성공");
        }

        @Test
        @Order(2)
        @DisplayName("유저1 로그인")
        void test2() throws JsonProcessingException {
            // given

            String requestBody = mapper.writeValueAsString(user1);
            HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

            // when
            ResponseEntity<ResultDto> response = restTemplate.postForEntity(
                    "/api/login",
                    request,
                    ResultDto.class
            );

            // then
            assertEquals(HttpStatus.OK, response.getStatusCode());

            ResultDto resultDto = response.getBody();
            assertNotNull(resultDto);
            assertTrue(resultDto.response);
            assertEquals(resultDto.username, user1.getUsername());
            assertEquals(resultDto.nickname, user1.getNickname());

            //user1의 토큰을 담아둠
            user1Token = resultDto.token;
        }


        @Test
        @Order(3)
        @DisplayName("유저1의 게시물1 작성")
        void test3() throws JsonProcessingException {
            // given
            String requestBody = mapper.writeValueAsString(post1);
            headers.set("Authorization", user1Token);
            HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

            //when
            ResponseEntity<ResultDto> response = restTemplate.postForEntity(
                    "/api/post",
                    request,
                    ResultDto.class
            );

            ResultDto resultDto = response.getBody();
            assertNotNull(resultDto);
            assertTrue(resultDto.response);
            assertEquals(resultDto.message, "게시글 생성 성공");
        }

        @Test
        @Order(4)
        @DisplayName("유저1의 게시물1 읽기")
        void test4() throws JsonProcessingException {
            // given
            headers.set("Authorization", user1Token);
            HttpEntity<String> request = new HttpEntity<>("",headers);
            post1.setId(postRepositoryImpl.findOneByUsername(user1.getUsername()));

            //when
            ResponseEntity<ResultDto> response = restTemplate.exchange(
                    "/api/post/"+post1.id,
                    HttpMethod.GET,
                    request,
                    ResultDto.class
            );

            //then
            ResultDto resultDto = response.getBody();
            assertNotNull(resultDto);
            assertEquals(resultDto.post.getId(),post1.getId());
            assertEquals(resultDto.post.getTitle(),post1.getTitle());
            assertEquals(resultDto.post.getCategory(),post1.getCategory());
            assertEquals(resultDto.post.getPrice(),post1.getPrice());
            assertEquals(resultDto.post.getArea(),post1.getArea());
            assertEquals(resultDto.post.getContent(),post1.getContent());
            assertEquals(resultDto.post.getImageUrl(),post1.getImageUrl());
            assertEquals(resultDto.post.getState(),post1.getState());
        }

        @Test
        @Order(5)
        @DisplayName("유저1의 본인 작성 게시물 수정")
        void test5() throws JsonProcessingException {
            // given
            String requestBody = mapper.writeValueAsString(post2);
            headers.set("Authorization", user1Token);
            HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

            //when
            ResponseEntity<ResultDto> response = restTemplate.exchange(
                    "/api/post/"+post1.id,
                    HttpMethod.PUT,
                    request,
                    ResultDto.class
            );

            //then
            Post post = postRepository.findById(post1.id).orElseThrow(()-> new IllegalArgumentException("테스트중 에러"));
            ResultDto resultDto = response.getBody();

            assertNotNull(resultDto);
            assertTrue(resultDto.response);
            assertEquals(resultDto.message, "게시글 수정 성공");

            assertEquals(post2.price, post.getPrice());
            assertEquals(post2.area, post.getArea());
            assertEquals(post2.category, post.getCategory());
            assertEquals(post2.content, post.getContent());
            assertEquals(post2.imageUrl, post.getImageUrl());
            assertEquals(post2.state, post.getState());
            assertEquals(post2.title, post.getTitle());
        }

        @Test
        @Order(6)
        @DisplayName("유저1의 본인 작성 게시물 삭제")
        void test6() throws JsonProcessingException {
            // given
            String requestBody = mapper.writeValueAsString(post2);
            headers.set("Authorization", user1Token);
            HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

            //when
            ResponseEntity<ResultDto> response = restTemplate.exchange(
                    "/api/post/"+post1.id,
                    HttpMethod.DELETE,
                    request,
                    ResultDto.class
            );

            //then
            ResultDto resultDto = response.getBody();

            assertNotNull(resultDto);
            assertTrue(resultDto.response);
            assertEquals(resultDto.message, "게시글 삭제 성공");
            assertThrows(IllegalArgumentException.class, () -> {
                postRepository.findById(post1.id).orElseThrow(()-> new IllegalArgumentException("테스트중 에러"));
            });

        }
    }

    @Nested
    @DisplayName("User2의 like")
    class User2Scenario {

    }

    @Getter
    @Setter
    @Builder
    static class ResultDto {
        private Boolean response;
        private String message;
        private String token;
        private String userId;
        private String username;
        private String nickname;
        private PostResponseDto post;
    }
    @Getter
    @Setter
    @Builder
    static class PostResponseDto{
        private Long id;
        private String title;
        private CategoryEnum category;
        private int price;
        private AreaEnum area;
        private String content;
        private String imageUrl;
        private StateEnum state;
        private String after;
        private Long userId;
        private String nickname;
        private long likeCount;
        private Boolean isLiked;
    }

    @Getter
    @Setter
    @Builder
    static class PostDto {
        private Long id;
        private String title;
        private CategoryEnum category;
        private int price;
        private AreaEnum area;
        private String content;
        private String imageUrl;
        private StateEnum state;
    }

    @Getter
    @Setter
    @Builder
    static class SignIn{
        private String username;
        private String password;
    }
}
