package com.account.service.accountService.kafka_consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.account.service.accountService.entiry.Account;
import com.account.service.accountService.entiry.Notification;

import lombok.extern.java.Log;

@Component
@Log
public class AccountActivityNotificationConsumer {

	Notification notification;

	@KafkaListener(topics = "diposite-event-topic")
	public void notificationCreditConsumer(Account account) {
		notification.setAccountFromId(account.getAccountFromId());
		notification.setAccountToId(account.getAccountToId());
		notification.setTransferedAmount(account.getAccountToId());

		System.out.println("account credited successfully.... " + notification.getTransferedAmount());
	}

	@KafkaListener(topics = "amount-not-sufficient-topic")
	public void notSufficiantBalanceConsumer(Account account) {
		notification.setAccountFromId(account.getAccountFromId());
		notification.setAccountToId(account.getAccountToId());
		notification.setTransferedAmount(account.getAccountToId());
		System.out.println("account balance is less then requested amount...  " + notification.getTransferedAmount());
	}

	@KafkaListener(topics = "debited-event-topic")
	public void notificationDebitConsumer(Account account) {
		notification.setAccountFromId(account.getAccountFromId());
		notification.setAccountToId(account.getAccountToId());
		notification.setTransferedAmount(account.getAccountToId());
		System.out.println("account debited successfully...  " + notification.getTransferedAmount());
	}

}
