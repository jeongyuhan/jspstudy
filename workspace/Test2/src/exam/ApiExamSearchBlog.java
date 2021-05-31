package exam;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class ApiExamSearchBlog {


    public static void main(String[] args) {
        String clientId = "eq9rbbdAZkL7CV6orG_O"; //애플리케이션 클라이언트 아이디값"
        String clientSecret = "lTiL70f5co"; //애플리케이션 클라이언트 시크릿값"


        Scanner sc = new Scanner(System.in);
        System.out.print("영화 검색어 입력 >>>");
        String query = sc.next();
        
        String text = null;
        try {
            text = URLEncoder.encode(query, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패",e);
        }


        String apiURL = "https://openapi.naver.com/v1/search/movie.json?query=" + text;    // json 결과
        //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml 결과


        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        String responseBody = get(apiURL,requestHeaders);

        BufferedWriter bw = null;
        BufferedWriter bw2 = null;
        try {
        	// 출력파일스트림
        	bw = new BufferedWriter(new FileWriter("search_result.html"));
        	// 파싱
        	JSONParser parser = new JSONParser();
        	JSONObject obj = (JSONObject)parser.parse(responseBody);        	
        	JSONArray items = (JSONArray)obj.get("items");
        	for(int i = 0; i < items.size(); i++) {
        		JSONObject item = (JSONObject)items.get(i);
        		String title = item.get("title").toString();
        		
        		// 파싱 결과 파일로 보내기
        		bw.write(title + "<br>"); // html 파일로 보내기 때문에 줄바꿈 코드는 <br>로 한다.
        	}
        	System.out.println("search_result.html 파일이 생성되었습니다.");
        } catch(Exception e) {
        	try {
        		bw2 = new BufferedWriter(new FileWriter("error.txt"));
        		bw2.write(e.getMessage());
        		System.out.println("error.txt 파일이 생성되었습니다.");
        	} catch (Exception e2) {
        		e2.printStackTrace();
        	}          	
        } finally {
        	try {
        		if(bw != null) {bw.close();}
        		if(bw2 != null) {bw2.close();}
        	} catch (Exception e) {
        		e.printStackTrace();
        	}
        }
        
        
    }


    private static String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }


            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }


    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }


    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);


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