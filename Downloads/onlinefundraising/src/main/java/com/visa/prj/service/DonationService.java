package com.visa.prj.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.visa.prj.dao.CampaignDao;
import com.visa.prj.dao.DonationDao;
import com.visa.prj.dao.UserDao;
import com.visa.prj.entity.Campaign;
import com.visa.prj.entity.Donation;
import com.visa.prj.entity.User;

@Service
public class DonationService {
	@Autowired
	private CampaignDao campaignDao;
	
	@Autowired
	private DonationDao donationDao;
	
	@Autowired
	private UserDao userDao;
	
	public List<Campaign> getCampaigns(){
		return campaignDao.findAll();
	}
	
	@Transactional
	public int donate(Donation d) {
		donationDao.save(d);
		double total = d.getCampaign().getDonated() + d.getAmount();
		Campaign c = d.getCampaign();
		c.setDonated(total);
		return d.getDid();
	}
	
	@Transactional
	public int saveCampaign(Campaign c) {
		campaignDao.save(c);
		return c.getId();
	}
	
	@Transactional
	public String saveUser(User u) {
		userDao.save(u);
		return u.getEmail();
	}
	
	public List<Donation> getDonations(){
		return donationDao.findAll();
	}
	
	public List<User> getUsers(){
		return userDao.findAll();
	}
}
