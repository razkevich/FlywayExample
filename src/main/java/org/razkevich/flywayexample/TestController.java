package org.razkevich.flywayexample;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Controller
public class TestController {
	private DataSource dataSource;

	public TestController(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@RequestMapping("/")
	@ResponseBody
	public String showStatus() throws SQLException {
		PreparedStatement ps = dataSource.getConnection().prepareStatement("select count(*) from test_table");
		ResultSet rs = ps.executeQuery();
		return String.format("<h2>entries in table test_table:</h2> <br/>%s", rs.next() ? rs.getString(1) : "(none)");
	}
}
