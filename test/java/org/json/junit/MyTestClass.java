package org.json.junit;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringWriter;
import java.util.*;

import org.json.*;
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
}
