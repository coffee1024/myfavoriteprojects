package org.ourpioneer.board.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.ourpioneer.board.domain.Message;
import org.springframework.security.access.annotation.Secured;

public class MessageBoardServiceImpl implements MessageBoardService {
	private Map<Long, Message> messages = new LinkedHashMap<Long, Message>();

	@Secured( { "ROLE_ADMIN", "IP_LOCAL_HOST" })
	public synchronized void deleteMeesage(Message message) {
		messages.remove(message.getId());
	}

	@Secured( { "ROLE_USER", "ROLE_GUEST" })
	public Message findMessageById(Long messageId) {
		return messages.get(messageId);
	}

	@Secured( { "ROLE_USER", "ROLE_GUEST" })
	public List<Message> listMessages() {
		return new ArrayList<Message>(messages.values());
	}

	@Secured( { "ROLE_USER" })
	public synchronized void postMessage(Message message) {
		message.setId(System.currentTimeMillis());
		messages.put(message.getId(), message);
	}

}
