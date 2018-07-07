package com.fzuTeachBolg.ask.util.disruptor;

import com.lmax.disruptor.EventHandler;
import com.fzuTeachBolg.ask.service.repository.UserEventRepository;
import com.fzuTeachBolg.ask.web.model.UserHistory;
import com.fzuTeachBolg.core.UKDataContext;
import com.fzuTeachBolg.util.event.UserDataEvent;

public class UserEventHandler implements EventHandler<UserDataEvent>{

	@Override
	public void onEvent(UserDataEvent arg0, long arg1, boolean arg2)
			throws Exception {
		UserEventRepository userEventRes = UKDataContext.getContext().getBean(UserEventRepository.class) ;
		userEventRes.save((UserHistory)arg0.getEvent()) ;
	}

}
