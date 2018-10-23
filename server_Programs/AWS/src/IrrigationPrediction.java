import java.sql.*;
import com.amazonaws.AmazonClientException;
import com.amazonaws.services.machinelearning.AmazonMachineLearningClient;
import com.amazonaws.services.machinelearning.model.DescribeMLModelsResult;
import com.amazonaws.services.machinelearning.model.MLModel;
import com.amazonaws.services.machinelearning.model.PredictRequest;
import com.amazonaws.services.machinelearning.model.PredictResult;
import com.amazonaws.services.machinelearning.model.RealtimeEndpointInfo;
import java.io.IOException;
import java.io.PrintWriter;
public class IrrigationPrediction {
	
	
	static Boolean isIrrigationRequired(String record[])
	{
		//if(record.length<12)
			//return false;
		AmazonMachineLearningClient client = new AmazonMachineLearningClient();
		// Get list of prediction models
		DescribeMLModelsResult models = client.describeMLModels();

		// Iterate over all models and show basic information about each one
		for (MLModel m : models.getResults()) {
			System.out.println("Model name: " + m.getName());
			System.out.println("Model id: " + m.getMLModelId());
			System.out.println("Model status: " + m.getStatus());
			RealtimeEndpointInfo endpoint = m.getEndpointInfo();
			System.out.println("Endpoint URL: " + endpoint.getEndpointUrl());
			System.out.println("Endpoint status: "+ endpoint.getEndpointStatus());
		}
			MLModel model = models.getResults().get(1);

			// Build a prediction request
			PredictRequest request = new PredictRequest();
			// Select prediction model
			request.setMLModelId(model.getMLModelId());
			// Select realtime endpoint
			request.setPredictEndpoint(model.getEndpointInfo().getEndpointUrl());
			//request.addRecordEntry("s.no", record[0])
			request.addRecordEntry("ct", record[1])
			.addRecordEntry("cd", record[2]).addRecordEntry("sm", record[3])
			.addRecordEntry("tm", record[4]).addRecordEntry("hm", record[5]);
			System.out.println("Sending prediction request for: "
					+ request.getRecord());

			// Send prediction request
			PredictResult result;
			try {
	                        long start = System.currentTimeMillis();
	                        result = client.predict(request);
	                        long end = System.currentTimeMillis();
	                        System.out.println((end - start) + " ms");
			} catch (Exception e) {
				throw new AmazonClientException("Prediction failed", e);
			}

			// Display predicted value
			System.out.println("Predicted value:"+ result.getPrediction().getPredictedLabel());
			String predictedString= result.getPrediction().getPredictedLabel();
			if(predictedString.equals("0"))
				return false;
			else if(predictedString.equals("1"))
				return true;
		
		return true;
	}
}
