package org.json.junit;

import static org.junit.Assert.*;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.*;
import static org.mockito.Mockito.*;
import org.json.*;
import org.json.junit.data.MySelfContainer;
import org.junit.Test;
/**
 * Created by aronszabo on 07/10/2017.
 */
public class MyTestClass {
    @Test
    public void escapingTest(){
        JSONStringer jsonStringer = new JSONStringer();
        jsonStringer.object();
        jsonStringer.key("quote").value("\"Sic transit gloria mundi.\"");
        jsonStringer.key("backslash").value("\\t\\n");
        jsonStringer.key("tabNewline").value("\t\n");
        jsonStringer.key("tab\tin\tkey").value("");
        jsonStringer.endObject();
        String str = jsonStringer.toString();
        JSONObject jsonObject = new JSONObject(str);
        assertEquals("{\"quote\":\"\\\"Sic transit gloria mundi.\\\"\",\"backslash\":\"\\\\t\\\\n\",\"tabNewline\":\"\\t\\n\",\"tab\\tin\\tkey\":\"\"}",str);
    }
    @Test(expected = StackOverflowError.class)
    public void selfContainerTest(){
        MySelfContainer container = new MySelfContainer();
        container.setC1(container);

        try {
            JSONObject jsonObject = new JSONObject(container);
            assertTrue("Expected an exception", false);
        } catch (Exception e) {
            assertTrue("Expected an exception",
                    true);
        }
    }
    @Test
    public void storeDate(){
        Date mockedDate = mock(Date.class);
        when(mockedDate.toString()).thenReturn("Sat Oct 14 17:28:02 CEST 2017");
        JSONStringer jsonStringer = new JSONStringer();
        jsonStringer.object();
        jsonStringer.key("date").value(mockedDate);
        jsonStringer.endObject();
        String str = jsonStringer.toString();
        assertEquals("{\"date\":\"Sat Oct 14 17:28:02 CEST 2017\"}",str);
    }
    @Test
    public void getQuoteFromUrl() throws IOException {

        //InputStream is = new URL("http://quotes.stormconsultancy.co.uk/quotes.json").openStream();

        try {
            //BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            BufferedReader rd = mock(BufferedReader.class);
            when(rd.readLine()).thenReturn("\n" +
                    "[{\"author\":\"Bill Sempf\",\"id\":44,\"quote\":\"QA Engineer walks into a bar. Orders a beer. Orders 0 beers. Orders 999999999 beers. Orders a lizard. Orders -1 beers. Orders a sfdeljknesv.\",\"permalink\":\"http://quotes.stormconsultancy.co.uk/quotes/44\"}]").thenReturn("");

            JSONArray json = new JSONArray(rd.readLine());
            assertEquals("Bill Sempf",json.getJSONObject(0).getString("author"));
        } finally {
            //is.close();
        }
    }
}
