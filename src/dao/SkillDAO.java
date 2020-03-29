package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Skill;
import utility.ConnectionManager;
public class SkillDAO{
	static List<Skill> skillList = new ArrayList<Skill>();
	final String insert_skills = "insert into skill values (?,?)";
	final String select_skills = "select * from skill order by name";
	
	public void insertSkills(Skill skill) throws SQLException, Exception {
		PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(insert_skills);
		ps.setLong(1, skill.getSkillId());
		ps.setString(2, skill.getSkillName());
		ps.executeUpdate();
		
	}
	
	public List<Skill> listAllSkills () throws SQLException, Exception {
		
		Skill skill;
		
		PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(select_skills);
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
		skill = new Skill();
		//	System.out.print(rs.getLong("id")+"\t");
		//	System.out.println(rs.getString("name"));
			Long id= rs.getLong(1);
			
			String name= rs.getString(2);		
			
			skill.setSkillId(id);
			skill.setSkillName(name);
			skillList.add(skill);
		}
		
		return skillList;
		
	}
}