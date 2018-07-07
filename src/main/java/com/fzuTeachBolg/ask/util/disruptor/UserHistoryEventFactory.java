package com.fzuTeachBolg.ask.util.disruptor;

import com.lmax.disruptor.EventFactory;
import com.fzuTeachBolg.util.event.UserDataEvent;

public class UserHistoryEventFactory implements EventFactory<UserDataEvent>{

	@Override
	public UserDataEvent newInstance() {
		return new UserDataEvent();
	}
}
