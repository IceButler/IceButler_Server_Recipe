# IceButler_Server
>  냉장고를 지켜주는 나만의 집사😺
<br>

## Tech Stack
### Backend
<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> ![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens) <img src="https://img.shields.io/badge/spring data jpa-6DB33F?style=for-the-badge&logoColor=white"> <img src="https://img.shields.io/badge/querydsl-6DB33F?style=for-the-badge&logoColor=white"> <img src="https://img.shields.io/badge/hibernate-59666C?style=for-the-badge&logo=hibernate&logoColor=white"> <img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white"> 

### DB
<img src="https://img.shields.io/badge/amazon rds-527FFF?style=for-the-badge&logo=amazonrds&logoColor=white"> <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> <img src="https://img.shields.io/badge/jasypt-0769AD?style=for-the-badge&logoColor=white"> <img src="https://img.shields.io/badge/redis-DC382D?style=for-the-badge&logo=redis&logoColor=white"> <img src="https://img.shields.io/badge/firebase-FFCA28?style=for-the-badge&logo=firebase&logoColor=white"> <img src="https://img.shields.io/badge/amazon s3-569A31?style=for-the-badge&logo=amazons3&logoColor=white">

### CI/CD
<img src="https://img.shields.io/badge/jenkins-D24939?style=for-the-badge&logo=jenkins&logoColor=white"> <img src="https://img.shields.io/badge/docker-2496ED?style=for-the-badge&logo=docker&logoColor=white"> <img src="https://img.shields.io/badge/docker hub-2496ED?style=for-the-badge&logo=docker&logoColor=white"> 

### Deploy
<img src="https://img.shields.io/badge/amazon ec2-FF9900?style=for-the-badge&logo=amazon ec2&logoColor=white"> <img src="https://img.shields.io/badge/amazon sqs-FF4F8B?style=for-the-badge&logo=amazonsqs&logoColor=white"> <img src="https://img.shields.io/badge/amazon api gateway-FF4F8B?style=for-the-badge&logo=amazonapigateway&logoColor=white"> <img src="https://img.shields.io/badge/aws lambda-FF9900?style=for-the-badge&logo=awslambda&logoColor=white"> 

### Develop Tool
<img src="https://img.shields.io/badge/intelliJ-000000?style=for-the-badge&logo=intellij idea&logoColor=white"> <img src="https://img.shields.io/badge/postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white"> <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white"> <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white"> 
<br> 
<br>

## Project Architecture
<details>
<summary>FINAL ARCHITECTURE</summary>
    
![스크린샷 2023-06-07 오전 1 09 2](https://github.com/IceButler/IceButler_Server/assets/90203250/16d4f6ad-5d01-4ecc-8fbb-4afacca7d55e)
</details>

<details>
<summary>CI/CD</summary>
    
![image 370](https://github.com/IceButler/IceButler_Server/assets/90203250/cec1115d-1014-4d57-80a4-7ba44408509d)
</details>

<details>
<summary>AWS Lambda</summary>
    
 ![image 340](https://github.com/IceButler/IceButler_Server/assets/90203250/f215a8d5-8201-4bcb-9033-fdaad5633e2b)
</details>

<details>
<summary>AWS SQS</summary>
    
 ![image 397](https://github.com/IceButler/IceButler_Server/assets/90203250/6f76861e-8335-4df7-b6db-2e0790882cfe)
</details>
<br>

## Project Structure

<details>
<summary>Details</summary>

```jsx
├── Dockerfile
├── build.gradle
├── gradle
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── example
    │   │           └── icebutler_server
    │   │               ├── IceButlerServerApplication.java
    │   │               ├── admin
    │   │               │   ├── controller
    │   │               │   │   ├── AdminController.java
    │   │               │   │   └── AdminExceptionController.java
    │   │               │   ├── dto
    │   │               │   │   ├── assembler
    │   │               │   │   │   └── AdminAssembler.java
    │   │               │   │   ├── condition
    │   │               │   │   │   └── SearchCond.java
    │   │               │   │   ├── request
    │   │               │   │   │   ├── JoinRequest.java
    │   │               │   │   │   ├── LoginRequest.java
    │   │               │   │   │   ├── ModifyFoodRequest.java
    │   │               │   │   │   ├── RemoveFoodRequest.java
    │   │               │   │   │   ├── RemoveFoodsRequest.java
    │   │               │   │   │   └── WithDrawRequest.java
    │   │               │   │   └── response
    │   │               │   │       ├── AdminResponse.java
    │   │               │   │       ├── LoginResponse.java
    │   │               │   │       ├── LogoutResponse.java
    │   │               │   │       ├── PostAdminRes.java
    │   │               │   │       ├── SearchFoodsResponse.java
    │   │               │   │       └── UserResponse.java
    │   │               │   ├── entity
    │   │               │   │   └── Admin.java
    │   │               │   ├── exception
    │   │               │   │   ├── AdminAnnotationIsNowhereException.java
    │   │               │   │   ├── AdminNotFoundException.java
    │   │               │   │   ├── AlreadyExistEmailException.java
    │   │               │   │   ├── FoodNotFoundException.java
    │   │               │   │   └── PasswordNotMatchException.java
    │   │               │   ├── repository
    │   │               │   │   ├── AdminRepository.java
    │   │               │   │   ├── AdminRepositoryQuerydsl.java
    │   │               │   │   └── AdminRepositoryQuerydslImpl.java
    │   │               │   └── service
    │   │               │       ├── AdminService.java
    │   │               │       └── AdminServiceImpl.java
    │   │               ├── alarm
    │   │               │   ├── dto
    │   │               │   │   ├── FcmMessage.java
    │   │               │   │   ├── Message.java
    │   │               │   │   ├── Notification.java
    │   │               │   │   └── assembler
    │   │               │   │       └── NotificationAssembler.java
    │   │               │   ├── entity
    │   │               │   │   └── PushNotification.java
    │   │               │   ├── repository
    │   │               │   │   └── PushNotificationRepository.java
    │   │               │   └── service
    │   │               │       ├── NotificationService.java
    │   │               │       └── NotificationServiceImpl.java
    │   │               ├── cart
    │   │               │   ├── controller
    │   │               │   │   ├── CartController.java
    │   │               │   │   ├── CartExceptionController.java
    │   │               │   │   └── MultiCartController.java
    │   │               │   ├── dto
    │   │               │   │   └── cart
    │   │               │   │       ├── assembler
    │   │               │   │       │   ├── CartAssembler.java
    │   │               │   │       │   ├── CartFoodAssembler.java
    │   │               │   │       │   └── MultiCartFoodAssembler.java
    │   │               │   │       ├── request
    │   │               │   │       │   ├── AddFoodRequest.java
    │   │               │   │       │   ├── AddFoodToCartRequest.java
    │   │               │   │       │   └── RemoveFoodFromCartRequest.java
    │   │               │   │       └── response
    │   │               │   │           └── CartResponse.java
    │   │               │   ├── entity
    │   │               │   │   ├── cart
    │   │               │   │   │   ├── Cart.java
    │   │               │   │   │   └── CartFood.java
    │   │               │   │   └── multiCart
    │   │               │   │       ├── MultiCart.java
    │   │               │   │       └── MultiCartFood.java
    │   │               │   ├── exception
    │   │               │   │   ├── CartFoodNotFoundException.java
    │   │               │   │   └── CartNotFoundException.java
    │   │               │   ├── repository
    │   │               │   │   ├── cart
    │   │               │   │   │   ├── CartFoodQuerydslRepository.java
    │   │               │   │   │   ├── CartFoodQuerydslRepositoryImpl.java
    │   │               │   │   │   ├── CartFoodRepository.java
    │   │               │   │   │   └── CartRepository.java
    │   │               │   │   └── multiCart
    │   │               │   │       ├── MultiCartFoodQuerydslRepository.java
    │   │               │   │       ├── MultiCartFoodQuerydslRepositoryImpl.java
    │   │               │   │       ├── MultiCartFoodRepository.java
    │   │               │   │       └── MultiCartRepository.java
    │   │               │   └── service
    │   │               │       ├── CartService.java
    │   │               │       ├── CartServiceImpl.java
    │   │               │       └── MultiCartServiceImpl.java
    │   │               ├── food
    │   │               │   ├── controller
    │   │               │   │   ├── FoodController.java
    │   │               │   │   └── FoodExceptionController.java
    │   │               │   ├── dto
    │   │               │   │   ├── assembler
    │   │               │   │   │   └── FoodAssembler.java
    │   │               │   │   ├── request
    │   │               │   │   │   └── FoodReq.java
    │   │               │   │   └── response
    │   │               │   │       ├── BarcodeFoodRes.java
    │   │               │   │       ├── FoodRes.java
    │   │               │   │       └── FoodResponse.java
    │   │               │   ├── entity
    │   │               │   │   ├── Food.java
    │   │               │   │   ├── FoodCategory.java
    │   │               │   │   └── FoodDeleteStatus.java
    │   │               │   ├── exception
    │   │               │   │   ├── BarcodeFoodNotFoundException.java
    │   │               │   │   ├── DuplicateFoodNameException.java
    │   │               │   │   ├── FoodCategoryNotFoundException.java
    │   │               │   │   ├── FoodDeleteStatusNotFoundException.java
    │   │               │   │   └── FoodNameNotFoundException.java
    │   │               │   ├── repository
    │   │               │   │   └── FoodRepository.java
    │   │               │   └── service
    │   │               │       ├── FoodService.java
    │   │               │       └── FoodServiceImpl.java
    │   │               ├── fridge
    │   │               │   ├── controller
    │   │               │   │   ├── FridgeController.java
    │   │               │   │   ├── FridgeExceptionController.java
    │   │               │   │   └── MultiFridgeController.java
    │   │               │   ├── dto
    │   │               │   │   ├── fridge
    │   │               │   │   │   ├── assembler
    │   │               │   │   │   │   ├── FridgeAssembler.java
    │   │               │   │   │   │   ├── FridgeFoodAssembler.java
    │   │               │   │   │   │   └── FridgeUtils.java
    │   │               │   │   │   ├── request
    │   │               │   │   │   │   ├── DeleteFridgeFoodsReq.java
    │   │               │   │   │   │   ├── FridgeFoodReq.java
    │   │               │   │   │   │   ├── FridgeFoodsReq.java
    │   │               │   │   │   │   ├── FridgeModifyMembersReq.java
    │   │               │   │   │   │   ├── FridgeModifyReq.java
    │   │               │   │   │   │   ├── FridgeRegisterMembersReq.java
    │   │               │   │   │   │   └── FridgeRegisterReq.java
    │   │               │   │   │   └── response
    │   │               │   │   │       ├── FridgeDiscardRes.java
    │   │               │   │   │       ├── FridgeFoodRes.java
    │   │               │   │   │       ├── FridgeFoodStatistics.java
    │   │               │   │   │       ├── FridgeFoodsRes.java
    │   │               │   │   │       ├── FridgeFoodsStatistics.java
    │   │               │   │   │       ├── FridgeMainRes.java
    │   │               │   │   │       ├── FridgeRes.java
    │   │               │   │   │       ├── FridgeUserMainRes.java
    │   │               │   │   │       ├── FridgeUserRes.java
    │   │               │   │   │       ├── FridgeUsersRes.java
    │   │               │   │   │       ├── GetFridgesMainRes.java
    │   │               │   │   │       ├── MultiFridgeRes.java
    │   │               │   │   │       ├── RecipeFridgeFoodListRes.java
    │   │               │   │   │       ├── RecipeFridgeFoodListsRes.java
    │   │               │   │   │       ├── SearchFoodRes.java
    │   │               │   │   │       ├── SearchFridgeFoodRes.java
    │   │               │   │   │       ├── SelectFridgeRes.java
    │   │               │   │   │       ├── SelectFridgesMainRes.java
    │   │               │   │   │       ├── UpdateMembersRes.java
    │   │               │   │   │       └── UpdateMultiMemberRes.java
    │   │               │   │   └── multiFridge
    │   │               │   │       └── assembler
    │   │               │   │           ├── MultiFridgeAssembler.java
    │   │               │   │           └── MultiFridgeFoodAssembler.java
    │   │               │   ├── entity
    │   │               │   │   ├── fridge
    │   │               │   │   │   ├── Fridge.java
    │   │               │   │   │   ├── FridgeFood.java
    │   │               │   │   │   └── FridgeUser.java
    │   │               │   │   └── multiFridge
    │   │               │   │       ├── MultiFridge.java
    │   │               │   │       ├── MultiFridgeFood.java
    │   │               │   │       └── MultiFridgeUser.java
    │   │               │   ├── exception
    │   │               │   │   ├── FridgeFoodNotFoundException.java
    │   │               │   │   ├── FridgeNameEmptyException.java
    │   │               │   │   ├── FridgeNotFoundException.java
    │   │               │   │   ├── FridgeRemoveException.java
    │   │               │   │   ├── FridgeTypeNotFoundException.java
    │   │               │   │   ├── FridgeUserNotFoundException.java
    │   │               │   │   ├── InvalidFridgeUserRoleException.java
    │   │               │   │   └── PermissionDeniedException.java
    │   │               │   ├── repository
    │   │               │   │   ├── fridge
    │   │               │   │   │   ├── FridgeFood
    │   │               │   │   │   │   ├── FridgeFoodCustom.java
    │   │               │   │   │   │   ├── FridgeFoodRepository.java
    │   │               │   │   │   │   └── FridgeFoodRepositoryImpl.java
    │   │               │   │   │   ├── FridgeRepository.java
    │   │               │   │   │   └── FridgeUserRepository.java
    │   │               │   │   └── multiFridge
    │   │               │   │       ├── MultiFridgeFood
    │   │               │   │       │   ├── MultiFridgeFoodCustom.java
    │   │               │   │       │   ├── MultiFridgeFoodRepository.java
    │   │               │   │       │   └── MultiFridgeFoodRepositoryImpl.java
    │   │               │   │       ├── MultiFridgeRepository.java
    │   │               │   │       └── MultiFridgeUserRepository.java
    │   │               │   └── service
    │   │               │       ├── FridgeService.java
    │   │               │       ├── FridgeServiceImpl.java
    │   │               │       └── MultiFridgeServiceImpl.java
    │   │               ├── global
    │   │               │   ├── config
    │   │               │   │   ├── AwsSqsConfig.java
    │   │               │   │   ├── JasyptConfig.java
    │   │               │   │   ├── QueryDslConfig.java
    │   │               │   │   ├── RedisConfig.java
    │   │               │   │   └── WebConfig.java
    │   │               │   ├── controller
    │   │               │   │   └── ExceptionController.java
    │   │               │   ├── dto
    │   │               │   │   └── response
    │   │               │   │       └── ResponseCustom.java
    │   │               │   ├── entity
    │   │               │   │   ├── BaseEntity.java
    │   │               │   │   └── FridgeRole.java
    │   │               │   ├── entityListener
    │   │               │   │   ├── CartEntityListener.java
    │   │               │   │   ├── FoodEntityListener.java
    │   │               │   │   ├── FridgeEntityListener.java
    │   │               │   │   ├── FridgeUserEntityListener.java
    │   │               │   │   ├── MultiCartEntityListener.java
    │   │               │   │   ├── MultiFridgeEntityListener.java
    │   │               │   │   ├── MultiFridgeUserEntityListener.java
    │   │               │   │   └── UserEntityListener.java
    │   │               │   ├── feign
    │   │               │   │   ├── dto
    │   │               │   │   │   ├── AdminReq.java
    │   │               │   │   │   ├── FoodReq.java
    │   │               │   │   │   └── UserReq.java
    │   │               │   │   ├── event
    │   │               │   │   │   ├── DeleteUserEvent.java
    │   │               │   │   │   ├── FoodEvent.java
    │   │               │   │   │   ├── UpdateFoodEvent.java
    │   │               │   │   │   ├── UpdateUserEvent.java
    │   │               │   │   │   └── UserEvent.java
    │   │               │   │   ├── feignClient
    │   │               │   │   │   └── RecipeServerClient.java
    │   │               │   │   ├── handler
    │   │               │   │   │   ├── RecipeServerEventHandler.java
    │   │               │   │   │   └── RecipeServerEventHandlerImpl.java
    │   │               │   │   └── publisher
    │   │               │   │       ├── RecipeServerEventPublisher.java
    │   │               │   │       └── RecipeServerEventPublisherImpl.java
    │   │               │   ├── resolver
    │   │               │   │   ├── Admin.java
    │   │               │   │   ├── AdminLoginStatus.java
    │   │               │   │   ├── AdminResolver.java
    │   │               │   │   ├── Auth.java
    │   │               │   │   ├── IsAdminLogin.java
    │   │               │   │   ├── IsLogin.java
    │   │               │   │   ├── LoginResolver.java
    │   │               │   │   └── LoginStatus.java
    │   │               │   ├── sqs
    │   │               │   │   ├── AmazonSQSSender.java
    │   │               │   │   ├── AmazonSQSSenderImpl.java
    │   │               │   │   ├── AwsSqsListener.java
    │   │               │   │   └── FoodData.java
    │   │               │   └── util
    │   │               │       ├── AppleUtils.java
    │   │               │       ├── AwsS3ImageUrlUtil.java
    │   │               │       ├── BeanUtils.java
    │   │               │       ├── Constant.java
    │   │               │       ├── TokenUtils.java
    │   │               │       └── redis
    │   │               │           ├── RedisTemplateService.java
    │   │               │           ├── RedisTemplateServiceImpl.java
    │   │               │           ├── RedisUtils.java
    │   │               │           └── SyncData.java
    │   │               └── user
    │   │                   ├── controller
    │   │                   │   ├── UserAuthController.java
    │   │                   │   ├── UserController.java
    │   │                   │   └── UserExceptionController.java
    │   │                   ├── dto
    │   │                   │   ├── LoginUserReq.java
    │   │                   │   ├── assembler
    │   │                   │   │   └── UserAssembler.java
    │   │                   │   ├── request
    │   │                   │   │   ├── PatchProfileReq.java
    │   │                   │   │   ├── PostNicknameReq.java
    │   │                   │   │   ├── PostUserReq.java
    │   │                   │   │   └── UserAuthTokenReq.java
    │   │                   │   └── response
    │   │                   │       ├── IsEnableRes.java
    │   │                   │       ├── MyNotificationRes.java
    │   │                   │       ├── MyProfileRes.java
    │   │                   │       ├── NickNameRes.java
    │   │                   │       ├── PostNickNameRes.java
    │   │                   │       └── PostUserRes.java
    │   │                   ├── entity
    │   │                   │   ├── Provider.java
    │   │                   │   └── User.java
    │   │                   ├── exception
    │   │                   │   ├── AccessDeniedUserException.java
    │   │                   │   ├── AlreadyExistNickNameException.java
    │   │                   │   ├── AlreadyWithdrawUserException.java
    │   │                   │   ├── AuthAnnotationIsNowhereException.java
    │   │                   │   ├── CannotDeleteFridgeException.java
    │   │                   │   ├── InvalidUserNickNameException.java
    │   │                   │   ├── InvalidUserProfileImgKeyException.java
    │   │                   │   ├── ProviderMissingValueException.java
    │   │                   │   ├── TokenExpirationException.java
    │   │                   │   ├── UserEmailMissingValueException.java
    │   │                   │   ├── UserNicknameNotFoundException.java
    │   │                   │   └── UserNotFoundException.java
    │   │                   ├── repository
    │   │                   │   └── UserRepository.java
    │   │                   └── service
    │   │                       ├── UserService.java
    │   │                       └── UserServiceImpl.java
    │   └── resources
    │       ├── application.yml
    └── test
        └── java
            └── com
                └── example
                    └── icebutler_server
                        └── IceButlerServerApplicationTests.java
```
<br>
</details>
<br><br>


## DB 
<details>
<summary>MAIN_SERVER</summary>
 
![image](https://github.com/IceButler/IceButler_Server/assets/90203250/de9db769-11c5-45e7-8c6a-5f861bb5ff19)
    
<br>
</details>
<details>
<summary>RECIPE_SERVER</summary>

![image](https://github.com/IceButler/IceButler_Server/assets/90203250/2f76bbac-2e7b-433e-b127-e592c700ef2d)

<br>
</details>
<br><br>

<br>

## Commit/PR Convention
**Commit**
```
#1 feat: 일정 등록 API 추가
```
- #이슈번호 타입: 커밋 설명
<br>

**Pull Request**
```
[feature/1-create-calender] 일정 등록
```
- [브랜치명]  설명
<br>

## Branch Strategy
- main
    - 배포 이력 관리 목적
- develop
    - feature 병합용 브랜치
    - 배포 전 병합 브랜치
- feature
    - develop 브랜치를 베이스로 기능별로 feature 브랜치 생성해 개발
- test
    - 테스트가 필요한 코드용 브랜치
- fix
    - 배포 후 버그 발생 시 버그 수정 
<br>

- feature branch의 경우, 기능명/이슈번호-기능설명 형태로 작성
```md
feature/7-desserts-patchDessert
```
<br>

## MSA (Micro Service Architecture)
|[MAIN_SERVER](https://github.com/IceButler/IceButler_Server)|[RECIPE_SERVER](https://github.com/IceButler/IceButler_Server_Recipe)|[CHAT_GPT_WORDS](https://github.com/IceButler/chatGPT-OneWord-lambda)|[CHAT_GPT_CATEGORY](https://github.com/IceButler/chatGPT-category-lambda)|[GET_PRESIGNED_S3_URL](https://github.com/IceButler/presignedURL-lambda)|
|:---:|:---:|:---:|:---:|:---:|
|**main 서버** | **레시피 서버**| **ChatGPT 활용 대표단어 추출 Lambda** | **ChatGPT 활용 카테고리 추출 Lambda** | **PresignedURL Lambda** |
<br>

## API
[👉 CLICK HERE](https://broadleaf-mist-919.notion.site/API-58f09d6c03ff4cbcbe7df30d21f60bd7?pvs=4)
<br>
<br>

## Member
|[김민기](https://github.com/dangnak2)|[박서연](https://github.com/psyeon1120)|[박소정](https://github.com/sojungpp)|[웃쿠](https://github.com/utku1989)|
|:---:|:---:|:---:|:---:|
|<img src="https://github.com/dangnak2.png" width="180" height="180" >|<img src="https://github.com/psyeon1120.png" width="180" height="180" >|<img src="https://github.com/sojungpp.png" width="180" height="180" >|<img src="https://github.com/utku1989.png" width="180" height="180">|
| **DB & <br> Backend Developer** | **PM & <br> Backend Developer**| **PM & <br> Backend Developer** | **QA & <br> Backend Developer** |

|[이승학](https://github.com/leeseunghakhello)|[이찬영](https://github.com/kingchan223)|[장채은](https://github.com/chaerlo127)|[냉집사](https://github.com/IceButler)|
|:---:|:---:|:---:|:---:|
|<img src="https://github.com/leeseunghakhello.png" width="180" height="180" >|<img src="https://github.com/kingchan223.png" width="180" height="180" >|<img src="https://github.com/chaerlo127.png" width="180" height="180" >|<img src="https://github.com/IceButler.png" width="180" height="180">|
| **Framework Leader & <br> Backend Developer** | **Architect & <br> Backend Developer**| **DB & <br> Backend Developer** | **ICE BUTLER** |
