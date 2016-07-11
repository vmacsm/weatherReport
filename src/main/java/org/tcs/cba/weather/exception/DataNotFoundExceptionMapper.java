package org.tcs.cba.weather.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.tcs.cba.weather.model.ErrorMessage;



@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException >{

	public DataNotFoundExceptionMapper() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Response toResponse(DataNotFoundException arg0) {
		ErrorMessage errorMessage = new ErrorMessage(arg0.getMessage(), 404, "http://http://api.openweathermap.org");
		
		return Response.status(Status.NOT_FOUND)
				.entity(errorMessage).build();
	}

}
