package com.jinseong.blog.dto;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPOJOBuilder
public class ResponseDto<T> {
	HttpStatus status;
	T data;
}
