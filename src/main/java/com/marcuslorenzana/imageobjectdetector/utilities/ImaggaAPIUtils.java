package com.marcuslorenzana.imageobjectdetector.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcuslorenzana.imageobjectdetector.constants.ImaggaConstants;
import com.marcuslorenzana.imageobjectdetector.models.ImaggaApiResponse;
import com.marcuslorenzana.imageobjectdetector.models.ImaggaApiTagItem;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ImaggaAPIUtils {

    private static String imaggaBaseUrl;

    public ImaggaAPIUtils(Environment env) {
        ImaggaAPIUtils.imaggaBaseUrl = env.getProperty("app.IMAGGA_API_ENDPOINT");
    }

    /**
     * Retrieves the tags from the JSON response and returns back the tags
     *
     * @param jsonResponse: String
     * @return List<ImaggaApiTagItem>
     */
    public static List<ImaggaApiTagItem> retrieveTagsFromResponse(String jsonResponse) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ImaggaApiResponse apiResponse = mapper.readValue(jsonResponse, ImaggaApiResponse.class);
        return apiResponse.getResult().getTags();
    }

    /**
     * Constructs url for fetching objects against the image.
     *
     * @param imageSource: String
     * @return String
     */
    public static String getTagsUrl(String imageSource) {
        String tagsBaseUrl = String.format("%s/v2/tags", imaggaBaseUrl);
        return tagsBaseUrl + "?image_url=" + imageSource + "&limit=" + ImaggaConstants.TAG_LIMIT +
                "&threshold=" + ImaggaConstants.CONFIDENCE_THRESHOLD;
    }
}
