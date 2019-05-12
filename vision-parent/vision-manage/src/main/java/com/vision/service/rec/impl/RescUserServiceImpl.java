package com.vision.service.rec.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vision.mapper.rec.ActivityPushMapper;
import com.vision.mapper.rec.RecUserMapper;
import com.vision.pojo.rec.RecActivityPush;
import com.vision.pojo.rec.RecPayUser;
import com.vision.pojo.sys.Users;
import com.vision.service.rec.RecUserService;

@Service
public class RescUserServiceImpl implements RecUserService{
	@Autowired
	private RecUserMapper recUserMapper;
	@Autowired
	private ActivityPushMapper activityPushMapper;

	/*
	 * @Override public List<RecPayUser> findObjectByUserIdAndParentId(Users user) {
	 * Integer role = user.getRole(); Integer parentId = user.getParentId();
	 * 
	 * try { if(role==8) { //角色为超级管理员 return recUserMapper.selectList(null); }else
	 * if(role==9){ QueryWrapper<RecPayUser> queryWrapper = new QueryWrapper<>();
	 * queryWrapper.eq("user_id", user.getId()); return
	 * recUserMapper.selectList(queryWrapper); }else if(role == 10) {
	 * List<RecPayUser> list = new ArrayList<RecPayUser>(); //查询子门店数据
	 * QueryWrapper<RecPayUser> queryWrapper = new QueryWrapper<>();
	 * queryWrapper.eq("parent_id", user.getParentId()); list =
	 * recUserMapper.selectList(queryWrapper); //查询父门店数据 QueryWrapper<RecPayUser>
	 * queryWrapper1 = new QueryWrapper<>(); queryWrapper1.eq("user_id",
	 * user.getId()); List<RecPayUser> list2 =
	 * recUserMapper.selectList(queryWrapper1);
	 * 
	 * for (RecPayUser recPayUser : list2) { list.add(recPayUser); } return list;
	 * 
	 * }else if(role == 11) { List<RecPayUser> list = new ArrayList<RecPayUser>();
	 * //查询子门店数据 QueryWrapper<RecPayUser> queryWrapper = new QueryWrapper<>();
	 * queryWrapper.eq("parent_id", user.getParentId()); list =
	 * recUserMapper.selectList(queryWrapper); //查询父门店数据 QueryWrapper<RecPayUser>
	 * queryWrapper1 = new QueryWrapper<>(); queryWrapper1.eq("user_id",
	 * user.getParentId()); List<RecPayUser> list2 =
	 * recUserMapper.selectList(queryWrapper1);
	 * 
	 * for (RecPayUser recPayUser : list2) { list.add(recPayUser); } return list; }
	 * } catch (Exception e) {
	 * 
	 * } return null;
	 * 
	 * }
	 */
	@Override public List<RecPayUser> findObjectByUserIdAndParentId(Users user) {
		Integer role = user.getRole();
		Integer parentId = user.getParentId();
		try {
			if(role==8) { //超级管理员登录
				List<RecPayUser> list1 = recUserMapper.selectList(null);
				return TitledBorder(list1);
			}else if(role==9) { //普通门店登录
				QueryWrapper<RecPayUser> queryWrapper = new QueryWrapper<>();
				queryWrapper.eq("user_id",user.getId());
				 List<RecPayUser> list1 = recUserMapper.selectList(queryWrapper);
				 return TitledBorder(list1);
			}else if(role==10) { //连锁门店登录
				QueryWrapper<RecPayUser> queryWrapper = new QueryWrapper<>();
				queryWrapper.eq("user_id",user.getId());//连锁门店查看自己门店的记录
			    List<RecPayUser> list1 = recUserMapper.selectList(queryWrapper);
			    QueryWrapper<RecPayUser> queryWrapper1 = new QueryWrapper<>();
			    queryWrapper1.eq("parent_id",user.getId());//连锁门店查看自己子门店的记录
			    List<RecPayUser> list2 = recUserMapper.selectList(queryWrapper1);
			    for(RecPayUser r:list2) {
			    	list1.add(r);
			    }
			    return TitledBorder(list1);
			}else if(role==11) {  //连锁门店下普通门店登录
				QueryWrapper<RecPayUser> queryWrapper = new QueryWrapper<>();
				queryWrapper.eq("user_id",user.getId());//查看自己门店的记录
			    List<RecPayUser> list1 = recUserMapper.selectList(queryWrapper);
			    
			    QueryWrapper<RecPayUser> queryWrapper1 = new QueryWrapper<>();
				queryWrapper.eq("user_id",user.getParentId());//查看自己父级门店的记录
			    List<RecPayUser> list2 = recUserMapper.selectList(queryWrapper);
			    
			    for(RecPayUser r:list2) {
			    	list1.add(r);
			    }	    
			    return TitledBorder(list1);
			    
			}
		} catch (Exception e) {
			
		}
		return null;
	}

	@Override
	public RecActivityPush doFindActivityObjectByUserPayType(Integer id) {		
		return activityPushMapper.selectById(id);	
	}
	
	public List<RecPayUser> TitledBorder(List<RecPayUser> list) {
		for (RecPayUser recPayUser : list) {
	    	int id = recPayUser.getRechargeType();
	    	RecActivityPush selectById = activityPushMapper.selectById(id);
	    	recPayUser.setTitle(selectById.getTitle());
		}
		return list;
	}

	@Override
	public String insertObjectRecUser(RecPayUser recPayUser) {
		String name = recPayUser.getName();
		//查询上次充值时间
		RecPayUser lastPayTime = recUserMapper.findLastPayTime(name);
		System.out.println("上次充值时间为："+lastPayTime);
		if(lastPayTime!=null)
		recPayUser.setLastPayTime(lastPayTime.getGmtModified());
		
		recPayUser.setGmtCreate(new Date());
		recPayUser.setGmtModified(recPayUser.getGmtCreate());
		try {
			recUserMapper.insert(recPayUser);
			return "新增记录成功";
		} catch (Exception e) {
			System.out.println("新增失败");
		}
	
		return null;
	}
}



























