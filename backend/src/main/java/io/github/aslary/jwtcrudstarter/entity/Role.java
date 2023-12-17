package io.github.aslary.jwtcrudstarter.entity;

import lombok.experimental.FieldNameConstants;

@FieldNameConstants(onlyExplicitlyIncluded = true)
public enum Role {

    @FieldNameConstants.Include USER,
    @FieldNameConstants.Include ADMIN

}
