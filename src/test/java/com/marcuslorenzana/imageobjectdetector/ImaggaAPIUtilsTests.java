package com.marcuslorenzana.imageobjectdetector;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.marcuslorenzana.imageobjectdetector.models.imagga.ImaggaApiTagItem;
import com.marcuslorenzana.imageobjectdetector.utilities.ImaggaAPIUtils;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class ImaggaAPIUtilsTests {

    @Test
    public void testRetrieveTagsFromValidResponse() throws JsonProcessingException {
        String validJsonResponse = "{\"result\":{\"tags\":[{\"confidence\":100,\"tag\":{\"en\":\"dog\"}},{\"confidence\":98,\"tag\":{\"en\":\"pet\"}}]}}";

        List<ImaggaApiTagItem> tags = ImaggaAPIUtils.retrieveTagsFromResponse(validJsonResponse);
        assertNotNull(tags);
        assertEquals(2, tags.size());
        assertEquals("dog", tags.get(0).getTag().getEn());
        assertEquals("pet", tags.get(1).getTag().getEn());
    }

    @Test
    public void testRetrieveTagsFromInvalidResponse() {
        String invalidJsonResponse = "Invalid JSON";

        Exception exception = assertThrows(JsonProcessingException.class, () ->
                ImaggaAPIUtils.retrieveTagsFromResponse(invalidJsonResponse));

        String expectedMessage = "Unrecognized token";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testRetrieveTagsFromEmptyResponse() {
        String emptyJsonResponse = "";

        Exception exception = assertThrows(JsonProcessingException.class, () ->
                ImaggaAPIUtils.retrieveTagsFromResponse(emptyJsonResponse));

        assertNotNull(exception);
    }
}

