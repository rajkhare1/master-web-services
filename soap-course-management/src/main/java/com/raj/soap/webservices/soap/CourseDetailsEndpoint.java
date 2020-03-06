package com.raj.soap.webservices.soap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.raj.courses.CourseDetails;
import com.raj.courses.DeleteCourseDetailsRequest;
import com.raj.courses.DeleteCourseDetailsResponse;
import com.raj.courses.GetAllCourseDetailsRequest;
import com.raj.courses.GetAllCourseDetailsResponse;
import com.raj.courses.GetCourseDetailsRequest;
import com.raj.courses.GetCourseDetailsResponse;
import com.raj.soap.webservices.soap.bean.Course;
import com.raj.soap.webservices.soap.exception.CourseNotFoundException;
import com.raj.soap.webservices.soap.service.CourseDetailsService;
import com.raj.soap.webservices.soap.service.CourseDetailsService.Status;

@Endpoint
public class CourseDetailsEndpoint {

	@Autowired
	CourseDetailsService service;

	// method
	// input - GetCourseDetailsRequest
	// output - GetCourseDetailsResponse

	// http://raj.com/courses
	// GetCourseDetailsRequest
	@PayloadRoot(namespace = "http://raj.com/courses", localPart = "GetCourseDetailsRequest")
	@ResponsePayload
	public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request) {

		Course course = service.findById(request.getId());
		
		if(course ==null) {
			throw new CourseNotFoundException("Invalid Course Id: "+request.getId());
		}

		return mapCourseDetails(course);
	}

	private GetCourseDetailsResponse mapCourseDetails(Course course) {
		GetCourseDetailsResponse response = new GetCourseDetailsResponse();
		response.setCourseDetails(mapCourse(course));
		return response;
	}

	private GetAllCourseDetailsResponse mapAllCourseDetails(List<Course> courses) {
		GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();
		for (Course course : courses) {
			CourseDetails mapCourse = mapCourse(course);
			response.getCourseDetails().add(mapCourse);
		}
		return response;
	}

	private CourseDetails mapCourse(Course course) {
		CourseDetails courseDetails = new CourseDetails();

		courseDetails.setId(course.getId());

		courseDetails.setName(course.getName());

		courseDetails.setDescription(course.getDescrtiption());
		return courseDetails;
	}

	@PayloadRoot(namespace = "http://raj.com/courses", localPart = "GetAllCourseDetailsRequest")
	@ResponsePayload
	public GetAllCourseDetailsResponse processAllCourseDetailsRequest(
			@RequestPayload GetAllCourseDetailsRequest request) {

		List<Course> courses = service.findAll();

		return mapAllCourseDetails(courses);
	}
	
	@PayloadRoot(namespace = "http://raj.com/courses", localPart = "DeleteCourseDetailsRequest")
	@ResponsePayload
	public DeleteCourseDetailsResponse deleteCourseDetailsRequest(@RequestPayload DeleteCourseDetailsRequest request) {

		Status status = service.deleteById(request.getId());
		DeleteCourseDetailsResponse response = new DeleteCourseDetailsResponse();
		response.setStatus(mapStatus(status));

		return response;
	}

	private Status mapStatus(Status status) {
		if(status == Status.FAILURE)
			return Status.FAILURE;
		return Status.SUCCESS;
	}


}