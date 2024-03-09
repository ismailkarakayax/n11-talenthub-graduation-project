package com.ismailkarakayax.restaurantservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nonapi.io.github.classgraph.json.Id;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.SolrDocument;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SolrDocument(collection = "restaurants")
public class Restaurant {
    @Id
    @Field("id")
    private String id;

    @Field("name")
    private String name;

    @Field("location")
    private String location;

    @Field("averageScore")
    private double averageScore;

}