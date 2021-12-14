package pl.alx;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.InstanceType;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.RunInstancesResult;

/**
 * Hello world!
 *
 */
public class App 
{
    private static AWSCredentials aws_cred = new AWSCredentials() {
        @Override
        public String getAWSAccessKeyId() {
            return "AWS_ACCESS_KEY";
        }

        @Override
        public String getAWSSecretKey() {
            return "AWS_SECRECT_KEY";
        }
    };

    public static void main( String[] args )
    {

        AmazonEC2 ec2 = AmazonEC2ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(aws_cred))
                .withRegion(Regions.EU_WEST_1)
                .build();

        RunInstancesRequest request_ec2 = new RunInstancesRequest()
                .withImageId("ami-04dd4500af104442f")
                .withInstanceType(InstanceType.T1Micro)
                .withMaxCount(1).withMinCount(1);

        RunInstancesResult result = ec2.runInstances(request_ec2);
        String instanceId = result.getReservation().getInstances().get(0).getInstanceId();
        System.out.println(instanceId);

    }
}
