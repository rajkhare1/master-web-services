package com.raj.soap.webservices.soap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.raj.courses.CourseDetails;
import com.raj.courses.GetCourseDetailsRequest;
import com.raj.courses.GetCourseDetailsResponse;
import com.raj.soap.webservices.soap.bean.Course;
import com.raj.soap.webservices.soap.service.CourseDetailsService;

@Endpoint
public class CourseDetailsEndpoint {
	
	@Autowired
	CourseDetailsService service;

	//method
	// input - GetCourseDetailsRequest
	//output - GetCourseDetailsResponse
	//"http://raj.com/courses"
	//GetCourseDetailsRequest
	
	@PayloadRoot(namespace = "http://raj.com/courses", localPart = "GetCourseDetailsRequest")
	@ResponsePayload
	public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request) {
		return mapCourse(request);
	}

	private GetCourseDetailsResponse mapCourse(GetCourseDetailsRequest request) {
		Course course = service.findById(request.getId());
		GetCourseDetailsResponse response = new GetCourseDetailsResponse();
		CourseDetails courseDetails = new CourseDetails();
		courseDetails.setId(course.getId());
		courseDetails.setName(course.getName());
		courseDetails.setDescription(course.getDescrtiption());
		
		response.setCourseDetails(courseDetails);
		return response;
	}
}

