package sheets;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;

import Runner.TestRunner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

public class API {
	/*	Application Name */ 
	private static final String APPLICATION_NAME = "Google Sheets API Java Quickstart";
	
	
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String CREDENTIALS_FOLDER = "credentials"; // Directory to store user credentials.

    

    private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS);
    
    private static final String CLIENT_SECRET_DIR = "client_secret.json";

    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws Exception {
         InputStream in = API.class.getResourceAsStream(CLIENT_SECRET_DIR);
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(CREDENTIALS_FOLDER)))
                .setAccessType("offline")
                .build();
        return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
    }
   
    /**
     * https://docs.google.com/spreadsheets/d/1N61Do4aJhtUQJcRCR1_ybd4HbnaKbDw7ig3tLh1sJ9I/edit#gid=0
     * @throws Throwable 
     */
 
    
    public static void main(String args[]) throws Throwable {
    	 int i=0;
    	 final String spreadsheetId = "1zQfb31C1vlxEjwSe_voNVlassTJ22-L7WHdTjW52vks";//Add your spreadSheet ID
 	    final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
 	   final String range = "A2:E";
       
 	    Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
 	            .setApplicationName(APPLICATION_NAME)
 	            .build();
 	    
 	       
       ValueRange response = service.spreadsheets().values()
               .get(spreadsheetId, range) 
               .execute();
       
       
       List<List<Object>> values = response.getValues();
       if (values == null || values.isEmpty()) {
           System.out.println("No data found.");
       } else {
    	   FileWriter fileWriter = null;
   		BufferedWriter bufferedWriter = null;
   		File storyFile = new File("/home/qainfotech/eclipse-workspace/first/automatedTests/Feature/Login.feature");
   		fileWriter = new FileWriter(storyFile.getAbsolutePath());
   		bufferedWriter = new BufferedWriter(fileWriter);
   		bufferedWriter.write("Feature:Login \n");
    	for (List row : values) {
    		if (!storyFile.exists()) {
				storyFile.getAbsoluteFile().createNewFile();
			}
		else 
		{
			System.out.printf("%s, %s, %s, %s, %s\n", row.get(0), row.get(1),row.get(2),row.get(3),row.get(4));
			bufferedWriter.write("@"+row.get(0)+"\n");
			bufferedWriter.write("Scenario:"+row.get(1)+"\n");
			bufferedWriter.write("Given "+row.get(2)+"\n");
			bufferedWriter.write("When "+row.get(3)+"\n");
			bufferedWriter.write("Then "+row.get(4)+"\n");
		}
    	    	}
    	bufferedWriter.close();
		 fileWriter.close();
		
       }
       TestRunner.main(null);
      
   }  
   
    
    
}
