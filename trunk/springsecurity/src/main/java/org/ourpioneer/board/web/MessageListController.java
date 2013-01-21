package org.ourpioneer.board.web;

import java.util.List;

import org.ourpioneer.board.domain.Message;
import org.ourpioneer.board.service.MessageBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/messageList.htm")
public class MessageListController {
	@Autowired
	private MessageBoardService messageBoardService;

	@RequestMapping(method = RequestMethod.GET)
	//@Secured( { "ROLE_USER" })
	public String generateList(Model model) {
		List<Message> messages = java.util.Collections.emptyList();
		messages = messageBoardService.listMessages();
		model.addAttribute("messages", messages);
		return "messageList";
	}

}
