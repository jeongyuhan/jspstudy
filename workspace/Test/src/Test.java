import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Test {

	public static void main(String[] args) {


		/*
		 * { "name" : "브레드", "age" : 50, "staff" : [ { "name" : "윌터", "age" : 30 }, {
		 * "name" : "데이빗", "age" : 20 } ] }
		 */
		/*
		String responseBody = "{\r\n" + 
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
		*/
		
		String responseBody = "{\r\n" + 
				"			\"name\" : \"브레드\",\r\n" + 
				"			\"age\" : 50,\r\n" + 
				"			\"staff\" : [\r\n" + 
				"	           {\r\n" + 
				"	        	   \"name\" : \"윌터\",\r\n" + 
				"	        	   \"age\" : 30\r\n" + 
				"	        	   },\r\n" + 
				"	           {\r\n" + 
				"	        	   \"name\" : \"데이빗\",\r\n" + 
				"	        	   \"age\" : 20 \r\n" + 
				"	           }\r\n" + 
				"    	   ]\r\n" + 
				"		}"; 

		// 파싱(분석) 연습
		// 각각의 데이터에 저장되어있는 값을 빼내는 연습이 필요하다.
		JSONParser parser = new JSONParser();
		JSONObject obj = null;
		try {
			obj = (JSONObject)parser.parse(responseBody);
			String name = obj.get("name").toString();
			long age = (long)obj.get("age");
			System.out.println("이름 : " + name + ", 나이 : " + age);
			JSONArray staff = (JSONArray)obj.get("staff");
			for(int i = 0; i < staff.size(); i++) {
				JSONObject obj2 = (JSONObject)staff.get(i);
				String name2 = obj2.get("name").toString();
				long age2 = (long
						)obj2.get("age");
				System.out.println("이름 : " + name2 + ", 나이 : " + age2);
			} 
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		// 위와 같은 방식으로 파싱을 사용하여 API에 있는 응답 예 의 데이터를 빼는 연습을 한다.
		
	}

}
