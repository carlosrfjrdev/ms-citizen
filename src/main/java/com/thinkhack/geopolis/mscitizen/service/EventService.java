package com.thinkhack.geopolis.mscitizen.service;

import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.thinkhack.geopolis.mscitizen.exception.NotEnoughEnergyException;
import com.thinkhack.geopolis.mscitizen.exception.NotEnoughGoldException;
import com.thinkhack.geopolis.mscitizen.exception.NotEnoughMoneyException;
import com.thinkhack.geopolis.mscitizen.exception.WrongEventException;
import com.thinkhack.geopolis.mscitizen.exception.WrongEventValueException;
import com.thinkhack.geopolis.mscitizen.model.CitizenModel;
import com.thinkhack.geopolis.mscitizen.to.EventTO;

@Service
public class EventService {
//	private final static long VIP_BONUS_EXPERIENCE = 10000L;
//	private final static int VIP_BONUS_SKILL = 10; 
	//private final static long VIP_BONUS_MONEY = 10000000L; 
	//private final static long VIP_BONUS_GOLD = 1000L; 
	public CitizenModel getCitizenUpdateByEvent(CitizenModel citizenModel, @Valid EventTO event) throws WrongEventException, WrongEventValueException, NotEnoughEnergyException, NotEnoughMoneyException, NotEnoughGoldException {

		/**
		 * Este metodo tratas os eventos de update do citizen
		 * 
		 * @ EVENT_NAME
		 * Atualiza nome do jogador
		 * 
		 * @ EVENT_DESC
		 * Atualiza descrição do jogador
		 * 
		 * @ EVENT_ADD_XP;
		 * Adiciona value ao xp
		 * 
		 * @ EVENT_ADD_JOBXP;
		 * Adiciona value no jobxp
		 * 
		 * @ EVENT_ADD_POLITCSXP
		 * Adiciona value no politcs
		 * 
		 * @ EVENT_ADD_WARXP
		 * Adiciona value no warxp
		 * 
		 * @ EVENT_ADD_SKILL_POWER
		 * Adiciona 1 no PowerSkill
		 * 
		 * @ EVENT_ADD_SKILL_KNOWLEDGE
		 * Adiciona 1 no KnowledgeSkill
		 * 
		 * @ EVENT_FULL_ENERGY
		 * Adiciona 500 a energia
		 * Adiciona 1000 a energia se vip
		 * 
		 * @ EVENT_MAKE_ENERGY
		 * Adiciona value a energia
		 * 
		 * @ EVENT_SPEND_ENERGY
		 * Subtrai value da energia
		 * 
		 * @ EVENT_MAKE_MONEY
		 * Adciona value ao money
		 * 
		 * @ EVENT_SPEND_MONEY
		 * Subtrai value do gold
		 * 
		 * @ EVENT_MAKE_GOLD
		 * Adciona value ao gold
		 *  
		 * @ EVENT_SPEND_GOLD;
		 * Subtrai value do gold
		 * 
		 * @ EVENT_VIP
		 * Seta Parametro VIP ao Citizen
		 * 
		 * @ EVENT_UNVIP
		 * Retira Parametro VIP do Citizen
		 * 
		 * @ EVENT_BAN
		 * Seta Parametro BAN do Citizen
		 * 
		 * @ EVENT_UNBAN
		 * Retira Parametro BAN do Citizen
		 * 
		 * @ EVENT_MOD
		 * Seta Parametro MODERADOR ao Citizen
		 * 
		 * @ EVENT_UNMOD
		 * Retira Parametro MODERADOR do Citizen
		 * 
		 */
		switch (event.getEvent()) {
		case "EVENT_NAME":

			citizenModel.setCitizenName(event.getValue().length()>29 ? event.getValue().substring(0,29) : event.getValue());
			citizenModel.setUpdated(LocalDateTime.now(ZoneId.of("UTC")));
			return citizenModel;
		case "EVENT_DESC":

			citizenModel.setDescription(event.getValue().length()>999 ? event.getValue().substring(0,999) : event.getValue());
			citizenModel.setUpdated(LocalDateTime.now(ZoneId.of("UTC")));
			return citizenModel;
		case "EVENT_ADD_XP":
			long old = citizenModel.getExperience();
			long add;
			try {
				if (Long.parseLong(event.getValue())<1) {
					throw new WrongEventValueException();
				}
				add = Long.parseLong(event.getValue());
				citizenModel.setExperience(old+add);
				citizenModel.setUpdated(LocalDateTime.now(ZoneId.of("UTC")));
				return citizenModel;
			} catch (Exception e) {
				throw new WrongEventValueException();
			}
		case "EVENT_ADD_JOBXP":
			long oldJobXP = citizenModel.getJobExperience();
			long addJobXP;
			try {
				if (Long.parseLong(event.getValue())<1) {
					throw new WrongEventValueException();
				}
				addJobXP = Long.parseLong(event.getValue());
				citizenModel.setJobExperience(oldJobXP+addJobXP);
				citizenModel.setUpdated(LocalDateTime.now(ZoneId.of("UTC")));
				return citizenModel;
			} catch (Exception e) {
				throw new WrongEventValueException();
			}
		case "EVENT_ADD_POLITCSXP":
			long oldPolXP = citizenModel.getJobExperience();
			long addPolXP;
			try {
				if (Long.parseLong(event.getValue())<1) {
					throw new WrongEventValueException();
				}
				addPolXP = Long.parseLong(event.getValue());
				citizenModel.setJobExperience(oldPolXP+addPolXP);
				citizenModel.setUpdated(LocalDateTime.now(ZoneId.of("UTC")));
				return citizenModel;
			} catch (Exception e) {
				throw new WrongEventValueException();
			}
		case "EVENT_ADD_WARXP":
			long oldWarXP = citizenModel.getWarExperience();
			long addWarXP;
			try {
				if (Long.parseLong(event.getValue())<1) {
					throw new WrongEventValueException();
				}
				addWarXP = Long.parseLong(event.getValue());
				citizenModel.setWarExperience(oldWarXP+addWarXP);
				citizenModel.setUpdated(LocalDateTime.now(ZoneId.of("UTC")));
				return citizenModel;
			} catch (Exception e) {
				throw new WrongEventValueException();
			}

		case "EVENT_ADD_SKILL_POWER":
			int powerSkill = citizenModel.getPowerSkill();

			citizenModel.setPowerSkill(powerSkill+1);
			citizenModel.setUpdated(LocalDateTime.now(ZoneId.of("UTC")));
			return citizenModel;
		case "EVENT_ADD_SKILL_KNOWLEDGE":
			int knowledgeSkill = citizenModel.getKnowledgeSkill();

			citizenModel.setKnowledgeSkill(knowledgeSkill+1);
			citizenModel.setUpdated(LocalDateTime.now(ZoneId.of("UTC")));
			return citizenModel;

		case "EVENT_FULL_ENERGY":

			if (citizenModel.isVip()) {
				citizenModel.setEnergy(1000);
			} else {
				citizenModel.setEnergy(500);
			}

			citizenModel.setUpdated(LocalDateTime.now(ZoneId.of("UTC")));
			return citizenModel;

		case "EVENT_MAKE_ENERGY":
			if (citizenModel.isVip()) {

				int actualEnergy = citizenModel.getEnergy();
				int addEnergy;
				try {
					if (Integer.parseInt(event.getValue())<1) {
						throw new WrongEventValueException();
					}
					addEnergy = Integer.parseInt(event.getValue());
					int newEnergy = actualEnergy+addEnergy;
					if (newEnergy>1000) {
						citizenModel.setEnergy(1000);
					} else {
						citizenModel.setEnergy(newEnergy);
					}

					citizenModel.setUpdated(LocalDateTime.now(ZoneId.of("UTC")));
					return citizenModel;
				} catch (Exception e) {
					throw new WrongEventValueException();
				}

			} else {
				int actualEnergy = citizenModel.getEnergy();
				int addEnergy;
				try {
					if (Integer.parseInt(event.getValue())<1) {
						throw new WrongEventValueException();
					}
					addEnergy = Integer.parseInt(event.getValue());
					int newEnergy = actualEnergy+addEnergy;
					if (newEnergy>500) {
						citizenModel.setEnergy(500);
					} else {
						citizenModel.setEnergy(newEnergy);
					}

					citizenModel.setUpdated(LocalDateTime.now(ZoneId.of("UTC")));
					return citizenModel;
				} catch (Exception e) {
					throw new WrongEventValueException();
				}
			}

		case "EVENT_SPEND_ENERGY":
			int actualEnergy = citizenModel.getEnergy();
			int spendEnergy;
			try {
				if (Integer.parseInt(event.getValue())<1) {
					throw new WrongEventValueException();
				}
				spendEnergy = Integer.parseInt(event.getValue());
				int newEnergy = actualEnergy-spendEnergy;
				if (newEnergy<0) {
					throw new NotEnoughEnergyException();

				} else {
					citizenModel.setEnergy(newEnergy);
				}

				citizenModel.setUpdated(LocalDateTime.now(ZoneId.of("UTC")));
				return citizenModel;
			} catch (WrongEventValueException|NumberFormatException e) {
				throw new WrongEventValueException();
			} 
		case "EVENT_MAKE_MONEY":
			//TODO
//			long actualMoney = citizenModel.getMoneyWallet();
//			long makeMoney;
//			try {
//				if (Integer.parseInt(event.getValue())<1) {
//					throw new WrongEventValueException();
//				}
//				makeMoney = Long.parseLong(event.getValue());
//				long newMoney = actualMoney+makeMoney;
//				citizenModel.setMoneyWallet(newMoney);
//				citizenModel.setUpdated(LocalDateTime.now(ZoneId.of("UTC")));
//				return citizenModel;
//			} catch (WrongEventValueException|NumberFormatException e) {
//				throw new WrongEventValueException();
//			} 
		case "EVENT_SPEND_MONEY":
			//TODO
//			long actualMoneyToSpend = citizenModel.getMoneyWallet();
//			long spendMoney;
//			try {
//				if (Integer.parseInt(event.getValue())<1) {
//					throw new WrongEventValueException();
//				}
//				spendMoney = Long.parseLong(event.getValue());
//				long newMoney = actualMoneyToSpend-spendMoney;
//				if (newMoney<0) {
//					throw new NotEnoughMoneyException();
//
//				} else {
//					citizenModel.setMoneyWallet(newMoney);
//				}
//
//				citizenModel.setUpdated(LocalDateTime.now(ZoneId.of("UTC")));
//				return citizenModel;
//			} catch (WrongEventValueException|NumberFormatException e) {
//				throw new WrongEventValueException();
//			} 
		case "EVENT_MAKE_GOLD":
			//TODO
//			long actualGold = citizenModel.getGoldWallet();
//			long makeGold;
//			try {
//				if (Integer.parseInt(event.getValue())<1) {
//					throw new WrongEventValueException();
//				}
//				makeGold = Long.parseLong(event.getValue());
//				long newGold = actualGold+makeGold;
//				citizenModel.setGoldWallet(newGold);
//				citizenModel.setUpdated(LocalDateTime.now(ZoneId.of("UTC")));
//				return citizenModel;
//			} catch (WrongEventValueException|NumberFormatException e) {
//				throw new WrongEventValueException();
//			} 
		case "EVENT_SPEND_GOLD":
			//TODO
//			long actualGoldToSpend = citizenModel.getGoldWallet();
//			long spendGold;
//			try {
//				if (Integer.parseInt(event.getValue())<1) {
//					throw new WrongEventValueException();
//				}
//				spendGold = Long.parseLong(event.getValue());
//				long newGold = actualGoldToSpend-spendGold;
//				if (newGold<0) {
//					throw new NotEnoughGoldException();
//
//				} else {
//					citizenModel.setGoldWallet(newGold);
//				}
//
//				citizenModel.setUpdated(LocalDateTime.now(ZoneId.of("UTC")));
//				return citizenModel;
//			} catch (WrongEventValueException|NumberFormatException e) {
//				throw new WrongEventValueException();
//			} 
		case "EVENT_VIP":
			//TODO
//			long experience = citizenModel.getExperience();
//			long gold = citizenModel.getGoldWallet();
//			long money = citizenModel.getMoneyWallet();
//			int pwSkill = citizenModel.getPowerSkill();
//			int kwSkill = citizenModel.getKnowledgeSkill();
//
//
//
//			citizenModel.setExperience(experience+VIP_BONUS_EXPERIENCE);
//			citizenModel.setGoldWallet(gold+VIP_BONUS_GOLD);
//			citizenModel.setMoneyWallet(money+VIP_BONUS_MONEY);
//			citizenModel.setPowerSkill(pwSkill+VIP_BONUS_SKILL);
//			citizenModel.setKnowledgeSkill(kwSkill+VIP_BONUS_SKILL);
//			citizenModel.setVip(true);
//
//			citizenModel.setUpdated(LocalDateTime.now(ZoneId.of("UTC")));
//			return citizenModel;
		case "EVENT_UNVIP":

			citizenModel.setVip(false);
			citizenModel.setUpdated(LocalDateTime.now(ZoneId.of("UTC")));
			return citizenModel;
		case "EVENT_BAN":

			citizenModel.setBan(true);
			citizenModel.setUpdated(LocalDateTime.now(ZoneId.of("UTC")));
			return citizenModel;
		case "EVENT_UNBAN":

			citizenModel.setBan(false);
			citizenModel.setUpdated(LocalDateTime.now(ZoneId.of("UTC")));
			return citizenModel;
		case "EVENT_MOD":

			citizenModel.setModerator(true);
			citizenModel.setUpdated(LocalDateTime.now(ZoneId.of("UTC")));
			return citizenModel;
		case "EVENT_UNMOD":
			citizenModel.setModerator(false);
			citizenModel.setUpdated(LocalDateTime.now(ZoneId.of("UTC")));
			return citizenModel;

		default:
			throw new WrongEventException();
		}

	}

	public void citizenEventListener(@Valid EventTO event, CitizenModel oldParam, CitizenModel newParam) {
		// TODO Auto-generated method stub

	}

}
