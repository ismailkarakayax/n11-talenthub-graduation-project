package com.ismailkarakayax.restaurantservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nonapi.io.github.classgraph.json.Id;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.SolrDocument;

import javax.validation.constraints.*;

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
    @NotBlank(message = "Name cannot be blank")
    @Size(max = 100, message = "Name cannot exceed 100 characters")
    private String name;

    @Field("location")
    @NotNull(message = "Location cannot be null")
    private String location;

    @Field("averageScore")
    @PositiveOrZero(message = "Average score must be a non-negative value")
    @DecimalMin(value = "0", inclusive = true, message = "Average score must be at least 0")
    @DecimalMax(value = "5", inclusive = true, message = "Average score must be at most 5")
    private double averageScore;

}