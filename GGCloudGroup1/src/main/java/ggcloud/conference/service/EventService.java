package ggcloud.conference.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import ggcloud.conference.dao.EventRespository;
import ggcloud.conference.model.Event;

@Service
@Transactional
public class EventService {
	
	private final EventRespository eventRespository;

	public EventService(EventRespository eventRespository) {
		super();
		this.eventRespository = eventRespository;
	}

	// Lấy hết dữ liệu ra để show
	public List<Event> findAllEvent() {
		List<Event> event = new ArrayList<>();
		for (Event newa : eventRespository.findAll()) {
			event.add(newa);
		}
		return event;
	}

	// Xóa tin tức
	public void Delete(int id) {
		eventRespository.delete(id);
	}

	// Tìm kiếm tin tức
	public Event findAbout(int id) {
		return eventRespository.findOne(id);
	}

	// Lưu tin tức khi cập nhật
	public void Save(Event newa) {
		eventRespository.save(newa);
	}
}
