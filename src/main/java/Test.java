import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

public class Test {
	public static void main(String[] args) throws ClientProtocolException, IOException {
		
		String data = "--DATA\n" +
			"Content-Disposition: form-data; name=\"query\"\n\n" +
			"SELECT * WHERE { ?s ?p ?o }\n" +
			"--DATA--";
		
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost("http://localhost:8080/services/sparql-endpoint");
		
		post.addHeader("Accept", "application/sparql-results+json");
		post.addHeader("Content-Type", "multipart/form-data; boundary=DATA");
		
		post.setEntity(new StringEntity(data));
		
		HttpResponse response = client.execute(post);
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		String line = "";
		while ((line = rd.readLine()) != null) {
			System.out.println(line);
		}
		
	}
}