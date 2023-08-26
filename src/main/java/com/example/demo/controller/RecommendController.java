package com.example.demo.controller;

import com.example.demo.repository.RecommendRepository;
import lombok.RequiredArgsConstructor;
import org.apache.hc.core5.http.ContentType;
import org.springframework.web.bind.annotation.*;

import org.apache.hc.client5.http.classic.methods.*;
import org.apache.hc.client5.http.impl.classic.*;
import org.apache.hc.core5.http.io.entity.*;


import java.nio.charset.Charset;

@RestController
@RequiredArgsConstructor
public class RecommendController {

    private final RecommendRepository repository;

    @RequestMapping("/recommend_search")

    @ResponseBody
    public String selectedProd(@RequestBody String input_word) throws Exception {

        System.out.println("input_word : " + input_word);

        // Set up the HTTP request
        HttpPost httpPost = new HttpPost("http://localhost:5000/get_similar_products");
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", ContentType.APPLICATION_JSON);

        StringEntity requestEntity = new StringEntity(input_word, Charset.forName("UTF-8"));
        httpPost.setEntity(requestEntity);

//        // Create an object to access Flask and retrieve the execution result
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
//
//            // Connect to Flask and get the execution result
            try (CloseableHttpResponse response2 = httpclient.execute(httpPost)) {
//
//                // Get the results of Flask execution
                String flaskResult = EntityUtils.toString(response2.getEntity(), Charset.forName("UTF-8"));
                System.out.println("flaskResult=" + flaskResult);
                return flaskResult;
            }
        }
    }
}
//            }
//        }
//            HttpPost httpPost = new HttpPost("http://127.0.0.1:5000/get_similar_products");
//
//            List<BasicNameValuePair> nvps = new ArrayList<>();
//
//            nvps.add(new BasicNameValuePair("input_word", word ));
//            //Flask로 전송할 문자 타입 설정 UTF-8
//            httpPost.setEntity(
//                    new UrlEncodedFormEntity(nvps, Charset.forName("UTF-8")));
//            //Flask 에 접속해서 실행 결과를 가져올 객체 생성
//            CloseableHttpClient httpclient = HttpClients.createDefault();
//            //Flask에 접속해서 실행 결과를 가져옴
//            CloseableHttpResponse response2 = httpclient.execute(httpPost);
//            //Flask 실행 결과를 가져옴
//            String flaskResult =
//                    EntityUtils.toString(response2.getEntity(),
//                            Charset.forName("UTF-8"));
//            System.out.println("flaskResult=" + flaskResult);
//
//            //Flask 서버와 연결 종료
//            httpclient.close();
//            return flaskResult;
//    }

