package exam;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class ApiSearchMovie {


    public static void main(String[] args) {
        String clientId = "eq9rbbdAZkL7CV6orG_O"; //애플리케이션 클라이언트 아이디값"
        String clientSecret = "lTiL70f5co"; //애플리케이션 클라이언트 시크릿값"

        Scanner sc = new Scanner(System.in);
        System.out.println("제목을 입력해주세요.");
        String search = sc.next();
        String text = null;
        try {
        	text = URLEncoder.encode(search, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패",e);
        }


        String apiURL = "https://openapi.naver.com/v1/search/blog?query=" + text;    // json 결과
        //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml 결과


        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        String responseBody = get(apiURL,requestHeaders);

        JSONParser parser = new JSONParser();
        JSONObject obj = null;
        File dir = new File("C:");
        if(!dir.exists() == false) {
        	dir.mkdirs();
        }
        File f = new File(dir, "search_result.txt");
        
        FileWriter fw = null;
        try {
           fw = new FileWriter(f);
           obj = (JSONObject)parser.parse(responseBody);
           JSONArray items = (JSONArray)obj.get("items");
           for(int i = 0; i < items.size(); i++) {
        	   JSONObject obj2 = (JSONObject)items.get(i);
        	   String title = obj2.get("title").toString();
        	   System.out.println(title);
        	   fw.write(title);
           }
           
        } catch(Exception e) {
        	e.printStackTrace();
        } finally {
        	try {
        		if(fw != null) {fw.close();}
        	} catch(Exception e) {
        		e.printStackTrace();
        	}
        }

        System.out.println(responseBody);
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
           /*
            File dir = new File(realPath);
            if(!dir.exists() == false) {
            	dir.mkdirs();
            }
            File f = new File(dir, "search_error.txt");
            
            FileWriter fw = null;
            fw = new FileWriter(f);
            fw.write(e.getMessage());
           */
            
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