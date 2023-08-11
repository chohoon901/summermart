package com.example.demo.recommend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.*;
import org.apache.hc.client5.http.classic.methods.*;
import org.apache.hc.client5.http.entity.*;
import org.apache.hc.client5.http.impl.classic.*;
import org.apache.hc.core5.http.io.entity.*;
import org.apache.hc.core5.http.message.*;
import java.nio.charset.Charset;

@Controller
public class RecommendController {

    @Autowired
    private RecommendRepository repository;

    @RequestMapping("/search")
    
    @ResponseBody
    public String selectedProd(String word) throws Exception {

//        List<Prod> selectedProdList = repository.findProd(word);
//
//        List<Prod> allProd = new ArrayList<>();
//
//        for (int i = 0; i<selectedProdList.size(); i++) {
//            Map oneProd = new HashMap();
//            Prod prod = selectedProdList.get(i);
//            oneProd.put("PROD", prod);
//
//            ArrayList <Prod> recommendProdList = new ArrayList<>();
//            String prodName = prod.getPROD();
//
//            System.out.println("prodName=" +prodName);

            HttpPost httpPost = new HttpPost("http://127.0.0.1:5000/get_similar_products");

            List<BasicNameValuePair> nvps = new ArrayList<>();

            nvps.add(new BasicNameValuePair("input_word", word ));
            //Flask로 전송할 문자 타입 설정 UTF-8
            httpPost.setEntity(
                    new UrlEncodedFormEntity(nvps, Charset.forName("UTF-8")));
            //Flask 에 접속해서 실행 결과를 가져올 객체 생성
            CloseableHttpClient httpclient = HttpClients.createDefault();
            //Flask에 접속해서 실행 결과를 가져옴
            CloseableHttpResponse response2 = httpclient.execute(httpPost);
            //Flask 실행 결과를 가져옴
            String flaskResult =
                    EntityUtils.toString(response2.getEntity(),
                            Charset.forName("UTF-8"));
            System.out.println("flaskResult=" + flaskResult);

            //Flask 서버와 연결 종료
            httpclient.close();
            return flaskResult;

//            //Flask서버에서 가져온 문자열을 JSON 형태 객체로 변환
//            JSONArray jsonArray = new JSONArray(flaskResult);
//            //jsonArray.length() : JSON 객체에 저장된 데이터수 (추천 영화 제목의 수) 만큼 반복
//            for (int j = 0; j < jsonArray.length(); j++) {
//
//                String recommendProdName = jsonArray.getString(j);
//                Prod recommendProd = (Prod) repository.findProd(recommendProdName);
//                recommendProdList.add(recommendProd);
//            }
//
//            oneProd.put("recommend", recommendProdList);
//            allProd.add((Prod) oneProd);
//
//        }
//        //박스오피스와 추천 영화가 모두 저장된 allMovie를 JSON 문자열로 변환해서 리턴
//        return new JSONArray(allProd).toString();

    }

}