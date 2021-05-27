import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Test2 {

    public static void main(String[] args) {
        String clientId = "SCkTtSAh7fM8Mkwh26si"; // 애플리케이션 클라이언트 아이디
        String clientSecret = "sBrXcO6nbF"; // 애플리케이션 클라이언트 시크릿

        String apiUrl = "https://openapi.naver.com/v1/datalab/search";

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        requestHeaders.put("Content-Type", "application/json");

        String requestBody = "{\"startDate\":\"2021-01-01\"," +
                "\"endDate\":\"2021-05-27\"," +
                "\"timeUnit\":\"month\"," +
                "\"keywordGroups\":[{\"groupName\":\"한글\"," + "\"keywords\":[\"한글\",\"korean\"]}," +
                "{\"groupName\":\"영어\"," + "\"keywords\":[\"영어\",\"english\"]}]," +
                "\"device\":\"pc\"," +
                "\"ages\":[\"1\",\"2\"]," +
                "\"gender\":\"f\"}";

        String responseBody = post(apiUrl, requestHeaders, requestBody);
        System.out.println(responseBody);
        
        responseBody = "{\r\n" + 
            "  \"startDate\": \"2017-01-01\",\r\n" + 
            "  \"endDate\": \"2017-04-30\",\r\n" + 
            "  \"timeUnit\": \"month\",\r\n" + 
            "  \"results\": [\r\n" + 
            "    {\r\n" + 
            "      \"title\": \"한글\",\r\n" + 
            "      \"keywords\": [\r\n" + 
            "        \"한글\",\r\n" + 
            "        \"korean\"\r\n" + 
            "      ],\r\n" + 
            "      \"data\": [\r\n" + 
            "        {\r\n" + 
            "          \"period\": \"2017-01-01\",\r\n" + 
            "          \"ratio\": 47.0\r\n" + 
            "        },\r\n" + 
            "        {\r\n" + 
            "          \"period\": \"2017-02-01\",\r\n" + 
            "          \"ratio\": 53.23\r\n" + 
            "        },\r\n" + 
            "        {\r\n" + 
            "          \"period\": \"2017-03-01\",\r\n" + 
            "          \"ratio\": 100.0\r\n" + 
            "        },\r\n" + 
            "        {\r\n" + 
            "          \"period\": \"2017-04-01\",\r\n" + 
            "          \"ratio\": 85.32\r\n" + 
            "        }\r\n" + 
            "      ]\r\n" + 
            "    },\r\n" + 
            "    {\r\n" + 
            "      \"title\": \"영어\",\r\n" + 
            "      \"keywords\": [\r\n" + 
            "        \"영어\",\r\n" + 
            "        \"english\"\r\n" + 
            "      ],\r\n" + 
            "      \"data\": [\r\n" + 
            "        {\r\n" + 
            "          \"period\": \"2017-01-01\",\r\n" + 
            "          \"ratio\": 40.08\r\n" + 
            "        },\r\n" + 
            "        {\r\n" + 
            "          \"period\": \"2017-02-01\",\r\n" + 
            "          \"ratio\": 36.69\r\n" + 
            "        },\r\n" + 
            "        {\r\n" + 
            "          \"period\": \"2017-03-01\",\r\n" + 
            "          \"ratio\": 52.11\r\n" + 
            "        },\r\n" + 
            "        {\r\n" + 
            "          \"period\": \"2017-04-01\",\r\n" + 
            "          \"ratio\": 44.45\r\n" + 
            "        }\r\n" + 
            "      ]\r\n" + 
            "    }\r\n" + 
            "  ]\r\n" + 
            "}";
        
        JSONParser parser = new JSONParser();
        JSONObject obj = null;
        String startDate = null;
        String endDate = null;
        String timeUnit = null;
        String title = null;
        String keyword = null;
        String period = null;
        String ratio = null;
        try {
           obj = (JSONObject)parser.parse(responseBody);
           startDate = obj.get("startDate").toString();
           endDate = obj.get("endDate").toString();
           timeUnit = obj.get("timeUnit").toString();
           System.out.println("시작" + startDate + ", 종료" + endDate + ", 단위" + timeUnit);
           JSONArray result = (JSONArray)obj.get("results");
           for(int i = 0; i < result.size(); i++) {
              JSONObject obj2 = (JSONObject)result.get(i);
              title = obj2.get("title").toString();
              System.out.println("제목 " + title);
              JSONArray keywordsList = (JSONArray)obj2.get("keywords");
              for(Object keywords : keywordsList) {
                 keyword = keywords.toString();
                 System.out.println(keyword);
              }
              JSONArray data = (JSONArray)obj2.get("data");
              for(int n = 0; n < data.size(); n++) {
                 JSONObject obj3 = (JSONObject)data.get(n);
                 period = obj3.get("period").toString();
                 ratio = obj3.get("ratio").toString();
                 System.out.println("period:" + period + "ratio:" + ratio);
              }
             }
           
        }catch(Exception e) {
           e.printStackTrace();
        }
        
        File dir = new File("C:\\test");
        if(!dir.exists() == false) {
           dir.mkdirs();
        }
        File f = new File(dir, "examtest.txt");
        
        FileWriter fw = null;
        try {
           fw = new FileWriter(f);
           fw.write(startDate);
           fw.write(endDate);
           fw.write(timeUnit);
           fw.write(title);
           fw.write(keyword);
           fw.write(period);
           fw.write(ratio);
        } catch(Exception e) {
           e.printStackTrace();
        } finally {
           try {
              if(fw != null) {fw.close();}
           } catch(Exception e) {
              e.printStackTrace();
           }
        }
    
    }

    private static String post(String apiUrl, Map<String, String> requestHeaders, String requestBody) {
        HttpURLConnection con = connect(apiUrl);

        try {
            con.setRequestMethod("POST");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            con.setDoOutput(true);
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.write(requestBody.getBytes());
                wr.flush();
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 응답
                return readBody(con.getInputStream());
            } else {  // 에러 응답
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect(); // Connection을 재활용할 필요가 없는 프로세스일 경우
        }
    }

    private static HttpURLConnection connect(String apiUrl) {
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection) url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    private static String readBody(InputStream body) {
        InputStreamReader streamReader = new InputStreamReader(body, StandardCharsets.UTF_8);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }
}