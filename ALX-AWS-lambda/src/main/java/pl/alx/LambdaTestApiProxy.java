package pl.alx;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class LambdaTestApiProxy implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent>
{
    static final Logger logger = LoggerFactory.getLogger(LambdaTestApiProxy.class);

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {

        logger.info("LambdaTestApiProxy");

        StringBuilder sb = new StringBuilder();

        Map<String, String> params = input.getQueryStringParameters();
        sb.append(" -------- GET ---------\n");
        if (params!=null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                sb.append(entry.getKey()+" : "+entry.getValue()+"\n");
            }
        }

        params = input.getHeaders();
        sb.append(" -------- HEADERS ---------\n");
        if (params!=null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                sb.append(entry.getKey()+" : "+entry.getValue()+"\n");
            }
        }

        sb.append(" -------- BODY ---------\n");
        String body = input.getBody();
        sb.append(body);

        APIGatewayProxyResponseEvent responseEvent = new APIGatewayProxyResponseEvent();
        responseEvent.setStatusCode(200);
        responseEvent.setBody(sb.toString());

        return responseEvent;
    }
}
