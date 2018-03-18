package ggcloud.conference.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import ggcloud.conference.dao.AboutRespository;
import ggcloud.conference.model.About;

@Service
@Transactional
public class AboutService {
	private final AboutRespository aboutRespository;

	public AboutService(AboutRespository aboutRespository) {
		super();
		this.aboutRespository = aboutRespository;
	}

	// Lấy hết dữ liệu ra để show
	public List<About> findAllAbout() {
		List<About> about = new ArrayList<>();
		for (About newa : aboutRespository.findAll()) {
			about.add(newa);
		}
		return about;
	}

	// Xóa tin tức
	public void Delete(int id) {
		aboutRespository.delete(id);
	}

	// Tìm kiếm tin tức
	public About findAbout(int id) {
		return aboutRespository.findOne(id);
	}

	// Lưu tin tức khi cập nhật
	public void Save(About newa) {
		aboutRespository.save(newa);
	}
}
