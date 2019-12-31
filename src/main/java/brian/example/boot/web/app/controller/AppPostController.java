package brian.example.boot.web.app.controller;

import brian.example.boot.web.app.command.PostCommand;
import brian.example.boot.web.app.domain.AppUser;
import brian.example.boot.web.app.mapper.AppPostMapper;
import brian.example.boot.web.app.service.AppUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import brian.example.boot.web.app.domain.AppPost;
import brian.example.boot.web.app.exception.CreationException;
import brian.example.boot.web.app.exception.NotFoundException;
import brian.example.boot.web.app.service.AppPostService;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
public class AppPostController {

	private AppPostService service;
	private AppUserService appUserService;
	private HttpSession session;
	private AppPostMapper mapper;

	@Autowired			// To use MockMvc, I commented out constructor based auto-wiring.
	public AppPostController(AppPostService postService, AppUserService appUserService,
							 HttpSession session, AppPostMapper mapper) {
		this.service = postService;
		this.appUserService = appUserService;
		this.session = session;
		this.mapper = mapper;
	}

	/**
	 * Display all posts
	 *
	 * @param model
	 * @return
	 */
	@GetMapping({"/posts"})
	public String getPosts(Model model) {
		model.addAttribute( "posts", service.getAllPost());

		return "post/posts";
	}

	/**
	 * Display selected post
	 *
	 * @param postId
	 * @return
	 */
	@GetMapping("/post/{post_id}")
	public ModelAndView getPost(@PathVariable("post_id") Integer postId){

		AppPost appPost = service.getPost(postId);

		if (appPost == null)
			throw new NotFoundException("Post not found with post id : " + postId);

//		PostCommand command = new PostCommand();
//		command.setPostId(p.getPostId());
//		command.setSubject(p.getSubject());
//		command.setContent(p.getContent());
//		command.setUserId(p.getAppUser().getUserId());
		PostCommand command = mapper.toPostCommand(appPost);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("postCommand", command);
		modelAndView.setViewName("post/post");

		return modelAndView;
	}

	/**
	 * Display Post Form to add new Post
	 *
	 * @param model
	 * @return
	 */
	@GetMapping("/post")
	public String initPostInsert(Model model){

		AppUser appUser = (AppUser)session.getAttribute("loggedInUser");

		PostCommand command = new PostCommand();
		command.setUserId(appUser.getUserId());
		model.addAttribute( "postCommand", command);

		return "post/form";
	}

	/**
	 * Adding new Post from Post Form (Command)
	 *
	 * @param postCommand
	 * @return
	 */
	@PostMapping("/post")
	public String processPostInsert(
			@Valid @ModelAttribute("postCommand") PostCommand postCommand,
			BindingResult bindingResult) {

		// Validate the form fields (Requires command object and BindingResult object)
		if (bindingResult.hasErrors()) {
			bindingResult.getAllErrors().forEach(e -> log.debug(e.toString()));	// just to log (not necessary)
			return "post/form";
		}

		String userId = postCommand.getUserId();

		if (null == userId)
			throw new NotFoundException(String.format("User ID [%s] was not found", userId));

		// Form to Model
		AppPost post = new AppPost();
		post.setSubject(postCommand.getSubject());
		post.setContent(postCommand.getContent());
		post.setAppUser(appUserService.getAppUser(postCommand.getUserId()));

		post = service.addPost(post);

		if (null == post.getPostId())
			throw new CreationException("Error occurred while adding Post");

		return "redirect:/posts";
	}

	@PutMapping("/post/{post_id}")
	public String updatePost(
			@Valid @ModelAttribute("postCommand") PostCommand postCommand,
			BindingResult bindingResult,
			@PathVariable("post_id") Integer postId,
			Model model) {

		// Validate the form fields (Requires command object and BindingResult object)
		if (bindingResult.hasErrors()) {
			model.addAttribute("postCommand", postCommand);
			return "post/post";
		}

		AppPost post = service.getPost(postCommand.getPostId());
		post.setSubject(postCommand.getSubject());
		post.setContent(postCommand.getContent());

		service.updatePost(post);

		return "redirect:/posts";
	}

	@DeleteMapping("/post/{post_id}")
	public String deletePost(@PathVariable("post_id") Integer postId) {

		service.deletePost(postId);

		return "redirect:/posts";
	}
}
