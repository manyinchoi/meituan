package cn.itcast.shop.service.impl;

import cn.itcast.core.factory.BeanFactory;
import cn.itcast.shop.dao.ShopAccountDao;
import cn.itcast.shop.entity.ShopAccount;
import cn.itcast.shop.service.ShopAccountService;

public class ShopAccountServiceImpl implements ShopAccountService {
	//产生一个dao对象
	private ShopAccountDao shopAccountDaoImpl=BeanFactory.getInstance("shopAccountDaoImpl", ShopAccountDao.class);

	@Override
	public ShopAccount checkLogin(String username,String password) {
		return shopAccountDaoImpl.checkLogin(username,password);
	}

	@Override
	public void register(ShopAccount shopAccount) {
		shopAccountDaoImpl.register(shopAccount);
	}

	@Override
	public ShopAccount getShopAccMsg(int id) {
		return shopAccountDaoImpl.getShopAccMsg(id);
	}

	@Override
	public void updateShopAcc(ShopAccount shopAccount) {
		shopAccountDaoImpl.updateShopAcc(shopAccount);
	}

}
