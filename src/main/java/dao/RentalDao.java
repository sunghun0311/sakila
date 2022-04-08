package dao;
import java.sql.*;
import java.util.*;
import util.DBUtil;
import vo.*;

public class RentalDao { // 렌탈Table 검색 결과의 리스트를 호출하는 메서드, 페이징 메서드
	public List<Map<String, Object>> selectRentalSearchList(int storeId, String customerName, String beginDate, String endDate, int beginRow, int rowPerPage) {			
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			
			// DB접속
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			// DBUtil 연동
			conn = DBUtil.getConnection();
			try {
				// ? 4개 -> storeId, customerName, beginDate, endDate
				// 기본 쿼리문(동적 쿼리)
				String sql =  "SELECT"
			       		+ "	r.*,"
			       		+ "	CONCAT(c.first_name ,'', c.last_name) name,"
			       		+ "	s.staff_id storeId,"
			       		+ "	i.film_id filmId,"
			       		+ "	f.title title"
			       		+ "	FROM rental r"
			       		+ "	INNER JOIN customer c"
			       		+ "	ON r.customer_id = c.customer_id"
			       		+ "	INNER JOIN staff s"
			       		+ "	ON r.staff_id = s.staff_id"
			       		+ "	INNER JOIN inventory i"
			       		+ "	ON r.inventory_id = i.inventory_id"
			       		+ "	INNER JOIN film f"
			       		+ "	ON i.film_id = f.film_id";
				
						
						
				
		
			
	
		
		
		
		
		/*
		SELECT 
		r.*, 
		CONCAT(c.first_name,' ',c.last_name) cName, 
		s.store_id storeId, 
		i.film_id filmId,
		f.title
		FROM rental r INNER JOIN customer c
		ON r.customer_id = c.customer_id
			INNER JOIN staff s
			ON r.staff_id = s.staff_id
				INNER JOIN inventory i
				ON r.inventory_id = i.inventory_id
					INNER JOIN film f
					ON i.film_id = f.film_id
		WHERE s.store_id=? AND CONCAT(c.first_name,' ',c.last_name) LIKE ?
		AND r.rental_date BETWEEN STR_TO_DATE(?,'%Y-%m-%d') 
		AND STR_TO_DATE(?,'%Y-%m-%d');
		*/
		return null; // 수정
	}
}
