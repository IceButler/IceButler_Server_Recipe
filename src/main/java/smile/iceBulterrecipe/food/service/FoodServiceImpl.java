package smile.iceBulterrecipe.food.service;

import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import smile.iceBulterrecipe.food.dto.assembler.FoodAssembler;
import smile.iceBulterrecipe.food.dto.request.FoodReq;
import smile.iceBulterrecipe.food.repository.FoodRepository;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService{
    private final FoodAssembler foodAssembler;
    private final FoodRepository foodRepository;

    @Transactional
    @Override
    public void addFood(FoodReq foodReq) {
        this.foodRepository.save(this.foodAssembler.toEntity(foodReq));
    }

    public String callGPTCategory(String word) throws IOException, ParseException {
        String encWord = URLEncoder.encode(word, StandardCharsets.UTF_8);
        URL categoryGPTUrl = new URL("https://za8hqdiis4.execute-api.ap-northeast-2.amazonaws.com/dev/chatgpt-category?keyword="+encWord);
        StringBuilder sb = callAPI(categoryGPTUrl);

        JSONParser parser = new JSONParser();
        JSONObject result = (JSONObject) parser.parse(sb.toString());
        JSONArray arr = (JSONArray)result.get("categories");
        if(arr != null){
            return (String) arr.get(0);
        }else return null;
    }

    private StringBuilder callAPI(URL url) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader rd;
        // 서비스코드가 정상이면 200~300
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        return sb;
    }
}
