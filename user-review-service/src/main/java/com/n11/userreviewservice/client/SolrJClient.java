package com.n11.userreviewservice.client;

import com.n11.userreviewservice.dto.response.RestaurantResponse;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class SolrJClient {

    private static final String SOLR_URL = "http://localhost:8983/solr/restaurants";

    public List<RestaurantResponse> createSolrQuery(Double latitude, Double longitude) {
        try (HttpSolrClient solrClient = new HttpSolrClient.Builder(SOLR_URL).build()) {

            SolrQuery solrQuery = new SolrQuery();
            solrQuery.set("fq", "{!geofilt pt="+latitude+","+longitude+" sfield=location d=10}");
            solrQuery.set("indent", true);
            solrQuery.set("q.op", "OR");
            solrQuery.set("q", "*:*");
            solrQuery.set("rows", 3);
            solrQuery.set("sort", "sum(mul(averageScore,14),mul(sub(10,geodist("+latitude+","+longitude+",location)),3)) desc");

            QueryResponse queryResponse = solrClient.query(solrQuery);
            SolrDocumentList solrDocuments = queryResponse.getResults();
            List<RestaurantResponse> resultDTOList = new ArrayList<>();

            for (SolrDocument solrDocument : solrDocuments) {
                RestaurantResponse resultDTO = new RestaurantResponse(
                        (String) solrDocument.getFieldValue("id"),
                        (String) solrDocument.getFieldValue("name"),
                        (String) solrDocument.getFieldValue("location"),
                        (Double) solrDocument.getFieldValue("averageScore")
                );
                resultDTOList.add(resultDTO);
            }
            return resultDTOList;
        } catch (SolrServerException | IOException e) {
            return Collections.emptyList();
        }
    }
}
