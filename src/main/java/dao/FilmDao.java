package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.DBUtil;
import vo.FilmList;

/* film 테이블 FilmDao
 * 프로시져 film_in_stock + film_not_in_stock 구현
 * film 테이블 검색기능 SELECT film price 구현 
 * film 테이블 검색기능 SELECT film FilmListSearch 구현 
 * film 테이블 검색기능 페이징 추가
 */

public class FilmDao {	// 검색
		public List<FilmList> selectFilmListSearch(int beginRow, int rowPerPage, String category, String rating, double price, int length, String title, String actor) {
			List<FilmList> list = new ArrayList<FilmList>();	
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			conn = DBUtil.getConnection();
		
			try {
			// 동적쿼리	
			String sql = "SELECT fid,title,description,category,price,length,rating,actors FROM film_list WHERE title LIKE ? AND actors LIKE ?";
			
			// 모두 공백
			if(category.equals("") && rating.equals("") && price==-1 && length==-1) { 
				sql += "ORDER BY fid LIMIT ?, ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+title+"%");
				stmt.setString(2, "%"+actor+"%");
				stmt.setInt(3,  beginRow);
				stmt.setInt(4,  rowPerPage);	
			}
			// category만 선택할 경우
			else if(!category.equals("") && rating.equals("") && price==-1 && length==-1) {
				sql += "AND category=? ORDER BY fid LIMIT ?,?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+title+"%");
				stmt.setString(2, "%"+actor+"%");
				stmt.setString(3, category);
				stmt.setInt(4,  beginRow);
				stmt.setInt(5,  rowPerPage);	
			}
			// rating만 선택할 경우
			else if(category.equals("") && !rating.equals("") && price==-1 && length==-1) {
				sql +="AND rating=? ORDER BY fid LIMIT ?,?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+title+"%");
				stmt.setString(2, "%"+actor+"%");
				stmt.setString(3, rating);
				stmt.setInt(4,  beginRow);
				stmt.setInt(5,  rowPerPage);
			}
			// price만 선택할 경우
			else if(category.equals("") && rating.equals("") && price!=-1 && length==-1) {
				sql +="AND price=? ORDER BY fid LIMIT ?,?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+title+"%");
				stmt.setString(2, "%"+actor+"%");
				stmt.setDouble(3, price);
				stmt.setInt(4,  beginRow);
				stmt.setInt(5,  rowPerPage);
			}				
			// length만 선택할 경우
			else if(category.equals("") && rating.equals("") && price==-1 && length!=-1) { 
				if(length == 0) {
					sql += "AND length<60 ORDER BY fid LIMIT ?,?";
				} else if(length == 1) {
					sql += " AND length>=60 ORDER BY fid LIMIT ?, ?";	
				}	
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+title+"%");
				stmt.setString(2, "%"+actor+"%");
				stmt.setInt(3,  beginRow);
				stmt.setInt(4,  rowPerPage);
			}
			// category, rating만 선택할 경우
			else if(!category.equals("") && !rating.equals("") && price==-1 && length==-1) {
				sql +="AND category=? AND rating=? ORDER BY fid LIMIT ?,?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+title+"%");
				stmt.setString(2, "%"+actor+"%");
				stmt.setString(3, category);
				stmt.setString(4, rating);
				stmt.setInt(5,  beginRow);
				stmt.setInt(6,  rowPerPage);
			}			
			// category, price만 선택할 경우
			else if(!category.equals("") && rating.equals("") && price!=-1 && length==-1) {
				sql +="AND category=? AND price=? ORDER BY fid LIMIT ?,?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+title+"%");
				stmt.setString(2, "%"+actor+"%");
				stmt.setString(3, category);
				stmt.setDouble(4, price);
				stmt.setInt(5,  beginRow);
				stmt.setInt(6,  rowPerPage);
			}			
			// category, length만 선택할 경우
			else if(!category.equals("") && rating.equals("") && price==-1 && length!=-1) {
				if(length == 0) {
					sql += "AND length<60 AND category=? ORDER BY fid LIMIT ?,?";
				} else if(length == 1) {
					sql += " AND length>=60 AND category=? ORDER BY fid LIMIT ?, ?";	
				}	
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+title+"%");
				stmt.setString(2, "%"+actor+"%");
				stmt.setString(3, category);
				stmt.setInt(4,  beginRow);
				stmt.setInt(5,  rowPerPage);
			}			
			// rating, price만 선택할 경우
			else if(category.equals("") && !rating.equals("") && price!=-1 && length==-1) {
				sql +="AND rating=? AND price=? ORDER BY fid LIMIT ?,?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+title+"%");
				stmt.setString(2, "%"+actor+"%");
				stmt.setString(3, rating);
				stmt.setDouble(4, price);
				stmt.setInt(5,  beginRow);
				stmt.setInt(6,  rowPerPage);
			}			
			// rating, length만 선택할 경우
			else if(category.equals("") && !rating.equals("") && price==-1 && length!=-1) {
				if(length == 0) {
					sql += "AND length<60 AND rating=? ORDER BY fid LIMIT ?,?";
				} else if(length == 1) {
					sql += " AND length>=60 AND rating=? ORDER BY fid LIMIT ?, ?";	
				}	
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+title+"%");
				stmt.setString(2, "%"+actor+"%");
				stmt.setString(3, rating);
				stmt.setInt(4,  beginRow);
				stmt.setInt(5,  rowPerPage);
			}			
			// price, length만 선택할 경우
			else if(category.equals("") && rating.equals("") && price!=-1 && length!=-1) {
				if(length == 0) {
					sql += "AND length<60 AND price=? ORDER BY fid LIMIT ?,?";
				} else if(length == 1) {
					sql += " AND length>=60 AND price=? ORDER BY fid LIMIT ?, ?";	
				}	
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+title+"%");
				stmt.setString(2, "%"+actor+"%");
				stmt.setDouble(3, price);
				stmt.setInt(4,  beginRow);
				stmt.setInt(5,  rowPerPage);
			}			
			// category, rating, price만 선택할 경우
			else if(!category.equals("") && !rating.equals("") && price!=-1 && length==-1) {
				sql +="AND category=? AND rating=? AND =? AND price=? ORDER BY fid LIMIT ?,?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+title+"%");
				stmt.setString(2, "%"+actor+"%");
				stmt.setString(3, category);
				stmt.setString(4, rating);				
				stmt.setDouble(5, price);
				stmt.setInt(6,  beginRow);
				stmt.setInt(7,  rowPerPage);
			}			
			// category, rating, length만 선택할 경우
			else if(!category.equals("") && !rating.equals("") && price==-1 && length!=-1) {
				if(length == 0) {
					sql += "AND length<60 AND category=? AND rating=? ORDER BY fid LIMIT ?,?";
				} else if(length == 1) {
					sql += " AND length>=60 AND category=? AND rating=? ORDER BY fid LIMIT ?, ?";	
				}	
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+title+"%");
				stmt.setString(2, "%"+actor+"%");
				stmt.setString(3, category);
				stmt.setString(4, rating);				
				stmt.setInt(5,  beginRow);
				stmt.setInt(6,  rowPerPage);
			}			
			// rating, price, length만 선택할 경우
			else if(category.equals("") && !rating.equals("") && price!=-1 && length!=-1) {
				if(length == 0) {
					sql += "AND length<60 AND rating=? AND price=? ORDER BY fid LIMIT ?,?";
				} else if(length == 1) {
					sql += " AND length>=60 AND rating=? AND price=? ORDER BY fid LIMIT ?, ?";	
				}	
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+title+"%");
				stmt.setString(2, "%"+actor+"%");
				stmt.setString(3, rating);
				stmt.setDouble(4, price);				
				stmt.setInt(5,  beginRow);
				stmt.setInt(6,  rowPerPage);
			}			
			// price, length, category만 선택할 경우
			else if(!category.equals("") && rating.equals("") && price!=-1 && length!=-1) {
				if(length == 0) { // 분기에 분기를 함(0과 1로 더 쪼갬)
					sql += "AND length<60 AND category=? AND price=? ORDER BY fid LIMIT ?,?";
				} else if(length == 1) {
					sql += " AND length>=60 AND category=? AND price=? ORDER BY fid LIMIT ?, ?";	
				}	
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+title+"%");
				stmt.setString(2, "%"+actor+"%");
				stmt.setString(3, category);
				stmt.setDouble(4, price);				
				stmt.setInt(5,  beginRow);
				stmt.setInt(6,  rowPerPage);
			}			
			// category, rating, price, length만 선택할 경우
			else if(!category.equals("") && !rating.equals("") && price!=-1 && length!=-1) {
				if(length == 0) { // 분기에 분기를 함(0과 1로 더 쪼갬)
					sql += "AND length<60 AND category=? AND rating=? AND price=? ORDER BY fid LIMIT ?,?";
				} else if(length == 1) {
					sql += " AND length>=60 AND category=? AND rating=? AND price=? ORDER BY fid LIMIT ?, ?";	
				}	
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+title+"%");
				stmt.setString(2, "%"+actor+"%");
				stmt.setString(3, category);
				stmt.setString(4, rating);		
				stmt.setDouble(5, price);	
				stmt.setInt(6,  beginRow);
				stmt.setInt(7,  rowPerPage);
			}			
		
			// 13(+알파)개의 쿼리 분기 추가
			rs = stmt.executeQuery();
			while(rs.next()) {
					FilmList f = new FilmList();
					f.setFid(rs.getInt("fid"));
					f.setTitle(rs.getString("title"));
					f.setDesciption(rs.getString("description"));
					f.setCategory(rs.getString("category"));
					f.setPrice(rs.getDouble("price"));
					f.setLength(rs.getInt("length"));
					f.setRating(rs.getString("rating"));
					f.setActors(rs.getString("actors"));
					list.add(f);
					}
			} catch(SQLException e) {
					e.printStackTrace();
			}
			return list;		
		}
		
// 영화의 가격을 가져오는 메서드-----------------------------------------------
		public List<Double> selectfilmPriceList() {
				List<Double> list = new ArrayList<Double>();
				Connection conn = null;
				PreparedStatement stmt = null;
				ResultSet rs = null;
				conn = DBUtil.getConnection();	
				String sql = "SELECT DISTINCT price FROM film_list ORDER BY price";
				try {
						stmt = conn.prepareStatement(sql);
						rs = stmt.executeQuery();
						while(rs.next()) { // rs의 다음 결과값이 없을때까지 실행
								list.add(rs.getDouble("price"));
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
		
		
	//-----------------------------------------------------------------
			// Film Search 페이징
			// filmSearchAction.jsp에 last페이지 totalCount
		  public int selectFilmListTotalRow(String category, String rating, double price, int length, String title, String actor) {  
		      int totalCount = 0; // 결과값을 반환해줄 변수 선언 후 초기화
		      
		      // DB도 초기화
		      Connection conn = null;
		      PreparedStatement stmt = null;
		      ResultSet rs = null;
		      // DB 호출   
		      conn = DBUtil.getConnection();
		      String sql ="select count(*) cnt FROM film_list";
		      try {
		    	  if(category.equals("") && rating.equals("") && price==-1 && length==-1) { 
						sql += "ORDER BY fid LIMIT ?, ?";
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+title+"%");
						stmt.setString(2, "%"+actor+"%");	
					}
					// category만 선택할 경우
					else if(!category.equals("") && rating.equals("") && price==-1 && length==-1) {
						sql += "AND category=? ORDER BY fid LIMIT ?,?";
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+title+"%");
						stmt.setString(2, "%"+actor+"%");
						stmt.setString(3, category);	
					}
					// rating만 선택할 경우
					else if(category.equals("") && !rating.equals("") && price==-1 && length==-1) {
						sql +="AND rating=? ORDER BY fid LIMIT ?,?";
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+title+"%");
						stmt.setString(2, "%"+actor+"%");
						stmt.setString(3, rating);
					}
					// price만 선택할 경우
					else if(category.equals("") && rating.equals("") && price!=-1 && length==-1) {
						sql +="AND price=? ORDER BY fid LIMIT ?,?";
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+title+"%");
						stmt.setString(2, "%"+actor+"%");
						stmt.setDouble(3, price);
					}				
					// length만 선택할 경우
					else if(category.equals("") && rating.equals("") && price==-1 && length!=-1) { 
						if(length == 0) {
							sql += "AND length<60 ORDER BY fid LIMIT ?,?";
						} else if(length == 1) {
							sql += " AND length>=60 ORDER BY fid LIMIT ?, ?";	
						}	
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+title+"%");
						stmt.setString(2, "%"+actor+"%");
					}
					// category, rating만 선택할 경우
					else if(!category.equals("") && !rating.equals("") && price==-1 && length==-1) {
						sql +="AND category=? AND rating=? ORDER BY fid LIMIT ?,?";
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+title+"%");
						stmt.setString(2, "%"+actor+"%");
						stmt.setString(3, category);
						stmt.setString(4, rating);
					}			
					// category, price만 선택할 경우
					else if(!category.equals("") && rating.equals("") && price!=-1 && length==-1) {
						sql +="AND category=? AND price=? ORDER BY fid LIMIT ?,?";
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+title+"%");
						stmt.setString(2, "%"+actor+"%");
						stmt.setString(3, category);
						stmt.setDouble(4, price);
					}			
					// category, length만 선택할 경우
					else if(!category.equals("") && rating.equals("") && price==-1 && length!=-1) {
						if(length == 0) {
							sql += "AND length<60 AND category=? ORDER BY fid LIMIT ?,?";
						} else if(length == 1) {
							sql += " AND length>=60 AND category=? ORDER BY fid LIMIT ?, ?";	
						}	
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+title+"%");
						stmt.setString(2, "%"+actor+"%");
						stmt.setString(3, category);
					}			
					// rating, price만 선택할 경우
					else if(category.equals("") && !rating.equals("") && price!=-1 && length==-1) {
						sql +="AND rating=? AND price=? ORDER BY fid LIMIT ?,?";
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+title+"%");
						stmt.setString(2, "%"+actor+"%");
						stmt.setString(3, rating);
						stmt.setDouble(4, price););
					}			
					// rating, length만 선택할 경우
					else if(category.equals("") && !rating.equals("") && price==-1 && length!=-1) {
						if(length == 0) {
							sql += "AND length<60 AND rating=? ORDER BY fid LIMIT ?,?";
						} else if(length == 1) {
							sql += " AND length>=60 AND rating=? ORDER BY fid LIMIT ?, ?";	
						}	
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+title+"%");
						stmt.setString(2, "%"+actor+"%");
						stmt.setString(3, rating);
					}			
					// price, length만 선택할 경우
					else if(category.equals("") && rating.equals("") && price!=-1 && length!=-1) {
						if(length == 0) {
							sql += "AND length<60 AND price=? ORDER BY fid LIMIT ?,?";
						} else if(length == 1) {
							sql += " AND length>=60 AND price=? ORDER BY fid LIMIT ?, ?";	
						}	
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+title+"%");
						stmt.setString(2, "%"+actor+"%");
						stmt.setDouble(3, price);
					}			
					// category, rating, price만 선택할 경우
					else if(!category.equals("") && !rating.equals("") && price!=-1 && length==-1) {
						sql +="AND category=? AND rating=? AND =? AND price=? ORDER BY fid LIMIT ?,?";
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+title+"%");
						stmt.setString(2, "%"+actor+"%");
						stmt.setString(3, category);
						stmt.setString(4, rating);				
						stmt.setDouble(5, price);
					}			
					// category, rating, length만 선택할 경우
					else if(!category.equals("") && !rating.equals("") && price==-1 && length!=-1) {
						if(length == 0) {
							sql += "AND length<60 AND category=? AND rating=? ORDER BY fid LIMIT ?,?";
						} else if(length == 1) {
							sql += " AND length>=60 AND category=? AND rating=? ORDER BY fid LIMIT ?, ?";	
						}	
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+title+"%");
						stmt.setString(2, "%"+actor+"%");
						stmt.setString(3, category);
						stmt.setString(4, rating);				
					}			
					// rating, price, length만 선택할 경우
					else if(category.equals("") && !rating.equals("") && price!=-1 && length!=-1) {
						if(length == 0) {
							sql += "AND length<60 AND rating=? AND price=? ORDER BY fid LIMIT ?,?";
						} else if(length == 1) {
							sql += " AND length>=60 AND rating=? AND price=? ORDER BY fid LIMIT ?, ?";	
						}	
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+title+"%");
						stmt.setString(2, "%"+actor+"%");
						stmt.setString(3, rating);
						stmt.setDouble(4, price);				
					}			
					// price, length, category만 선택할 경우
					else if(!category.equals("") && rating.equals("") && price!=-1 && length!=-1) {
						if(length == 0) { // 분기에 분기를 함(0과 1로 더 쪼갬)
							sql += "AND length<60 AND category=? AND price=? ORDER BY fid LIMIT ?,?";
						} else if(length == 1) {
							sql += " AND length>=60 AND category=? AND price=? ORDER BY fid LIMIT ?, ?";	
						}	
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+title+"%");
						stmt.setString(2, "%"+actor+"%");
						stmt.setString(3, category);
						stmt.setDouble(4, price);				
					}			
					// category, rating, price, length만 선택할 경우
					else if(!category.equals("") && !rating.equals("") && price!=-1 && length!=-1) {
						if(length == 0) { // 분기에 분기를 함(0과 1로 더 쪼갬)
							sql += "AND length<60 AND category=? AND rating=? AND price=? ORDER BY fid LIMIT ?,?";
						} else if(length == 1) {
							sql += " AND length>=60 AND category=? AND rating=? AND price=? ORDER BY fid LIMIT ?, ?";	
						}	
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+title+"%");
						stmt.setString(2, "%"+actor+"%");
						stmt.setString(3, category);
						stmt.setString(4, rating);		
						stmt.setDouble(5, price);	
					}		
					
		          rs =stmt.executeQuery();
		          if(rs.next()) {
		             totalCount = rs.getInt("cnt");
		          } 
		         } catch (SQLException e) {
		            e.printStackTrace();
		         }finally {
		            try {
		               rs.close();
		               stmt.close();
		               conn.close();
		            } catch(Exception e) {
		               e.printStackTrace();
		            }
		         }
		      return totalCount; // 반환값  
		   }
	
// 프로시저-----------------------------------------------------------------
		// film_in_Stock 프로시저 호출 메서드 (film이 store에 있는지 확인)
		public Map<String, Object> filmInStock(int filmId, int storeId) {
			Map<String, Object> map = new HashMap<String, Object>();
			
			Connection conn = null;
			// PreparedStatement : 쿼리를 실행
			// CallableStatement : 프로시저를 실행, 결과물을 저장
			CallableStatement stmt = null;
			ResultSet rs = null;
			
			// select inventory_id .... 
			List<Integer> list = new ArrayList<>();
			// select count(inventroy_id) ....
			Integer count = 0; // 결과물을 받을 변수(@x) -> film_in_Stock의 결과물
			
			// DBUtil 호출
			conn = DBUtil.getConnection();
			try {
				stmt = conn.prepareCall("{call film_in_stock(?, ?, ?)}");
				stmt.setInt(1, filmId);
				stmt.setInt(2, storeId);
				stmt.registerOutParameter(3, Types.INTEGER); // 결과값을 받을 변수는 registerOutPJarameter()사용 3번째 변수형
				rs = stmt.executeQuery();
				while(rs.next()) {
					list.add(rs.getInt(1)); // rs.getInt("inventory_id")
				}
				count = stmt.getInt(3); // 프로시저 3번째 out변수 값 -> 3번째 물음표의 값이다.
			} catch (SQLException e) {
				e.printStackTrace();
			}
			map.put("list", list);
			map.put("count", count);
			return map;
		}
		
// 단위 테스트------------------------------------------------------------------------
		public static void main(String[] args) {
			FilmDao fd = new FilmDao();
			int filmId = 7;
			int storeId = 2;
			Map<String, Object> map = fd.filmInStock(filmId, storeId);
			List<Integer> list = (List<Integer>)map.get("list");
			int count = (Integer)map.get("count");
	
			System.out.println(filmId + "번 영화는 "+ storeId +"번 가게에 "+count+"개 남음");
			for(int id : list) {
				System.out.println(id);
			}
		}
	} 