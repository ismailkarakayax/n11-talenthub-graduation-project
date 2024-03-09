package com.n11.userreviewservice.mapper;

import com.n11.userreviewservice.dto.solr.RestaurantResponse;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface RestaurantMapper {

    RestaurantResponse convertToResponse(SolrDocument solrDocument);
    List<RestaurantResponse> convertToResponseList(List<SolrDocument> solrDocuments);

    @AfterMapping
    default void convertLocationString(SolrDocument solrDocument, @MappingTarget RestaurantResponse restaurantResponse) {
        String locationString = (String) solrDocument.getFieldValue("location");
    }
}
