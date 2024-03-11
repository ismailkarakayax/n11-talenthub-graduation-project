package com.n11.userreviewservice.mapper;

import com.n11.userreviewservice.dto.response.RestaurantResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface RestaurantMapper {



    RestaurantResponse solrDocumentToDto(SolrDocument solrDocument);

    List<RestaurantResponse> solrDocumentListToDto(SolrDocumentList solrDocuments);
}
