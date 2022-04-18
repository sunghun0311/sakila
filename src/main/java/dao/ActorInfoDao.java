package dao;
import java.sql.*;
import java.util.*;
import util.DBUtil;
import vo.ActorInfo;

public class ActorInfoDao {
	
		// 페이징작업, ActorInfo List 보여주기
		public List<ActorInfo> selectActorInfoListByPage(int beginRow, int rowPerPage) {
			List<ActorInfo> list = new ArrayList<ActorInfo>();
		
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			conn = DBUtil.getConnection(); // DBUtil과 연결
			String sql ="SELECT actor_id ActorId, first_name firstName, last_name lastName, film_info filmInfo FROM actor_info ORDER BY actor_id LIMIT ?,?";
			try {
			 stmt = conn.prepareStatement(sql);
			 stmt.setInt(1, beginRow);
			 stmt.setInt(2, rowPerPage);
			 rs = stmt.executeQuery();
			 while(rs.next()) {
				 ActorInfo a = new ActorInfo();
				 a.setActorId(rs.getInt("actorId")); // alias
				 a.setActorId(rs.getInt("firstName"));
				 a.setActorId(rs.getInt("lastName"));
				 a.setActorId(rs.getInt("filmInfo"));
				 a.toString();
				 list.add(a);
			 	}		 
			} catch (SQLException e) {
				e.printStackTrace(); 
			} finally {
					try {
						rs.close();
						stmt.close();
						conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
		// 전체 페이지 구하기(총 개수 구하기)
		public int selectActorInfoTotalRow() throws Exception {
			int row = 0; // 전체 행의 수 변수 초기화
			
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			// static으로 연결(사용?)
			conn = DBUtil.getConnection();
			
			String sql = "SELECT COUNT(*) cnt FROM actor_infor"; // 쿼리문
			try {
					stmt = conn.prepareStatement(sql);
					rs = stmt.executeQuery();
					if(rs.next()) { 
						row = rs.getInt("cnt");
						System.out.println(row+"<- selectActorInfoTotalRow");
					}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					rs.close();
					stmt.close();
					conn.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			return row; // 전체 행의 수 리턴
			
		}
		
	
	
	
}