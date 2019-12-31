package brian.example.boot.web.app.controller;

import brian.example.boot.web.app.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalTime;

@ControllerAdvice
public class ControllerExceptionHandler {

	private Logger log = LoggerFactory.getLogger(ControllerExceptionHandler.class);

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({IllegalArgumentException.class, NotFoundException.class})
	public ModelAndView handleBadRequest(HttpServletRequest req, Exception ex){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("timestamp", LocalTime.now());
		modelAndView.addObject("path", req.getRequestURL());
		modelAndView.addObject("error", ex.getCause());
		modelAndView.addObject("message", ex.getMessage());
		modelAndView.addObject("status", HttpStatus.NOT_FOUND.value());
		modelAndView.addObject("trace", ex.getStackTrace());

		modelAndView.setViewName("error/400");
		return modelAndView;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({NumberFormatException.class, MethodArgumentTypeMismatchException.class})
	public ModelAndView handleBadRequest2(HttpServletRequest req, Exception ex){

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("timestamp", LocalTime.now());
		modelAndView.addObject("path", req.getRequestURL());
		modelAndView.addObject("error", ex.getCause());
		modelAndView.addObject("message", ex.getMessage());
		modelAndView.addObject("status", HttpStatus.NOT_FOUND.value());
		modelAndView.addObject("trace", ex.getStackTrace());

		modelAndView.setViewName("error/400");
		return modelAndView;
	}

	/**
	 * This will handle Page Not Found exception (404)
	 *
	 * @param ex
	 * @param model
	 * @return
	 */
	@ResponseStatus(HttpStatus.NOT_FOUND)
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
