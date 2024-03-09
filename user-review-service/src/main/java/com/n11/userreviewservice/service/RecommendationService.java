package com.n11.userreviewservice.service;

import com.n11.userreviewservice.dto.solr.RestaurantResponse;
import org.apache.solr.common.SolrDocumentList;

import java.util.List;

public interface RecommendationService {
    SolrDocumentList performSolrQuery();

}
