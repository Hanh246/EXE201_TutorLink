package com.exe201.tutorlink.common.dto.response;

import com.exe201.tutorlink.common.dto.pagination.PaginationMetadata;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PaginationResponse<T> extends BaseResponse<T>{
    @JsonProperty("metadata")
    private PaginationMetadata metadata;
}
