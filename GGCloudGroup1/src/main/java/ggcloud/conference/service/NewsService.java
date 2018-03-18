package ggcloud.conference.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import ggcloud.conference.dao.NewsRespository;
import ggcloud.conference.model.News;


@Service
@Transactional
public class NewsService {

	private final NewsRespository newsRespository;

	public NewsService(NewsRespository newsRespository) {
		super();
		this.newsRespository = newsRespository;
	}

	// Lấy hết dữ liệu ra để show
	public List<News> findAllNews() {
		List<News> news = new ArrayList<>();
		for (News newa : newsRespository.findAll()) {
			news.add(newa);
		}
		return news;
	}

	// Xóa tin tức
	public void Delete(int id) {
		newsRespository.delete(id);
	}

	// Tìm kiếm tin tức
	public News findNews(int id) {
		return newsRespository.findOne(id);
	}

	// Lưu tin tức khi cập nhật
	public void Save(News newa) {
		newsRespository.save(newa);
	}
	
}
