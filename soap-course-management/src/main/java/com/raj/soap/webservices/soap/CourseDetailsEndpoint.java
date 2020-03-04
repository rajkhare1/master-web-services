package com.raj.soap.webservices.soap;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.raj.courses.CourseDetails;
import com.raj.courses.GetCourseDetailsRequest;
import com.raj.courses.GetCourseDetailsResponse;

@Endpoint
public class CourseDetailsEndpoint {

	//method
	// input - GetCourseDetailsRequest
	//output - GetCourseDetailsResponse
	//"http://raj.com/courses"
	//GetCourseDetailsRequest
	
	@PayloadRoot(namespace = "http://raj.com/courses", localPart = "GetCourseDetailsRequest")
	@ResponsePayload
	public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request) {
		GetCourseDetailsResponse response = new GetCourseDetailsResponse();
		
		CourseDetails courseDetails = new CourseDetails();
		courseDetails.setId(request.getId());
		courseDetails.setName("Microrvices Course");
		courseDetails.setDescription("That woudl be a wonderful course!");
		
		response.setCourseDetails(courseDetails);
		
		
		return response;
	}
}

