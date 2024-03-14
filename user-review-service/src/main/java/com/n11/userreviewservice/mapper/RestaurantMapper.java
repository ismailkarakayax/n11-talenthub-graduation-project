package com.n11.userreviewservice.mapper;

import com.n11.userreviewservice.dto.response.RestaurantResponse;
import org.apache.solr.common.SolrDocument;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface RestaurantMapper {

    RestaurantResponse solrDocumentToDto(SolrDocument solrDocument);

}
