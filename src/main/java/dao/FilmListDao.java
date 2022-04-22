package dao;

import java.sql.*;
import java.util.*;

import util.DBUtil;
import vo.*;

public class FilmListDao {
	// filmlist(view)
	public List<FilmList> selectFilmListByPage(int beginRow, int rowPerPage) {
		List<FilmList> list = new ArrayList<FilmList>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		// DB접속 클래스
		conn = DBUtil.getConnection();
		String sql = "select fid, title, descriptionm, category, price, length, rating, actors from film_list order by fid limit ?,?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rs = stmt.executeQuery();
			while(rs.next()) {
				FilmList f = new FilmList();
				f.setFid(rs.getInt("filmId"));
				f.setTitle(rs.getString("title"));
				f.setDescription(rs.getString("description"));
				f.setCategory(rs.getString("category"));
				f.setPrice(rs.getDouble("price"));
				f.setLength(rs.getInt("length"));
				f.setRating(rs.getString("rating"));
				f.setActors(rs.getString("actors"));
				System.out.println(f.toString());
				list.add(f);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return list;
	}
	
	// filmList(view) 전체 페이지(총 개수) 구하기
	public int selectFilmListTotalRow() {
		int row = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		conn = DBUtil.getConnection();
		
		String sql = "select count(*) cnt from film_list";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				row = rs.getInt("cnt");
				System.out.println(row+ " <-- selectFilmListTotalRow");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}
}
