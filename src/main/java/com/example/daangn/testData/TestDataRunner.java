package com.example.daangn.testData;

import com.example.daangn.model.*;
import com.example.daangn.repository.UserRepository;
import com.example.daangn.repository.like.LikeRepository;
import com.example.daangn.repository.post.PostRepository;
import com.example.daangn.service.PostService;
import com.example.daangn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

@Component
public class TestDataRunner implements ApplicationRunner {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    LikeRepository likeRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    PostService postService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        User testUser1 = new User("테스트유저1","test1", passwordEncoder.encode("123"));
        User testUser2 = new User("테스트유저2","test2", passwordEncoder.encode("123"));
        User testUser3 = new User("테스트유저3","test3",passwordEncoder.encode("123"));
        userRepository.save(testUser1);
        userRepository.save(testUser2);
        userRepository.save(testUser3);

        CreateUserAndPost(100,testUser1,testUser2);
        CreateLike(10,testUser1,testUser2);
    }

    private void CreateLike(int count, User testUser1, User testUser2) {
        Random random = new Random();
        HashSet<Long> postSet = new HashSet<>();
        Long randomPost = (long)random.nextInt(100);
        User user;

        for(int j=count/2; j<count; j++){
            int userNumber = random.nextInt(2)+1;

            //유저 생성
            if (userNumber==1){
                user = testUser1;
            }else {
                user = testUser2;
            }

            //post 생성
            while(postSet.contains(randomPost)){
                randomPost = (long)random.nextInt(100);
            }
            postSet.add(randomPost);

            Post post = postRepository.findById(randomPost).orElseThrow(()->new IllegalArgumentException("테스트 생성 중 요류"));
            Like like = new Like((long)j,user,post);
            likeRepository.save(like);
        }

    }

    public void  CreateUserAndPost(int count,User testUser1,User testUser2){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 3;
        List<Like> likeList = new ArrayList<>();
        for(int i =1; i<count/2; i++){
            Random random = new Random();

            //title 생성
            String title =random.ints(leftLimit, rightLimit + 1)
                    .limit(targetStringLength)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();

            //cateoory 생성
            int categoryNumber = random.nextInt(14)+1;
            CategoryEnum category = getCategoryEnum(categoryNumber);

            //price 생성
            int price = random.nextInt(100000);

            //area 생성
            int areaNumber = random.nextInt(9)+1;
            AreaEnum area = getAreaEnum(areaNumber);

            //content 생성
            String content = random.ints(leftLimit, rightLimit + 1)
                    .limit(targetStringLength)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();

            //imageUrl 생성
            String imageUrl = random.ints(leftLimit, rightLimit + 1)
                    .limit(targetStringLength)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();

            //state 생성
            int stateNumber = random.nextInt(2)+1;
            StateEnum state = getStateEnum(stateNumber);

            User user = testUser1;
            Post post = new Post((long)i,title,category,price,area,content,imageUrl,state,user,likeList);
            postRepository.save(post);
        }
        for(int j=count/2; j<count; j++){
            Random random = new Random();

            //title 생성
            String title =random.ints(leftLimit, rightLimit + 1)
                    .limit(targetStringLength)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();

            //cateoory 생성
            int categoryNumber = random.nextInt(14)+1;
            CategoryEnum category = getCategoryEnum(categoryNumber);

            //price 생성
            int price = random.nextInt(100000);

            //area 생성
            int areaNumber = random.nextInt(9)+1;
            AreaEnum area = getAreaEnum(areaNumber);

            //content 생성
            String content = random.ints(leftLimit, rightLimit + 1)
                    .limit(targetStringLength)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();

            //imageUrl 생성
            String imageUrl = random.ints(leftLimit, rightLimit + 1)
                    .limit(targetStringLength)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();

            //state 생성
            int stateNumber = random.nextInt(2)+1;
            StateEnum state = getStateEnum(stateNumber);

            User user = testUser2;
            Post post = new Post((long)j,title,category,price,area,content,imageUrl,state,user,likeList);
            postRepository.save(post);
        }


    }

    private CategoryEnum getCategoryEnum
            (int categoryNumber) {
        switch (categoryNumber){
            case 1:
                return CategoryEnum.DIGITAL;
            case 2:
                return CategoryEnum.APPLIANCES;
            case 3:
                return CategoryEnum.HOUSEHOLD;
            case 4:
                return CategoryEnum.KID;
            case 5:
                return CategoryEnum.GROCERIES;
            case 6:
                return CategoryEnum.SPORT;
            case 7:
                return CategoryEnum.CLOTHES;
            case 8:
                return CategoryEnum.INTEREST;
            case 9:
                return CategoryEnum.BEAUTY;
            case 10:
                return CategoryEnum.PET;
            case 11:
                return CategoryEnum.BOOK;
            case 12:
                return CategoryEnum.PLANT;
            default:
                return CategoryEnum.ETC;
        }
    }

    private AreaEnum getAreaEnum
            (int areaNumber) {
        switch (areaNumber){
            case 1:
                return AreaEnum.JUNGGU;
            case 2:
                return AreaEnum.BUKGU;
            case 3:
                return AreaEnum.DONGGU;
            case 4:
                return AreaEnum.DALSEONGGUN;
            case 5:
                return AreaEnum.DALSEOGU;
            case 6:
                return AreaEnum.SEOGU;
            case 7:
                return AreaEnum.NAMGU;
            default:
                return AreaEnum.SUSEONGGU;
        }
    }

    private StateEnum getStateEnum
            (int stateNumber) {
        switch (stateNumber){
            case 1:
                return StateEnum.RESERVED;
            case 2:
                return StateEnum.DONE;
            default:
                return StateEnum.SALE;
        }
    }

}
