package com.todo.app.model.response;

import com.couchbase.client.core.deps.com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WrapperResponse<T> {
    private T data;
    private int status;
    private String errorDetail;
    private String errorCode;
    private String timestamp;

    public WrapperResponse(T data) {
        this.data = data;
    }

    public WrapperResponse(T data, int status) {
        this.data = data;
        this.status = status;
    }
}
