package com.n11.userreviewservice.dto.solr;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Params {

    private String q;
    private String indent;
    @JsonProperty("q.op")
    private String qOp;
    private String fq;
    private String sort;
    private String rows;

}
