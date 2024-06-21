package com.mysite.sbb.question;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mysite.sbb.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class QuestionService {

	private final QuestionRepository questionRepository;
	
	public List<Question> getList(){
		return this.questionRepository.findAll();
	}
	
	public Question getQuestion(Integer id) {
		Optional<Question> oq = this.questionRepository.findById(id);
		if(oq.isPresent()) {
			return oq.get();
		}else {
			throw new DataNotFoundException("question not found");
		}
	}
	
	public void create(Map<String, String> question) {
		Question q = new Question();
		q.setSubject(question.get("subject"));
		q.setContent(question.get("content"));
		q.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q);
	}
}







