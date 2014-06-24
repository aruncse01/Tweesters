

     import java.util.ArrayList;
import java.util.List;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.elasticmapreduce.AmazonElasticMapReduceClient;
import com.amazonaws.services.elasticmapreduce.model.HadoopJarStepConfig;
import com.amazonaws.services.elasticmapreduce.model.JobFlowInstancesConfig;
import com.amazonaws.services.elasticmapreduce.model.RunJobFlowRequest;
import com.amazonaws.services.elasticmapreduce.model.RunJobFlowResult;
import com.amazonaws.services.elasticmapreduce.model.StepConfig;
import com.amazonaws.services.elasticmapreduce.util.StepFactory;

public class TweetEMR {

    /**
     * @param args
     *
     */
    public static void main(String[] args) {
    	

        AWSCredentials credentials = new BasicAWSCredentials("AKIAJTIVFEVUBKUL44UQ", "16Qy/3+giPaa6v3TAT1+EIEMKvvvcWbm8vjIeWC+");
        AmazonElasticMapReduceClient emr = new AmazonElasticMapReduceClient(credentials);

        StepFactory stepFactory = new StepFactory();
     
        List<StepConfig> sscon= new ArrayList();
        
        
        StepConfig enableDebugging = new StepConfig()
            .withName("Enable Debugging")
            .withActionOnFailure("TERMINATE_JOB_FLOW")
            .withHadoopJarStep(stepFactory.newEnableDebuggingStep());
        
       
       
        StepConfig installHive = new StepConfig()
            .withName("Install Hive")
            .withActionOnFailure("TERMINATE_JOB_FLOW")
            .withHadoopJarStep(stepFactory.newInstallHiveStep());
        
        
        HadoopJarStepConfig scriptStep =
        	    stepFactory.newRunHiveScriptStep("s3://twitterlog-uri/tweet.q");
        	List<String> scriptArgs = scriptStep.getArgs();
        	scriptArgs.add(1, "--hive-versions"); // <- New lines added to 
        	scriptArgs.add(2, "latest");          // <- fix Hive version.
        	scriptStep.setArgs(scriptArgs);
        	System.out.println(scriptStep.getArgs());
        	 
        	StepConfig runHiveScript = new StepConfig()
        	    .withName("Run Hive Script")
        	    .withActionOnFailure("TERMINATE_JOB_FLOW")
        	    .withHadoopJarStep(scriptStep);

        
        
        	
        /*StepConfig hiveScript = new StepConfig().withName("Hive Script")
            .withActionOnFailure("TERMINATE_JOB_FLOW")
            .withHadoopJarStep(stepFactory.newRunHiveScriptStep("s3://twitterlog-uri/tweet.q",null));
        */
        sscon.add(enableDebugging);
        sscon.add(installHive);
        sscon.add(runHiveScript);
        
        RunJobFlowRequest request = new RunJobFlowRequest()
            .withName("Hive Interactive7")
            .withSteps(sscon)
            .withLogUri("s3://twitterlog-uri/")
            .withInstances(new JobFlowInstancesConfig()
                .withEc2KeyName("RaghuKPEMR")
                .withHadoopVersion("1.0.3")
                .withInstanceCount(Integer.valueOf(5))
                .withKeepJobFlowAliveWhenNoSteps(Boolean.TRUE)
                .withMasterInstanceType("m1.large")
                .withSlaveInstanceType("m1.large"));

        RunJobFlowResult result = emr.runJobFlow(request);

    }

}