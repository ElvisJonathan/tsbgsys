package com.tsbg.ecosys.service.base;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import com.tsbg.ecosys.model.UserInfo;
import com.tsbg.ecosys.model.tokenBlacklist;
import com.tsbg.ecosys.service.tokenBlacklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/***
 * token 下发
 */
@Service("TokenService")
public class TokenService {

	@Autowired
	private tokenBlacklistService tokenBlackService;

	public String getToken(UserInfo userInfo) {
		Date start = new Date();
		long currentTime = System.currentTimeMillis() + 60* 60 * 1000;//一小时有效时间
		Date end = new Date(currentTime);
		String token = "";
		
		token = JWT.create().withAudience(userInfo.getUserId().toString()).withIssuedAt(start).withExpiresAt(end)
				.sign(Algorithm.HMAC256(userInfo.getUserPwd()));
		//在数据库插入token
		tokenBlacklist tokenBlacklist = new tokenBlacklist();
		tokenBlacklist.setTokenCode(token);
		tokenBlackService.insertSelective(tokenBlacklist);
		return token;
	}
}
