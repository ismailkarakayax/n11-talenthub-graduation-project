package com.n11.userreviewservice.service.impl;


import com.n11.userreviewservice.dto.solr.RestaurantResponse;
import com.n11.userreviewservice.mapper.RestaurantMapper;
import com.n11.userreviewservice.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationServiceImpl implements RecommendationService {

    private final RestaurantMapper restaurantMapper;
    private final String solrUrl = "http://localhost:8983/solr/restaurants"; // Solr server URL

    public SolrDocumentList performSolrQuery() {
        try (HttpSolrClient solrClient = new HttpSolrClient.Builder(solrUrl).build()) {
            SolrQuery solrQuery = new SolrQuery();
            solrQuery.set("fq", "{!geofilt pt=41.06663951827246,28.88753330324236 sfield=location d=10}");
            solrQuery.set("indent", true);
            solrQuery.set("q.op", "OR");
            solrQuery.set("q", "*:*");
            solrQuery.set("rows", 3);
            solrQuery.set("sort", "sum(mul(averageScore,14),mul(sub(10,geodist(41.06663951827246,28.88753330324236,location)),3)) desc");

            QueryResponse queryResponse = solrClient.query(solrQuery);

            return queryResponse.getResults();
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
