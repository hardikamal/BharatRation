package utils;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ragavendran on 30-06-2015.
 */
public class RestfulService
{
    public static String source(String URLData)
    {
        JSONObject jsonObject = null;
        String concatoutput = "";
        String output;

        try
        {
            URL url = new URL(URLData);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200)
            {
                throw new RuntimeException("Failed PHP: HTTP error code : "
                        + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            while ((output = br.readLine()) != null)
            {
                concatoutput += output;
            }
            conn.disconnect();
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return concatoutput;
    }
}
