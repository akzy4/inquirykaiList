package com.example.demo;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class InquirykaiController {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@GetMapping("/")
	public String index(Model model) {
		String sql = "SELECT id, name, mail, age, gender, contents FROM inquiry";
		
		RowMapper<Inquirykai> rowMapper = new RowMapper<Inquirykai>() {

			@Override
			public Inquirykai mapRow(ResultSet rs, int rowNum) throws SQLException {
				Inquirykai inquiry = new Inquirykai();
				inquiry.setId(rs.getInt("id"));
				inquiry.setName(rs.getString("name"));
				inquiry.setMail(rs.getString("age"));
				inquiry.setAge(rs.getInt("age"));
				inquiry.setGender(rs.getString("gender"));
				inquiry.setContents(rs.getString("contents"));
				return inquiry;
			}
			
		};
		
		List<Inquirykai> inquiries = jdbcTemplate.query(sql, rowMapper);
		model.addAttribute("inquiries", inquiries);
		return "index";
			
	}

	@GetMapping("/inquiry/{id}")
	public String inquirykai(@PathVariable int id, Model model) {
		String sql = "SELECT id, name, mail, age, gender, contents FROM inquiry WHERE id = ?";
				
        Object[] args = new Object[] {id};
        RowMapper<Inquirykai> rowMapper = BeanPropertyRowMapper.newInstance(Inquirykai.class);
        Inquirykai inquirykai = jdbcTemplate.queryForObject(sql, args, rowMapper);
        model.addAttribute("inquirykai", inquirykai);
        return "inquiry"; 
	}

}










