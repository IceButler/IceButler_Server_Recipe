package smile.iceBulterrecipe.food.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import smile.iceBulterrecipe.food.dto.request.FoodReq;
import smile.iceBulterrecipe.food.service.FoodService;
import smile.iceBulterrecipe.food.service.FoodServiceImpl;
import smile.iceBulterrecipe.global.response.ResponseCustom;
import smile.iceBulterrecipe.global.sqs.AmazonSQSSender;
import smile.iceBulterrecipe.global.sqs.FoodData;

@RestController
@RequestMapping(value = "/foods")
@RequiredArgsConstructor
public class FoodController {
    private final FoodServiceImpl foodService;

    private final AmazonSQSSender amazonSQSSender;

    @GetMapping("")
    public ResponseCustom<?> addFood(@RequestBody FoodReq foodReq){
        this.foodService.addFood(foodReq);
        return ResponseCustom.OK();
    }

    @GetMapping("/hohotest")
    public void hohotest() {
        amazonSQSSender.sendMessage(
                FoodData.builder()
                        .foodIdx(2L)
                        .foodName("zxc")
                        .foodCategory("zxc")
                        .foodImgKey("zxc")
                        .build());
    }

}
