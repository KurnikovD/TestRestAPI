
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;




public class RestAPI{

    String URI = "https://jsonplaceholder.typicode.com/posts";

    @Test
    public void connection(){
        RestAssured.when().get(URI).then().assertThat().statusCode(200);
    }

    @Test
    public void filterByUserId(){

        String param = "?userId=1";

        RestAssured.when().get(URI + param).
                then().
                    statusCode(200).
                    contentType(ContentType.JSON).
                    body("userId[0]", equalTo(1)).
                    body("id[0]", equalTo(1));
    }

    @Test
    public void getById(){

        String param = "?id=1";

        RestAssured.when().get(URI + param).
                then().
                    statusCode(200).
                    contentType(ContentType.JSON).
                    body("userId[0]", equalTo(1)).
                    body("id[0]", equalTo(1));
    }

    @Test
    public void getAllList(){
        String str = RestAssured.when().get(URI).asString();
        JsonElement json = JsonParser.parseString(str);
        assertEquals(((JsonArray) json).size(), 100);
    }



}