package ggcloud.conference.dao;

import org.springframework.data.repository.CrudRepository;
import ggcloud.conference.model.Event;

public interface EventRespository extends CrudRepository<Event, Integer>{

}
