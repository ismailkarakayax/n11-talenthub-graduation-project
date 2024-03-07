package com.n11.userreviewservice.model.base;

import jakarta.persistence.Embedded;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity implements BaseModel {

    @Embedded
    private BaseAdditionalFields baseAdditionalFields;
}
