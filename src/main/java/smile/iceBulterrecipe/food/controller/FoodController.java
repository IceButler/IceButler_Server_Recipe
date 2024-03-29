package smile.iceBulterrecipe.food.controller;

import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.*;
import smile.iceBulterrecipe.food.dto.request.FoodReq;
import smile.iceBulterrecipe.food.service.FoodService;
import smile.iceBulterrecipe.food.service.FoodServiceImpl;
import smile.iceBulterrecipe.global.response.ResponseCustom;
import smile.iceBulterrecipe.global.sqs.AmazonSQSSender;
import smile.iceBulterrecipe.global.sqs.FoodData;
import smile.iceBulterrecipe.user.dto.request.UserReq;

import java.io.IOException;

@RestController
@RequestMapping(value = "/foods")
@RequiredArgsConstructor
public class FoodController {
    private final FoodServiceImpl foodService;

    private final AmazonSQSSender amazonSQSSender;

    @GetMapping("")
    public ResponseCustom<?> addFood(@RequestBody FoodReq foodReq) {
        this.foodService.addFood(foodReq);
        return ResponseCustom.OK();
    }

    @DeleteMapping("")
    public ResponseCustom<?> deleteFood(@RequestBody FoodReq foodReq){
        this.foodService.deleteFood(foodReq);
        return ResponseCustom.OK();
    }

    @PostMapping("")
    public ResponseCustom<?> updateFood(@RequestBody FoodReq foodReq) {
        this.foodService.updateFood(foodReq);
        return ResponseCustom.OK();
    }


}
