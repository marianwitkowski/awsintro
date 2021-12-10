package pl.alx;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.HashMap;

public class LambdaTestApiGw implements RequestHandler<Object, GatewayResponse>
{
    LambdaLogger logger = null;

    @Override
    public GatewayResponse handleRequest(Object o, Context context) {
        logger = context.getLogger();
        logger.log("Hello from AWS Lambda function");

        HashMap<String, String> hMap = new HashMap<>();
        hMap.put("App", "Test Lambda");
        hMap.put("Content-Type", "text/html");

        return new GatewayResponse("<html><h1>Helo from AWS Lambda function</h1></html>",
                200, hMap, false);
    }
}
