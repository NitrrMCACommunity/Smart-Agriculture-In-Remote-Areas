

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amazonaws.services.machinelearning.AmazonMachineLearning;
import com.amazonaws.services.machinelearning.AmazonMachineLearningClient;
import com.amazonaws.services.machinelearning.AmazonMachineLearningClientBuilder;
import com.amazonaws.services.machinelearning.model.GetMLModelRequest;
import com.amazonaws.services.machinelearning.model.GetMLModelResult;
import com.amazonaws.services.machinelearning.model.PredictRequest;
import com.amazonaws.services.machinelearning.model.PredictResult;
import com.sun.javafx.collections.MappingChange.Map;

/**
 * Servlet implementation class PredictMachine
 */
@WebServlet("/PredictMachine")
public class PredictMachine extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PredictMachine() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		AmazonMachineLearning client =AmazonMachineLearningClientBuilder.defaultClient();
		PredictRequest request=new PredictRequest();
		String mLModelId="ml-NJOPs6Y3DDk";
		GetMLModelRequest getMlModelRequest=new GetMLModelRequest().withMLModelId(mLModelId);
		GetMLModelResult r=client.getMLModel(getMlModelRequest);
		String pend=r.getEndpointInfo().getEndpointUrl();
		java.util.Map<String,String> hm=new HashMap<String,String>();
		hm.put("s.no", "40");
		hm.put("ct", "wheat");
		hm.put("td", "30");
		hm.put("sm", "302");
		hm.put("tm", "32");
		hm.put("hm", "32");
		request.withMLModelId(mLModelId)
		.withPredictEndpoint(pend)
		.withRecord(hm);
		PredictResult prediction=client.predict(request);
		response.getWriter().println(prediction);
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
