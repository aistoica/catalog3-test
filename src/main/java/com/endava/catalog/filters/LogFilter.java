package com.endava.catalog.filters;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.filter.log.LogDetail;
import io.restassured.internal.print.RequestPrinter;
import io.restassured.internal.print.ResponsePrinter;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import java.util.HashSet;

public class LogFilter implements Filter {

	@Override
	public Response filter( FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec,
			FilterContext context ) {

		System.out.println("---------------------------");
		RequestPrinter.print( requestSpec, requestSpec.getMethod(), requestSpec.getURI(), LogDetail.ALL
				, new HashSet<>(), System.out, true );
		final Response response = context.next( requestSpec, responseSpec );
		System.out.println("***************************");
		ResponsePrinter.print( response, response.getBody(), System.out, LogDetail.ALL,
				true, new HashSet<>() );
		System.out.println("---------------------------");
		return response;
	}
}
