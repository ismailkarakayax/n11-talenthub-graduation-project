package com.n11.userreviewservice.dto.solr;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseHeader {
    private int status;
    private int QTime;
    private Params params;
}
