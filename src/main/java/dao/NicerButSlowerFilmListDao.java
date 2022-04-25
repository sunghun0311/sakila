package dao;

import java.util.*;
import java.sql.*;
import util.DBUtil;
import vo.NicerButSlowerFilmList;

public class NicerButSlowerFilmListDao {
	public List<NicerButSlowerFilmList> selectNicerButSlowerFilmListByPage(int beginRow, int rowPerPage) {
		List<NicerButSlowerFilmList> list = new ArrayList<NicerButSlowerFilmList>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		// DBUtil을 통해 DB 접속(로그인)
		conn = DBUtil.getConnection();
		String sql = "select fid, itle, description, category, price, length, rating, actors from nicer_but_slower_film_list order by fid limit ?,?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rs = stmt.executeQuery();
			while(rs.next()) {
				NicerButSlowerFilmList n = new NicerButSlowerFilmList();
				n.setFid(rs.getInt("filmId"));
				n.setTitle(rs.getString("title"));
				n.setDescription(rs.getString("description"));
				n.setCategory(rs.getString("category"));
				n.setPrice(rs.getDouble("price"));
				n.setLength(rs.getInt("length"));
				n.setRating(rs.getString("rating"));
				n.setActors(rs.getString("actors"));
				System.out.println(n.toString());
				list.add(n);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int selectNicerButSlowerFilmListTotalRow() {
		int row = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		// DB접속
		conn=DBUtil.getConnection();
		
		String sql = "select count(*) cnt from nicer_but_slower_film_list";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				row = rs.getInt("cnt");
				System.out.println(row+ " <-- selectNicerButSlowerFilmListTotalRow");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return row;
	}
	
	
}
