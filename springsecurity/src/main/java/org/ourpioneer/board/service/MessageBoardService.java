package org.ourpioneer.board.service;

import java.util.List;

import org.ourpioneer.board.domain.Message;


public interface MessageBoardService {
	public List<Message> listMessages();

	public void postMessage(Message message);

	public void deleteMeesage(Message message);

	public Message findMessageById(Long messageId);
}
