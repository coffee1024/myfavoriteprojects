package org.ourpioneer.board.web;

import javax.servlet.http.HttpServletRequest;

import org.ourpioneer.board.domain.Message;
import org.ourpioneer.board.service.MessageBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/messagePost.htm")
public class MessagePostController {
	@Autowired
	private MessageBoardService messageBoardService;

	@RequestMapping(method = RequestMethod.GET)
	//@Secured( { "ROLE_USER" })
	public String setupForm(Model model) {
		Message message = new Message();
		model.addAttribute("message", message);
		return "messagePost";
	}

	@RequestMapping(method = RequestMethod.POST)
	//@Secured( { "ROLE_USER" })
	public String onSubmit(@ModelAttribute("message") Message message,
			BindingResult result, HttpServletRequest request) {
		message.setAuthor(request.getRemoteUser());
		if (result.hasErrors()) {
			return "messagePost";
		} else {
			messageBoardService.postMessage(message);
			return "redirect:messageList.htm";
		}
	}

}
