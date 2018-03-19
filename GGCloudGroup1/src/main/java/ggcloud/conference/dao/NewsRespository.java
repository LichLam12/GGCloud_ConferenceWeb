package ggcloud.conference.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ggcloud.conference.model.News;

public interface NewsRespository extends CrudRepository<News, Integer>{
	/*public List<News> findAllNews();*/
	
}
