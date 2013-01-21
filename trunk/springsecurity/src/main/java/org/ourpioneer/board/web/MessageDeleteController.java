package org.ourpioneer.board.web;

import org.ourpioneer.board.domain.Message;
import org.ourpioneer.board.service.MessageBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/messageDelete.htm")
public class MessageDeleteController {
	@Autowired
	private MessageBoardService messageBoardService;

	@RequestMapping(method = RequestMethod.GET)
	//@Secured( { "ROLE_ADMIN", "IP_LOCAL_HOST" })
	public String messageDelete(
			@RequestParam(required = true, value = "messageId") Long messageId,
			Model model) {
		Message message = messageBoardService.findMessageById(messageId);
		messageBoardService.deleteMeesage(message);
		model.addAttribute("messages", messageBoardService.listMessages());
		return "redirect:messageList.htm";

	}

}
