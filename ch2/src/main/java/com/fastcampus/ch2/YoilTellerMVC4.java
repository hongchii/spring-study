package com.fastcampus.ch2;

import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class YoilTellerMVC4 {
	@ExceptionHandler(Exception.class)
	public String catcher(Exception ex) {
		ex.printStackTrace();
		return "yoilError";
	}

	@RequestMapping("/getYoilMVC4") // http://localhost/ch2/getYoilMVC4?year=2021&month=10&day=1
	public String main(MyDate date, Model model) { // 반환 타입이 ModelAndView

		// 2. 유효성 검사
		if (!isValid(date)) {
			return "yoilError";
		}

		// 3. 처리
		char yoil = getYoil(date);

		// 4. ModelAndView에 작업한 결과를 저장
		// mv.addObject("year", date.getYear());
		// mv.addObject("month", date.getMonth());
		// mv.addObject("day", date.getDay());
		model.addAttribute("myDate", date);
		model.addAttribute("yoil", yoil);

		return "yoil";
	}

	private char getYoil(MyDate date) {
		return getYoil(date.getYear(), date.getMonth(), date.getDay());
	}

	private char getYoil(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day);

		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		return " 일월화수목금토".charAt(dayOfWeek);
	}

	private boolean isValid(MyDate date) {
		return isValid(date.getYear(), date.getMonth(), date.getDay());
	}

	private boolean isValid(int year, int month, int day) {
		if (year == -1 || month == -1 || day == -1)
			return false;

		return (1 <= month && month <= 12) && (1 <= day && day <= 31); // 간단히 체크
	}
}