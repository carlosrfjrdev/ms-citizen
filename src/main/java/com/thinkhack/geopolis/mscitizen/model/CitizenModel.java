package com.thinkhack.geopolis.mscitizen.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.thinkhack.geopolis.mscitizen.service.LevelService;

@Entity
@Table(name="citizens")
public class CitizenModel implements Serializable {

	private static final long serialVersionUID = -9119103563608272947L;
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Type(type="org.hibernate.type.UUIDCharType")
	private UUID id;
	
	@Column(nullable = false, unique = true, length = 30)
	private String citizenName;
	
	@Column(nullable = false, unique = true)
	private String citizenMail;
	
	private String citizenPhone;
	
	@Column(length = 1000)
	private String description;
	
	@Transient
	private int level;
	@Transient
	private int percXP;
	
	//Relations
	@Type(type="org.hibernate.type.UUIDCharType")
	private UUID partyID;
	
	//ToImplements
//	private UUID stateHomeID;
//	private List<UUID> stateVisaIDS;
//	private UUID jobPoliticalID;
//	private UUID jobIndustryID;
//	private UUID industryID;
	
	
	//Experiences
	private long experience;
	
	private long jobExperience;
	private long warExperience;
	private long politicsExperience;
	
	//skills
	private int powerSkill;
	private int knowledgeSkill;
	
	
	//PowerUps
	private int energy;
	private boolean vip;
	private boolean moderator;
	private boolean ban;
	
	//Wallets
	private UUID moneyWallet;
	private UUID goldWallet;
	
	//Datas
	private LocalDateTime created;
	private LocalDateTime updated;
	
	private boolean active =true;
	
	
	//Genetared Getters
	public int getLevel() {
		LevelService ls = new LevelService();
		return ls.getLevelByXp(experience);
	}	
	public void setLevel(int level) {
		this.level = level;
	}
	public int getPercXP() {
		LevelService ls = new LevelService();
		int level = ls.getLevelByXp(experience);
		
		int fullLevel = ls.getMaxXPForNextLevel(level);
		
		float perc = (float) ((experience*100)/fullLevel);
		
		return Math.round(perc);
	}
	public void setPercXP(int percXP) {
		this.percXP = percXP;
	}

	
	//Common Getters and Setters
	public UUID getId() {
		return id;
	}


	public void setId(UUID id) {
		this.id = id;
	}


	


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public long getExperience() {
		return experience;
	}


	public void setExperience(long experience) {
		this.experience = experience;
	}





	public long getJobExperience() {
		return jobExperience;
	}


	public void setJobExperience(long jobExperience) {
		this.jobExperience = jobExperience;
	}


	public long getWarExperience() {
		return warExperience;
	}


	public void setWarExperience(long warExperience) {
		this.warExperience = warExperience;
	}


	public long getPoliticsExperience() {
		return politicsExperience;
	}


	public void setPoliticsExperience(long politicsExperience) {
		this.politicsExperience = politicsExperience;
	}


	public int getPowerSkill() {
		return powerSkill;
	}


	public void setPowerSkill(int powerSkill) {
		this.powerSkill = powerSkill;
	}


	public int getKnowledgeSkill() {
		return knowledgeSkill;
	}


	public void setKnowledgeSkill(int knowledgeSkill) {
		this.knowledgeSkill = knowledgeSkill;
	}


	public int getEnergy() {
		return energy;
	}


	public void setEnergy(int energy) {
		this.energy = energy;
	}


	public boolean isVip() {
		return vip;
	}


	public void setVip(boolean vip) {
		this.vip = vip;
	}

	public LocalDateTime getCreated() {
		return created;
	}


	public void setCreated(LocalDateTime created) {
		this.created = created;
	}


	public LocalDateTime getUpdated() {
		return updated;
	}


	public void setUpdated(LocalDateTime updated) {
		this.updated = updated;
	}
	
	public boolean isBan() {
		return ban;
	}
	public void setBan(boolean ban) {
		this.ban = ban;
	}
	public boolean isModerator() {
		return moderator;
	}
	public void setModerator(boolean moderator) {
		this.moderator = moderator;
	}
	public UUID getPartyID() {
		return partyID;
	}
	public void setPartyID(UUID partyID) {
		this.partyID = partyID;
	}

	public String getCitizenName() {
		return citizenName;
	}
	public void setCitizenName(String citizenName) {
		this.citizenName = citizenName;
	}
	public UUID getMoneyWallet() {
		return moneyWallet;
	}
	public void setMoneyWallet(UUID moneyWallet) {
		this.moneyWallet = moneyWallet;
	}
	public UUID getGoldWallet() {
		return goldWallet;
	}
	public void setGoldWallet(UUID goldWallet) {
		this.goldWallet = goldWallet;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getCitizenMail() {
		return citizenMail;
	}
	public void setCitizenMail(String citizenMail) {
		this.citizenMail = citizenMail;
	}
	public String getCitizenPhone() {
		return citizenPhone;
	}
	public void setCitizenPhone(String citizenPhone) {
		this.citizenPhone = citizenPhone;
	}
	

	
	
	

}
