package dao;

import java.util.*;
import vo.*;
import java.sql.*;
import util.DBUtil;

public class SalesByFilmCategoryDao {
	public List<SalesByFilmCategory> selectSalesByFilmCategoryByPage(int beginRow, int rowPerPage) {
		List<SalesByFilmCategory> list = new ArrayList<SalesByFilmCategory>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		// DB접속
		conn = DBUtil.getConnection();
		String sql = "select category, total_sales totalSales from sales_by_film_category order by category";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				SalesByFilmCategory s = new SalesByFilmCategory();
				s.setCategory(rs.getString("category"));
				s.setTotalSales(rs.getDouble("totalSales"));
				System.out.println(s.toString());
				list.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
