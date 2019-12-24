package brian.example.boot.web.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalTime;

//@Controller
//public class ControllerExceptionHandler implements ErrorController {
//
//	private Logger log = LoggerFactory.getLogger(ControllerExceptionHandler.class);

//	@GetMapping("/error")
//	public String handleError(HttpServletRequest request) {
//		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
//
//		if (status != null) {
//			int statusCode = Integer.parseInt(status.toString());
//
//			if (statusCode == HttpStatus.NOT_FOUND.value()) {
//				return "error/404";
//			} else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
//				return "error/500";
//			}
//		}
//
//		return "error";
//	}
//
//	@Override
//	public String getErrorPath() {
//		return "/error";
//	}

@ControllerAdvice
public class ControllerExceptionHandler {

	private Logger log = LoggerFactory.getLogger(ControllerExceptionHandler.class);

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({IllegalArgumentException.class})
	public String handleBadRequest(HttpServletRequest req, Exception ex){

		return "error/400";
	}

	/**
	 * This will handle Page Not Found exception (404)
	 *
	 * @param ex
	 * @param model
	 * @return
	 */
	@ExceptionHandler(NoHandlerFoundException.class)
	public String handle404Exception(NoHandlerFoundException ex, Model model) {
		//do whatever you want
		model.addAttribute("timestamp", LocalTime.now());
		model.addAttribute("path", ex.getRequestURL());
		model.addAttribute("error", ex.getCause());
		model.addAttribute("message", ex.getMessage());
		model.addAttribute("status", HttpStatus.NOT_FOUND.value());
		model.addAttribute("trace", ex.getStackTrace());

		log.error("No page found for the path: "+ex.getRequestURL(), ex);

		return "error/404";
	}

	/**
	 * Any exceptions that doesn't have a handler will be handled here.
	 *
	 * @param req
	 * @param ex
	 * @param model
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public String handleAllTheOtherException(HttpServletRequest req, Exception ex, Model model){

		model.addAttribute("timestamp", LocalTime.now());
		model.addAttribute("path", req.getRequestURL());
		model.addAttribute("error", ex.getClass().getName());
		model.addAttribute("message", ex.getMessage());
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		model.addAttribute("trace", ex.getStackTrace());

		log.error("-------------- Exception Handled: ", ex);

		return "error";
	}
}
