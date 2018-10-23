
import com.amazonaws.AmazonClientException;
import com.amazonaws.services.machinelearning.AmazonMachineLearningClient;
import com.amazonaws.services.machinelearning.model.DescribeMLModelsResult;
import com.amazonaws.services.machinelearning.model.MLModel;
import com.amazonaws.services.machinelearning.model.PredictRequest;
import com.amazonaws.services.machinelearning.model.PredictResult;
import com.amazonaws.services.machinelearning.model.RealtimeEndpointInfo;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class CallingMl
 */
@WebServlet("/CallingMl")
public class CallingMl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CallingMl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		AmazonMachineLearningClient client = new AmazonMachineLearningClient();
		PrintWriter out=response.getWriter();
		// Get list of prediction models
		DescribeMLModelsResult models = client.describeMLModels();

		// Iterate over all models and show basic information about each one
		for (MLModel m : models.getResults()) {
			out.println("Model name: " + m.getName());
			out.println("Model id: " + m.getMLModelId());
			out.println("Model status: " + m.getStatus());
			RealtimeEndpointInfo endpoint = m.getEndpointInfo();
			out.println("Endpoint URL: " + endpoint.getEndpointUrl());
			out.println("Endpoint status: "+ endpoint.getEndpointStatus());
		}
			MLModel model = models.getResults().get(0);

			// Build a prediction request
			PredictRequest request = new PredictRequest();
			// Select prediction model
			request.setMLModelId(model.getMLModelId());
			// Select realtime endpoint
			request.setPredictEndpoint(model.getEndpointInfo().getEndpointUrl());
			request.addRecordEntry("s.no", "1")
			.addRecordEntry("ct", "wheat")
			.addRecordEntry("cd", "40").addRecordEntry("sm", "400")
			.addRecordEntry("tm", "32").addRecordEntry("hm", "30");
			out.println("Sending prediction request for: "
					+ request.getRecord());

			// Send prediction request
			PredictResult result;
			try {
	                        long start = System.currentTimeMillis();
	                        result = client.predict(request);
	                        long end = System.currentTimeMillis();
	                        out.println((end - start) + " ms");
			} catch (Exception e) {
				throw new AmazonClientException("Prediction failed", e);
			}

			// Display predicted value
			out.println("Predicted value:"+ result.getPrediction().getPredictedLabel());
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
