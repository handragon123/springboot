package com.mysite.sbb.question;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/question")
@Controller
public class QuestionController {
	
	private final QuestionService questionService;

	@GetMapping("/list")
	public String list(Model model) {
		List<Question> questionList = questionService.getList();
		model.addAttribute("questionList",questionList);
		return "question/list";
	}
	
	@GetMapping("/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		Question question = questionService.getQuestion(id);
		model.addAttribute("question",question);
		
		return "question/detail";		
	}
	
	@GetMapping("/create")
	public String create(QuestionForm questionForm) {
		
		return "question/form";
	}
	
	@PostMapping("/create")
	public String create(@Valid QuestionForm questionForm, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "question/form";
		}
		Map<String, String> question = new HashMap<>();
		question.put("subject", questionForm.getSubject());
		question.put("content", questionForm.getContent());
		questionService.create(question);
		
		return "redirect:list";
	}
	
	
}
