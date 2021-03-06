package com.fastcampus.ch2;

import java.io.IOException;
import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

//년월일을 입력하면 요일을 알려주는 프로그램
@Controller
public class YoilTellerMVC {
	@ExceptionHandler(Exception.class)
	public String catcher(Exception ex) {
		ex.printStackTrace();
		return "yoilError";
	}

	@RequestMapping("/getYoilMVC")
//	public void main(HttpServletRequest request, HttpServletResponse response) throws IOException {
	public String main(@RequestParam(required=true) int year, 
			@RequestParam(required=true) int month, 
			@RequestParam(required=true) int day, Model model) {

		// 1.유효성 검사
		if (!isValid(year, month, day))
			return "yoilError";

		// 2. 요일 계산
		char yoil = getYoil(year, month, day);

		// 3. 계산한 결과를 model에 저장
		model.addAttribute("year", year);
		model.addAttribute("month", month);
		model.addAttribute("day", day);
		model.addAttribute("yoil", yoil);

		return "yoil";
	}

	// ModelAndView - 잘 사용하지 않음. 
	@RequestMapping("/getYoilMVC7")
	public ModelAndView main(int year, int month, int day) throws IOException {
		// 1. ModelAndView를 생성하고, 기본 뷰를 지정
		ModelAndView mv = new ModelAndView();
		mv.setViewName("yoilError");

		// 2.유효성 검사
		if (!isValid(year, month, day))
			return mv;

		// 3. 요일 계산
		char yoil = getYoil(year, month, day);

		// 4. 계산한 결과를 ModelAndView에 저장
		mv.addObject("year", year);
		mv.addObject("month", month);
		mv.addObject("day", day);
		mv.addObject("yoil", yoil);

		// 5. 작업 결과를 보여줄 뷰의 이름을 지정
		mv.setViewName("yoil");

		// 6. ModelAndView를 반환
		return mv;
	}

	private boolean isValid(int year, int month, int day) {
		// TODO Auto-generated method stub
		return true;
	}

	private char getYoil(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day);

		int dayofWeek = cal.get(Calendar.DAY_OF_WEEK); // 1:일요일, 2:월요일 ..
		return " 일월화수목금토".charAt(dayofWeek);
	}

}