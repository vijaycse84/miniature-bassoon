package com.addition;

import java.sql.ResultSet;    
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;    
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;    
    
public class EmpDao {    
JdbcTemplate template;    
    
public void setTemplate(JdbcTemplate template) {    
    this.template = template;    
}    
public int save(Emp p){    
	List ld=getMaxEmpId();
	int id=(Integer)ld.get(0);
	System.out.println("************"+id);
	id++;
    String sql="insert into Emp99(id,name,salary,designation) values("+id+",'"+p.getName()+"',"+p.getSalary()+",'"+p.getDesignation()+"')";    
    return template.update(sql);    
}    
public int update(Emp p){    
    String sql="update Emp99 set name='"+p.getName()+"', salary="+p.getSalary()+",designation='"+p.getDesignation()+"' where id="+p.getId()+"";    
    return template.update(sql);    
}    
public int delete(int id){    
    String sql="delete from Emp99 where id="+id+"";    
    return template.update(sql);    
}    
public Emp getEmpById(int id){    
    String sql="select * from Emp99 where id=?";    
    return template.queryForObject(sql, new Object[]{id},new BeanPropertyRowMapper<Emp>(Emp.class));    
}  
public List getMaxEmpId() {
	
	return template.query("select max(ID) from Emp99",new ResultSetExtractor(){  
	    @Override  
	     public List extractData(ResultSet rs) throws SQLException,  
	            DataAccessException {  
	      
	        List list=new ArrayList();  
	        rs.next();
	        
	        int id=rs.getInt(1);  
	        list.add(id);
	        System.out.println(list);
	        return list;  
	        }  
	    });  
	  }  

public List<Emp> getEmployees(){    
    return template.query("select * from Emp99",new RowMapper<Emp>(){    
        public Emp mapRow(ResultSet rs, int row) throws SQLException {    
            Emp e=new Emp();    
            e.setId(rs.getInt(1));    
            e.setName(rs.getString(2));    
            e.setSalary(rs.getFloat(3));    
            e.setDesignation(rs.getString(4));    
            return e;    
        }    
    });    
}    
}   