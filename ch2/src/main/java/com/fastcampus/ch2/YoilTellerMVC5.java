package com.fastcampus.ch2;

import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class YoilTellerMVC5 {
	@ExceptionHandler(Exception.class)
	public String catcher(Exception ex) {
		ex.printStackTrace();
		return "yoilError";
	}

	@RequestMapping("/getYoilMVC5") // http://localhost/ch2/getYoilMVC5
//  public String main(@ModelAttribute("myDate") MyDate date, Model model){	// 아래와 동일
	public String main(@ModelAttribute MyDate date, Model model) { 

		// 2. 유효성 검사
		if (!isValid(date)) {
			return "yoilError";
		}

		// 3. 처리
//		char yoil = getYoil(date);

		// 4. 
		// mv.addObject("year", date.getYear());
		// mv.addObject("month", date.getMonth());
		// mv.addObject("day", date.getDay());
//		model.addAttribute("myDate", date);
//		model.addAttribute("yoil", yoil);

		return "yoil";
	}

	private char getYoil(MyDate date) {
		return getYoil(date.getYear(), date.getMonth(), date.getDay());
	}

	private @ModelAttribute("yoil") char getYoil(int year, int month, int day) {
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