package com.example.daangn.model;

import com.example.daangn.service.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserIntegrationTest {
    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    Long userId = null;
    Post createdPost = null;
    int updatedMyPrice = -1;

    @Test
    @Order(1)
    @DisplayName("회원 가입 정보 없이 상품 등록 시 에러발생")
    void test1() {
        // given
        String title = "Apple <b>에어팟</b> 2세대 유선충전 모델 (MV7N2KH/A)";
        String imageUrl = "https://shopping-phinf.pstatic.net/main_1862208/18622086330.20200831140839.jpg";
        String linkUrl = "https://search.shopping.naver.com/gate.nhn?id=18622086330";
        int lPrice = 77000;
//        ProductRequestDto requestDto = new ProductRequestDto(
//                title,
//                imageUrl,
//                linkUrl,
//                lPrice
//        );
//
//        // when
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//            productService.createProduct(requestDto, userId);
//        });
//
//        // then
//        assertEquals("회원 Id 가 유효하지 않습니다.", exception.getMessage());
    }
}
